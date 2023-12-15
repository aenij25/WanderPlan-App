package com.aplikasi.wanderplan.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.aplikasi.wanderplan.Activity.AfterLoginActivity;
import com.aplikasi.wanderplan.Activity.LoginActivity;
import com.aplikasi.wanderplan.Activity.MainActivity;
import com.aplikasi.wanderplan.Model.data.Account.Account;

public class SessionManager {
    SharedPreferences sharedPreferences;
    private String token;

    public SessionManager(SharedPreferences sp){
        this.sharedPreferences = sp;
        this.token = sp.getString("token", "");
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public void login(Context context, Activity activity, String token, Account account, String msg){
        sharedPreferences
                .edit()
                .putBoolean("isLogged", true)
                .putString("token", token)
                .putString("account_id", account.getId())
                .putString("account_name", account.getName())
                .putString("account_username", account.getUsername())
                .putString("account_email", account.getEmail())
                .apply();
        setToken(token);

        System.out.println(sharedPreferences.getAll());
        Intent intent = new Intent(context, AfterLoginActivity.class);
        intent.putExtra("TOAST_MSG", msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    public void logout(Context context, Activity activity, String msg){
        sharedPreferences
                .edit()
                .clear()
                .apply();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("TOAST_MSG", msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}
