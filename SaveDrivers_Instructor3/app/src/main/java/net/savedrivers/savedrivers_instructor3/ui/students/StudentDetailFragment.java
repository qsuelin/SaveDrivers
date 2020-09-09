package net.savedrivers.savedrivers_instructor3.ui.students;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import net.savedrivers.savedrivers_instructor3.MainActivity;
import net.savedrivers.savedrivers_instructor3.R;

public class StudentDetailFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_student_detail,container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)getActivity()).hideBottomNavigation();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity)getActivity()).showBottomeNavigation();
    }
}
