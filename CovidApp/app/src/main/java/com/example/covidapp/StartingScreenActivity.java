package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covidapp.firebase.FirebaseDatabaseReadListener;
import com.example.covidapp.firebase.FirebaseFunctions;
import com.example.covidapp.stats.StatsInfoHolder;

public class StartingScreenActivity extends AppCompatActivity {

    private static int itemsGottenFromDatabase = 0;
    private static int itemsToGetFromDatabase = 0;
    private static ProgressBar progressBar;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        setContext();
        setAnimations();
        getProgressBar();
        FirebaseFunctions.getAllVictimsFromFirestore();
        getInfoFromDatabase();
    }

    private void setContext() {
        context = this;
    }

    private void setAnimations() {
        Animation fromTopAnim = AnimationUtils.loadAnimation(this, R.anim.slide_from_top_anim);

        ImageView doctorImage = findViewById(R.id.doctor);
        TextView covidAppText = findViewById(R.id.covid_app_textView);

        covidAppText.setAnimation(fromTopAnim);
        doctorImage.setAnimation(fromTopAnim);
    }

    private void getProgressBar() {
        progressBar = findViewById(R.id.progress_bar);
    }
    
    public static void setProgress() {
        itemsGottenFromDatabase++;
        int progress = 0;

        if(itemsToGetFromDatabase != 0) {
            progress = (int) (((double) itemsGottenFromDatabase / itemsToGetFromDatabase) * 100);
            progressBar.setProgress(progress);
        }

        if(progress == 100 && itemsToGetFromDatabase != 0) {
            moveToNextActivity();
        }
    }

    private void getInfoFromDatabase() {
        FirebaseFunctions.getDatabaseValue("TOTAL_CASES", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setTotal_cases(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_JANUARY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_january(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_FEBRUARY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_february(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_MARCH", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_march(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_APRIL", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_april(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_MAY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_may(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_JUNE", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_june(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_JULY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_july(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_AUGUST", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_august(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_SEPTEMBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_september(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_OCTOBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_october(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_NOVEMBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_november(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_DECEMBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_december(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_MALES", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_males(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_FEMALES", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_females(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_0_24", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_0_24(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_25_34", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_25_34(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_35_44", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_35_44(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_45_54", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_45_54(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_55_64", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_55_64(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_65_74", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_65_74(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_75_84", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_75_84(valueRead);
                setProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_85_PLUS", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_85_plus(valueRead);
                setProgress();
            }
        });
    }

    private static void moveToNextActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void initializeItemsToDownloadAmountIfNoVictims() {
        StartingScreenActivity.itemsToGetFromDatabase = 23;
    }

    public static void addVictimsCountToDownloadAmount(int victimsCounts) {
        StartingScreenActivity.itemsToGetFromDatabase += victimsCounts;
    }
}