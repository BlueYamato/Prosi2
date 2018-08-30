package com.example.win10.prosi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment implements View.OnClickListener{


    protected MainMuridFragmentListener listener;


    protected TextView tvName;
    protected TextView tvBorn;
    protected TextView tvAddress;
    protected TextView tvSex;
    protected TextView tvPhone;
    protected TextView tvEmail;
    protected Button btnEdit;

    public ProfileFragment(){

    }

    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof  MainMuridFragmentListener){
            this.listener = (MainMuridFragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.profile_fragment1, container, false);
        this.tvName = view.findViewById(R.id.tv_name_profile);
        this.tvBorn = view.findViewById(R.id.tv_born);
        this.tvAddress = view.findViewById(R.id.tv_address);
        this.tvSex = view.findViewById(R.id.tv_sex);
        this.tvPhone = view.findViewById(R.id.tv_phone);
        this.tvEmail = view.findViewById(R.id.tv_mail);
        this.btnEdit = view.findViewById(R.id.edit_profile);

        this.listener.setProfileFragment1();
        this.tvBorn.setText("Bandung, 10 November 1996");
        this.tvAddress.setText("Jalan Kencana no 18, Bandung");
        this.tvSex.setText("Perempuan");
        this.tvPhone.setText("0818 5054 2323");
        this.tvEmail.setText("lol@gmail.com");

        this.btnEdit.setOnClickListener(this);
        editProfile(((MainActivity)listener).m);
        return view;
    }

    public void editProfile(Murid m){

        this.tvBorn.setText(m.Nama_Kota+","+m.getTanggal_Lahir());
        this.tvAddress.setText(m.getAlamat());
        this.tvSex.setText("Perempuan");
        this.tvPhone.setText(m.getNo_Telp());
        this.tvEmail.setText(m.getEmail());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnEdit.getId()){
            this.listener.changePage(4);
        }
    }
}
