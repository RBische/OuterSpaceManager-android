package outerspacemanager.bischof.raphael.outerspacemanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.OuterSpaceManagerService;
import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.BuildingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static outerspacemanager.bischof.raphael.outerspacemanager.config.Settings.KEY_TOKEN;
import static outerspacemanager.bischof.raphael.outerspacemanager.config.Settings.SHARED_PREFERENCES_FILENAME;

public class BuildingsActivity extends AppCompatActivity {

    private static final String TAG = BuildingsActivity.class.getSimpleName();
    private RecyclerView rvBuildings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        this.rvBuildings = (RecyclerView) findViewById(R.id.rvBuildings);
        rvBuildings.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

        Call<BuildingResponse> call = service.getBuildings(currentToken);
        call.enqueue(new Callback<BuildingResponse>() {
            @Override
            public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                Log.d(TAG, "Ships retrieved");
                rvBuildings.setAdapter(new BuildingsAdapter(response.body().buildings, BuildingsActivity.this));
            }

            @Override
            public void onFailure(Call<BuildingResponse> call, Throwable t) {
                Log.e(TAG, "Error while retrieving buildings", t);
            }
        });
    }

}
