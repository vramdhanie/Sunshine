package com.vincentramdhanie.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "ON STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "ON DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "ON PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "ON RESUME");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "ON START");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "ON CREATE");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
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


        if(id == R.id.action_settings){
            Log.i(LOG_TAG, "The Settings button was clicked on the main activity");
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        if(id == R.id.action_map){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String location = preferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
            showMap(Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q",location).build());
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            Log.e(LOG_TAG, "No Map Viewing Apps available.");
        }
    }


}
