package com.aplikasi.wanderplan.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.aplikasi.wanderplan.Model.ViewModel.AccountViewModel;
import com.aplikasi.wanderplan.Model.GlobalModel;
import com.aplikasi.wanderplan.Model.data.Account.Account;
import com.aplikasi.wanderplan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    AccountViewModel accountViewModel;
//    RecipeViewModel recipeViewModel;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountViewModel = ((GlobalModel) getApplication()).getAccountViewModel();
        sharedPreferences = getSharedPreferences("wanderplan", Context.MODE_PRIVATE);

        Map session = sharedPreferences.getAll();
        if(!Boolean.parseBoolean(Objects.requireNonNull(session.get("isLogged")).toString())){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
            return;
        }

        Intent intent = getIntent();
        String toastMsg = intent.getStringExtra("TOAST_MSG");

        Account account = new Account(
                sharedPreferences.getString("account_id", Objects.requireNonNull(session.get("account_id")).toString()),
                sharedPreferences.getString("account_name", Objects.requireNonNull(session.get("account_name")).toString()),
                sharedPreferences.getString("account_username", Objects.requireNonNull(session.get("account_username")).toString()),
                sharedPreferences.getString("account_email", Objects.requireNonNull(session.get("account_email")).toString())
        );

        accountViewModel.setAccount(account);

        System.out.println("Account: " + accountViewModel.getAccount().getValue().toString());

        setContentView(R.layout.activity_main);

//        BottomNavigationView navView = findViewById(R.id.nav_view);

//        NavController navController = Navigation
//                .findNavController(this, R.id.nav_host_fragment_activity_main2);

//        NavigationUI.setupWithNavController(navView, navController);

//        navView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // This method will be called once the layout has been populated, and you can obtain its height.
//                int height = navView.getHeight();
//
//                // Remove the listener to avoid multiple calls
//                navView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });

    }
    public static void deleteCache(Context context) {
        try {
            File cacheDirectory = context.getCacheDir();
            File[] cacheFiles = cacheDirectory.listFiles();
            if (cacheFiles != null) {
                for (File file : cacheFiles) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearAppData(Context context) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear " + context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restartApp() {
        Intent intent = new Intent(this, MainActivity.class); // Replace YourMainActivity with your app's main activity class
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }
}
