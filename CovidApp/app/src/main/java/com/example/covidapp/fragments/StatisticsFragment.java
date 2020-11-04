package com.example.covidapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.covidapp.R;
import com.example.covidapp.stats.StatsInfoHolder;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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
        handlePieChart();
        handleLineChart();
        return fragmentView;
    }

    private void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }


    private void handlePieChart(){
        PieChart secondPieChart = fragmentView.findViewById(R.id.secondPieChart);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(60.5f, "Males"));
        entries.add(new PieEntry(39.5f, "Females"));
        PieDataSet set = new PieDataSet(entries, "");
        PieData data = new PieData(set);
        secondPieChart.setData(data);
        secondPieChart.invalidate();
        //set.setColor(new int[]{ R.color.colorPrimary}, Context);
        //set2.setColor(new int[]{ R.color.material_on_surface_disabled}, Context);
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        Description description = secondPieChart.getDescription();
        description.setText("% confirmed cases based on gender");
    }

    private void handleLineChart(){
        LineChart firstLineChart = fragmentView.findViewById(R.id.firstLineChart);
        List<Entry> ageGroups = new ArrayList<Entry>();
        Entry c1e1 = new Entry(0f, 20f);
        ageGroups.add(c1e1);
        Entry c1e2 = new Entry(1f, StatsInfoHolder.getCases_25_34());
        ageGroups.add(c1e2);
        Entry c1e3 = new Entry(2f, 50f);
        ageGroups.add(c1e3);
        Entry c1e4 = new Entry(3f, 35f);
        ageGroups.add(c1e4);
        Entry c1e5 = new Entry(4f, 22f);
        ageGroups.add(c1e5);
        Entry c1e6 = new Entry(5f, 37f);
        ageGroups.add(c1e6);
        Entry c1e7 = new Entry(6f, 13f);
        ageGroups.add(c1e7);
        Entry c1e8 = new Entry(7f, 40f);
        ageGroups.add(c1e8);

        LineDataSet setComp1 = new LineDataSet(ageGroups, "Ages groups");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        LineData data = new LineData(dataSets);
        firstLineChart.setData(data);
        firstLineChart.invalidate(); // refresh

        // the labels that should be drawn on the XAxis
        final String[] quarters = new String[] { "0-24", "25-34", "35-44", "45-54", "55-64", "65-74", "75-84", "85+" };
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        };
        XAxis xAxis = firstLineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        Description description = firstLineChart.getDescription();
        description.setText("");

    }

}