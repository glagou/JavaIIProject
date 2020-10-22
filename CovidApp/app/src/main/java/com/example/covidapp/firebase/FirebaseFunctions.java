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
import com.google.firebase.firestore.DocumentReference;
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
                                            final String[] phonesOfCloseContact, final String id, final boolean isSusceptible) {
        if(firebaseFirestore == null) {
            firebaseFirestore = FirebaseFirestore.getInstance();
        }

        getFirestoreDocumentExists("Victims", id, new FirebaseDocumentExistanceGetterListener() {
            @Override
            public void onFinish(boolean exists) {
                if(!exists) {
                    Map<String, Object> victim = new HashMap<>();
                    victim.put("First Name", firstName);
                    victim.put("Last Name", lastName);
                    victim.put("Phone", phone);
                    victim.put("Residence Region", residenceRegion);
                    victim.put("Date Of Disease", dateOfDisease);
                    int contactsLength = closeContactWith.length;
                    for(int i = 0; i < contactsLength; i ++) {
                        victim.put("Person" + i, closeContactWith[i]);
                    }
                    for(int i = 0; i < contactsLength; i++) {
                        victim.put("Person Phone" + i, phonesOfCloseContact[i]);
                    }
                    victim.put("Is Susceptible", isSusceptible);
                    victim.put("Contacts Length" , contactsLength);

                    firebaseFirestore.collection("Victims")
                            .document(id)
                            .set(victim)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(DEBUG_TAG, "Victim added successfully");
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

}
