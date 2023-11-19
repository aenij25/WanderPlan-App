package com.aplikasi.wanderplan.ui.Fragments.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.wanderplan.API.RetrofitClient;
import com.aplikasi.wanderplan.Activity.LoginActivity;
import com.aplikasi.wanderplan.Model.GlobalModel;
import com.aplikasi.wanderplan.Model.data.Category.CategoryData;
import com.aplikasi.wanderplan.Util.CustomToast;
import com.aplikasi.wanderplan.Util.RecyclerViewItemSpacing;
import com.aplikasi.wanderplan.ui.Adapters.CategoryAdapter;
import com.aplikasi.wanderplan.R;
import com.aplikasi.wanderplan.databinding.FragmentHomeBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    List<String> categories;

    RecyclerView rvReceipt, rvCategory;

    CategoryAdapter categoryAdapter;

    LinearLayout llParentContent;

    EditText etSearch;
    private FragmentHomeBinding binding;

    private String token;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        token = "Bearer " + ((GlobalModel) getContext().getApplicationContext()).getSessionManager().getToken();
        if(token.isEmpty()){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.putExtra("custom_toast_msg", "Sesi telah berakhir, masuk kembali");
            startActivity(intent);
            getActivity().finish();
        }

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        root = binding.getRoot();

        categories = new ArrayList<>();


        int receiptSpacing = getResources().getDimensionPixelSize(R.dimen.receipt_spacing);
        rvReceipt = (RecyclerView) binding.rvReceipt;
        RecyclerView.LayoutManager receiptLayoutManager = new LinearLayoutManager(getContext());
        rvReceipt.setLayoutManager(receiptLayoutManager);
        rvReceipt.addItemDecoration(new RecyclerViewItemSpacing(getContext(), receiptSpacing));

        int categorySpacing = getResources().getDimensionPixelSize(R.dimen.category_spacing);
        rvCategory = (RecyclerView) binding.rvCategory;
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(categoryLayoutManager);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.addItemDecoration(new RecyclerViewItemSpacing(getContext(), categorySpacing));


        llParentContent = (LinearLayout) binding.llParentContent;
        etSearch = (EditText) binding.etSearch;

        llParentContent.setOnTouchListener((v, event) -> {
            etSearch.clearFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rvReceipt.scrollToPosition(0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();

//        new CustomToast("Current Category: " + categoryAdapter.getCurrentCategory(), getView().getRootView(), false).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}