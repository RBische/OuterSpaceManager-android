package outerspacemanager.bischof.raphael.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.OuterSpaceManagerService;
import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.SignupRequest;
import outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model.SignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static outerspacemanager.bischof.raphael.outerspacemanager.config.Settings.KEY_TOKEN;
import static outerspacemanager.bischof.raphael.outerspacemanager.config.Settings.SHARED_PREFERENCES_FILENAME;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignup;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
        String currentToken = settings.getString(KEY_TOKEN, null);
        if (currentToken != null){
            setContentView(R.layout.activity_signup);
            btnSignup = (Button) findViewById(R.id.btnSignup);
            etUsername = (EditText) findViewById(R.id.etUsername);
            etPassword = (EditText) findViewById(R.id.etPassword);
            btnSignup.setOnClickListener(this);
        } else {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignup) {
            SignupRequest signupRequest = new SignupRequest(etUsername.getText().toString(), etPassword.getText().toString());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://outer-space-manager.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OuterSpaceManagerService service = retrofit.create(OuterSpaceManagerService.class);

            Call<SignupResponse> call = service.createUser(signupRequest);
            call.enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                    if (response.isSuccessful()) {
                        SignupResponse signupResponse = response.body();
                        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_FILENAME, 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("KEY_TOKEN", signupResponse.getToken());
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        //TODO: Handle errors here
                    }
                }

                @Override
                public void onFailure(Call<SignupResponse> call, Throwable t) {
                    //TODO: Handle errors here too
                }
            });

        }
    }
}
