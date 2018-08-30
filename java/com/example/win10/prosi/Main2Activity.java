package com.example.win10.prosi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class Main2Activity extends AppCompatActivity
        implements MainGuruFragmentListener,NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    protected ImageView profilePic;
    protected TextView nama;
    protected TextView role;

    protected Main2Fragment main;
    protected BantuanFragment bantuan;
    protected GuruProfileFragment1 profile1;
    protected GuruProfileFragment2 profile2;
    protected FragmentManager fm;
    public Guru g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarz);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        View hview = navigationView.getHeaderView(0);

        this.profilePic = (ImageView)hview.findViewById(R.id.imageView2);
        this.nama = (TextView) hview.findViewById(R.id.tv_name2);
        this.role = (TextView) hview.findViewById(R.id.tv_role2);

        this.main = Main2Fragment.newInstance();
        this.profile1 = GuruProfileFragment1.newInstance();
        this.profile2 = GuruProfileFragment2.newInstance();
        this.fm = getSupportFragmentManager();

        changePage(1);

        this.setProfile("Bradd Pitt");
        this.role.setText("Lecturer");
        this.profilePic.setOnClickListener(this);
    }

    private void setProfile(String nama){
        Intent itn =getIntent();
        String json = itn.getExtras().getString("guru");
        System.out.println(json);
        Gson gson = new Gson();
        g = gson.fromJson(json,Guru.class);
        this.nama.setText(g.getNama());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

        if (id == R.id.nav_home2) {
            changePage(1);

        } else if (id == R.id.nav_transaction2) {

        } else if (id == R.id.nav_teacher2) {

        } else if (id == R.id.nav_order2) {

        } else if (id == R.id.nav_help2) {

        }
        else if (id == R.id.nav_logout2) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.profilePic.getId()){
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
        String pendidikan = profile2.etPendidikan.getText().toString();
        String pelajaran = profile2.etPelajaran.getText().toString();

        this.profile1.tvName.setText(name);
        this.profile1.tvBorn.setText(born);
        this.profile1.tvAddress.setText(address);
        this.profile1.tvSex.setText(sex);
        this.profile1.tvPhone.setText(phone);
        this.profile1.tvEmail.setText(email);
        this.profile1.tvPendidikan.setText(pendidikan);
        this.profile1.tvPelajaran.setText(pelajaran);
        this.nama.setText(name);
    }

    @Override
    public void setProfileFragment1() {
        this.profile1.tvName.setText(this.nama.getText().toString());
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
        String pendidikan = profile1.tvPendidikan.getText().toString();
        String pelajaran = profile1.tvPelajaran.getText().toString();

        this.profile2.etName.setText(name);
        this.profile2.etBorn.setText(born);
        this.profile2.etAddress.setText(address);
        this.profile2.etPhone.setText(phone);
        this.profile2.etEmail.setText(email);
        this.profile2.etPendidikan.setText(pendidikan);
        this.profile2.etPelajaran.setText(pelajaran);
    }
}
