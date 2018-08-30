package com.example.win10.prosi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity
        implements MainMuridFragmentListener,NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    protected MainFragment main;
    protected FragmentManager fm;
    protected BantuanFragment bantuan;
    protected ProfileFragment profile1;
    protected ProfileFragment2 profile2;
    protected FloatingActionButton fab;
    protected ImageView profileView;
    protected TextView tvName;
    protected TextView tvRole;
    public Murid m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = findViewById(R.id.fab);
        this.fab.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.main = MainFragment.newInstance();
        this.bantuan = BantuanFragment.newInstance();
        this.profile1 =  ProfileFragment.newInstance();
        this.profile2 = ProfileFragment2.newInstance();

        this.fm = getSupportFragmentManager();
        this.changePage(1);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
        this.profileView = (ImageView) hView.findViewById(R.id.imageView);
        this.tvName = (TextView)hView.findViewById(R.id.tv_name);
        this.tvRole = (TextView)hView.findViewById(R.id.tv_role);

        this.setProfile("Maria Mai");
        this.profileView.setOnClickListener(this);
        this.tvRole.setText("Student");
    }

    private void setProfile(String nama){
        Intent itn =getIntent();
        String json = itn.getExtras().getString("murid");
        System.out.println(json);
        Gson gson = new Gson();
         m = gson.fromJson(json,Murid.class);

        this.tvName.setText(m.getNama());
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
            this.changePage(1);

        } else if (id == R.id.nav_transaction) {
            this.changePage(3);
        } else if (id == R.id.nav_teacher) {

        } else if (id == R.id.nav_order) {

        } else if (id == R.id.nav_confirm) {

        } else if (id == R.id.nav_help) {
            this.changePage(2);
        }
        else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        //Intent intent = new Intent(this, FiturPrivatActivity.class);
        //startActivity(intent);
        if(v.getId()==this.fab.getId()){
            Intent intent = new Intent(this, TopupActivity.class);
            startActivity(intent);
        }
        if(v.getId()==this.profileView.getId()){
            this.changePage(3);
        }
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fm.beginTransaction();
        if(page==1){
            //ft.replace(R.id.fragment_container,this.fragment1).addToBackStack(null);
            if(this.main.isAdded()){
                ft.show(this.main);
            }
            else{
                ft.add(R.id.fragment_container_main,this.main);
            }
            if(this.bantuan.isAdded()){
                ft.hide(this.bantuan);
            }
            if(this.profile1.isAdded()){
                ft.hide(this.profile1);
            }
            if(this.profile2.isAdded()){
                ft.hide(this.profile2);
            }
        }
        else if(page==2){
            //ft.replace(R.id.fragment_container,this.fragment2).addToBackStack(null);
            if(this.bantuan.isAdded()){
                ft.show(this.bantuan);
            }
            else{
                ft.add(R.id.fragment_container_main, this.bantuan);
            }
            if(this.bantuan.isAdded()){
                ft.hide(this.main);
            }
            if(this.profile1.isAdded()){
                ft.hide(this.profile1);
            }
            if(this.profile2.isAdded()){
                ft.hide(this.profile2);
            }
        }
        else if(page==3){
            //ft.replace(R.id.fragment_container,this.fragment2).addToBackStack(null);
            if(this.profile1.isAdded()){
                ft.show(this.profile1);
            }
            else{
                ft.add(R.id.fragment_container_main, this.profile1);
            }
            if(this.bantuan.isAdded()){
                ft.hide(this.main);
            }
            if(this.main.isAdded()){
                ft.hide(this.main);
            }
            if(this.profile2.isAdded()){
                ft.hide(this.profile2);
            }
        }
        else if(page==4){
            //ft.replace(R.id.fragment_container,this.fragment2).addToBackStack(null);
            if(this.profile2.isAdded()){
                ft.show(this.profile2);
            }
            else{
                ft.add(R.id.fragment_container_main, this.profile2);
            }
            if(this.bantuan.isAdded()){
                ft.hide(this.main);
            }
            if(this.profile1.isAdded()){
                ft.hide(this.profile1);
            }
            if(this.main.isAdded()){
                ft.hide(this.main);
            }
        }

        ft.commit();
    }

    @Override
    public void changeProfile() {
        String name = profile2.etName.getText().toString();
        String born = profile2.etBorn.getText().toString();
        String address = profile2.etAddress.getText().toString();
        String sex = profile2.spSex.getSelectedItem().toString();
        String phone = profile2.etPhone.getText().toString();
        String email = profile2.etEmail.getText().toString();

        this.profile1.tvName.setText(name);
        this.profile1.tvBorn.setText(born);
        this.profile1.tvAddress.setText(address);
        this.profile1.tvSex.setText(sex);
        this.profile1.tvPhone.setText(phone);
        this.profile1.tvEmail.setText(email);
        this.tvName.setText(name);
    }

    @Override
    public void setProfileFragment1() {
        this.profile1.tvName.setText(this.tvName.getText().toString());
    }

    @Override
    public void sameSpinnerSelect() {
        String x = this.profile1.tvSex.getText().toString();
        if(x.equalsIgnoreCase("perempuan"))
            this.profile2.spSex.setSelection(1);
        else
            this.profile2.spSex.setSelection(0);
    }

    @Override
    public void sameDataProfile() {
        String name = profile1.tvName.getText().toString();
        String born = profile1.tvBorn.getText().toString();
        String address = profile1.tvAddress.getText().toString();
        String phone = profile1.tvPhone.getText().toString();
        String email = profile1.tvEmail.getText().toString();

        this.profile2.etName.setText(name);
        this.profile2.etBorn.setText(born);
        this.profile2.etAddress.setText(address);
        this.profile2.etPhone.setText(phone);
        this.profile2.etEmail.setText(email);
    }
}
