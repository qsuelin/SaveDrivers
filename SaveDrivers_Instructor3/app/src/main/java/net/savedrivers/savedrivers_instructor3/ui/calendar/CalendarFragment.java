package net.savedrivers.savedrivers_instructor3.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.savedrivers.savedrivers_instructor3.Appointment;
import net.savedrivers.savedrivers_instructor3.AppointmentAdapter;
import net.savedrivers.savedrivers_instructor3.R;

import java.util.Arrays;

public class CalendarFragment extends Fragment {

    private AppointmentAdapter appointmentAdapter;

    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1000;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1001;

    public static final Appointment[] appointments = {
            new Appointment("7am", "Rose Chandler", "1624 Locust St, St. Louis", "63103", "+13144523673", "+13145847844", " !!time to take exam!!"),
            new Appointment("10:30am", "Jun Stein", "1640 S Lindbergh Blvd, St. Louis", "63131", "+13144442322", "","More practice on parrellel parking."),
            new Appointment("2pm", "Jack Dome", "4401 Hampton Ave, St. Louis", "63109", "+13144218963", "+13141213864", ""),
            new Appointment("4pm", "Fred Cooper", "1301 Olive St, St. Louis","63013", "+13142424232", "", "Please park at the curb and call me 10 minutes before you arrive")
    };



    // Projection array. Creating indices for this array instead of doing dynamic lookups improves performance.


    public CalendarFragment(){
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        appointmentAdapter = new AppointmentAdapter(getActivity(), Arrays.asList(appointments));
        ListView listView = rootView.findViewById(R.id.listview_appointment);
        listView.setAdapter(appointmentAdapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }



}
