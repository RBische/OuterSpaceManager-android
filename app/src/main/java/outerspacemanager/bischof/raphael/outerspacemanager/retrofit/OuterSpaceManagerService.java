package outerspacemanager.bischof.raphael.outerspacemanager.retrofit;


import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.SignupRequest;
import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.SignupResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OuterSpaceManagerService {
    @POST("/api/v1/auth/create")
    Call<SignupResponse> createUser(@Body SignupRequest request);
}
