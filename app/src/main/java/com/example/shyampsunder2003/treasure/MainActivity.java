package com.example.shyampsunder2003.treasure;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView clueStatus;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clueStatus=(TextView) findViewById(R.id.textView2);
        editPassword=(EditText) findViewById(R.id.editText);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "A2bfZu7LOncINvmg1TEfLBUZe9eZ0BjvedsuXq9e", "aFSDZ9JlxcbLVDZ4bj9N1Y8YrGdQ6VvOrHDX1zgR");


    }
    //Just a little change

    public boolean isOnline() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
    public void downloadClues(View view)
    {
        final DatabaseHelp db= new DatabaseHelp(this);
        if(isOnline()) {
            Log.d("score", "Start");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                    if (e == null) {
                        Log.d("score", "Retrieved " + parseObjects.size() + " scores");
                        db.open();

                        clueStatus.setText("Completed");
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                        Toast.makeText(getApplicationContext(), e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this,"Please check your Internet Connection",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
