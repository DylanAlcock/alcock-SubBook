package com.example.dylan.alcock_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubDetails extends AppCompatActivity {

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
