package com.example.shyampsunder2003.treasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tharun on 2/4/2015.
 */
public class CongratsActivity extends Activity {

    EditText resultField;
    String resultKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congrats);
        resultField= (EditText) findViewById(R.id.etResults);


    }
    public void checkResults(View v){
        resultKey=resultField.getText().toString();
        if(resultKey.equals("results")){ //Change it to MD5
            startActivity(new Intent(this,ResultActivity.class));
        }
        else{
            Toast.makeText(this,"Please go to the registration desk to know the results"+resultKey, Toast.LENGTH_SHORT).show();
        }
    }
}
