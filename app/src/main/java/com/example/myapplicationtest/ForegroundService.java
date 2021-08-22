package com.example.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class ForegroundService extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    TextView textView;

    protected static final String TAG = "ForegroundService";
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        permissionCheck();

        textView = findViewById(R.id.tv_message);

        beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().clear();

        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));

        beaconManager.setRegionStatePersistenceEnabled(false);

        beaconManager.addMonitorNotifier(new MonitorNotifier() {

            //들어올때 인식
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!" + this);

                //textView.setText("Beacon wtf connected");

                //인식되면 range, identify 시작
                beaconManager.startRangingBeacons(region);
            }

            //나갈때 인식
            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");

            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state + region);

            }
        });

        beaconManager.addRangeNotifier(new RangeNotifier() {

            //인식되면 range, identify 시작
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                for (Beacon beacon: beacons)  {
                    Log.i(TAG, "This beacon has identifiers:"+beacon.getId1()+", "+beacon.getId2()+", "+beacon.getId3());
                    Log.i(TAG, "This beacon is far away from "+beacon.getDistance()+"m");
                }

                Log.i(TAG, "done");
            }
        });

        //"74278bda-b644-4520-8f0c-720eaf059935"
        beaconManager.startMonitoring(new Region("myMonitoringUniqueId", null, null, null));

    }

    //위치 permission이 부여되야 한다.
    public void permissionCheck(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
        getLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "access denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //Get location
    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (myLocation == null)
        {
            myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
    }





}