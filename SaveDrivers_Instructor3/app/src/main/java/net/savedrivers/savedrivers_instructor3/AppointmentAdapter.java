package net.savedrivers.savedrivers_instructor3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    public AppointmentAdapter(Activity context, List<Appointment> appointments) {
        super(context, 0, appointments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Appointment appointment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.appointment_item, parent, false);
        }

        TextView tv_time = convertView.findViewById(R.id.tv_time);
        tv_time.setText(appointment.getTime());
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        tv_name.setText(appointment.getName());
        TextView tv_address = convertView.findViewById(R.id.tv_address);
        tv_address.setText(appointment.getAddress());
        TextView tv_phone = convertView.findViewById(R.id.tv_phone);
        tv_phone.setText(appointment.getPhone());
        if (appointment.getPhone2() != "") {
           convertView.findViewById(R.id.phone2_row).setVisibility(View.VISIBLE);
            TextView tv_phone2 = convertView.findViewById(R.id.tv_phone2);
            tv_phone2.setText(appointment.getPhone2());
        }
        if (appointment.getStudentNote() != "") {
            TextView tv_student_note = convertView.findViewById(R.id.tv_student_note);
            tv_student_note.setVisibility(View.VISIBLE);
            tv_student_note.setText((appointment.getStudentNote()));
        }

        return convertView;
    }
}
