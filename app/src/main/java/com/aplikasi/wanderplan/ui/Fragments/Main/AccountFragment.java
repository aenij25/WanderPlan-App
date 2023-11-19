package com.aplikasi.wanderplan.ui.Fragments.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aplikasi.wanderplan.Model.GlobalModel;
import com.aplikasi.wanderplan.Model.ViewModel.AccountViewModel;
import com.aplikasi.wanderplan.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private AccountViewModel accountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accountViewModel = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel();

        binding.tvNameAccount.setText(
                accountViewModel.getName()
        );

        binding.tvEmailAccount.setText(
                accountViewModel.getEmail()
        );

        binding.btnLogoutAccount.setOnClickListener(v -> {
            ((GlobalModel) getContext().getApplicationContext())
                    .getSessionManager()
                    .logout(getContext(), getActivity(), "Logout berhasil");
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}