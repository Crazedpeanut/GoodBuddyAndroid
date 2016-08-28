package au.com.hacklord.goodbuddy.service;

import au.com.hacklord.goodbuddy.dto.RegistrationRequestDto;
import au.com.hacklord.goodbuddy.dto.RegistrationResponseDto;
import au.com.hacklord.goodbuddy.dto.UpdateUserRequestDto;
import au.com.hacklord.goodbuddy.dto.UpdateUserResponseDto;
import au.com.hacklord.goodbuddy.model.User;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by john on 27/08/2016.
 */
public interface IUserService {
    @POST("user")
    Observable<Response<RegistrationResponseDto>> registerUser(@Body RegistrationRequestDto registrationRequestDto);

    @PUT("user/{userId}")
    Observable<Response<UpdateUserResponseDto>> updateUser(@Path("userId") String userid, @Body User user);
}
