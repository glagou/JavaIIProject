package com.example.covidapp.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseFunctions {

    //The tag used for debugging;
    private static final String DEBUG_TAG = "DATABASE UPDATE";

    //Firebase Database Instance
    private static FirebaseDatabase firebaseDatabase;

    //Firebase Firestore Instance
    private static FirebaseFirestore firebaseFirestore;

    //Used in getDatabaseValue(String fieldName)
    private static int valueReadFromDatabase;

    //Returns the Firebase Database Instance
    public static FirebaseDatabase getFirebaseDatabaseInstance() {
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        return  firebaseDatabase;
    }

    //Returns the Firebase Firestore Instance
    public static FirebaseFirestore getFirestoreInstance() {
        final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        return firebaseFirestore;
    }

    //Increases or Subtracts the value with name - databaseFieldName - in the database by one, according to the increase boolean (True to increase / False to decrease).
    public static void modifyDatabaseValueByOne(final String databaseFieldName, final boolean increase) {
        if(firebaseDatabase == null)
        {
            firebaseDatabase = getFirebaseDatabaseInstance();
        }

        try {
            final DatabaseReference reference = firebaseDatabase.getReference(databaseFieldName);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int  value = 0;
                    if(snapshot.exists())
                    {
                        try {
                            value = snapshot.getValue(Integer.class);
                        } catch (NullPointerException e)
                        {
                            Log.w(DEBUG_TAG, "Failed To Get Value" + e.getMessage());
                        }

                        Log.d(DEBUG_TAG, "Value Read Is: " + value);

                        if(increase) {
                            reference.setValue(value + 1);
                            Log.d(DEBUG_TAG, "Value Set To: " + (value + 1));
                        } else {
                            reference.setValue(value - 1);
                            Log.d(DEBUG_TAG, "Value Set To: " + (value - 1));
                        }

                    } else
                    {
                        if(increase) {
                            reference.setValue(1);
                            Log.d(DEBUG_TAG, "Field " + databaseFieldName + " Does Not Exist" + ". Value set to 1");
                        } else
                        {
                            Log.d(DEBUG_TAG, "Field " + databaseFieldName + " Does Not Exist");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(DEBUG_TAG, "Failed to read value: " + error.toException());
                }
            });
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Returns the value with name - databaseFieldName - in the database.
    public static void getDatabaseValue(final String databaseFieldName, final FirebaseDatabaseReadListener firebaseDatabaseReadListener) {
        valueReadFromDatabase = 0;
        if(firebaseDatabase == null)
        {
            firebaseDatabase = getFirebaseDatabaseInstance();
        }
        try {
            final DatabaseReference reference = firebaseDatabase.getReference(databaseFieldName);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int readValue = 0;
                    if(snapshot.exists())
                    {
                        try {
                            readValue = snapshot.getValue(Integer.class);
                        } catch (NullPointerException e)
                        {
                            Log.w(DEBUG_TAG, "Failed To Get Value" + e.getMessage());
                        }

                        Log.d(DEBUG_TAG, "Value Read Is: " + readValue);

                        valueReadFromDatabase = readValue;
                    } else
                    {
                        Log.d(DEBUG_TAG, "Field " + databaseFieldName + " Does Not Exist");
                    }
                    firebaseDatabaseReadListener.onFinish(valueReadFromDatabase);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    firebaseDatabaseReadListener.onFinish(valueReadFromDatabase);
                    Log.w(DEBUG_TAG, "Failed to read value: " + error.toException());
                }
            });
        } catch (NullPointerException e)
        {
            firebaseDatabaseReadListener.onFinish(valueReadFromDatabase);
            e.printStackTrace();
        }
    }

    //Adds a new victim to FireStore using the parameters below.
    public static void addVictimToFirestore(final String firstName, final String lastName, final String phone, final String residenceRegion, final String dateOfDisease, final String[] closeContactWith,
                                            final String[] phonesOfCloseContact, final String id, final int age, final String gender, final boolean isSusceptible) {
        if(firebaseFirestore == null) {
            firebaseFirestore = FirebaseFirestore.getInstance();
        }

        getFirestoreDocumentExists("Cases", id, new FirebaseDocumentExistanceGetterListener() {
            @Override
            public void onFinish(boolean exists) {
                if(!exists) {
                    Map<String, Object> victim = new HashMap<>();
                    victim.put("First Name", firstName);
                    victim.put("Last Name", lastName);
                    victim.put("Phone", phone);
                    victim.put("Residence Region", residenceRegion);
                    victim.put("Date Of Disease", dateOfDisease);
                    victim.put("Age", age);
                    victim.put("Gender", gender);
                    int contactsLength = closeContactWith.length;
                    for(int i = 0; i < contactsLength; i ++) {
                        victim.put("Person" + i, closeContactWith[i]);
                    }
                    for(int i = 0; i < contactsLength; i++) {
                        victim.put("Person Phone" + i, phonesOfCloseContact[i]);
                    }
                    victim.put("Is Susceptible", isSusceptible);
                    victim.put("Contacts Length" , contactsLength);

                    firebaseFirestore.collection("Cases")
                            .document(id)
                            .set(victim)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    modifyAgeGroupValuesInDatabase(age, true);
                                    modifyDatabaseValueByOne("TOTAL_CASES", true);
                                    modifyGenderValuesInDatabase(gender, true);
                                    modifyMonthGroupValuesInDatabase(dateOfDisease, true);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(DEBUG_TAG, "Victim was NOT added successfully: " + e.getMessage());
                                }
                            });
                } else {
                    Log.d(DEBUG_TAG, "Document with this ID already exists.");
                }
            }
        });

    }

    //Returns true if the firestore document exists. In the other case returns false
    private static void getFirestoreDocumentExists(String collection, String documentName, final FirebaseDocumentExistanceGetterListener firebaseDocumentExistanceGetterListener) {
        firebaseFirestore.collection(collection)
                .document(documentName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()) {
                            firebaseDocumentExistanceGetterListener.onFinish(true);
                        } else
                        {
                            firebaseDocumentExistanceGetterListener.onFinish(false);
                        }
                    }
                });
    }

    //Modify the database variables based on age
    private static void modifyAgeGroupValuesInDatabase(int age, boolean increase) {
        if(age >= 0 && age <= 24) {
            modifyDatabaseValueByOne("CASES_0_24",increase);
        } else if(age <= 34) {
            modifyDatabaseValueByOne("CASES_25_34", increase);
        } else if(age <= 44) {
            modifyDatabaseValueByOne("CASES_35_44", increase);
        } else if(age <= 54) {
            modifyDatabaseValueByOne("CASES_45_54", increase);
        } else if(age <= 64) {
            modifyDatabaseValueByOne("CASES_55_64",increase);
        } else if (age <= 74) {
            modifyDatabaseValueByOne("CASES_65_74", increase);
        } else if(age <= 84) {
            modifyDatabaseValueByOne("CASES_75_84" ,increase);
        } else {
            modifyDatabaseValueByOne("CASES_85_PLUS",increase);
        }
    }

    //Modify the database variables based on gender
    private static void modifyGenderValuesInDatabase(String gender, boolean increase) {
        switch (gender.toUpperCase()) {
            case "MALE":
                modifyDatabaseValueByOne("CASES_MALES", increase);
                break;
            case "FEMALE":
                modifyDatabaseValueByOne("CASES_FEMALES", increase);
                break;
        }
    }

    //Modify the database variables based on month
    private static void modifyMonthGroupValuesInDatabase(String dateOfDisease, boolean increase) {
        try {
            String month = dateOfDisease.split("/")[1];
            switch (month) {
                case "01":
                    modifyDatabaseValueByOne("CASES_JANUARY", increase);
                    break;
                case "02":
                    modifyDatabaseValueByOne("CASES_FEBRUARY", increase);
                    break;
                case "03":
                    modifyDatabaseValueByOne("CASES_MARCH", increase);
                    break;
                case "04":
                    modifyDatabaseValueByOne("CASES_APRIL", increase);
                    break;
                case "05":
                    modifyDatabaseValueByOne("CASES_MAY", increase);
                    break;
                case "06":
                    modifyDatabaseValueByOne("CASES_JUNE", increase);
                    break;
                case "07":
                    modifyDatabaseValueByOne("CASES_JULY", increase);
                    break;
                case "08":
                    modifyDatabaseValueByOne("CASES_AUGUST", increase);
                    break;
                case "09":
                    modifyDatabaseValueByOne("CASES_SEPTEMBER", increase);
                    break;
                case "10":
                    modifyDatabaseValueByOne("CASES_OCTOBER", increase);
                    break;
                case "11":
                    modifyDatabaseValueByOne("CASES_NOVEMBER", increase);
                    break;
                case "12":
                    modifyDatabaseValueByOne("CASES_DECEMBER", increase);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
