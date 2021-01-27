package org.sejonguniv.if_2020.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.databinding.ActivityUserMainBinding;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.user.attendance.AttendanceFragment;
import org.sejonguniv.if_2020.ui.user.calendar.CalendarFragment;
import org.sejonguniv.if_2020.ui.user.home.HomeFragment;
import org.sejonguniv.if_2020.ui.user.excel.ExcelFragment;
import org.sejonguniv.if_2020.ui.user.notice.NoticeFragment;

import java.io.Serializable;

public class UserMainActivity extends BaseActivity<ActivityUserMainBinding> {

    HomeFragment homeFragment = new HomeFragment();
    Fragment currentFragment = new Fragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
            transaction.replace(R.id.frame_layout, currentFragment).commitAllowingStateLoss();
        } else {
            currentFragment = homeFragment;
            transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
        }
        setContentView(R.layout.activity_user_main);
        setBinding(R.layout.activity_user_main);
        binding.navigationview.setNavigationItemSelectedListener(new navigationItemSelectedListener());
    }

    public void openDrawerLayout(View view) {
        binding.drawerlayout.openDrawer(GravityCompat.END);
    }

    private class navigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (id) {
                case R.id.home: {
                    transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
                    currentFragment = homeFragment;
                    break;
                }

                case R.id.notice: {
                    NoticeFragment noticeFragment = new NoticeFragment();
                    transaction.replace(R.id.frame_layout, noticeFragment).commitAllowingStateLoss();
                    currentFragment = noticeFragment;
                    break;
                }
                case R.id.attendancecheck: {
                    AttendanceFragment attendanceFragment = new AttendanceFragment();
                    transaction.replace(R.id.frame_layout, attendanceFragment).commitAllowingStateLoss();
                    currentFragment = attendanceFragment;
                    break;
                }
                case R.id.excel: {
                    ExcelFragment excelFragment = new ExcelFragment();
                    transaction.replace(R.id.frame_layout, excelFragment).commitAllowingStateLoss();
                    currentFragment = excelFragment;
                    break;
                }
                case R.id.calendar: {
                    CalendarFragment calendarFragment = new CalendarFragment();
                    transaction.replace(R.id.frame_layout, calendarFragment).commitAllowingStateLoss();
                    currentFragment = calendarFragment;
                    break;
                }

            }
            binding.drawerlayout.closeDrawer(GravityCompat.END);
            return false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
    }
}