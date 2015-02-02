package com.example.shyampsunder2003.treasure;

import android.content.Intent;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Locate extends ActionBarActivity implements LocationListener{
    private String provider,clueData;
    TextView textLat,textLong,textAccuracy,textClue;
    Double lat,longi,clueLat,clueLongi;
    float accuracy=999;
    LocationManager service;
    DatabaseHelp db;
    int NumberOfCluesDone,delimiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        textClue=(TextView) findViewById(R.id.textView11);
        textLat=(TextView) findViewById(R.id.textView9);
        textLong=(TextView) findViewById(R.id.textView8);
        textAccuracy=(TextView) findViewById(R.id.textView5);
        Criteria criteria = new Criteria();
        provider = service.getBestProvider(criteria, false);
        Location location = service.getLastKnownLocation(provider);
        if (location != null) {
            //System.out.println("Provider " + provider + " has been selected.");
            lat=location.getLatitude();
            longi=location.getLongitude();
            textLat.setText(String.valueOf(location.getLatitude()));
            textLong.setText(String.valueOf(location.getLongitude()));
            textAccuracy.setText("Location not available");
        } else {
            textLat.setText("Location not available");
            textLong.setText("Location not available");
            textAccuracy.setText("Location not available");
        }
        onCreateRepeat();



    }
    public void onCreateRepeat()
    {
        db= new DatabaseHelp(this);
        db.open();
        NumberOfCluesDone=db.countSolvedClues();
        clueData=db.getData(NumberOfCluesDone+1);
        db.close();
        delimiter=clueData.indexOf(" ");                                                    //Gets the index of the space
        clueLat=Double.parseDouble(clueData.substring(0,delimiter));                     //Converts first half of clueData String
        clueLongi=Double.parseDouble(clueData.substring(delimiter+1,clueData.length())); //Converts second half of clueData String
        textClue.setText(String.valueOf(NumberOfCluesDone+1));

    }
    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        service.requestLocationUpdates(provider, 400, 1, this);
        onCreateRepeat();
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        service.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        longi=location.getLongitude();
        accuracy=location.getAccuracy();
        textLat.setText(String.valueOf(location.getLatitude()));
        textLong.setText(String.valueOf(location.getLongitude()));
        textAccuracy.setText(String.valueOf(location.getAccuracy()));
    }

    public boolean mockCheck()
    {
        if (Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0"))
            return false;
        else return true;
    }

    public void clueCheck(View view)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        Log.d("Click","Entered method");
        if(accuracy>=30)
        {
            Toast.makeText(getApplicationContext(), "Inaccurate location, ensure accuracy is less than 30", Toast.LENGTH_LONG).show();
            db.open();
            db.createResult(formattedDate,"Failure","Inaccurate");
            db.close();
        }
        if(mockCheck())
        {
            Toast.makeText(getApplicationContext(), "Mock locations option is enabled, disable by going into Setting -> Developer Options, ",Toast.LENGTH_LONG).show();
            db.open();
            db.createResult(formattedDate,"Failure","Mock Locations");
            db.close();
        }
        Location currLocation=service.getLastKnownLocation(provider);
        Location clueLocation=service.getLastKnownLocation(provider);
        currLocation.setLatitude(lat);
        currLocation.setLongitude(longi);
        clueLocation.setLatitude(clueLat);
        clueLocation.setLongitude(clueLongi);
        if(currLocation.distanceTo(clueLocation)<30)
        {
            Toast.makeText(getApplicationContext(), "Congratulations! Next clue loading...", Toast.LENGTH_LONG).show();
            db.open();
            db.createResult(formattedDate, "Success", "Success");
            db.close();
            onCreateRepeat();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Nope, keep trying!", Toast.LENGTH_LONG).show();
            db.open();
            db.createResult(formattedDate,"Failure","Wrong Location");
            db.close();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
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