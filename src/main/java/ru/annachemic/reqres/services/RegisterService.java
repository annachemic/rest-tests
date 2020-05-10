package ru.annachemic.reqres.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.annachemic.reqres.dto.CredentialsDto;
import ru.annachemic.reqres.dto.register.RegisterDtoResponse;

public interface RegisterService {
    @POST("register")
    Call<RegisterDtoResponse> postRegister(@Body CredentialsDto credentialsDto);
}
