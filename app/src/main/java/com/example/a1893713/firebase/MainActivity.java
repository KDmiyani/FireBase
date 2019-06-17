package com.example.a1893713.firebase;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransitionImpl;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txt_nm;
    ArrayList<Github> vals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_nm= findViewById(R.id.txt_nm);


        vals = new ArrayList<>();

        FirebaseApp.initializeApp(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //  String nm = dataSnapshot.child("0").child("name").getValue().toString();

                 // txt_nm.setText(nm);


                  Iterable<DataSnapshot>childs = dataSnapshot.getChildren();
                  for (DataSnapshot snap : childs){

                   //   System.out.println("Name :"+snap.child("name").getValue().toString());

                      Github git = snap.getValue(Github.class);
                      System.out.println(git.getId()+" "+ git.getFull_name()+" "+git.getWatchers());
                  }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
