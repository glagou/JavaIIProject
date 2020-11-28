package com.example.covidapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.adapters.CasesAdapter;
import com.example.covidapp.models.ModelCase;
import com.google.android.gms.common.data.DataHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.covidapp.stats.StatsInfoHolder;

public class HomeFragment extends Fragment {

    private View fragmentView;

    private final int GREECE_POP = 10720000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        setTextColors();
        setUpProgressBar();
        return fragmentView;
    }

    //Sets the substring text colors.
    private void setTextColors() {
        TextView doctorsText = fragmentView.findViewById(R.id.doctorsText);
        String text = "Doctor Suggestions";
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan fcsBlack = new ForegroundColorSpan(Color.BLACK);
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(getResources().getColor(R.color.textColor));
        spannableString.setSpan(fcsBlack, 0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(fcsBlue, 7,18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        doctorsText.setText(spannableString);

        TextView statusText = fragmentView.findViewById(R.id.statusText);
        text = "Present Status";
        SpannableString spannableStringTwo = new SpannableString(text);
        spannableStringTwo.setSpan(fcsBlack, 0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringTwo.setSpan(fcsBlue, 7,14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        statusText.setText(spannableStringTwo);

        TextView coronaText = fragmentView.findViewById(R.id.coronaText);
        text = "Known Symptoms";
        SpannableString spannableStringThree= new SpannableString(text);
        spannableStringThree.setSpan(fcsBlack, 0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringThree.setSpan(fcsBlue, 6,14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        coronaText.setText(spannableStringThree);

        TextView bottomTips = fragmentView.findViewById(R.id.bottomTips);
        String textOne = "If COVID-19 is spreading in your community, stay safe by taking some simple precautions, " +
                "such as physical distancing, wearing a mask, keeping rooms well ventilated, avoiding crowds, " +
                "cleaning your hands, and coughing into a bent elbow or tissue.";
        SpannableString spannableStringFour = new SpannableString(textOne);
        String[] blueWords = new String[] {"stay safe", "precautions", "distancing","wearing","mask", "ventilated", "avoiding crowds","cleaning",
        "hands","coughing", "elbow or tissue"};
        for(int i = 0; i < blueWords.length; i ++) {
            int index = textOne.indexOf(blueWords[i]);
            ForegroundColorSpan fcsBlueNew = new ForegroundColorSpan(getResources().getColor(R.color.textColor));
            spannableStringFour.setSpan(fcsBlueNew, index,index + blueWords[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        bottomTips.setText(spannableStringFour);

        TextView bottomTips2 = fragmentView.findViewById(R.id.bottomTips2);
        String textTwo = "Seek immediate medical attention if you have serious symptoms. Always call before visiting your doctor or health facility. " +
                "People with mild symptoms who are otherwise healthy should manage their symptoms at home. " +
                "On average it takes 5–6 days from when someone is infected with the virus for symptoms to show, however it can take up to 14 days.";
        SpannableString spannableStringFive = new SpannableString(textTwo);
        String[] blueWordsTwo = new String[] {"immediate medical attention", "symptoms", "call before visiting", "mild symptoms", "otherwise healthy",
        "manage", "at home", "5", "6" ,"to show", "can take up", "14 days"};
        for(int i = 0; i < blueWordsTwo.length; i ++) {
            int index = textTwo.indexOf(blueWordsTwo[i]);
            ForegroundColorSpan fcsBlueNew = new ForegroundColorSpan(getResources().getColor(R.color.textColor));
            spannableStringFive.setSpan(fcsBlueNew, index,index + blueWordsTwo[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        bottomTips2.setText(spannableStringFive);
    }

    private void setUpProgressBar() {
        ProgressBar progressbar = fragmentView.findViewById(R.id.progressbar1);
        double progress = ( (double) StatsInfoHolder.getTotal_cases() / GREECE_POP) * 100;
        progressbar.setProgress((int) Math.round(progress));
    }

}