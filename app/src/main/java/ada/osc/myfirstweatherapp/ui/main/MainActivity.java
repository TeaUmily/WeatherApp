package ada.osc.myfirstweatherapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.locationRepository.AllLocations;
import ada.osc.myfirstweatherapp.presentation.MainActivityPresenter;
import ada.osc.myfirstweatherapp.ui.adapter.CustomViewPagerFragmentAdapter;
import ada.osc.myfirstweatherapp.ui.addLocation.AddNewLocationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.main_activity_navigation_view) NavigationView mNavigationView;
    @BindView(R.id.main_activity_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.main_activity_view_pager) ViewPager viewPager;


    private MainActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter = new MainActivityPresenter(this);

        mNavigationView.inflateMenu(R.menu.menu);
        mNavigationView.inflateHeaderView(R.layout.header);

        initUI();
        initToolbar();

        mPresenter.setAdapter(getSupportFragmentManager());

    }

    @Override
    protected void onStart() {
        super.onStart();
        initNavigationDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.updateData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddNewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }

    @Override
    public void setAdapterToViewPager(CustomViewPagerFragmentAdapter adapter) {
        viewPager.setAdapter(adapter);
    }
}