package net.savedrivers.savedrivers_instructor3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding the BottomNavigationView
        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        // Finding the NavController
        NavController navController = Navigation.findNavController(this, R.id.fragNavHost);
        // Setting NavController with the BottomNavigationView
        NavigationUI.setupWithNavController(bottomNavView, navController);

        // Initialize appBarConfiguration
        AppBarConfiguration appBarconfiguration = new AppBarConfiguration.Builder(
                R.id.calendarFragment, R.id.studentsFragment, R.id.instructionsFragment, R.id.notificationsFragment)
                .build();

        // Setting up ActionBar with NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarconfiguration);
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