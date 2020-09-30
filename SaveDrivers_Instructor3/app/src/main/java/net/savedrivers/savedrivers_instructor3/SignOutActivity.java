package net.savedrivers.savedrivers_instructor3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignOutActivity extends AppCompatActivity {

    ImageView profileImageView;
    TextView tv_profile_name, tv_profile_email, tv_profile_id;
    Button signOutButton;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        profileImageView = findViewById(R.id.profile_imageView);
        tv_profile_name = findViewById(R.id.tv_profile_name);
        tv_profile_email = findViewById(R.id.tv_profile_email);
//        tv_profile_id = findViewById(R.id.tv_profile_id);
        signOutButton = findViewById(R.id.btn_signout);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String profileName = account.getDisplayName();
            String profileEmail = account.getEmail();
            String profileId = account.getId();
            Uri profilePhotoUri = account.getPhotoUrl();

            tv_profile_name.setText(profileName);
            tv_profile_email.setText(profileEmail);
//            tv_profile_id.setText(profileId);
            Glide.with(this).load(String.valueOf(profilePhotoUri)).into(profileImageView);

        }

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_signout:
                        signOut();
                        break;
                }
            }
        });
    }


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SignOutActivity.this, getString(R.string.sign_out), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignOutActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
