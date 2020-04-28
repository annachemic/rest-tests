package rest;

import dto.user.UsersInfoResponse;
import services.UserService;
import utils.RetrofitUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AdditionalSteps {


    public static List<UsersInfoResponse.User> getUsersList(int page) throws IOException {
        UserService userService = RetrofitUtils.getRetrofit()
                .create(UserService.class);
        return Objects.requireNonNull(userService.getUserList(page).execute().body()).getData();
    }
}
