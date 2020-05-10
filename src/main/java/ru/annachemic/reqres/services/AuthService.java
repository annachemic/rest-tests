package ru.annachemic.reqres.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.annachemic.reqres.dto.CredentialsDto;
import ru.annachemic.reqres.dto.auth.AuthDtoResponse;

public interface AuthService {
    @POST("login")
    Call<AuthDtoResponse> postLogin(@Body CredentialsDto credentialsDto);
}
