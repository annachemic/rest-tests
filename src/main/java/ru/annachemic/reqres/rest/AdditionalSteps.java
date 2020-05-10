package ru.annachemic.reqres.rest;

import ru.annachemic.reqres.dto.user.UsersInfoResponse;
import ru.annachemic.reqres.services.UserService;
import ru.annachemic.reqres.utils.RetrofitUtils;

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
