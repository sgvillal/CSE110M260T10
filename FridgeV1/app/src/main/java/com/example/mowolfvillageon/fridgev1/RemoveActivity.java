package com.example.mowolfvillageon.fridgev1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RemoveActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        final String loginPassword = extras.getString("loginPassword");

        final Firebase myFirebaseRef = new Firebase("https://fridge-it2.firebaseio.com/" + loginPassword);
        final ArrayList<String> foodNames = new ArrayList<>();

        //Get ListView object from XML
        listView = (ListView)findViewById(R.id.listViewFridgeRemove);

        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String foodName = (String) child.child("name").getValue();
                    foodNames.add(foodName);
                }
                //Define a new Adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        RemoveActivity.this,
                        R.layout.custom_textview,
                        foodNames); //items to add to list

                listView.setAdapter(adapter);
                Collections.sort(foodNames);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3)
                    {
                        //Obtain string representation of food item to remove
                        String value = (String)adapter.getItemAtPosition(position);

                        Intent ir = new Intent(getApplicationContext(), removeConfirmActivity.class);
                        ir.putExtra("item", value);
                        ir.putExtra("loginPassword", loginPassword);
                        startActivity(ir);
                    }
                });


            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }

        });

    }

    // Button for canceling the removal of an Item
    public void onCancelRemoveItemButtonClick(View v){
        Button button = (Button) v;

        Bundle extras = getIntent().getExtras();
        final String loginPassword = extras.getString("loginPassword");

        Intent irc = new Intent(getApplicationContext(), Main2Activity.class);
        irc.putExtra("loginPassword", loginPassword);
        irc.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(irc);
    }

}
