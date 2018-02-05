/*
 * Edit Sub
 *
 * February 5, 2018
 *
 * Copyright (c) 2018 Dylan Alcock, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *  You can find a copy of the license on this project, Otherwise please contact alcock@ualberta.ca
 */

package com.example.dylan.alcock_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Purpose:
 * Activity that gets the user to edit a tweet in the sub list
 *
 * Design Rationale:
 * Having a separate activity where all the subscriptions could be edited was need as the main
 * activity did not have edit texts to be able to change any of the subscriptions values.
 *
 * @author Dylan
 * @version 1.5
 * @see Subscription
 */
public class EditSub extends AppCompatActivity {

    /**
     * Purpose:
     * Sets up the view when the edit sub activity is started
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sub);

        String name = getIntent().getStringExtra("Name");
        String date = getIntent().getStringExtra("Date");
        Double mCharge = getIntent().getDoubleExtra("Monthly Charge", 0);
        String comment = getIntent().getStringExtra("Comment");

        final int index = getIntent().getIntExtra("Index", -1);


        final EditText nameTxt = findViewById(R.id.nameTextDetails);
        final EditText dateTxt = findViewById(R.id.dateTextDetails);
        final EditText mChrgTxt = findViewById(R.id.monchrgTextDetails);
        final EditText commentTxt = findViewById(R.id.commentTextDetails);


        nameTxt.setText(name);
        dateTxt.setText(date);
        mChrgTxt.setText(Double.toString(mCharge));
        commentTxt.setText(comment);

        nameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (nameTxt.getText().length() > 20){
                    nameTxt.setError("Name must be 20 characters or less");
                }
            }
        });

        commentTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (commentTxt.getText().length() > 30){
                    commentTxt.setError("Comment must be 30 characters or less");
                }
            }
        });

        dateTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // regular expression from vinod
                // URL: https://stackoverflow.com/questions/22061723/regex-date-validation-for-yyyy-mm-dd
                if (dateTxt.getText().toString().matches("\\d{4}-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$") == false) {
                    dateTxt.setError("Date must be in format yyyy-mm-dd");
                }
            }
        });

        mChrgTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mChrgTxt.getText().toString().indexOf("-") != -1){
                    mChrgTxt.setError("Monthly charge cannot be negative");
                } else if (mChrgTxt.getText().toString().matches("\\d+.?\\d*") == false) {
                    mChrgTxt.setError("Monthly charge needs to be a positive number");
                }
            }
        });

        Button editSubBtn = findViewById(R.id.editBtn2);

        editSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nameTxt.getError() != null || nameTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Name has an error or is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(dateTxt.getError() != null || dateTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Date has an error or is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(mChrgTxt.getError() != null || mChrgTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Monthly Charge has an error or is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(commentTxt.getError() != null){
                    Toast.makeText(getApplicationContext(), "Comment has an error", Toast.LENGTH_LONG).show();
                    return;
                }

                String name = nameTxt.getText().toString();
                String date = dateTxt.getText().toString();
                double charge = Double.valueOf(mChrgTxt.getText().toString());
                String comment = commentTxt.getText().toString();

                Intent i = new Intent();

                i.putExtra("Name",name);
                i.putExtra("Date",date);
                i.putExtra("Monthly Charge",charge);
                i.putExtra("Comment",comment);
                i.putExtra("Index", index);

                setResult(RESULT_OK,i);

                nameTxt.setText("");
                dateTxt.setText("");
                mChrgTxt.setText("");
                commentTxt.setText("");

                finish();
            }

        });
    }
}
