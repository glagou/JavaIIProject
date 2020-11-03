package com.example.covidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.covidapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView =  inflater.inflate(R.layout.fragment_statistics, container, false);
        setFragmentView(fragmentView);
        handleBarChart();
        return fragmentView;
    }

    private void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    private void handleBarChart() {
        BarChart topBarChart = fragmentView.findViewById(R.id.topBarChart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(2f, 10f));
        entries.add(new BarEntry(2f, 20f));
        entries.add(new BarEntry(6f, 30f));
        entries.add(new BarEntry(8f, 40f));
        entries.add(new BarEntry(10f, 50f));
        entries.add(new BarEntry(15f, 60f));
        entries.add(new BarEntry(30f, 70f));
        BarDataSet barDataSet = new BarDataSet(entries, "Cases By Month");
        BarData barData = new BarData(barDataSet);
        topBarChart.setData(barData);
        topBarChart.invalidate();
    }
}