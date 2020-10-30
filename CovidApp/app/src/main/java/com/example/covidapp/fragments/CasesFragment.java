package com.example.covidapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.AddCaseActivity;
import com.example.covidapp.EditCaseActivity;
import com.example.covidapp.R;
import com.example.covidapp.adapters.CasesAdapter;
import com.example.covidapp.models.ModelCase;

import java.util.ArrayList;
import java.util.List;

public class CasesFragment extends Fragment {

    private View fragmentView;

    private static List<ModelCase> cases = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_cases, container, false);
        setButtonListeners();
        setUpRecyclerView();
        return fragmentView ;
    }

    private void setButtonListeners() {
        ImageButton addCaseButton = fragmentView.findViewById(R.id.add_case_button);
        ImageButton editCaseButton = fragmentView.findViewById(R.id.edit_case_button);

        addCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNewActivity(AddCaseActivity.class);
            }
        });

        editCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNewActivity(EditCaseActivity.class);
            }
        });
    }

    private void setUpRecyclerView() {
        RecyclerView victims_recycler_view = fragmentView.findViewById(R.id.victims_recycler_view);
        victims_recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        victims_recycler_view.setLayoutManager(linearLayoutManager);
        CasesAdapter adapter = new CasesAdapter(getContext(),cases);
        victims_recycler_view.setAdapter(adapter);
    }

    public static void addToCases(ModelCase modelCase) {
        cases.add(modelCase);
    }

    private void loadNewActivity(Class activity) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
    }

    public static int returnCases()
    {
        return cases.size();
    }

}