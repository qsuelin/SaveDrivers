package net.savedrivers.savedrivers_instructor3;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1000;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1001;
    ListView listView;

    // TODO: get info from user input
    String account_name = "sinvsmae@gmail.com";
    String account_type = "com.google";
    String owner_account = "sinvsmae@gmail.com";
    long calendar_id = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        listView = findViewById(R.id.list_view);
    }
    // Calendar


    public void getCalendars(View view) {
        // verify the permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return;
        }

        final String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
                CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
                CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
        };

        // The indices for the projection array above.
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

        ContentResolver contentResolver = getContentResolver();
        Cursor cur = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, null, null, null);

        ArrayList<String> calendarInfos = new ArrayList<>();
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            String calendarInfo = String.format("Calendar ID: %s\nDisplay Name: %s\nAccount Name: %s\nOwner Name: %s", calID, displayName, accountName, ownerName);
            calendarInfos.add(calendarInfo);
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, calendarInfos);
        listView.setAdapter(stringArrayAdapter);
    }

    public void getPrimaryCalendar(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return;
        }

        // Projection array. Creating indices for this array instead of doing dynamic lookups improves performance.
        final String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
                CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
                CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
        };

        // The indices for the projection array above.
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

        ContentResolver contentResolver = getContentResolver();
        String selection = CalendarContract.Calendars.VISIBLE + " = 1 AND " + CalendarContract.Calendars.IS_PRIMARY + " = 1";
        Cursor cur = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, selection, null, null);

        ArrayList<String> calendarInfos = new ArrayList<>();
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            String calendarInfo = String.format("Calendar ID: %s\nDisplay Name: %s\nAccount Name: %s\nOwner Name: %s", calID, displayName, accountName, ownerName);
            calendarInfos.add(calendarInfo);
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, calendarInfos);
        listView.setAdapter(stringArrayAdapter);
    }


    public void getInstancesByCalendarByDate(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return;
        }

        final String[] INSTANCE_PROJECTION = new String[]{
                CalendarContract.Instances._ID,                 // 0
                CalendarContract.Instances.TITLE,              // 1
                CalendarContract.Instances.BEGIN,               // 2
                CalendarContract.Instances.DESCRIPTION         // 3
        };

        // The indices for the projection array above.
        final int PROJECTION_INSTANCE_ID_INDEX = 0;
        final int PROJECTION_TITLE_INDEX = 1;
        final int PROJECTION_DTSTART_INDEX = 2;
        final int PROJECTION_DESCRIPTION = 3;

        // Specify the date you wnat to search
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2020,9,7,1,0);
        long startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020,10,9,1,0);
        long endMillis = endTime.getTimeInMillis();

        // Run query
        Cursor cur = null;
        ContentResolver cr = getContentResolver();

        String selection = CalendarContract.Instances.CALENDAR_ID + " = ?";
        String[] selectionsArgs = new String[]{String.valueOf(calendar_id)};


        // Construct query with the desired date
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder,startMillis);
        ContentUris.appendId(builder,endMillis);

//        // Submit the query
//        cur = cr.query(builder.build(), INSTANCE_PROJECTION, selection, selectionsArgs, null);

        cur = CalendarContract.Instances.query(cr, INSTANCE_PROJECTION, startMillis, endMillis);

        ArrayList<String> events = new ArrayList<>();
        if (cur.getCount() < 1) {
            Log.i("Calendar", "nothing returned");
        }
        while (cur.moveToNext()) {

            // Get the field values
            long eventID = cur.getLong(PROJECTION_INSTANCE_ID_INDEX);
            String title = cur.getString(PROJECTION_TITLE_INDEX);
            long beginVal = cur.getLong(PROJECTION_DTSTART_INDEX);
            String eventDescription = cur.getString(PROJECTION_DESCRIPTION);

            // Do something with the values.
            Log.i("Calendar", "Event:  " + title);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(beginVal);
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String startDatetime = formatter.format(calendar.getTime());
            Log.i("Calendar", "Date: " + startDatetime);

            String eventInfo = String.format("Event ID: %s\nTitle: %s\nStart Time: %s\nDescription: %s", eventID, title, startDatetime, eventDescription);
            events.add(eventInfo);

        }
        cur.close();

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, events);
        listView.setAdapter(stringArrayAdapter);
    }

}
