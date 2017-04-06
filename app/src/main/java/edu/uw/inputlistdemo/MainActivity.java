package edu.uw.inputlistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button; // This is the line I was looking for!
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        //model
        String[] data = new String[99];
        for(int i = 99; i > 0; i--) {
            data[99-i] = i + " bottles beer on wall";
        }

        //view
        //See list_item.xml

        //controller
        // Bheavioral hooking up. Made list_layout.xml to hold all listitems
        ListView listView = (ListView) findViewById(R.id.listView);

        // Adapter connects data and view. It turns strings into views for example.
        // Specifically the array adapter

        // Constructor takes a context. Which is basically something that your program
        // is currently running over. Could think of it as the current environment.
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem,
            data);

        listView.setAdapter(adapter);

        Button butt = (Button) findViewById(R.id.btnSearch);
//        butt.setOnClickListener(new View.OnClickListener()  {
//            @Override
//            public void onClick(View v) {
//                Log.v(TAG, "You clicked me");
//            }
//        });
    }

    // Will be called whenever the button is clicked. As defined with the onClick attribute
    // in the button xml in input_control.layout.xml
    // The parameter passed in is the view for that button I think
    // Many different event handlers
    public void handleButtSearch(View v) {
        Log.v(TAG, "yo kyle");

        EditText searchQuery = (EditText) findViewById(R.id.txtSearch);
        String text = searchQuery.getText().toString();

        Log.v(TAG, "Searched for " + text);
    }

}
