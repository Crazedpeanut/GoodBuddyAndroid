package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.dto.AuthenticationRequestDto;
import au.com.hacklord.goodbuddy.model.AuthenticationResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public interface IAuthService {

    @POST("auth/authenticate")
    Observable<AuthenticationResponse> authenticateUser(@Body AuthenticationRequestDto authenticationRequestDto);
}
