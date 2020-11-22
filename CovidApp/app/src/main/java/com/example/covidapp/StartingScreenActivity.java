package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covidapp.firebase.FirebaseDatabaseReadListener;
import com.example.covidapp.firebase.FirebaseFunctions;
import com.example.covidapp.stats.StatsInfoHolder;

public class StartingScreenActivity extends AppCompatActivity {

    private static int REALTIME_DATABASE_VARIABLES = 23;

    private static int itemsGottenFromDatabase = 0;
    private static int itemsToGetFromDatabase = 0;
    private static Context context;

    private static boolean casesHaveBeenGotten = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        setContext();
        setAnimations();
        initializeItemsToDownloadAmount();
        getInfoFromDatabase();
    }

    private void setContext() {
        context = this;
    }

    private void setAnimations() {
        Animation fromTopAnim = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom_anim);

        ImageView doctorImage = findViewById(R.id.doctor);

        doctorImage.setAnimation(fromTopAnim);
    }
    
    public static void checkOnProgress() {
        itemsGottenFromDatabase++;
        if((itemsGottenFromDatabase >= itemsToGetFromDatabase) && casesHaveBeenGotten) {
            moveToNextActivity();
        } else if(itemsGottenFromDatabase == REALTIME_DATABASE_VARIABLES) {
            FirebaseFunctions.getAllVictimsFromFirestore();
        }
        Log.i("PROGRESS", itemsGottenFromDatabase + "/" + itemsToGetFromDatabase + "/" + casesHaveBeenGotten);
    }

    private void getInfoFromDatabase() {
        FirebaseFunctions.getDatabaseValue("TOTAL_CASES", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setTotal_cases(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_JANUARY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_january(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_FEBRUARY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_february(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_MARCH", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_march(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_APRIL", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_april(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_MAY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_may(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_JUNE", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_june(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_JULY", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_july(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_AUGUST", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_august(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_SEPTEMBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_september(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_OCTOBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_october(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_NOVEMBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_november(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_DECEMBER", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_december(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_MALES", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_males(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_FEMALES", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_females(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_0_24", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_0_24(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_25_34", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_25_34(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_35_44", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_35_44(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_45_54", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_45_54(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_55_64", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_55_64(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_65_74", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_65_74(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_75_84", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_75_84(valueRead);
                checkOnProgress();
            }
        });
        FirebaseFunctions.getDatabaseValue("CASES_85_PLUS", new FirebaseDatabaseReadListener() {
            @Override
            public void onFinish(int valueRead) {
                StatsInfoHolder.setCases_85_plus(valueRead);
                checkOnProgress();
            }
        });
    }

    private static void moveToNextActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void setCasesHaveBeenGotten(boolean casesHaveBeenGotten) {
        StartingScreenActivity.casesHaveBeenGotten = casesHaveBeenGotten;
    }

    public static void initializeItemsToDownloadAmount() {
        StartingScreenActivity.itemsToGetFromDatabase = REALTIME_DATABASE_VARIABLES;
    }

    public static void addVictimsCountToDownloadAmount(int victimsCounts) {
        StartingScreenActivity.itemsToGetFromDatabase += victimsCounts;
    }

}