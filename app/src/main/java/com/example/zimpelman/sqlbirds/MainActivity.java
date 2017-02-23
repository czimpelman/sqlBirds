package com.example.zimpelman.sqlbirds;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper db = new dbHelper(this);
        //db.createTable();
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");

        db.addBird(new Bird("Woodpecker", "It makes noise"));
        db.addBird(new Bird("Blue Jay", "It's blue"));
        db.addBird(new Bird("Cardinal", "It's Red"));
        db.addBird(new Bird("Crow", "It's black"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Bird> birds = db.getAllBirds();

        for (Bird bd : birds) {
            String log = "Id: "+bd.getId()+" ,Name: " + bd.getName() + " ,Phone: " + bd.getDescription();
            db.deleteBird(bd);
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}
