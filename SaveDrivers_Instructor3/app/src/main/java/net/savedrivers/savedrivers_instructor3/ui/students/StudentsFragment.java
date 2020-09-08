package net.savedrivers.savedrivers_instructor3.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import net.savedrivers.savedrivers_instructor3.R;


public class StudentsFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_students,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnStudentDetail = view.findViewById(R.id.btnStudentDetail);

        // Retrieve NavController from any Fragment created by a NavHostFragment
        final NavController navController = NavHostFragment.findNavController(this);
        // Alternatively, retrieve NavController from any View within NavHostFragment
//        final NavController viewNavController = Navigation.findNavController(btnStudentDetail);

        // Set listener
        btnStudentDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_studentsFragment_to_studentDetailFragment);
            }
        });
        // Or use convenience methods in Navigation to combine all previous steps
//        btnStudentDetail.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.action_studentsFragment_to_studentDetailFragment));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }
}
