package ru.annachemic.reqres.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.annachemic.reqres.dto.user.SingleUserInfoResponse;
import ru.annachemic.reqres.dto.user.UsersInfoResponse;

public interface UserService {
    @GET("users")
    Call<UsersInfoResponse> getUserList(@Query("page") int page);

    @GET("users/{id}")
    Call<SingleUserInfoResponse> getUser(@Path("id") int userId);
}
