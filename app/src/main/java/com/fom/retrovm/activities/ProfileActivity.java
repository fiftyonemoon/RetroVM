package com.fom.retrovm.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fom.retrovm.databinding.ActivityProfileBinding;
import com.fom.retrovm.fragments.ProfileFragment;
import com.fom.retrovm.models.User;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentData();
    }

    private void getIntentData() {
        User user = (User) getIntent().getSerializableExtra("user");
        if (user == null) {
            Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        setTitle(user);
        loadUser(user);
    }

    private void setTitle(User user) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(user.login);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public void loadUser(User user) {
        loadFragment(new ProfileFragment(user));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.frame.getId(), fragment);
        if (!getSupportFragmentManager().getFragments().isEmpty()) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
