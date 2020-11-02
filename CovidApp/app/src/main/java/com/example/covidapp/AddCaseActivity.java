package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.covidapp.firebase.FirebaseFunctions;
import com.google.android.material.textfield.TextInputEditText;

public class AddCaseActivity extends AppCompatActivity {

    private boolean hasError = false ;
    private String firstName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);
        setAddButtonListener();
    }
    private void setAddButtonListener(){
        final Button button = findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasError=false;
                handleFirstName();
                handleLastName();
                handlePhoneNumber();
                handleResidenceRegion();
                handleDateOfDisease();
                handleCloseContactWith();
                handlephoneOfCloseContact();
                handleID();
                handleAge();
                handleGender();
                handleIsSusceptible();
            }
        });
    }
    private void handleFirstName() {

        if(!hasError) {
            TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
            String firstName = firstNameEditText.getText().toString();
            if(TextUtils.isEmpty(firstName.trim())){
                makeToast("First Name Is Empty");
            }else {
                this.firstName=firstName;
                
            }
        }
    }
    private void handleLastName() {
        if(!hasError) {
            TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
            String lastName = lastNameEditText.getText().toString();
            if (TextUtils.isEmpty(lastName.trim())) {
                makeToast("Last Name Is Empty");
            }
        }

    }
    private void handlePhoneNumber() {
        if(!hasError) {
            TextInputEditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (TextUtils.isEmpty(phoneNumber.trim())) {
                makeToast("Phone Number Is Empty");
            }
        }

    }
    private void handleResidenceRegion() {
        if(!hasError) {
            TextInputEditText residenceRegionEditText = findViewById(R.id.residenceRegionEditText);
            String residenceRegion = residenceRegionEditText.getText().toString();
            if (TextUtils.isEmpty(residenceRegion.trim())) {
                makeToast("ResidenceRegion Is Empty");
            }
        }

    }
    private void handleDateOfDisease() {
        if(!hasError) {
            TextInputEditText dateOfDiseaseEditText = findViewById(R.id.dateOfDiseaseEditText);
            String dateOfDisease = dateOfDiseaseEditText.getText().toString();
            if (TextUtils.isEmpty(dateOfDisease.trim())) {
                makeToast("ResidenceRegion Is Empty");
            }
        }

    }
    private void handleCloseContactWith() {
        if(!hasError) {
            TextInputEditText closeContactWithEditText = findViewById(R.id.closeContactWithEditText);
            String closeContactWith = closeContactWithEditText.getText().toString();
            if (TextUtils.isEmpty(closeContactWith.trim())) {
                makeToast("Close Contact With Is Empty");
            }
        }

    }
    private void handlephoneOfCloseContact() {
        if(!hasError) {
            TextInputEditText phoneOfCloseContactEditText = findViewById(R.id.phoneOfCloseContactEditText);
            String phoneOfCloseContact = phoneOfCloseContactEditText.getText().toString();
            if (TextUtils.isEmpty(phoneOfCloseContact.trim())) {
                makeToast("Phone of Close Contact Is Empty");
            }
        }

    }
    private void handleID() {
        if(!hasError) {
            TextInputEditText idEditText = findViewById(R.id.idEditText);
            String id = idEditText.getText().toString();
            if (TextUtils.isEmpty(id.trim())) {
                makeToast("ID Is Empty");
            }
        }

    }
    private void handleAge() {
        if(!hasError) {
            TextInputEditText ageEditText = findViewById(R.id.ageEditText);
            String age = ageEditText.getText().toString();
            if (TextUtils.isEmpty(age.trim())) {
                makeToast("Age Is Empty");
            }
        }

    }
    private void handleGender() {
        if(!hasError) {
            TextInputEditText genderEditText = findViewById(R.id.genderEditText);
            String gender = genderEditText.getText().toString();
            if (TextUtils.isEmpty(gender.trim())) {
                makeToast("Gender Is Empty");
            }
        }
    }
    private void handleIsSusceptible() {
        if(!hasError) {
            TextInputEditText isSusceptibleEditText = findViewById(R.id.isSusceptibleEditText);
            String isSusceptible = isSusceptibleEditText.getText().toString();
            if (TextUtils.isEmpty(isSusceptible.trim())) {
                makeToast("isSusceptible Is Empty");
            }
        }
    }
    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        hasError = true;
    }
}
