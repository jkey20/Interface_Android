package org.sejonguniv.if_2020.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminMainBinding;
import org.sejonguniv.if_2020.ui.admin.attendance.AdminAttendanceFragment;
import org.sejonguniv.if_2020.ui.admin.calendar.AdminCalendarFragment;
import org.sejonguniv.if_2020.ui.admin.home.AdminHomeFragment;
import org.sejonguniv.if_2020.ui.admin.list.AdminListFragment;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.admin.notification.AdminNotificationFragment;
import org.sejonguniv.if_2020.ui.user.attendance.AttendanceFragment;
import org.sejonguniv.if_2020.ui.user.calendar.CalendarFragment;
import org.sejonguniv.if_2020.ui.user.home.HomeFragment;
import org.sejonguniv.if_2020.ui.user.notice.NoticeFragment;

public class AdminMainActivity extends BaseActivity<ActivityAdminMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        setBinding(R.layout.activity_admin_main);
        AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, adminHomeFragment).commitAllowingStateLoss();

        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (id) {
                    case R.id.home: {
                        transaction.replace(R.id.frame_layout, adminHomeFragment).commitAllowingStateLoss();
                        break;
                    }

                    case R.id.notice: {
                        AdminNoticeFragment adminNoticeFragment = new AdminNoticeFragment();
                        transaction.replace(R.id.frame_layout, adminNoticeFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.attendancecheck: {
                        AdminAttendanceFragment adminAttendanceFragment = new AdminAttendanceFragment();
                        transaction.replace(R.id.frame_layout, adminAttendanceFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.list: {
                        AdminListFragment adminListFragment = new AdminListFragment();
                        transaction.replace(R.id.frame_layout, adminListFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.calendar: {
                        AdminCalendarFragment adminCalendarFragment = new AdminCalendarFragment();
                        transaction.replace(R.id.frame_layout, adminCalendarFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.notification: {
                        AdminNotificationFragment adminNotificationFragment = new AdminNotificationFragment();
                        transaction.replace(R.id.frame_layout, adminNotificationFragment).commitAllowingStateLoss();
                        break;
                    }


                }
                binding.drawerlayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    public void openDrawerLayout(View view) {
        binding.drawerlayout.openDrawer(GravityCompat.START);
    }

}