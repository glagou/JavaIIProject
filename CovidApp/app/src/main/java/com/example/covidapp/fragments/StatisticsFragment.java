package com.example.covidapp.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        // Calling the three methods for pie, line and bar chart
        handlePieChart();
        handleLineChart();
        handleBarChart();
        return fragmentView;
    }

    // Initializes the fragmentView field
    private void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    // Creating the Pie Chart
    private void handlePieChart(){
        // Creating an object of the chart and connect it with the according one in the fragment_statistics.xml, using the id name
        PieChart secondPieChart = fragmentView.findViewById(R.id.secondPieChart);
        // Creating a list with the entries of the Pie Chart
        List<PieEntry> entries = new ArrayList<>();
        // Taking the entries from the StatsInfoHolder, using the methods getCases_males and getCases_females
        entries.add(new PieEntry(StatsInfoHolder.getCases_males(), "Males"));
        entries.add(new PieEntry(StatsInfoHolder.getCases_females(), "Females"));
        // Trick to erase the label from the chart
        PieDataSet set = new PieDataSet(entries, "");
        PieData data = new PieData(set);
        secondPieChart.setData(data);
        // Refresh the pie chart
        secondPieChart.invalidate();
        // Setting colors using a template
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        // Setting my own description to the graph, in order to be more easy for user to understand the data
        Description description = secondPieChart.getDescription();
        description.setText("% confirmed cases based on gender");
        // Animation for the X and Y axis. 1,3 seconds graph's movement when the user goes to Statistics tab
        secondPieChart.animateXY(1300,1300);
    }

    // Creating the line chart
    private void handleLineChart(){
        // Creating an object of the chart and connect it with the according one in the fragment_statistics.xml, using the id name
        LineChart firstLineChart = fragmentView.findViewById(R.id.firstLineChart);
        // Creating a list to put the entries for the graph from the StatsInfoHolder
        List<Entry> ageGroups = new ArrayList<Entry>();
        // Creating the entries calling the appropriate methods
        // The methods are separated according the age as you can see in the names
        Entry c1e1 = new Entry(0f, StatsInfoHolder.getCases_0_24());
        // Once we create the entry, we add it to the list
        ageGroups.add(c1e1);
        // Keep doing the same for every age group
        Entry c1e2 = new Entry(1f, StatsInfoHolder.getCases_25_34());
        ageGroups.add(c1e2);
        Entry c1e3 = new Entry(2f, StatsInfoHolder.getCases_35_44());
        ageGroups.add(c1e3);
        Entry c1e4 = new Entry(3f, StatsInfoHolder.getCases_45_54());
        ageGroups.add(c1e4);
        Entry c1e5 = new Entry(4f, StatsInfoHolder.getCases_55_64());
        ageGroups.add(c1e5);
        Entry c1e6 = new Entry(5f, StatsInfoHolder.getCases_65_74());
        ageGroups.add(c1e6);
        Entry c1e7 = new Entry(6f, StatsInfoHolder.getCases_75_84());
        ageGroups.add(c1e7);
        Entry c1e8 = new Entry(7f, StatsInfoHolder.getCases_85_plus());
        ageGroups.add(c1e8);
        // Setting the graph's label
        LineDataSet setComp1 = new LineDataSet(ageGroups, "Ages groups");
        // We place the label on the left
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        // Animation for the X and Y axis. 1,3 seconds graph's movement when the user goes to Statistics tab
        firstLineChart.animateXY(1300, 1300);
        // Use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        LineData data = new LineData(dataSets);
        firstLineChart.setData(data);
        // Refresh the line chart
        firstLineChart.invalidate();
        // The labels that should be drawn on the XAxis
        final String[] quarters = new String[] { "0-24", "25-34", "35-44", "45-54", "55-64", "65-74", "75-84", "85+" };
        // Method to format the quarters to have equal distance between them
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        };
        // Creating an object for the xAxis
        XAxis xAxis = firstLineChart.getXAxis();
        // Minimum axis-step (interval) is 1
        xAxis.setGranularity(1f);
        // Using the object to call the method in order to format the distance between the quarters in xAxis
        xAxis.setValueFormatter(formatter);
        // Making the description null because isn't useful for the specific chart
        firstLineChart.setDescription(null);
    }

    // Creating the bar chart
    private void handleBarChart() {
        // Creating an object of the chart and connect it with the according one in the fragment_statistics.xml, using the id name
        BarChart topBarChart = fragmentView.findViewById(R.id.topBarChart);
        // Creating a list to put the entries for the chart from the StatsInfoHolder
        List<BarEntry> entries = new ArrayList<>();
        // Set the data and custom bar width from 0.5, adding 1 each time
        entries.add(new BarEntry(0.5f, StatsInfoHolder.getCases_january()));
        entries.add(new BarEntry(1.5f, StatsInfoHolder.getCases_february()));
        entries.add(new BarEntry(2.5f, StatsInfoHolder.getCases_march()));
        entries.add(new BarEntry(3.5f, StatsInfoHolder.getCases_april()));
        entries.add(new BarEntry(4.5f, StatsInfoHolder.getCases_may()));
        entries.add(new BarEntry(5.5f, StatsInfoHolder.getCases_june()));
        entries.add(new BarEntry(6.5f, StatsInfoHolder.getCases_july()));
        entries.add(new BarEntry(7.5f, StatsInfoHolder.getCases_august()));
        entries.add(new BarEntry(8.5f, StatsInfoHolder.getCases_september()));
        entries.add(new BarEntry(9.5f, StatsInfoHolder.getCases_october()));
        entries.add(new BarEntry(10.5f, StatsInfoHolder.getCases_november()));
        entries.add(new BarEntry(11.5f, StatsInfoHolder.getCases_december()));
        // Creating an object of the BarData class in order to edit the graph
        BarDataSet barDataSet = new BarDataSet(entries, "");
        BarData barData = new BarData(barDataSet);
        // Setting colors from a palette
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        // Creating a list for labels in x axis
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        // Adding entries with the months' names in the list
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
        // Creating an object of the xAxis
        XAxis xAxis = topBarChart.getXAxis();
        // Using the object of the xAxis we created before in order to set the
        // position of the labels on top of the specific axis
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        // Setting text size
        xAxis.setTextSize(10f);
        // Drawing a line in the xAxis
        xAxis.setDrawAxisLine(true);
        // Hiding the Gridlines from the axis
        xAxis.setDrawGridLines(false);
        // Setting the color in black
        xAxis.setTextColor(Color.BLACK);
        // Format the axis and the distance between the labels
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(12);
        topBarChart.setData(barData);
        // Animation 1.3 seconds for both X and Y axis when the user enters the statistics tab
        topBarChart.animateXY(1300, 1300);
        // Make the x-axis fit exactly all bars
        topBarChart.setFitBars(true);
        // Make the description null
        topBarChart.setDescription(null);
        // Make the labels in xAxis aligned in the center
        topBarChart.getXAxis().setCenterAxisLabels(true);
        // Make the gridlines in the background to be invisible
        topBarChart.setDrawGridBackground(false);
        // Refresh the bar chart
        topBarChart.invalidate();
    }
}