/*
 * Sub Details
 *
 * February 5, 2018
 *
 * Copyright (c) 2018 Dylan Alcock, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *  You can find a copy of the license on this project, Otherwise please contact alcock@ualberta.ca
 */

package com.example.dylan.alcock_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Purpose:
 * Gets the details of the specified subscription.
 *
 * Design Rationale:
 * Made a more user friendly interface to show the details of a subscription rather than having to
 * go into the edit sub activity.
 *
 * @author Dylan
 * @version 1.5
 * @see Subscription
 */
public class SubDetails extends AppCompatActivity {

    /**
     * Purpose:
     * sets the view when the activity is started
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_details);

        String name = getIntent().getStringExtra("Name");
        String date = getIntent().getStringExtra("Date");
        Double mCharge = getIntent().getDoubleExtra("Monthly Charge", 0);
        String comment = getIntent().getStringExtra("Comment");

        //final int index = getIntent().getIntExtra("Index", -1);


        final TextView nameTxt = findViewById(R.id.nameTextDetails);
        final TextView dateTxt = findViewById(R.id.dateTextDetails);
        final TextView mChrgTxt = findViewById(R.id.monchrgTextDetails);
        final TextView commentTxt = findViewById(R.id.commentTextDetails);


        nameTxt.setText(name);
        dateTxt.setText(date);
        mChrgTxt.setText("$" + String.format("%.2f", mCharge));
        commentTxt.setText(comment);
    }
}
