package com.example.covidapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.covidapp.R;
import com.example.covidapp.adapters.CasesAdapter;
import com.example.covidapp.models.ModelCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private static List<ModelCase> cases = new ArrayList<>();

    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        setUpRecyclerView();
        return fragmentView;
    }

    private void setUpRecyclerView() {
        RecyclerView victims_recycler_view = fragmentView.findViewById(R.id.victims_recycler_view);
        victims_recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        victims_recycler_view.setLayoutManager(linearLayoutManager);
        CasesAdapter adapter = new CasesAdapter(getContext(),cases);
        victims_recycler_view.setAdapter(adapter);
        Log.i("XD", String.valueOf(cases.size()));
    }

    public static void addToCases(ModelCase modelCase) {
        cases.add(modelCase);
    }
}