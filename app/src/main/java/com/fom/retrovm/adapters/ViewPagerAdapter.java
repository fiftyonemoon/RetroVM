package com.fom.retrovm.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fom.retrovm.fragments.FollowersFragment;
import com.fom.retrovm.fragments.FollowingFragment;
import com.fom.retrovm.fragments.InfoFragment;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    private String username;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new FollowersFragment(username);
        } else if (position == 2) {
            return new FollowingFragment(username);
        } else {
            return new InfoFragment(username);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}