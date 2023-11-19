package com.aplikasi.wanderplan.ui.Fragments.Detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aplikasi.wanderplan.Model.GlobalModel;
import com.aplikasi.wanderplan.ui.Adapters.StepAdapter;
import com.aplikasi.wanderplan.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StepsFragment extends Fragment {
    RecyclerView rvSteps;
    List<String> steps;

    int index;

    public StepsFragment() {  }

    public StepsFragment(String[] steps, int index) {
        this.steps = new ArrayList<>(Arrays.asList(steps));
        this.index = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        rvSteps = (RecyclerView) view.findViewById(R.id.rv_steps);

        StepAdapter adapter = new StepAdapter(steps);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvSteps.setLayoutManager(layoutManager);
        rvSteps.setAdapter(adapter);
        rvSteps.addItemDecoration(new DividerItemDecoration(rvSteps.getContext(), DividerItemDecoration.VERTICAL));



        return view;
    }
}