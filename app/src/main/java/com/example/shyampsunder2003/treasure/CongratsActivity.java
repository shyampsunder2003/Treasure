package com.example.shyampsunder2003.treasure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tharun on 2/4/2015.
 */
public class CongratsActivity extends Activity {

    EditText resultField;
    TextView congrats,regist;
    CardView card;
    String resultKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congrats);
        resultField= (EditText) findViewById(R.id.etResults);
        congrats= (TextView) findViewById(R.id.tvCongrats);
        regist= (TextView) findViewById(R.id.tvRegist);

        Typeface font = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Bold.ttf");
        congrats.setTypeface(font);
        congrats.setText("Congrats!");
        regist.setTypeface(font);
        regist.setText("\nYou've Completed DIGIHUNT 2015.\n" +
                "\n" +
                "Report to the registration desk");


    }
    public void checkResults(View v){
        resultKey=resultField.getText().toString();
        if(resultKey.equals("e6078b9b1aac915d11b9fd59791030bf")){ //Change it to MD5
            startActivity(new Intent(this,ResultActivity.class));
        }
        else{
            Toast.makeText(this,"Please go to the registration desk to know the results", Toast.LENGTH_SHORT).show();
        }
    }
}
