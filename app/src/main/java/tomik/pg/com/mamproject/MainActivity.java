package tomik.pg.com.mamproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import tomik.pg.com.mamproject.fragments.ARFragment;
import tomik.pg.com.mamproject.fragments.DisplaySensorsFragment;
import tomik.pg.com.mamproject.fragments.TrackingAlgorithmFragment;
import tomik.pg.com.mamproject.fragments.VRGlassesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    private float[] accelerometerValues = new float[3];
    private float[] magneticValues = new float[3];
    private float[] gravityValues = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Sensor sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            Sensor sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            if (sensorAccelerometer != null) {
                sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_GAME);
            }
            if (sensorMagnetic != null) {
                sensorManager.registerListener(this, sensorMagnetic, SensorManager.SENSOR_DELAY_GAME);
            }
            if (sensorGravity != null) {
                sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_GAME);
            }
        }

        // Start default fragment on startup
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.content_frame, new DisplaySensorsFragment());
            transaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try {
            int id = item.getItemId();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (id == R.id.nav_display_sensors) {
                transaction.replace(R.id.content_frame, new DisplaySensorsFragment());
            } else if (id == R.id.nav_tracking_algorithm) {
                transaction.replace(R.id.content_frame, new TrackingAlgorithmFragment());
            } else if (id == R.id.nav_vr_glasses) {
                transaction.replace(R.id.content_frame, new VRGlassesFragment());
            } else if (id == R.id.nav_ar) {
                transaction.replace(R.id.content_frame, new ARFragment());
            } else {
                throw new Exception("[Error] Selected tab is not recognized");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerValues = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magneticValues = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_GRAVITY:
                gravityValues = sensorEvent.values.clone();
                break;
            default:
                // do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    public float[] getAccelerometerCurrentValues() {
        return accelerometerValues;
    }

    public float[] getMagneticCurrentValues() {
        return magneticValues;
    }

    public float[] getGravityCurrentValues() {
        return gravityValues;
    }
}