package com.example.win10.prosi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GuruProfileFragment1 extends Fragment implements View.OnClickListener{


    protected MainGuruFragmentListener listener;


    protected TextView tvName;
    protected TextView tvBorn;
    protected TextView tvAddress;
    protected TextView tvSex;
    protected TextView tvPhone;
    protected TextView tvEmail;
    protected TextView tvPendidikan;
    protected TextView tvPelajaran;
    protected Button btnEdit;

    public GuruProfileFragment1(){

    }

    public static GuruProfileFragment1 newInstance(){
        GuruProfileFragment1 fragment = new GuruProfileFragment1();
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof  MainGuruFragmentListener){
            this.listener = (MainGuruFragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.guru_profile1, container, false);
        this.tvName = view.findViewById(R.id.tv_name_profile_guru);
        this.tvBorn = view.findViewById(R.id.tv_born_guru);
        this.tvAddress = view.findViewById(R.id.tv_address_guru);
        this.tvSex = view.findViewById(R.id.tv_sex_guru);
        this.tvPhone = view.findViewById(R.id.tv_phone_guru);
        this.tvEmail = view.findViewById(R.id.tv_mail_guru);
        this.btnEdit = view.findViewById(R.id.edit_profile_guru);
        this.tvPelajaran = view.findViewById(R.id.tv_pelajaran_guru);
        this.tvPendidikan = view.findViewById(R.id.tv_pendidikan_guru);


        this.listener.setProfileFragment1();
        Guru g= ((Main2Activity)listener).g;
        this.tvBorn.setText(g.Nama_Kota+" ,"+g.Tanggal_Lahir);
        this.tvAddress.setText(g.getAlamat());
        this.tvSex.setText("Perempuan");
        this.tvPhone.setText(g.getNo_Telp());
        this.tvEmail.setText(g.getEmail());
        //this.tvPelajaran.setText(g.);
        //this.tvPendidikan.setText(g.);

        this.btnEdit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnEdit.getId()){
            this.listener.changePage(4);
        }
    }
}
