/*
 * Main Activity
 *
 * February 5, 2018
 *
 * Copyright (c) 2018 Dylan Alcock, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *  You can find a copy of the license on this project, Otherwise please contact alcock@ualberta.ca
 */

package com.example.dylan.alcock_subbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Purpose:
 * Shows the list of subscriptions and the total charge, user can add new sub or long click
 * an existing subscription in the list to edit, delete, or see the details.
 *
 * Design Rationale:
 * Main focus of the app is the list of subscriptions so made a clear list of these subscriptions
 * with their own format and had the lists bring up a context menu when long clicked with the
 * ability to edit,delete, or get the details of the that subscription making all these functions
 * very compact and easily accessible. With that the only other things needed was a total of the
 * charges and an add subscription button which where put underneath the list of subscriptions.
 *
 * @author Dylan
 * @version 1.5
 * @see SubDetails
 * @see AddSub
 * @see EditSub
 */
public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    public static final String EXTRA_MESSAGE = "com.example.dylan.assignment0.SUBLIST";

    private static final int REQUEST_ADD_SUB = 50;
    private static final int REQUEST_EDIT_SUB = 51;

    ListView subListView;
    TextView totalTextView;
    ArrayList<Subscription> subscriptionList;
    SubscriptionListAdapter adapter;
    private double totalCharge;
    Subscription newSub;

    /**
     * Purpose:
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subListView = (ListView) findViewById(R.id.subList);
        totalTextView = (TextView) findViewById(R.id.totalCharge);

        subscriptionList = new ArrayList<Subscription>();
        loadData();
        //subscriptionList.add(new Subscription("dylan", "2018-02-02", 20, "fuck"));
        //subscriptionList.add(new Subscription("kat", "2012-02-02", 1, "cats"));
        //subscriptionList.add(new Subscription("cory", "2018-01-02", 2, "dogs"));
        //subscriptionList.add(new Subscription("Netflix", "2013-12-11", 8.99, "basic"));

        adapter = new SubscriptionListAdapter(getApplicationContext(), subscriptionList);
        subListView.setAdapter(adapter);

        registerForContextMenu(subListView);

        setTotalCharge();

        Button addSubBtn = (Button) findViewById(R.id.addSubBtn);
        addSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSub.class);
                startActivityForResult(intent,REQUEST_ADD_SUB);
            }
        });

    }

    /**
     * Purpose:
     * Sets up the menu when a long click is performed on a sub in the list view
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v , menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }


    /**
     * Purpose:
     * Does a specified action when a menu item is clicked
     *
     * @param item items in the menu
     * @return boolean if function worked properly
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // Find which item was clicked on
        switch (item.getItemId()){
            case R.id.deleteMenu:
                subscriptionList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveData();
                setTotalCharge();
                return true;

            case R.id.editMenu:
                Intent i = new Intent(MainActivity.this, EditSub.class);

                i.putExtra("Name",subscriptionList.get(info.position).getName());
                i.putExtra("Date",subscriptionList.get(info.position).getDate());
                i.putExtra("Monthly Charge",subscriptionList.get(info.position).getCharge());
                i.putExtra("Comment",subscriptionList.get(info.position).getComment());
                i.putExtra("Index",info.position);

                startActivityForResult(i,REQUEST_EDIT_SUB);

                return true;

            case R.id.detailsMenu:
                Intent dIntent = new Intent(MainActivity.this, SubDetails.class);

                dIntent.putExtra("Name",subscriptionList.get(info.position).getName());
                dIntent.putExtra("Date",subscriptionList.get(info.position).getDate());
                dIntent.putExtra("Monthly Charge",subscriptionList.get(info.position).getCharge());
                dIntent.putExtra("Comment",subscriptionList.get(info.position).getComment());
                startActivity(dIntent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Purpose:
     * Sets the total charge to the sum of all the subscriptions in the list.
     */
    private void setTotalCharge(){
        totalCharge = 0;
        for(int i=0; i<subscriptionList.size(); i++){
            totalCharge += subscriptionList.get(i).getCharge();
        }
        totalTextView.setText("$" + String.format("%.2f", totalCharge));
    }


    /**
     * Purpose:
     * When an activity sends back a result do the specified action of that activity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_ADD_SUB) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Subscription Added", Toast.LENGTH_LONG).show();

                String name = data.getStringExtra("Name");
                String date = data.getStringExtra("Date");
                Double mCharge = data.getDoubleExtra("Monthly Charge", 0);
                String comment = data.getStringExtra("Comment");

                subscriptionList.add(new Subscription(name, date, mCharge, comment));
                adapter.notifyDataSetChanged();
                setTotalCharge();

                saveData();
            } else {
                Toast.makeText(getApplicationContext(), "Subscription NOT Added", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == REQUEST_EDIT_SUB) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Subscription Edited", Toast.LENGTH_LONG).show();

                String name = data.getStringExtra("Name");
                String date = data.getStringExtra("Date");
                Double mCharge = data.getDoubleExtra("Monthly Charge", 0);
                String comment = data.getStringExtra("Comment");
                Integer index  = data.getIntExtra("Index", -1);

                subscriptionList.set(index, new Subscription(name, date, mCharge, comment));
                adapter.notifyDataSetChanged();
                setTotalCharge();

                saveData();
            } else {
                Toast.makeText(getApplicationContext(), "Subscription NOT Edited", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Purpose:
     * Whenever the main activity is not shown it save the data in the subscription list
     */
    @Override
    protected void onPause(){
        super.onPause();
        saveData();
    }

    /**
     * Purpose:
     * Saves the data in the subscription list to shared preferences, so the data is not lost
     * when the app is closed or another activity starts.
     */
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(subscriptionList);
        editor.putString("sub list", json);
        editor.apply();
    }

    /**
     * Loads the data from shared preferences so you can get the same subscription list that was
     * previously on the app when it was closed.
     */
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("sub list", null);
        Type type = new TypeToken<ArrayList<Subscription>>(){}.getType();
        subscriptionList = gson.fromJson(json, type);

        if (subscriptionList == null){
            subscriptionList = new ArrayList<Subscription>();
        }
    }
}
