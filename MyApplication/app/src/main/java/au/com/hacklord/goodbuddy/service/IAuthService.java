package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.dto.AuthenticationRequestDto;
import au.com.hacklord.goodbuddy.dto.AuthenticationResponseDto;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public interface IAuthService {

    @POST("auth/authenticate")
    Observable<Response<AuthenticationResponseDto>> authenticateUser(@Body AuthenticationRequestDto authenticationRequestDto);
}
