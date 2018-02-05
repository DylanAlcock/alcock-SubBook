package com.example.dylan.alcock_subbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditSub extends AppCompatActivity {

    ArrayList<Subscription> subscriptionList;

    EditText nameTxt;
    EditText dateTxt;
    EditText mChrgTxt;
    EditText commentTxt;
    //int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sub);


        //Bundle bundleObject = getIntent().getExtras();
        //subscriptionList = (ArrayList<Subscription>) bundleObject.getSerializable("Sub list");

        String name = getIntent().getStringExtra("Name");
        String date = getIntent().getStringExtra("Date");
        Double mCharge = getIntent().getDoubleExtra("Monthly Charge", 0);
        String comment = getIntent().getStringExtra("Comment");

        final int index = getIntent().getIntExtra("Index", -1);


        final EditText nameTxt = findViewById(R.id.nameText2);
        final EditText dateTxt = findViewById(R.id.dateText2);
        final EditText mChrgTxt = findViewById(R.id.monchrgText2);
        final EditText commentTxt = findViewById(R.id.commentText2);


        nameTxt.setText(name);
        dateTxt.setText(date);
        mChrgTxt.setText(Double.toString(mCharge));
        commentTxt.setText(comment);


        // subscriptionList.add(new Subscription(name, date, charge, comment));

        //ListView subListView = (ListView) findViewById(R.id.subList);

        //final ArrayAdapter<Subscription> aa;

        //aa = new ArrayAdapter<Subscription>(this,android.R.layout.simple_list_item_1,subscriptionList);
        //aa = new FancyAdapter();

        //subscriptionList.setAdapter(aa);

        //Button cancelBtn = findViewById(R.id.cancelBtn)
        //;
        //subListView.setAdapter(aa);

        Button editSubBtn = findViewById(R.id.editBtn2);

        editSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameTxt.getText().toString();
                String date = dateTxt.getText().toString();
                double charge = Double.valueOf(mChrgTxt.getText().toString());
                String comment = commentTxt.getText().toString();

                //if (comment.isEmpty()) {
                //    subscriptionList.add(new Subscription(name, date, charge, comment));
                //} else {
                //    subscriptionList.add(new Subscription(name, date, charge));
                //}

                //aa.notifyDataSetChanged();
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

                //Toast.makeText(getApplicationContext(), "Subscription Edited", Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(this, MainActivity.class);
                //intent.putExtra("subList", subscriptionList);

                finish();
            }

        });

    }
}
