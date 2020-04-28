package services;

import dto.CredentialsDto;
import dto.auth.AuthDtoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("login")
    Call<AuthDtoResponse> postLogin(@Body CredentialsDto credentialsDto);
}
