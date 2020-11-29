package com.example.covidapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class activity_view_contact extends AppCompatActivity {
    private static String fullName, id, phone, residenceRegion, dateOfDisease, gender, susceptible, contactWith ;
    private static String[] closeContactWith, phonesOfCloseContact;
    private static int age;

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
        TextView contactWithTextView = findViewById(R.id.contactWithText);
        //TextView wholeTextView = findViewById(R.id.wholeText);

        fullNameTextView.setText(fullName);
        idTextView.setText(id);
        phoneTextView.setText(phone);
        residenceTextView.setText(residenceRegion);
        dateTextView.setText(dateOfDisease);
        genderTextView.setText(gender);
        ageTextView.setText(age);
        susceptibleTextView.setText(susceptible);
        contactWithTextView.setText(contactWith);
        //wholeTextView.setText(whole);

        String contactWith = "";
        for (int i = 0; i < closeContactWith.length; i++) {
           contactWith = contactWith + closeContactWith[i] + "," + phonesOfCloseContact[i] + "\n";
        }
        System.out.println(contactWith);
        
        //System.out.println(whole);
        //String whole = "";
        //for (int i = 0; i < closeContactWith.length; i++) {
        //    whole = whole + closeContactWith[i] + "," + phonesOfCloseContact[i] + "\n";
        //}

        //System.out.println(whole);
    }
    public static void setFullName(String fullName) {
        activity_view_contact.fullName = fullName;
    }

    public static void setId(String id) {
        activity_view_contact.id = id;
    }

    public static void setPhone(String phone) {
        activity_view_contact.phone = phone;
    }

    public static void setResidenceRegion(String residenceRegion) {
        activity_view_contact.residenceRegion = residenceRegion;
    }

    public static void setDateOfDisease(String dateOfDisease) {
        activity_view_contact.dateOfDisease = dateOfDisease;
    }

    public static void setGender(String gender) {
        activity_view_contact.gender = gender;
    }

    public static void setAge(int age) {
        activity_view_contact.age = age;
    }

    public static void setSusceptible(String susceptible) {
        activity_view_contact.susceptible = susceptible;
    }

    public static void setCloseContactWith(String[] closeContactWith) {
        activity_view_contact.closeContactWith = closeContactWith;
    }

    public static void setPhonesOfCloseContact(String[] phonesOfCloseContact) {
        activity_view_contact.phonesOfCloseContact = phonesOfCloseContact;
    }

    public static void setContactWith(String contactWith) {
        activity_view_contact.contactWith = contactWith;
    }

    //public static void setWhole(String whole){
    //    activity_view_contact.whole = whole;
    //}

}