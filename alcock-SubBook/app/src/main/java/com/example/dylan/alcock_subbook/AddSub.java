package com.example.dylan.alcock_subbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddSub extends AppCompatActivity {

    ArrayList<Subscription> subscriptionList;

    EditText nameTxt;
    EditText dateTxt;
    EditText mchrgTxt;
    EditText commentTxt;

    static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);


        //Bundle bundleObject = getIntent().getExtras();
        //subscriptionList = (ArrayList<Subscription>) bundleObject.getSerializable("Sub list");


        //settings = getSharedPreferences("sub list", MODE_PRIVATE);
        //final SharedPreferences.Editor editor = settings.edit();

        //subscriptionList = new ArrayList<Subscription>();
        //loadData();
        final EditText nameTxt = findViewById(R.id.nameText);
        final EditText dateTxt = findViewById(R.id.dateText);
        final EditText mchrgTxt = findViewById(R.id.monchrgText);
        final EditText commentTxt = findViewById(R.id.commentText);



        // subscriptionList.add(new Subscription(name, date, charge, comment));

        //ListView subListView = (ListView) findViewById(R.id.subList);

        //final ArrayAdapter<Subscription> aa;

        //aa = new ArrayAdapter<Subscription>(this,android.R.layout.simple_list_item_1,subscriptionList);
        //aa = new FancyAdapter();

        //subscriptionList.setAdapter(aa);

        //Button cancelBtn = findViewById(R.id.cancelBtn)
        //;
        //subListView.setAdapter(aa);

        Button addSubBtn = findViewById(R.id.addBtn);

        addSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Subscription newSub;

                String name = nameTxt.getText().toString();
                String date = dateTxt.getText().toString();
                double charge = Double.valueOf(mchrgTxt.getText().toString());
                String comment = commentTxt.getText().toString();

                if (comment.isEmpty()) {
                    newSub = new Subscription(name, date, charge, comment);
                } else {
                    newSub = new Subscription(name, date, charge);
                }

                //aa.notifyDataSetChanged();


                //saveData();
                //Intent intent = new Intent(this, MainActivity.class);
                //intent.putExtra("subList", subscriptionList);

                Intent i = new Intent();
                //Bundle bundle = new Bundle();
                //bundle.putSerializable("new sub", newSub);

                i.putExtra("Name",name);
                i.putExtra("Date",date);
                i.putExtra("Monthly Charge",charge);
                i.putExtra("Comment",comment);

                setResult(RESULT_OK,i);

                nameTxt.setText("");
                dateTxt.setText("");
                mchrgTxt.setText("");
                commentTxt.setText("");

                finish();
                //startActivityForResult(i,50);

            }

        });

    }
}
