package edu.uw.inputlistdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button; // This is the line I was looking for!
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        //model
//        String[] data = new String[99];
//        for(int i = 99; i > 0; i--) {
//            data[99-i] = i + " bottles beer on wall";
//        }

        ArrayList<String> data = new ArrayList<String>();



        //view
        //See list_item.xml

        //controller
        // Bheavioral hooking up. Made list_layout.xml to hold all listitems
        ListView listView = (ListView) findViewById(R.id.listView);

        // Adapter connects data and view. It turns strings into views for example.
        // Specifically the array adapter

        // Constructor takes a context. Which is basically something that your program
        // is currently running over. Could think of it as the current environment.
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtItem,
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
        EditText searchQuery = (EditText) findViewById(R.id.txtSearch);
        String textSrch = searchQuery.getText().toString();

        Log.v(TAG, "Searched for " + textSrch);

        // THis is our AsyncTask
        MovieDownloadTask myTask = new MovieDownloadTask();
        myTask.execute(textSrch); // begin thread in background. Equivalent to .start()
    }

    // AA+SyncTask takes in three generics. The input, the progres, and the output (return type)
    public class MovieDownloadTask extends AsyncTask<String, Void, String[]> {
        @Override
        // Takes in a list of parameters passed into execute
        protected String[] doInBackground(String... params) {

            String[] results = MovieDownloader.downloadMovieData(params[0]);
            return results;
        }

        // Gets return from te doInbackground method
        @Override
        protected void onPostExecute(String[] movies) {
            super.onPostExecute(movies);

            if(movies != null) {
                adapter.clear();
                for(String movie : movies) {
                    adapter.add(movie);
                }
            }
        }
    }

}
