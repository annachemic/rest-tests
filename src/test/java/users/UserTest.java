package users;

import dto.user.SingleUserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.UserService;
import utils.RetrofitUtils;

import java.io.IOException;

import static utils.CommonResponseMatchers.*;

@Slf4j
public class UserTest {
    private UserService userService;
    private Integer userId;
    private static final Integer USERS_AMOUNT = 12;

    @BeforeMethod
    public void setUp() throws IOException {
        userService = RetrofitUtils.getRetrofit()
                .create(UserService.class);
        userId = RandomUtils.nextInt(1, USERS_AMOUNT);
    }

    @Test
    public void getUserInfo() throws IOException {
        retrofit2.Response<SingleUserInfoResponse> response =
                userService.getUser(userId).execute();
        checkStatusCodeStep(response, 200);
        checkNotEmptyBodyStep(response, SingleUserInfoResponse.class);
        log.info(response.body().toString());
    }

    @Test
    public void getNonExistentUserInfo() throws IOException {
        retrofit2.Response<SingleUserInfoResponse> response =
                userService.getUser(RandomUtils.nextInt(USERS_AMOUNT + 1, 100)).execute();
        checkStatusCodeStep(response, 404);
        checkErrorBodyStep(response, "{}");
    }

}
