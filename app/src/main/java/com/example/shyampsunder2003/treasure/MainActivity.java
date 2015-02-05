package com.example.shyampsunder2003.treasure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import com.parse.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView clueStatus, welcome;
    EditText editPassword,cryptPassword;
    SharedPreferences sharedpreferences;
    Intent intentToLocate;

    //Location Variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences=getSharedPreferences("firstpref",MODE_PRIVATE);
        intentToLocate = new Intent(this, Locate.class);
        if(sharedpreferences.contains("first"))
        {
            Log.d("Shared","Second launch detected");
            startActivity(intentToLocate);
        }
        else {
            setContentView(R.layout.main);
            clueStatus = (TextView) findViewById(R.id.tvClues);
            welcome= (TextView) findViewById(R.id.tvWelcome);
            editPassword = (EditText) findViewById(R.id.etPass);
            cryptPassword= (EditText) findViewById(R.id.editText);
            Typeface font = Typeface.createFromAsset(getAssets(), "RobotoCondensed-Bold.ttf");
            welcome.setTypeface(font);
            welcome.setText("DIGIHUNT 2015");
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "A2bfZu7LOncINvmg1TEfLBUZe9eZ0BjvedsuXq9e", "aFSDZ9JlxcbLVDZ4bj9N1Y8YrGdQ6VvOrHDX1zgR");
        }

    }
    //Just a little change

    public void gameStart(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException {     //On clicking the start button
        DatabaseHelp db= new DatabaseHelp(getApplicationContext());
        String password=editPassword.getText().toString();
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(password.getBytes(Charset.forName("UTF8")));
        final byte[] resultByte = messageDigest.digest();
        final String result = new String(Hex.encodeHex(resultByte));
        db.open();
        if(result.compareTo("1a1dc91c907325c69271ddf0c944bc72")==0&&clueStatus.getText().toString().compareTo("Completed")==0)   //The MD5 of 'pass'
        {
            SharedPreferences.Editor editor=sharedpreferences.edit();
            editor.putInt("first",1);

            editor.commit();
            db.close();
            startActivity(intentToLocate);
        }
        else if(clueStatus.getText().toString().compareTo("Completed")!=0)
        {
            Toast.makeText(getApplicationContext(), "Download the clues before proceeding",Toast.LENGTH_LONG).show();
        }
        else if (result.compareTo("35d3b77a72b7ddb6aff67e381f402a37")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump1
        {
            db.createResult("Clue 1","Success","Override");

            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("1fe90703693781b5943e0adc9c159fbe")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump2
        {
            db.createResult("Clue 2","Success","Override");
            //db.createResult("Override","Success","Override");

            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("92e796e6a40378c59b6cc79a053b3ba9")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump3
        {
            db.createResult("Clue 3","Success","Override");
            //db.createResult("Override","Success","Override");
            //db.createResult("Override","Success","Override");

            db.close();
            startActivity(intentToLocate);

        }
        else if (result.compareTo("b0568542e541ffca2973b1a602ad7687")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump4
        {
            db.createResult("Clue 4","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("fa7f742ff35b412262d2ff887aacb6f2")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump5
        {
            db.createResult("Clue 5","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("c1d8d6150fe78af43df3a745ace29431")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump6
        {
            db.createResult("Clue 6","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("f874229e8e8cb7069d1c07cdb75992f9")==0&&clueStatus.getText().toString().compareTo("Completed")==0) //jump7
        {

            db.createResult("Clue 7","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("53e61336bb49ec978968786b07dea50b")==0) //results
        {
            db.createResult("Admin","Results","Override");

            /*db.createResult("Override","Results","Override");
            db.createResult("Override","Results","Override");
            db.createResult("Override","Results","Override");
            db.createResult("Override","Results","Override");
            db.createResult("Override","Results","Override");
            db.createResult("Override","Results","Override");
            db.createResult("Override","Results","Override");*/

            db.close();
            startActivity(new Intent(this,ResultActivity.class));
        }
        else if(result.compareTo("1a1dc91c907325c69271ddf0c944bc72")!=0)
        {
            Toast.makeText(getApplicationContext(), "Incorrect Password",Toast.LENGTH_LONG).show();
        }
        db.close();


    }
    public boolean isOnline() {                         //To check if the app has internet connectivity

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void downloadClues(View view) throws Exception                //Downloads clues from the cloud
    {
        final String password=cryptPassword.getText().toString();
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(password.getBytes(Charset.forName("UTF8")));
        final byte[] resultByte = messageDigest.digest();
        final String result = new String(Hex.encodeHex(resultByte));
        if(result=="28bc8c78881b2f89bbeab4f9bb8fbeda") {                //password='clue'
            final DatabaseHelp db = new DatabaseHelp(getApplicationContext());
            if (isOnline()) {
                //Log.d("score", "Start");
                final SimpleCrypto s = new SimpleCrypto();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("ClueTest");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                        if (e == null) {
                            Log.d("Download", "Retrieved " + parseObjects.size() + " scores");
                            db.open();
                            db.delete();                    //To be removed on deployment
                            int i, j;
                            for (i = 0; i < parseObjects.size(); ++i) {
                                for (j = 0; j < parseObjects.size(); ++j) {
                                    int val = parseObjects.get(j).getInt("Number");
                                    if (val == i + 1)
                                        break;
                                }
                                String lat = null;
                                String longitude = null;
                                try {
                                    lat = s.decrypt(password, parseObjects.get(j).getString("Latitude"));
                                    longitude = s.decrypt(password,parseObjects.get(j).getString("Longitude"));
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                Log.d("Download", lat + " " + longitude);
                                db.createEntry(lat, longitude);
                            }
                            db.close();


                            clueStatus.setText("Completed");
                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please check your Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "Enter correct clue password", Toast.LENGTH_LONG).show();
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
