package services;

import dto.CredentialsDto;
import dto.register.RegisterDtoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("register")
    Call<RegisterDtoResponse> postRegister(@Body CredentialsDto credentialsDto);
}
