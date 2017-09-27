package ro.bbasilescu.bogdanbasilescu.presentation.usecases.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import ro.bbasilescu.bogdanbasilescu.R;
import ro.bbasilescu.bogdanbasilescu.data.remote.FirebaseApiImpl;
import ro.bbasilescu.bogdanbasilescu.ui.activities.AbsBaseActivity;
import ro.bbasilescu.bogdanbasilescu.utils.IntentUtils;
import ro.bbasilescu.bogdanbasilescu.utils.PermissionUtils;

public class MainActivity extends AbsBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String VIEW_PAGER_POSITION = "VP_POSITION";

    private TabAdapter tabAdapter;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    @BindView(R.id.tab_layout_main)
    public TabLayout tabLayout;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    public NavigationView navigationView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        // Initialize FragmentViewPagerAdapter and Tabs
        initViewPagerAndTabs();
        // Initialize NavigationDrawer
        initNavigationDrawer();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(VIEW_PAGER_POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(VIEW_PAGER_POSITION));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @VisibleForTesting
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_download_cv:
                if (PermissionUtils.askForWriteToExternalStoragePermission(this)) {
                    FirebaseApiImpl.getInstance()
                            .downloadFile("gs://bogdanbasilescu.appspot.com/Bogdan_Basilescu_CV.pdf");
                }
                break;

            case R.id.menu_by_email:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_linkedIn:
                IntentUtils.sendViewIntent(this, getString(R.string.link_linkedIn));
                break;

            case R.id.nav_pluralSight:
                IntentUtils.sendViewIntent(this, getString(R.string.link_pluralsight));
                break;

            case R.id.nav_medium:
                IntentUtils.sendViewIntent(this, getString(R.string.link_medium));
                break;

            case R.id.nav_facebook:
                IntentUtils.sendViewIntent(this, getString(R.string.link_facebook));
                break;

            case R.id.nav_by_phone:
                if (PermissionUtils.askForCallPhonePermission(this)) {
                    IntentUtils.sendCallIntent(this, "+40773867743");
                }
                break;

            case R.id.nav_by_email:
                IntentUtils.sendEmailIntent(this, new String[]{"b.bogdan0993@gmail.com"},
                        "Meeting proposal", "Say hi!");
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @VisibleForTesting
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.REQUEST_WRITE_EXTERNAL_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FirebaseApiImpl.getInstance()
                            .downloadFile("gs://bogdanbasilescu.appspot.com/Bogdan_Basilescu_CV.pdf");
                } else {
                    Toast.makeText(this, "Unable to download.Permission not granted!", Toast.LENGTH_SHORT).show();
                }
                return;

            case PermissionUtils.REQUEST_CALL_PHONE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IntentUtils.sendCallIntent(this, "+40773867743");
                } else {
                    Toast.makeText(this, "Unable to call.Permission not granted!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initViewPagerAndTabs() {
        tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(TabAdapter.Tab.values().length - 1);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
            tabAdapter.setTabLayout(tabLayout);
            tabAdapter.setToolbar(toolbar);
            viewPager.addOnPageChangeListener(tabAdapter);

            // Set up all tab headers
            for (int i = 0; i < TabAdapter.Tab.values().length; i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null) {
                    View customView = tabAdapter.getCustomView(i);
                    tab.setCustomView(customView);
                    // Highlight the first tab header
                    if (i == 0) {
                        customView.setSelected(true);
                        tabAdapter.onPageSelected(0);
                    }
                }
            }
        }
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
}
