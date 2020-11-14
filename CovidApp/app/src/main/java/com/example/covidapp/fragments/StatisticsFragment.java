package com.example.covidapp.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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
        handleBarChart();
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
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        Description description = secondPieChart.getDescription();
        description.setText("% confirmed cases based on gender");
        secondPieChart.animateXY(1300,1300);
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
        firstLineChart.animateXY(1300, 1300);

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
    private void handleBarChart() {
        BarChart topBarChart = fragmentView.findViewById(R.id.topBarChart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0.5f, StatsInfoHolder.getCases_january()));
        entries.add(new BarEntry(1.5f, 20f));
        entries.add(new BarEntry(2.5f, 10f));
        entries.add(new BarEntry(3.5f, 50f));
        entries.add(new BarEntry(4.5f, 5f));
        entries.add(new BarEntry(5.5f, 43f));
        entries.add(new BarEntry(6.5f, 25f));
        entries.add(new BarEntry(7.5f, 5f));
        entries.add(new BarEntry(8.5f, 15f));
        entries.add(new BarEntry(9.5f, 55f));
        entries.add(new BarEntry(10.5f, 11f));
        entries.add(new BarEntry(11.5f, 8f));
        BarDataSet barDataSet = new BarDataSet(entries, "");
        BarData barData = new BarData(barDataSet);
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Jan");
        xAxisLabel.add("Feb");
        xAxisLabel.add("Mar");
        xAxisLabel.add("Apr");
        xAxisLabel.add("May");
        xAxisLabel.add("Jun");
        xAxisLabel.add("Jul");
        xAxisLabel.add("Aug");
        xAxisLabel.add("Sep");
        xAxisLabel.add("Oct");
        xAxisLabel.add("Nov");
        xAxisLabel.add("Dec");

        XAxis xAxis = topBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(12);

        topBarChart.setData(barData);
        topBarChart.animateXY(1300, 1300);
        topBarChart.setFitBars(true);
        topBarChart.setDescription(null);
        topBarChart.getXAxis().setCenterAxisLabels(true);
        topBarChart.setDrawGridBackground(false);
        topBarChart.invalidate();
    }

}