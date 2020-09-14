package net.savedrivers.savedrivers_instructor3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    private static final String TAG = "CalenderFragment";

    public AppointmentAdapter(Activity context, List<Appointment> appointments) {
        super(context, 0, appointments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Appointment appointment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.appointment_item, parent, false);
        }

        TextView tv_time = convertView.findViewById(R.id.tv_time);
        tv_time.setText(appointment.getTime());
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        tv_name.setText(appointment.getName());
        TextView tv_address = convertView.findViewById(R.id.tv_address);

        // location intent
        final String address = appointment.getAddress();
        tv_address.setText(address);
        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse("geo:0.0?q=" + address));
                if (mapIntent.resolveActivity((getContext().getPackageManager())) != null) {
                    getContext().startActivity(mapIntent);
                } else {
                    Log.e(TAG, "Can't resolve app for ACTION_VIEW intent.");
                }
            }
        });
        TextView tv_phone = convertView.findViewById(R.id.tv_phone);
        final String phoneNum = appointment.getPhone();
        tv_phone.setText(phoneNum);

        // dial intent
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" + phoneNum));
                if (dialIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(dialIntent);
                } else {
                    Log.e(TAG, "Can't resolve app for ACTION_DIAL intent");
                }
            }
        });
        //sms intent
        ImageButton btn_sms = convertView.findViewById(R.id.phone_msg_btn);
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:" + phoneNum));
                // Create sms
                String sms = "From SaveDrivers: ";
                // Add sms with key: "sms_body"
                smsIntent.putExtra("sms_body", sms);
                if (smsIntent.resolveActivity(getContext().getPackageManager())!= null) {
                    getContext().startActivity(smsIntent);
                } else {
                    Log.e(TAG, "Can't resolve app for ACTION_SENDTO intent");
                }
            }
        });
        if (appointment.getPhone2() != "") {
           convertView.findViewById(R.id.phone2_row).setVisibility(View.VISIBLE);
            TextView tv_phone2 = convertView.findViewById(R.id.tv_phone2);
            final String phoneNum2 = appointment.getPhone2();
            tv_phone2.setText(phoneNum2);
            // dial intent
            tv_phone2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + phoneNum2));
                    if (dialIntent.resolveActivity(getContext().getPackageManager()) != null) {
                        getContext().startActivity(dialIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_DIAL intent");
                    }
                }
            });
            //sms intnet
            ImageButton btn_sms2 = convertView.findViewById(R.id.phone2_msg_btn);
            btn_sms2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    smsIntent.setData(Uri.parse("smsto:" + phoneNum2));
                    // Create sms
                    String sms = "From SaveDrivers: ";
                    // Add sms with key: "sms_body"
                    smsIntent.putExtra("sms_body", sms);
                    if (smsIntent.resolveActivity(getContext().getPackageManager())!= null) {
                        getContext().startActivity(smsIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_SENDTO intent");
                    }
                }
            });
        }
        if (appointment.getStudentNote() != "") {
            TextView tv_student_note = convertView.findViewById(R.id.tv_student_note);
            tv_student_note.setVisibility(View.VISIBLE);
            tv_student_note.setText((appointment.getStudentNote()));
        }

        return convertView;
    }

}
