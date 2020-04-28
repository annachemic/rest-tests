package auth;

import dto.CredentialsDto;
import dto.auth.AuthDtoResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rest.AdditionalSteps;
import services.AuthService;
import utils.RetrofitUtils;

import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static utils.CommonResponseMatchers.*;

@Slf4j
public class AuthTest {
    private String randomEmail;
    private String randomPassword;
    private String correctEmail;
    private AuthService authService;


    @BeforeMethod
    public void setup() throws IOException {
        correctEmail = AdditionalSteps.getUsersList(1)
                .stream()
                .findAny()
                .get().getEmail();
        randomEmail = randomAlphanumeric(10).concat("@gmail.com").toLowerCase();
        randomPassword = randomAlphabetic(10);
        authService = RetrofitUtils.getRetrofit()
                .create(AuthService.class);
    }

    @Test
    public void successfulLogin() throws IOException {
        retrofit2.Response<AuthDtoResponse> response =
                authService.postLogin(CredentialsDto
                        .builder()
                        .email(correctEmail)
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 200);
        checkNotEmptyBodyStep(response, AuthDtoResponse.class);
        log.info("User token: " + response.body().getToken());

    }

    @Test
    public void unsuccessfulLoginWithUnregisteredEmail() throws IOException {
        retrofit2.Response<AuthDtoResponse> response =
                authService.postLogin(CredentialsDto
                        .builder()
                        .email(randomEmail)
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"user not found\"}");
    }

    @Test
    public void unsuccessfulLoginWithoutEmail() throws IOException {
        retrofit2.Response<AuthDtoResponse> response =
                authService.postLogin(CredentialsDto
                        .builder()
                        .email(null)
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing email or username\"}");
    }

    @Test
    public void unsuccessfulLoginWithoutPassword() throws IOException {
        retrofit2.Response<AuthDtoResponse> response =
                authService.postLogin(CredentialsDto
                        .builder()
                        .email(correctEmail)
                        .password(null)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing password\"}");

    }

    @Test
    public void unsuccessfulLoginWithEmptyPassword() throws IOException {
        retrofit2.Response<AuthDtoResponse> response =
                authService.postLogin(CredentialsDto
                        .builder()
                        .email(correctEmail)
                        .password("")
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing password\"}");

    }

    @Test
    public void unsuccessfulLoginWithEmptyEmail() throws IOException {
        retrofit2.Response<AuthDtoResponse> response =
                authService.postLogin(CredentialsDto
                        .builder()
                        .email("")
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing email or username\"}");
    }
}
