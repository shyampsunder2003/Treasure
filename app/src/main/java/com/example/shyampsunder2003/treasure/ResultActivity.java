package com.example.shyampsunder2003.treasure;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Tharun on 2/4/2015.
 */
public class ResultActivity extends Activity {

    DatabaseHelp db;
    TextView result;
    String tvResult;
    LinkedList ll=new LinkedList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        result= (TextView) findViewById(R.id.tvResults);
        db= new DatabaseHelp(getApplicationContext());
        db.open();
        ll=db.getResults();
        for (int i = 0; i < ll.size(); i++) {
            tvResult= tvResult+'\n'+(ll.get(i));
        }
        result.setText(tvResult);
        db.close();
    }
}
