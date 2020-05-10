package register;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;
import ru.annachemic.reqres.dto.CredentialsDto;
import ru.annachemic.reqres.dto.register.RegisterDtoResponse;
import ru.annachemic.reqres.rest.AdditionalSteps;
import ru.annachemic.reqres.services.RegisterService;
import ru.annachemic.reqres.utils.RetrofitUtils;

import java.io.IOException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static ru.annachemic.reqres.utils.CommonResponseMatchers.*;

@Slf4j
public class RegisterTest {
    private String randomEmail;
    private String randomPassword;
    private String correctEmail;
    private RegisterService registerService;

    @BeforeMethod
    public void setUp() throws IOException {
        correctEmail = AdditionalSteps.getUsersList(1)
                .stream()
                .findAny()
                .get().getEmail();
        randomEmail = randomAlphanumeric(10).concat("@gmail.com").toLowerCase();
        randomPassword = randomAlphabetic(10);
        registerService = RetrofitUtils.getRetrofit()
                .create(RegisterService.class);
    }

    @Test
    public void successfulRegistration() throws IOException {
        retrofit2.Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email(correctEmail)
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 200);
        checkNotEmptyBodyStep(response, RegisterDtoResponse.class);
        log.info("User id: " + response.body().getId() + ", User token: " + response.body().getToken());


    }

    @Test
    public void unsuccessfulRegistrationWithUnknownEmail() throws IOException {
        Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email(randomEmail)
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Note: Only defined users succeed registration\"}");


    }

    @Test
    public void unsuccessfulRegistrationWithIncorrectEmail() throws IOException {
        Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email(correctEmail + "@gmail.com")
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Note: Only defined users succeed registration\"}");
    }

    @Test
    public void unsuccessfulRegistrationWithEmptyEmail() throws IOException {
        Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email("")
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing email or username\"}");
    }

    @Test
    public void unsuccessfulRegistrationWithEmptyPassword() throws IOException {
        Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email(correctEmail)
                        .password("")
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing password\"}");
    }

    @Test
    public void unsuccessfulRegistrationWithoutEmail() throws IOException {
        Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email(null)
                        .password(randomPassword)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing email or username\"}");
    }

    @Test
    public void unsuccessfulRegistrationWithoutPassword() throws IOException {
        Response<RegisterDtoResponse> response =
                registerService.postRegister(CredentialsDto
                        .builder()
                        .email(correctEmail)
                        .password(null)
                        .build())
                        .execute();
        checkStatusCodeStep(response, 400);
        checkErrorBodyStep(response, "{\"error\":\"Missing password\"}");
    }


}
