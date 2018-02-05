package com.example.dylan.alcock_subbook;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    /** Called when the activity is first created. */
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




        //aa = new ArrayAdapter<Subscription>(this,R.layout.list_item, R.id.txtSub,subscriptionList);
        //subListView.setAdapter(aa);
        /*
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // show edit box
                showEditBox(subscriptionList.get(position),position);
                //Toast.makeText(getApplicationContext(), "Clicked name:"+view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
        */


        Button addSubBtn = (Button) findViewById(R.id.addSubBtn);
        addSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSub.class);
                startActivityForResult(intent,REQUEST_ADD_SUB);
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v , menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

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
                //Toast.makeText(getApplicationContext(), "Subscription NOT Edited", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void addScreen(View view) {
        Intent intent = new Intent(this, AddSub.class);
        //EditText editText = (EditText) findViewById(R.id.editText2);
        //String message = editText.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putSerializable("Sub list", subscriptionList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setTotalCharge(){
        totalCharge = 0;
        for(int i=0; i<subscriptionList.size(); i++){
            totalCharge += subscriptionList.get(i).getCharge();
        }
        totalTextView.setText("$" + String.format("%.2f", totalCharge));
    }


/*
    @Override
    protected void onResume() {
        super.onResume();
        subscriptionList = new ArrayList<Subscription>();
        loadData();
        adapter.notifyDataSetChanged();
        subListView.setAdapter(adapter);
        setTotalCharge();
    }
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

    @Override
    protected void onPause(){
        super.onPause();
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(subscriptionList);
        editor.putString("sub list", json);
        editor.apply();
    }

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
