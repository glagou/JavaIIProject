package com.example.covidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.covidapp.R;
import com.example.covidapp.firebase.FirebaseFunctions;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddCaseActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String residenceRegion;
    private String dateOfDisease;
    private String id;
    private String age;
    private String isSusceptible;
    private String gender;
    private String[] people;
    private int peopleLength;
    private String[] phones;
    private boolean noContacts = false;
    private boolean hasError = false; // breaks the  code when there  is  an error in user imput
    private String phoneOfCloseContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);
        setAddButtonListener();
    }

    private void setAddButtonListener() {
        final Button button = findViewById(R.id.addButton);  //create button
        button.setOnClickListener(new View.OnClickListener() { //made the button usable
            @Override
            public void onClick(View v) { //calling each checking  method
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
                if (!hasError) { //when there isno error import contact to database
                    FirebaseFunctions.addVictimToFirestore
                            (firstName, lastName, phoneNumber, residenceRegion,
                                    dateOfDisease, people, phones, id, Integer.parseInt(age), gender,
                                    isSusceptible.equalsIgnoreCase("yes"));
                }
            }
        });
    }

    //Checking if name is empty
    private void handleFirstName() { //checks if the user input is correct
        if (!hasError) {
            TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
            String firstName = firstNameEditText.getText().toString();
            if (TextUtils.isEmpty(firstName.trim())) { //checks if user input is  empty
                makeToast("First Name Is Empty");
            } else {
                boolean containsDigit = false;
                for (int i = 0; i < firstName.trim().length(); i++) {
                    if (Character.isDigit(firstName.charAt(i))) { //checks if the name contains digits
                        containsDigit = true;
                        break;
                    }
                }
                if (!containsDigit) {
                    this.firstName = firstName.trim().toUpperCase();
                } else {
                    makeToast("Name contains number");
                }
            }
        }
    }

    private void handleLastName() {  //Checking if last name is empty
        if (!hasError) {
            TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
            String lastName = lastNameEditText.getText().toString();
            if (TextUtils.isEmpty(lastName.trim())) { //checks if last name is  empty
                makeToast("Last Name Is Empty");
            } else {
                boolean containsDigit = false;
                for (int i = 0; i < lastName.trim().length(); i++) {
                    if (Character.isDigit(lastName.charAt(i))) { //checks if last name contains  digit
                        containsDigit = true;
                        break;
                    }
                }
                if (!containsDigit) {
                    this.lastName = lastName.trim().toUpperCase();
                } else {
                    makeToast("Last name contains number");
                }
            }
        }
    }
    private void handlePhoneNumber() {
        if (!hasError) {
            TextInputEditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (TextUtils.isEmpty(phoneNumber.trim())) { //Checking if phone number is empty
                makeToast("Phone Number Is Empty");
            } else if (phoneNumber.trim().length() != 10) { //Checking if phone number contains 10 digitis
                makeToast("A valid phone number contains 10 digits");
            } else {
                try { //checking if the phone number contains letter
                    Integer.parseInt(phoneNumber.trim());
                    this.phoneNumber = phoneNumber.trim();
                } catch (NumberFormatException e) {
                    makeToast("Please fill a valid phone number");
                }
            }
        }
    }

    private void handleResidenceRegion() {
        if (!hasError) {
            TextInputEditText residenceRegionEditText = findViewById(R.id.residenceRegionEditText);
            String residenceRegion = residenceRegionEditText.getText().toString();
            if (TextUtils.isEmpty(residenceRegion.trim())) {  //Checking if residence is empty
                makeToast("ResidenceRegion Is Empty");
            } else {
                this.residenceRegion = residenceRegion.trim().toUpperCase();
            }
        }
    }

    private void handleDateOfDisease() {
        if (!hasError) {
            TextInputEditText dateOfDiseaseEditText = findViewById(R.id.dateOfDiseaseEditText);
            String dateOfDisease = dateOfDiseaseEditText.getText().toString();
            if (TextUtils.isEmpty(dateOfDisease.trim())) { //Checking if date is empty
                makeToast("Date of Disease Is Empty");
            } else {
                String[] parts = dateOfDisease.trim().split("/"); //splits date in the format
                try {                                                   // DD/MM/YYYY
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    int day = Integer.parseInt(part1);
                    int month = Integer.parseInt(part2);
                    int year = Integer.parseInt(part3);
                    if (day <= 0 || day >= 32) {//Checks if user has entered valid day
                        makeToast("insert valid day");
                    } else if (month >= 13 || month <= 0) {//Checks if user has entered valid month
                        makeToast("insert valid month");
                    } else if (year < 2019) { //Checks if user has entered valid year
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

    private void handleCloseContactWith() {
        if (!hasError) {
            TextInputEditText closeContactWithEditText =
                    findViewById(R.id.closeContactWithEditText);
            String closeContactWith = closeContactWithEditText.getText().toString();
            noContacts = false;
            if (TextUtils.isEmpty(closeContactWith.trim())) { //Checking if close contact with is empty
                makeToast("Close Contact With Is Empty");
            } else if (closeContactWith.trim().equals("-")) { //if empty there is no close contact
                this.people = null;
                noContacts = true;
            } else {
                String[] people = closeContactWith.trim().split(","); // splits close contacts
                this.peopleLength = people.length;                          //with ','
                boolean containsDigit = false;
                for (int i = 0; i < people.length; i++) {
                    String person = people[i];
                    for (int j = 0; j < person.trim().length(); j++) {
                        if (Character.isDigit(person.charAt(j))) {
                            containsDigit = true;
                            break;
                        }
                    }
                    if (containsDigit) { //checking which name of close contacts contains digit
                        makeToast((i + 1) + " Name  contains number");
                        break;
                    }
                }
                if (!containsDigit) {
                    this.people = people;
                }
            }
        }
    }

    private void handlephoneOfCloseContact() {
        if (!hasError) {
            TextInputEditText phoneOfCloseContactEditText =
                    findViewById(R.id.phoneOfCloseContactEditText);
            String phoneOfCloseContact = phoneOfCloseContactEditText.getText().toString();
            if (TextUtils.isEmpty(phoneOfCloseContact.trim())) {  //Checking if phone number of close contact is empty
                makeToast("Phone of Close Contact Is Empty");
            } else if (noContacts && phoneOfCloseContact.trim().equals("-")) {  //if user inputs '-' there is no  phone of close contact
                this.phoneOfCloseContact = null;
            } else {
                String[] peoplePhones = phoneOfCloseContact.trim().split(",");  // splits phone  of close contacts with ','
                if ((peoplePhones.length != this.peopleLength) || (noContacts &&
                        !phoneOfCloseContact.trim().equals("-"))) { //checks if phones  match people
                    makeToast("Phones don't match people");
                } else {
                    boolean phoneIsInvalid = false;
                    boolean phoneNoTenDigits = false;
                    for (int i = 0; i < peoplePhones.length; i++) {
                        String phone = peoplePhones[i];
                        try {
                            Integer.parseInt(phone.trim());
                        } catch (NumberFormatException e) {
                            makeToast((i + 1) + " Phone is invalid"); //checks if every phone contains letter
                            phoneIsInvalid = true;
                            break;
                        }
                        if (phone.length() != 10) {
                            makeToast((i + 1) + " phone does not contain 10 digits"); //checks if every phone contains  10 digits
                            phoneNoTenDigits = true;
                            break;
                        }
                    }
                    if (!phoneIsInvalid && !phoneNoTenDigits) {
                        this.phones = peoplePhones;
                    }
                }
            }
        }
    }

    //Checking if ID is empty and in correct format of 2 capital digits followed by 6 numbers
    private void handleID() {
        if (!hasError) {
            TextInputEditText idEditText = findViewById(R.id.idEditText);
            String id = idEditText.getText().toString();
            if (TextUtils.isEmpty(id.trim())) {
                makeToast("ID Is Empty");  //Checking if ID is empty
            } else if (id.trim().length() != 8) {
                makeToast("A valid ID contains 8 digits"); //checks if id  contains 8 digits
            } else {
                char[] characters = new char[8];
                for (int i = 0; i < characters.length; i++) {
                    characters[i] = id.charAt(i);
                }
                if (!Character.isLetter(characters[0])) {
                    makeToast("First character has to be a Letter"); //checks if  first character is  letter
                } else if (!Character.isLetter(characters[1])) {
                    makeToast("Second character has to be a Letter"); //checks if second character is  letter
                } else {
                    boolean foundError = false;
                    for (int i = 2; i < characters.length; i++) {
                        if (!Character.isDigit(characters[i])) {
                            makeToast(i + 1 + " character has to be a Number");  // checks if the  rest of the characters are numbers
                            foundError = true;
                            break;
                        }
                    }
                    if (!foundError) {
                        this.id = id.trim();
                    }
                }
            }
        }
    }

    private void handleAge() {
        if (!hasError) {
            TextInputEditText ageEditText = findViewById(R.id.ageEditText);
            String age = ageEditText.getText().toString();
            if (TextUtils.isEmpty(age.trim())) {
                makeToast("Age Is Empty"); //Checking if age is empty
            } else {
                try {
                    int i = Integer.parseInt(age.trim());
                    if (i <= 0) {
                        makeToast("Please insert a valid age"); //Checks if integer
                    } else {
                        this.age = age.trim();
                    }
                } catch (NumberFormatException e) {
                    makeToast("Please fill a valid age");  //checks if it contains letter
                }
            }
        }
    }

    private void handleGender() {
        if (!hasError) {
            TextInputEditText genderEditText = findViewById(R.id.genderEditText);
            String gender = genderEditText.getText().toString();
            if (TextUtils.isEmpty(gender.trim())) {
                makeToast("Gender Is Empty");  //Checking if gender is empty
            } else if (!gender.trim().equalsIgnoreCase("male") //Only  allows male and  female
                    && !gender.trim().equalsIgnoreCase("female")) {
                makeToast("Please insert male or female");
            } else {
                this.gender = gender.trim().toUpperCase();
            }
        }
    }

    private void handleIsSusceptible() {
        if (!hasError) {
            TextInputEditText isSusceptibleEditText = findViewById(R.id.isSusceptibleEditText);
            String isSusceptible = isSusceptibleEditText.getText().toString();
            if (TextUtils.isEmpty(isSusceptible.trim())) {
                makeToast("is Susceptible Is Empty");  //Checking if the answer is empty
            } else if (!isSusceptible.trim().equalsIgnoreCase("yes")
                    && !isSusceptible.trim().equalsIgnoreCase("no")) { //has to be yes or no
                makeToast("Please insert yes or no");
            } else {
                this.isSusceptible = isSusceptible.trim().toUpperCase();
            }
        }
    }

    //Stops the rest of the code when there is an error
    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        hasError = true;
    }
}