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
import android.widget.TextView;

import com.example.covidapp.R;
import com.example.covidapp.adapters.CasesAdapter;
import com.example.covidapp.models.ModelCase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        setTextColors();
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

        TextView bottomTips = fragmentView.findViewById(R.id.bottomTips);
        String textOne = "If COVID-19 is spreading in your community, stay safe by taking some simple precautions, " +
                "such as physical distancing, wearing a mask, keeping rooms well ventilated, avoiding crowds, " +
                "cleaning your hands, and coughing into a bent elbow or tissue.";
        SpannableString spannableStringOne = new SpannableString(textOne);
        String[] blueWords = new String[] {"stay safe", "precautions", "distancing","wearing","mask", "ventilated", "avoiding crowds","cleaning",
        "hands","coughing", "elbow or tissue"};
        for(int i = 0; i < blueWords.length; i ++) {
            int index = textOne.indexOf(blueWords[i]);
            ForegroundColorSpan fcsBlueNew = new ForegroundColorSpan(getResources().getColor(R.color.textColor));
            spannableStringOne.setSpan(fcsBlueNew, index,index + blueWords[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        bottomTips.setText(spannableStringOne);

    }

}