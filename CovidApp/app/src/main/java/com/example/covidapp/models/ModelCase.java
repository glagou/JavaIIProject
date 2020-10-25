package com.example.covidapp.models;

public class ModelCase {

    private String firstName, lastName, phone, residenceRegion, dateOfDisease, id, gender;
    private String[] closeContactWith, phonesOfCloseContact;
    private int age;
    private boolean isSusceptible;

    public ModelCase(String firstName, String lastName, String phone, String residenceRegion, String dateOfDisease, String id,
                     String gender, String[] closeContactWith, String[] phonesOfCloseContact, int age, boolean isSusceptible) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.residenceRegion = residenceRegion;
        this.dateOfDisease = dateOfDisease;
        this.id = id;
        this.gender = gender;
        this.closeContactWith = closeContactWith;
        this.phonesOfCloseContact = phonesOfCloseContact;
        this.age = age;
        this.isSusceptible = isSusceptible;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getResidenceRegion() {
        return residenceRegion;
    }

    public String getDateOfDisease() {
        return dateOfDisease;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String[] getCloseContactWith() {
        return closeContactWith;
    }

    public String[] getPhonesOfCloseContact() {
        return phonesOfCloseContact;
    }

    public int getAge() {
        return age;
    }

    public boolean isSusceptible() {
        return isSusceptible;
    }
}
