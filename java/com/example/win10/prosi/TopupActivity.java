package com.example.win10.prosi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TopupActivity extends AppCompatActivity implements TopupFragmentListener,  NavigationView.OnNavigationItemSelectedListener {

    protected TopupFragment1 mainFragment;
    protected TopupFragment2 secondFragment;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topup1_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_topup1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.mainFragment = TopupFragment1.newInstance();
        this.secondFragment = TopupFragment2.newInstance();
        this.fragmentManager = this.getSupportFragmentManager();
        //FragmentTransaction ft = this.fragmentManager.beginTransaction();
        this.changePage(1);
        //ft.add(R.id.fragment_container,this.fragment1).addToBackStack(null).commit();
        //ft.add(R.id.fragment_container,this.fragment1).commit();
        //this.changePage(2);
        //this.changePage(1);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_topup1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action

        } else if (id == R.id.nav_transaction) {

        } else if (id == R.id.nav_teacher) {

        } else if (id == R.id.nav_order) {

        } else if (id == R.id.nav_confirm) {

        } else if (id == R.id.nav_help) {

        }
        else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_topup1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            //ft.replace(R.id.fragment_container,this.fragment1).addToBackStack(null);
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }
            else{
                ft.add(R.id.fragment_container,this.mainFragment);
            }
            if(this.secondFragment.isAdded()){
                ft.hide(this.secondFragment);
            }
        }
        else if(page==2){
            //ft.replace(R.id.fragment_container,this.fragment2).addToBackStack(null);
            if(this.secondFragment.isAdded()){
                ft.show(this.secondFragment);
            }
            else{
                ft.add(R.id.fragment_container, this.secondFragment);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
        }
        ft.commit();
    }
}
