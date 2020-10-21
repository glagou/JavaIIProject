package com.example.covidapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseFunctions {

    //The tag used for debugging;
    private static final String DEBUG_TAG = "DATABASE UPDATE";

    //Firebase Database Instance
    public static FirebaseDatabase firebaseDatabase;

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
    public static void incrementDatabaseValueByOne(final String databaseFieldName, final boolean increase) {
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
                        } else {
                            reference.setValue(value - 1);
                        }

                    } else
                    {
                        Log.d(DEBUG_TAG, "Field " + databaseFieldName + " Does Not Exist");
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


}
