package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.covidapp.firebase.FirebaseFunctions;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddCaseActivity extends AppCompatActivity {

    private boolean hasError = false;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String residenceRegion;
    private String dateOfDisease;
    private String id;
    private String age;
    private String isSusceptible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);
        setAddButtonListener();
    }

    private void setAddButtonListener() {
        final Button button = findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasError = false;
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
    //Checking if name is empty
    private void handleFirstName() {
        if (!hasError) {
            TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
            String firstName = firstNameEditText.getText().toString();
            if (TextUtils.isEmpty(firstName.trim())) {
                makeToast("First Name Is Empty");
            } else {
                this.firstName = firstName.trim().toUpperCase();
            }
        }
    }
    //Checking if last name is empty
    private void handleLastName() {

        if (!hasError) {
            TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
            String lastName = lastNameEditText.getText().toString();
            if (TextUtils.isEmpty(lastName.trim())) {
                makeToast("Last Name Is Empty");
            } else {
                this.lastName = lastName.trim().toUpperCase();
            }
        }

    }
    //Checking if phone number is empty and if it is a number and has 10 digits
    private void handlePhoneNumber() {
        if (!hasError) {
            TextInputEditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (TextUtils.isEmpty(phoneNumber.trim())) {
                makeToast("Phone Number Is Empty");
            } else if (phoneNumber.trim().length() != 10) {
                makeToast("A valid phone number contains 10 digits");
            } else {
                try {
                    Integer.parseInt(phoneNumber.trim());
                    this.phoneNumber = phoneNumber.trim();
                } catch (NumberFormatException e) {
                    makeToast("Please fill a valid phone number");
                }
            }
        }
    }
    //Checking if residence is empty
    private void handleResidenceRegion() {
        if (!hasError) {
            TextInputEditText residenceRegionEditText = findViewById(R.id.residenceRegionEditText);
            String residenceRegion = residenceRegionEditText.getText().toString();
            if (TextUtils.isEmpty(residenceRegion.trim())) {
                makeToast("ResidenceRegion Is Empty");
            } else {
                this.residenceRegion = residenceRegion.trim().toUpperCase();
            }
        }
    }
    //Checking if date is empty and in correct format of DD/MM/YYYY
    private void handleDateOfDisease() {
        if (!hasError) {
            TextInputEditText dateOfDiseaseEditText = findViewById(R.id.dateOfDiseaseEditText);
            String dateOfDisease = dateOfDiseaseEditText.getText().toString();
            if (TextUtils.isEmpty(dateOfDisease.trim())) {
                makeToast("Date of Disease Is Empty");
            } else {
                String[] parts = dateOfDisease.trim().split("/");
                try {
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    int day = Integer.parseInt(part1);
                    int month = Integer.parseInt(part2);
                    int year = Integer.parseInt(part3);
                    if (day <= 0 || day >= 32) {
                        makeToast("insert valid day");
                    } else if (month >= 13 || month <= 0) {
                        makeToast("insert valid month");
                    } else if (year < 2019) {
                        makeToast("insert valid year");
                    } else {
                        this.dateOfDisease = dateOfDisease.trim();
                    }
                } catch (Exception e) {
                    makeToast("Use format DD/MM/YYYY");
                }
            }
        }
    }
    //Checking if close contact with is empty and
    private void handleCloseContactWith() {
        if (!hasError) {
            TextInputEditText closeContactWithEditText = findViewById(R.id.closeContactWithEditText);
            String closeContactWith = closeContactWithEditText.getText().toString();
            if (TextUtils.isEmpty(closeContactWith.trim())) {
                makeToast("Close Contact With Is Empty");
            } else {
                String[] parts = dateOfDisease.trim().split(",");
            }
        }
    }
    //Checking if phone number of close contact is empty and
    private void handlephoneOfCloseContact() {
        if (!hasError) {
            TextInputEditText phoneOfCloseContactEditText = findViewById(R.id.phoneOfCloseContactEditText);
            String phoneOfCloseContact = phoneOfCloseContactEditText.getText().toString();
            if (TextUtils.isEmpty(phoneOfCloseContact.trim())) {
                makeToast("Phone of Close Contact Is Empty");
            }
        }
    }
    //Checking if ID is empty and in correct format of 2 capital digits followed by 6 numbers
    private void handleID() {
        if (!hasError) {
            TextInputEditText idEditText = findViewById(R.id.idEditText);
            String id = idEditText.getText().toString();
            if (TextUtils.isEmpty(id.trim())) {
                makeToast("ID Is Empty");
            } else if (id.trim().length() != 8) {
                makeToast("A valid ID contains 8 digits");
            } else {
                char[] characters = new char[8];
                for (int i = 0; i < characters.length; i++) {
                    characters[i] = id.charAt(i);
                }
                if (!Character.isLetter(characters[0])) {
                    makeToast("First character has to be a Letter");
                } else if (!Character.isLetter(characters[1])) {
                    makeToast("Second character has to be a Letter");
                } else {
                    boolean foundError = false;
                    for (int i = 2; i < characters.length; i++) {
                        if (!Character.isDigit(characters[i])) {
                            makeToast(i + 1 + " character has to be a Number");
                            foundError = true;
                            break;
                        }
                    }
                    if (!foundError) {
                        this.id = id;
                    }
                }
            }
        }
    }
    //Checking if age is empty and a number
    private void handleAge() {
        if (!hasError) {
            TextInputEditText ageEditText = findViewById(R.id.ageEditText);
            String age = ageEditText.getText().toString();
            if (TextUtils.isEmpty(age.trim())) {
                makeToast("Age Is Empty");
            } else {
                try {
                    Integer.parseInt(age.trim());
                    this.age = age.trim();
                } catch (NumberFormatException e) {
                    makeToast("Please fill a valid age");
                }
            }
        }
    }
    //Checking if gender is empty and has to be either male or female
    private void handleGender() {
        if (!hasError) {
            TextInputEditText genderEditText = findViewById(R.id.genderEditText);
            String gender = genderEditText.getText().toString();
            if (TextUtils.isEmpty(gender.trim())) {
                makeToast("Gender Is Empty");
            } else if (!Objects.equals(gender, new String("male")) && !Objects.equals(gender, new String("female"))) {
                makeToast("Please insert male or female");
            }
        }
    }
    //Checking if the answer is empty and has to be yes or no
    private void handleIsSusceptible() {
        if (!hasError) {
            TextInputEditText isSusceptibleEditText = findViewById(R.id.isSusceptibleEditText);
            String isSusceptible = isSusceptibleEditText.getText().toString();
            if (TextUtils.isEmpty(isSusceptible.trim())) {
                makeToast("is Susceptible Is Empty");
            } else if (!Objects.equals(isSusceptible, new String("yes")) && !Objects.equals(isSusceptible, new String("no"))) {
                makeToast("Please insert yes or no");
            }
        }
    }
    //Stops the rest of the code when there is an error
    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        hasError = true;
    }
}