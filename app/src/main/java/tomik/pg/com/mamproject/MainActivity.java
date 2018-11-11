package tomik.pg.com.mamproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import tomik.pg.com.mamproject.fragments.ARFragment;
import tomik.pg.com.mamproject.fragments.DisplaySensorsFragment;
import tomik.pg.com.mamproject.fragments.TrackingAlgorithmFragment;
import tomik.pg.com.mamproject.fragments.VRGlassesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int currentFragment;

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

        // Start default fragment on startup
        if (savedInstanceState == null) {
            currentFragment = R.id.nav_display_sensors;
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

            currentFragment = id;
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
}