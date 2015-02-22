package com.swooby.toolbarswitch;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity
        extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "+onCreate(...)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        Log.d(TAG, "-onCreate(...)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Why is this logged before onCreateOptionsMenu?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "Why is this logged after onResume?");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_switch);
        if (item != null) {
            CharSequence title = item.getTitle();
            Switch action_bar_switch = (Switch) item.getActionView().findViewById(R.id.action_switch);
            if (action_bar_switch != null) {
                action_bar_switch.setText(title);
                action_bar_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int resId = isChecked ? R.string.enabled : R.string.disabled;
                        Toast.makeText(MainActivity.this, getString(resId), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_switch:
                Toast.makeText(this, "action_switch", Toast.LENGTH_LONG).show();
                // TODO:(pv) Why is item.mActionView null?
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
