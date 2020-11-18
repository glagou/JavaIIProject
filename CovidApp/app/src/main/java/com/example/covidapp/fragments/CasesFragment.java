package com.example.covidapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.AddCaseActivity;
import com.example.covidapp.EditCaseActivity;
import com.example.covidapp.R;
import com.example.covidapp.adapters.CasesAdapter;
import com.example.covidapp.models.ModelCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CasesFragment extends Fragment {

    private View fragmentView;

    private static List<ModelCase> cases = new ArrayList<>();

    private CasesAdapter adapter;

    private EditText search ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_cases, container, false);
        setButtonListeners();
        setUpRecyclerView();
        setEditTextListener();
        return fragmentView ;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpRecyclerView();
    }

    private void setEditTextListener() {
        search = (EditText) fragmentView.findViewById(R.id.searchBar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                handleSearch(s.toString().trim().toUpperCase());
            }
        });
    }

    private void handleSearch(String typedId) {
        List<ModelCase> casesToShow = new ArrayList<>();
        for (int i = 0 ; i < cases.size(); i++) {
            String id = cases.get(i).getId();
            if(id.contains(typedId)) {
                casesToShow.add(cases.get(i));
            }
        }
        adapter.modifyList(casesToShow);
    }

    private void setButtonListeners() {
        ImageButton addCaseButton = fragmentView.findViewById(R.id.add_case_button);

        addCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNewActivity(AddCaseActivity.class);
            }
        });

    }

    private void setUpRecyclerView() {
        RecyclerView victims_recycler_view = fragmentView.findViewById(R.id.victims_recycler_view);
        victims_recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        victims_recycler_view.setLayoutManager(linearLayoutManager);
        adapter = new CasesAdapter(getContext(),cases);
        victims_recycler_view.setAdapter(adapter);
    }

    public static void addToCases(ModelCase modelCase) {
        cases.add(modelCase);
    }

    private void loadNewActivity(Class activity) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
    }

    public static void removeFromCases(ModelCase modelCase) {
        cases.remove(modelCase);
    }

    public static int returnCases()
    {
        return cases.size();
    }

}