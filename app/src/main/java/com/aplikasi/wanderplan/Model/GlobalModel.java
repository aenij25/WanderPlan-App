package com.aplikasi.wanderplan.Model;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.aplikasi.wanderplan.Model.ViewModel.AccountViewModel;
import com.aplikasi.wanderplan.Util.SessionManager;

public class GlobalModel extends Application {
    private AccountViewModel accountViewModel;
//    private FavouriteViewModel favouriteViewModel;
//    private RecipeViewModel recipeViewModel;
    private SessionManager sessionManager;
    @Override
    public void onCreate() {
        super.onCreate();

        accountViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AccountViewModel.class);
        sessionManager = new SessionManager(getSharedPreferences("gorenganindonesia", Context.MODE_PRIVATE));
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
