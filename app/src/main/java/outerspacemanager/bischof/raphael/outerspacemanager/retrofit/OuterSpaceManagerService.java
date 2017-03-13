package outerspacemanager.bischof.raphael.outerspacemanager.retrofit;


import java.util.ArrayList;

import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.BuildingResponse;
import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.SignupRequest;
import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.SignupResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OuterSpaceManagerService {
    @POST("/api/v1/auth/create")
    Call<SignupResponse> createUser(@Body SignupRequest request);
    @GET("/api/v1/buildings/list")
    Call<BuildingResponse> getBuildings(@Header("x-access-token") String accessToken);
}
