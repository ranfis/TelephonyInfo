package com.unibe.cm.ranfis.telephonyinfo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.develper, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            TelephonyManager tMgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

            String mNetworkOperatorName = tMgr.getSimOperatorName();
            Log.v(LOG_TAG, "mNetworkOperatorName="+mNetworkOperatorName);

            String mSimSerialNumber = tMgr.getSimSerialNumber();//
            ((TextView) findViewById(R.id.tSimSerialNumber)).setText(mSimSerialNumber);
            Log.v(LOG_TAG, "mSimSerialNumber=" + mSimSerialNumber);

            String mSimOperator = tMgr.getSimOperator();
            ((TextView) findViewById(R.id.tSimOperator)).setText(mSimOperator);
            Log.v(LOG_TAG, "mSimOperator=" + mSimOperator);

            String mMcc = tMgr.getSimOperator().substring(0, 3) + " - " + tMgr.getNetworkCountryIso(); //
            Log.v(LOG_TAG, "mMcc=" + mMcc);
            ((TextView) findViewById(R.id.tMcc)).setText(mMcc);

            String mMnc = tMgr.getSimOperator().substring(3) + " - " + mNetworkOperatorName; //
            Log.v(LOG_TAG, "mMnc=" + mMnc);
            ((TextView) findViewById(R.id.tMnc)).setText(mMnc);

            ((TextView) findViewById(R.id.tNetworkOperator)).setText(mNetworkOperatorName);
            Log.v(LOG_TAG, "mNetworkOperatorName=" + mNetworkOperatorName);

            int networkType = tMgr.getNetworkType();
            Log.v(LOG_TAG, "networkType=" + networkType);

            ((TextView) findViewById(R.id.tNetworkType)).setText(getNetworkTypeDescription(networkType));

            String phoneNumber = tMgr.getLine1Number();
            Log.v(LOG_TAG, "phoneNumber=" + phoneNumber);

            String mMSISDN;

            if(phoneNumber.length()>1){
                mMSISDN = phoneNumber;
            }
            else{
                mMSISDN = "No Disponible";
            }

            ((TextView) findViewById(R.id.tMSISDN)).setText(mMSISDN);

        } catch (Exception e) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private String getNetworkTypeDescription(int networkType){
        String mNetworkType = "";
        switch (networkType)
        {
            case 7:
                mNetworkType += " - 1xRTT";
                break;
            case 4:
                mNetworkType += " - CDMA";
                break;
            case 2:
                mNetworkType += " - EDGE";
                break;
            case 14:
                mNetworkType += " - eHRPD";
                break;
            case 5:
                mNetworkType += " - EVDO re;. 0";
                break;
            case 6:
                mNetworkType += " - EVDO re;. A";
                break;
            case 12:
                mNetworkType += " - EVDO re;. B";
                break;
            case 1:
                mNetworkType += " - GPRS";
                break;
            case 8:
                mNetworkType += " - HSDPA";
                break;
            case 10:
                mNetworkType += " - HSPA";
                break;
            case 15:
                mNetworkType += " - HSPA+";
                break;
            case 9:
                mNetworkType += " - HSUPA";
                break;
            case 11:
                mNetworkType += " - iDen";
                break;
            case 13:
                mNetworkType += " - LTE";
                break;
            case 3:
                mNetworkType += " - UMTS";
                break;
            case 0:
                mNetworkType += " - Unknown";
                break;
        }
        return mNetworkType;
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
