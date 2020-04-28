package services;

import dto.user.SingleUserInfoResponse;
import dto.user.UsersInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("users")
    Call<UsersInfoResponse> getUserList(@Query("page") int page);

    @GET("users/{id}")
    Call<SingleUserInfoResponse> getUser(@Path("id") int userId);
}
