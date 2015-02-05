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
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import se.simbio.encryption.Encryption;

import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.decrypt;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.generateKeyFromPassword;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.generateSalt;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.keyString;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.decryptString;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.encrypt;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.generateKey;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.keys;
import static com.example.shyampsunder2003.treasure.AesCbcWithIntegrity.saltString;

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
        Encryption encryption = new Encryption();
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
        if(result.compareTo("b0a965e296b905b763b11494571759d2")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
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
        else if (result.compareTo("35d3b77a72b7ddb6aff67e381f402a37")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {
            db.createResult("Override","Success","Override");

            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("1fe90703693781b5943e0adc9c159fbe")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");

            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("92e796e6a40378c59b6cc79a053b3ba9")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");

            db.close();
            startActivity(intentToLocate);

        }
        else if (result.compareTo("b0568542e541ffca2973b1a602ad7687")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("fa7f742ff35b412262d2ff887aacb6f2")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("c1d8d6150fe78af43df3a745ace29431")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");


            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("f874229e8e8cb7069d1c07cdb75992f9")==0&&clueStatus.getText().toString().compareTo("Completed")==0)
        {

            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");
            db.createResult("Override","Success","Override");



            db.close();
            startActivity(intentToLocate);
        }
        else if (result.compareTo("e6078b9b1aac915d11b9fd59791030bf")==0)
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
        final String password = editPassword.getText().toString();
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(password.getBytes(Charset.forName("UTF8")));
        final byte[] resultByte = messageDigest.digest();
        final String result = new String(Hex.encodeHex(resultByte));
        if (result.compareTo("ecae13117d6f0584c25a9da6c8f8415e") == 0) {

            final Encryption encryption = new Encryption();
            final String key = editPassword.getText().toString();
            Log.d("Clue pass", "Entered correctly");
            final DatabaseHelp db = new DatabaseHelp(getApplicationContext());
            if (isOnline()) {
                //Log.d("score", "Start");
//                final SimpleCrypto s = new SimpleCrypto();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("ClueObject");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(final List<ParseObject> parseObjects, com.parse.ParseException e) {
                        if (e == null) {
                            Log.d("Download", "Retrieved " + parseObjects.size() + " scores");
                            clueStatus.setText("Download Complete, Decryption running");
                            // db.delete();                    //To be removed on deployment
                            int i, j;
                            final LinkedList la = new LinkedList();
                            final LinkedList lo = new LinkedList();
                            final LinkedList no = new LinkedList();
                            Thread t[] = new Thread[parseObjects.size()];
                            for (i = 0; i < parseObjects.size(); ++i) {
                                final int finalI = i;
                                t[i] = new Thread(new Runnable() {
                                    public void run() {
                                        ParseObject temp = parseObjects.get(finalI);
                                        String tempLat = temp.getString("Latitude");
                                        String tempLongitude = temp.getString("Longitude");
                                        la.addFirst(encryption.decrypt(key, tempLat));
                                        lo.addFirst(encryption.decrypt(key, tempLongitude));
                                        no.addFirst(temp.getInt("Number"));
                                    }
                                });
                                t[i].start();

                            }
                            for (i = 0; i < parseObjects.size(); ++i) {
                                try {
                                    t[i].join();
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            Log.d("Values", la.toString());
                            Log.d("Values", lo.toString());
                            Log.d("Values", no.toString());
                            db.open();
                            for (i = 0; i < parseObjects.size(); ++i) {
                                int k = no.indexOf(i + 1);
                                db.createEntry((String) la.get(k), (String) lo.get(k));
                            }
                            db.close();

//                            for (i = 0; i < parseObjects.size(); ++i) {
//                                for (j = 0; j < parseObjects.size(); ++j) {
//                                    int val = parseObjects.get(j).getInt("Number");
//                                    if (val == i + 1)
//                                        break;
//                                }
//                                final int finalJ = j;
//                                new Thread(new Runnable() {
//                                    public void run() {
//                                        String lat = encryption.decrypt(key, parseObjects.get(finalJ).getString("Latitude"));
//                                        String longitude = encryption.decrypt(key, parseObjects.get(finalJ).getString("Longitude"));
//                                        db.open();
//                                        db.createEntry(lat,longitude);
//                                        db.close();
//                                        Log.d("Decryption",lat+" "+longitude);
//                                    }
//                                }).start();
//
//
////                                Log.d("Clue pass",parseObjects.get(j).getString("Latitude"));
//
////                                        String lat = encryption.decrypt(key, parseObjects.get(j).getString("Latitude"));
////                                        String longitude = encryption.decrypt(key, parseObjects.get(j).getString("Longitude"));
////                                try {
////                                    lat = s.decrypt(password, parseObjects.get(j).getString("Latitude"));
////                                    longitude = s.decrypt(password,parseObjects.get(j).getString("Longitude"));
////                                } catch (Exception e1) {
////                                    e1.printStackTrace();
////                                }
//                            }
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
        } else{
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
