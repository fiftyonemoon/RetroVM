package com.fom.retrovm.fragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.fom.retrovm.adapters.ViewPagerAdapter;
import com.fom.retrovm.databinding.FragmentProfileBinding;
import com.fom.retrovm.models.User;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * 07th Aug 2022.
 *
 * @author <ref url="https://github.com/fiftyonemoon">hardkgosai</ref>.
 */
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private User user;

    public ProfileFragment() {
    }

    public ProfileFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.setUser(user);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        setViewPager();
    }

    private void initUI() {
        binding.pager.registerOnPageChangeCallback(pageChangeCallback);
        binding.collapsePanel.tab.addOnTabSelectedListener(tabSelectedListener);
        binding.collapsePanel.appbar.addOnOffsetChangedListener(offsetChangedListener);
    }

    private void setViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        adapter.setUsername(user.login);
        binding.pager.setAdapter(adapter);
    }

    final ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            TabLayout.Tab tab = binding.collapsePanel.tab.getTabAt(position);
            binding.collapsePanel.tab.selectTab(tab);
        }
    };

    final TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = binding.collapsePanel.tab.getSelectedTabPosition();
            binding.pager.setCurrentItem(position);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    final AppBarLayout.OnOffsetChangedListener offsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            float value = 12f - (-verticalOffset * 12f) / appBarLayout.getTotalScrollRange();
            float[] radius = new float[]{dpToPx(value), dpToPx(value), dpToPx(value), dpToPx(value), 0, 0, 0, 0};

            GradientDrawable drawable = (GradientDrawable) binding.collapsePanel.tab.getBackground();
            drawable.setCornerRadii(radius);
        }
    };

    float dpToPx(float dp) {
        return dp * ((float) getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
