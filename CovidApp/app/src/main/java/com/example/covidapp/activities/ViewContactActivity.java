package com.example.covidapp.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.covidapp.R;

public class ViewContactActivity extends AppCompatActivity {
    private static String fullName, id, phone, residenceRegion, age, dateOfDisease, gender, susceptible, closeContactWith ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        handleTextViews();
    }
    
    private void handleTextViews() {
        TextView fullNameTextView = findViewById(R.id.fullNameText);
        TextView idTextView = findViewById(R.id.idText);
        TextView phoneTextView = findViewById(R.id.phoneText);
        TextView residenceTextView = findViewById(R.id.residenceText);
        TextView dateTextView = findViewById(R.id.dateText);
        TextView genderTextView = findViewById(R.id.genderText);
        TextView ageTextView = findViewById(R.id.ageText);
        TextView susceptibleTextView = findViewById(R.id.susceptibleText);
        TextView closeContactWithTextView = findViewById(R.id.contactWithText);


        fullNameTextView.setText(fullName);
        idTextView.setText(id);
        phoneTextView.setText(phone);
        residenceTextView.setText(residenceRegion);
        dateTextView.setText(dateOfDisease);
        genderTextView.setText(gender);
        ageTextView.setText(age);
        susceptibleTextView.setText(susceptible);
        closeContactWithTextView.setText(closeContactWith);

    }
    //assign parameter to the variable fullName
    public static void setFullName(String fullName) {
        ViewContactActivity.fullName = fullName;
    }
    //assign parameter to the variable id
    public static void setId(String id) {
        ViewContactActivity.id = id;
    }
    //assign parameter to the variable phone
    public static void setPhone(String phone) {
        ViewContactActivity.phone = phone;
    }
    //assign parameter to the variable residenceRegion
    public static void setResidenceRegion(String residenceRegion) {
        ViewContactActivity.residenceRegion = residenceRegion;
    }
    //assign parameter to the variable dateOfDisease
    public static void setDateOfDisease(String dateOfDisease) {
        ViewContactActivity.dateOfDisease = dateOfDisease;
    }
    //assign parameter to the variable gender
    public static void setGender(String gender) {
        ViewContactActivity.gender = gender;
    }
    //assign parameter to the variable age
    public static void setAge(String age) {
        ViewContactActivity.age = age;
    }
    //assign parameter to the variable susceptible
    public static void setSusceptible(String susceptible) {
        ViewContactActivity.susceptible = susceptible;
    }
    //assign parameter to the variable closeContactWith
    public static void setCloseContactWith(String closeContactWith) {
        ViewContactActivity.closeContactWith = closeContactWith;
    }
}