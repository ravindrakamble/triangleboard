package com.r2apps.triangleboard.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.r2apps.triangleboard.R;
import com.r2apps.triangleboard.ui.core.BaseActivity;

public class MainActivity extends BaseActivity implements Main.View, NavigationView.OnNavigationItemSelectedListener {

    private MainPresenter presenter;
    private  Toolbar mToolbar;
    private NavigationView mLeftNavigation;
    private NavigationView mRightNavigation;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFabButton;
    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_main);
        initializePresenter();
        initializeViews();
        setListeners();
        setToolBar();
    }

    private void initializePresenter(){
        presenter = new MainPresenter();
        presenter.takeView(this);
    }

    private void initializeViews(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFabButton = (FloatingActionButton) findViewById(R.id.fab);
        mLeftNavigation = (NavigationView) findViewById(R.id.nav_view_left);
        mRightNavigation = (NavigationView) findViewById(R.id.nav_view_right);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar,
                R.string.action_settings,
                R.string.action_settings){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    private void setListeners(){
        mFabButton.setOnClickListener(this);
        mLeftNavigation.setNavigationItemSelectedListener(this);
        mRightNavigation.setNavigationItemSelectedListener(this);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
    private void setToolBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(byte requestCode, Object response) {

    }

    @Override
    public void onFailure(byte requestCode, byte responseCode, String message) {

    }

    @Override
    public void navigateToHome() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:
                break;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void close() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public boolean isInternetAvailable() {
        return isConnectedToInternet();
    }

    @Override
    public void showNoInternetDialog() {

    }

    @Override
    public void hideSoftKeyboard() {
        hideSoftKeyboard();
    }
}
