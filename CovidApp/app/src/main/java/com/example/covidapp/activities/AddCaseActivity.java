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

    // Variables that take the user input
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
    private String[] phones;
    private String phoneOfCloseContact;
    private int peopleLength;
    // Variable for counting close contacts
    private boolean noContacts = false;
    // Breaks the  code when there  is  an error in user input
    private boolean hasError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);
        //Makes the button clickable
        setAddButtonListener();
    }

    private void setAddButtonListener() {
        // Create button
        final Button button = findViewById(R.id.addButton);
        // Calling each checking  method
        button.setOnClickListener(new View.OnClickListener() { //made the button usable
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
                // When there is no error imports contact to database
                if (!hasError) {
                    // Inserts data in data base
                    FirebaseFunctions.addVictimToFirestore
                            (firstName, lastName, phoneNumber, residenceRegion,
                                    dateOfDisease, people, phones, id, Integer.parseInt(age), gender,
                                    isSusceptible.equalsIgnoreCase("yes"));
                }
            }
        });
    }

    // Checking if name has error
    private void handleFirstName() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
            String firstName = firstNameEditText.getText().toString();
            // Checking if name is empty
            if (TextUtils.isEmpty(firstName.trim())) {
                makeToast("First Name Is Empty");
            } else {
                boolean containsDigit = false;
                for (int i = 0; i < firstName.trim().length(); i++) {
                    // Checks if the name contains digits
                    if (Character.isDigit(firstName.charAt(i))) {
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

    // Checking if last name has error
    private void handleLastName() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
            String lastName = lastNameEditText.getText().toString();
            // Checks if last name is  empty
            if (TextUtils.isEmpty(lastName.trim())) {
                makeToast("Last Name Is Empty");
            } else {
                boolean containsDigit = false;
                for (int i = 0; i < lastName.trim().length(); i++) {
                    // Checks if last name contains  digit
                    if (Character.isDigit(lastName.charAt(i))) {
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

    // Checking if phone number has error
    private void handlePhoneNumber() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
            String phoneNumber = phoneNumberEditText.getText().toString();
            // Checking if phone number is empty
            if (TextUtils.isEmpty(phoneNumber.trim())) {
                makeToast("Phone Number Is Empty");
                // Checking if phone number contains 10 digits
            } else if (phoneNumber.trim().length() != 10) {
                makeToast("A valid phone number contains 10 digits");
            } else {
                // Checking if the phone number contains letter
                char[] chars = phoneNumber.trim().toCharArray();
                boolean noLetters = true;
                for (int i = 0 ; i < chars.length ; i++) {
                    if(Character.isLetter(chars[i])) {
                        noLetters = false;
                        break;
                    }
                }
                if(noLetters) {
                    this.phoneNumber = phoneNumber.trim();
                } else {
                    makeToast("Please fill a valid phone number");
                }
            }
        }
    }

    // Checking if Residence has error
    private void handleResidenceRegion() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText residenceRegionEditText = findViewById(R.id.residenceRegionEditText);
            String residenceRegion = residenceRegionEditText.getText().toString();
            // Checking if residence is empty
            if (TextUtils.isEmpty(residenceRegion.trim())) {
                makeToast("ResidenceRegion Is Empty");
            } else {
                this.residenceRegion = residenceRegion.trim().toUpperCase();
            }
        }
    }

    // Checks if date of disease has error
    private void handleDateOfDisease() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText dateOfDiseaseEditText = findViewById(R.id.dateOfDiseaseEditText);
            String dateOfDisease = dateOfDiseaseEditText.getText().toString();
            // Checking if date is empty
            if (TextUtils.isEmpty(dateOfDisease.trim())) {
                makeToast("Date of Disease Is Empty");
            } else {
                // Splits date in the format  DD/MM/YYYY
                String[] parts = dateOfDisease.trim().split("/");
                try {
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    int day = Integer.parseInt(part1);
                    int month = Integer.parseInt(part2);
                    int year = Integer.parseInt(part3);
                    // Checks if user has entered valid day
                    if (day <= 0 || day >= 32) {
                        makeToast("insert valid day");
                        // Checks if user has entered valid month
                    } else if (month >= 13 || month <= 0) {
                        makeToast("insert valid month");
                        // Checks if user has entered valid year
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

    // Checks if close contacts have error
    private void handleCloseContactWith() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText closeContactWithEditText =
                    findViewById(R.id.closeContactWithEditText);
            String closeContactWith = closeContactWithEditText.getText().toString();
            noContacts = false;
            // Checking if close contact  is empty
            if (TextUtils.isEmpty(closeContactWith.trim())) {
                makeToast("Close Contact With Is Empty");
                // If empty there is no close contact
            } else if (closeContactWith.trim().equals("-")) {
                this.people = null;
                noContacts = true;
            } else {
                // Splits close contacts with ','
                String[] people = closeContactWith.trim().split(",");
                this.peopleLength = people.length;
                boolean containsDigit = false;
                for (int i = 0; i < people.length; i++) {
                    String person = people[i];
                    for (int j = 0; j < person.trim().length(); j++) {
                        if (Character.isDigit(person.charAt(j))) {
                            containsDigit = true;
                            break;
                        }
                    }
                    // Checking which name of close contacts contains digit
                    if (containsDigit) {
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

    // Checks if phones of close contacts have error
    private void handlephoneOfCloseContact() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText phoneOfCloseContactEditText =
                    findViewById(R.id.phoneOfCloseContactEditText);
            String phoneOfCloseContact = phoneOfCloseContactEditText.getText().toString();
            // Checking if phone number of close contact is empty
            if (TextUtils.isEmpty(phoneOfCloseContact.trim())) {
                makeToast("Phone of Close Contact Is Empty");
                // If user inputs '-' there is no  phone of close contact
            } else if (noContacts && phoneOfCloseContact.trim().equals("-")) {
                this.phoneOfCloseContact = null;
            } else {
                // Splits phone  of close contacts with ','
                String[] peoplePhones = phoneOfCloseContact.trim().split(",");
                if ((peoplePhones.length != this.peopleLength) || (noContacts &&
                        //Checks if phones  match people
                        !phoneOfCloseContact.trim().equals("-"))) {
                    makeToast("Phones don't match people");
                } else {
                    boolean phoneIsInvalid = false;
                    boolean phoneNoTenDigits = false;
                    for (int i = 0; i < peoplePhones.length; i++) {
                        String phone = peoplePhones[i];
                        try {
                            Integer.parseInt(phone.trim());
                        } catch (NumberFormatException e) {
                            //Checks if every phone is valid
                            makeToast((i + 1) + " Phone is invalid");
                            phoneIsInvalid = true;
                            break;
                        }
                        // Checks if every phone contains  10 digits
                        if (phone.length() != 10) {
                            makeToast((i + 1) + " phone does not contain 10 digits");
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

    // Checking if ID is empty and in correct format of 2 capital letters followed by 6 numbers
    private void handleID() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText idEditText = findViewById(R.id.idEditText);
            String id = idEditText.getText().toString();
            // Checking if ID is empty
            if (TextUtils.isEmpty(id.trim())) {
                makeToast("ID Is Empty");
                //Checks if id  contains 8 digits
            } else if (id.trim().length() != 8) {
                makeToast("A valid ID contains 8 digits");
            } else {
                char[] characters = new char[8];
                for (int i = 0; i < characters.length; i++) {
                    characters[i] = id.charAt(i);
                }
                // Checks if  first character is  letter
                if (!Character.isLetter(characters[0])) {
                    makeToast("First character has to be a Letter");
                    // Checks if second character is  letter
                } else if (!Character.isLetter(characters[1])) {
                    makeToast("Second character has to be a Letter");
                } else {
                    boolean foundError = false;
                    // Checks if the  rest of the characters are numbers
                    for (int i = 2; i < characters.length; i++) {
                        if (!Character.isDigit(characters[i])) {
                            makeToast(i + 1 + " character has to be a Number");
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

    // Checks if age has error
    private void handleAge() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText ageEditText = findViewById(R.id.ageEditText);
            String age = ageEditText.getText().toString();
            // Checking if age is empty
            if (TextUtils.isEmpty(age.trim())) {
                makeToast("Age Is Empty");
            } else {
                try {
                    // Checks if integer
                    int i = Integer.parseInt(age.trim());
                    // Checks if age is a positive number
                    if (i <= 0) {
                        makeToast("Please insert a valid age");
                    } else {
                        this.age = age.trim();
                    }
                    // Checks if it contains letter
                } catch (NumberFormatException e) {
                    makeToast("Please fill a valid age");
                }
            }
        }
    }

    // Checks if gender has error
    private void handleGender() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText genderEditText = findViewById(R.id.genderEditText);
            String gender = genderEditText.getText().toString();
            // Checking if gender is empty
            if (TextUtils.isEmpty(gender.trim())) {
                makeToast("Gender Is Empty");
                // Only allows male or female
            } else if (!gender.trim().equalsIgnoreCase("male")
                    && !gender.trim().equalsIgnoreCase("female")) {
                makeToast("Please insert male or female");
            } else {
                this.gender = gender.trim().toUpperCase();
            }
        }
    }

    // Checks if is susceptible has error
    private void handleIsSusceptible() {
        // If there is an error code stops
        if (!hasError) {
            TextInputEditText isSusceptibleEditText = findViewById(R.id.isSusceptibleEditText);
            String isSusceptible = isSusceptibleEditText.getText().toString();
            // Checking if the answer is empty
            if (TextUtils.isEmpty(isSusceptible.trim())) {
                makeToast("is Susceptible Is Empty");
                // Has to be yes or no
            } else if (!isSusceptible.trim().equalsIgnoreCase("yes")
                    && !isSusceptible.trim().equalsIgnoreCase("no")) {
                makeToast("Please insert yes or no");
            } else {
                this.isSusceptible = isSusceptible.trim().toUpperCase();
            }
        }
    }

    // Stops the rest of the code when there is an error
    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        hasError = true;
    }
}