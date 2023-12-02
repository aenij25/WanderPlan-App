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

public class MainActivity extends AppCompatActivity {
    AccountViewModel accountViewModel;
//    RecipeViewModel recipeViewModel;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountViewModel = ((GlobalModel) getApplication()).getAccountViewModel();
//        recipeViewModel = ((GlobalModel) getApplication()).getRecipeViewModel();


        sharedPreferences = getSharedPreferences("wanderplan", Context.MODE_PRIVATE);

        boolean isLogged = sharedPreferences.getBoolean("isLogged", false);

        if(!isLogged){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
            return;
        }

        Intent intent = getIntent();
        String toastMsg = intent.getStringExtra("TOAST_MSG");

        Account account = new Account(
                sharedPreferences.getString("account_id", "undefined"),
                sharedPreferences.getString("account_name", "undefined"),
                sharedPreferences.getString("account_username", "undefined"),
                sharedPreferences.getString("account_image_url", "undefined"),
                sharedPreferences.getString("account_email", "undefined")
        );

        accountViewModel.setAccount(account);

        System.out.println("Account: " + accountViewModel.getAccount().getValue().toString());

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation
                .findNavController(this, R.id.nav_host_fragment_activity_main2);

        NavigationUI.setupWithNavController(navView, navController);

        navView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // This method will be called once the layout has been populated, and you can obtain its height.
                int height = navView.getHeight();

                // Remove the listener to avoid multiple calls
                navView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

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
