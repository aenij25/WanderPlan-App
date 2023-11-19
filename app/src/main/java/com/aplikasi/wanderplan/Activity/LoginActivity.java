package com.aplikasi.wanderplan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aplikasi.wanderplan.API.Services.AuthService;
import com.aplikasi.wanderplan.API.RetrofitClient;
import com.aplikasi.wanderplan.Model.api.AccountResponse;
import com.aplikasi.wanderplan.Util.CustomToast;
import com.aplikasi.wanderplan.Model.GlobalModel;
import com.aplikasi.wanderplan.Model.ViewModel.AccountViewModel;
import com.aplikasi.wanderplan.Model.api.Login.LoginRequest;
import com.aplikasi.wanderplan.Model.api.Login.LoginResponse;
import com.aplikasi.wanderplan.Model.data.Account.Account;
import com.aplikasi.wanderplan.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText etIdentifier, etPassword;
    LinearLayout llRootLoadingLogin;


    SharedPreferences sharedPreferences;

    Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        thisActivity = this;

        Intent intent = getIntent();
        String toastMsg = intent.getStringExtra("TOAST_MSG");
        if(toastMsg != null){
            if(!toastMsg.isEmpty())
                new CustomToast(
                        toastMsg,
                        LayoutInflater.from(this).inflate(R.layout.activity_login, null)
                ).show();
        }

        sharedPreferences = getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        etIdentifier = (EditText) findViewById(R.id.et_identifier);
        etPassword = (EditText) findViewById(R.id.et_password);

        llRootLoadingLogin = (LinearLayout) findViewById(R.id.ll_root_loading_login);

        btnLogin.setOnClickListener(v -> {
            etIdentifier.clearFocus();
            etPassword.clearFocus();
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            boolean isCompleted = true;

            EditText[] required = {etIdentifier, etPassword};
            for (EditText et : required) {
                if (et.getText().toString().isEmpty()) {
                    et.setError("Must be filled");
                    isCompleted = false;
                } else {
                    et.setError(null);
                }
            }

            if (isCompleted) {
                llRootLoadingLogin.setVisibility(View.VISIBLE);

                String identifier = etIdentifier.getText().toString();
                String password = etPassword.getText().toString();


                RetrofitClient
                        .getInstance()
                        .create(AuthService.class)
                        .login(new LoginRequest(identifier, password))
                        .enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                int statusCode = response.code();

                                if (response.isSuccessful()) {
                                    AccountResponse accountResponseJson = response.body().getAccount();

                                    Account account = new Account(
                                            accountResponseJson.getId(),
                                            accountResponseJson.getName(),
                                            accountResponseJson.getUsername(),
                                            accountResponseJson.getImageUrl(),
                                            accountResponseJson.getEmail()
                                    );

                                    AccountViewModel accountViewModel = ((GlobalModel) getApplication()).getAccountViewModel();
                                    accountViewModel.setAccount(account);

                                    System.out.println("Account: " + accountViewModel.getAccount().getValue().toString());

                                    llRootLoadingLogin.setVisibility(View.INVISIBLE);

                                    ((GlobalModel) getApplication())
                                            .getSessionManager()
                                            .login(
                                                    thisActivity,
                                                    thisActivity,
                                                    response.body().getToken(),
                                                    account,
                                                    "Login Successed!"
                                            );
                                } else {
                                    llRootLoadingLogin.setVisibility(View.INVISIBLE);
                                    try {
                                        String errorBody = response.errorBody().string();
                                        Log.e("Status code: ", String.valueOf(statusCode));
                                        Log.e("Error Response Body", errorBody);

                                        JSONObject errorJson = new JSONObject(errorBody);
                                        String errorMessage = errorJson.optString("message");
                                        String errorText = errorJson.optString("error");

                                        switch (statusCode) {
                                            case 401:
                                                etPassword.setError(errorMessage);
                                            case 404:
                                                new CustomToast(errorMessage, v).show();
                                                etIdentifier.setError(errorMessage);
                                                break;
                                            case 500:
                                                new CustomToast(errorText, v).show();
                                        }
                                    } catch (IOException | JSONException e) {
                                        Log.e("error", e.toString());
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                System.out.println("LOGIN FAILURE: " + t.getMessage());
                                new CustomToast("Error Connection!", v).show();
                                llRootLoadingLogin.setVisibility(View.INVISIBLE);
                            }
                        });
            } else {
                new CustomToast("Please fill in the form!", v).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}