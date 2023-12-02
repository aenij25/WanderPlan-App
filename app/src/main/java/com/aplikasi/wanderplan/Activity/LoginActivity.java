package com.aplikasi.wanderplan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aplikasi.wanderplan.R;

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


        sharedPreferences = getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

//        etIdentifier = (EditText) findViewById(R.id.et_identifier);
//        etPassword = (EditText) findViewById(R.id.et_password);
//
//        llRootLoadingLogin = (LinearLayout) findViewById(R.id.ll_root_loading_login);

//        btnLogin.setOnClickListener(v -> {
//            Intent intent1 = new Intent(v.getContext(), AfterLoginActivity.class);
//            v.getContext().startActivity(intent1);
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), AfterLoginActivity.class);
//                v.getContext().startActivity(intent1);
//            }
//            etIdentifier.clearFocus();
//            etPassword.clearFocus();
//            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//            boolean isCompleted = true;
//
//            EditText[] required = {etIdentifier, etPassword};
//            for (EditText et : required) {
//                if (et.getText().toString().isEmpty()) {
//                    et.setError("Must be filled");
//                    isCompleted = false;
//                } else {
//                    et.setError(null);
//                }
//            }
//
//            if (isCompleted) {
//                llRootLoadingLogin.setVisibility(View.VISIBLE);
//
//                String identifier = etIdentifier.getText().toString();
//                String password = etPassword.getText().toString();



//            } else {
//                new CustomToast("Please fill in the form!", v).show();
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AfterLoginActivity.class);
                v.getContext().startActivity(intent);
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