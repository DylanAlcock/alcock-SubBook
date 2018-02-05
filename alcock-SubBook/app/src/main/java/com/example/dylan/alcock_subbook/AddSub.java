



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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddSub extends AppCompatActivity {

    ArrayList<Subscription> subscriptionList;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    EditText nameTxt;
    EditText dateTxt;
    EditText mchrgTxt;
    EditText commentTxt;

    static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        dateFormat.setLenient(false);


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

        mchrgTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mchrgTxt.getText().toString().indexOf("-") != -1){
                    mchrgTxt.setError("Monthly charge cannot be negative");
                } else if (mchrgTxt.getText().toString().matches("\\d+.?\\d*") == false) {
                    mchrgTxt.setError("Monthly charge needs to be a positive number");
                }
            }
        });



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

                if(nameTxt.getError() != null || nameTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Name has an error or is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(dateTxt.getError() != null || dateTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Date has an error or is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(mchrgTxt.getError() != null || mchrgTxt.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Monthly Charge has an error or is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(commentTxt.getError() != null){
                    Toast.makeText(getApplicationContext(), "Comment has an error", Toast.LENGTH_LONG).show();
                    return;
                }

                Subscription newSub;

                String name = nameTxt.getText().toString();
                String date = dateTxt.getText().toString();
                double charge = Double.valueOf(mchrgTxt.getText().toString());
                String comment = commentTxt.getText().toString();



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
