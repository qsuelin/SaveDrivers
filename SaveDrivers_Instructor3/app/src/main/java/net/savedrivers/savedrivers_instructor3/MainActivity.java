package net.savedrivers.savedrivers_instructor3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    private NavController navController;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding the BottomNavigationView
        bottomNavView = findViewById(R.id.bottomNavView);
        // Finding the NavControllerjava.lang.NullPointerException
        //        at net.savedrivers.savedrivers_instructor3.MainActivity.hideBottomNavigation(MainActivity.java:68)
        //        at net.savedrivers.savedrivers_instructor3.ui.students.StudentDetailFragment.onAttach(StudentDetailFragment.java:32)
        navController = Navigation.findNavController(this, R.id.fragNavHost);
        // Setting NavController with the BottomNavigationView
        NavigationUI.setupWithNavController(bottomNavView, navController);

        // Initialize appBarConfiguration
        AppBarConfiguration appBarconfiguration = new AppBarConfiguration.Builder(
                R.id.calendarFragment, R.id.studentsFragment, R.id.checklistFragment, R.id.instructionsFragment, R.id.notificationsFragment)
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
        switch(item.getItemId()) {
            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                return true;

            // If profile_btn clicked
            case  R.id.action_profile:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));

        }




        return super.onOptionsItemSelected(item);
    }



    public void showBottomeNavigation(){
        bottomNavView.setVisibility(View.VISIBLE);
    }

    public void hideBottomNavigation() {
        bottomNavView.setVisibility(View.GONE);
    }

    private Boolean backPressedOnce = false;

    @Override
    public void onBackPressed() {
        // check if the current destination is home
        if (navController.getGraph().getStartDestination() == navController.getCurrentDestination().getId()) {
            // check if back is already pressed. If yes, exit the app.
            if (backPressedOnce) {
                super.onBackPressed();
                return;
            }

            backPressedOnce = true;
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();


            new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              backPressedOnce = false;
                                          }
                                      }, 2000);
        }
        else {
            super.onBackPressed();
        }
    }
}