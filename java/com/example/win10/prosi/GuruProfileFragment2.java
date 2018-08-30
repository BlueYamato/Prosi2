package com.example.win10.prosi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class GuruProfileFragment2 extends Fragment implements View.OnClickListener{

    protected MainGuruFragmentListener listener;


    protected TextView etName;
    protected TextView etBorn;
    protected TextView etAddress;
    protected Spinner spSex;
    protected TextView etPhone;
    protected TextView etEmail;
    protected TextView etPendidikan;
    protected TextView etPelajaran;
    protected Button btnOk;
    protected Button btnCancel;

    public GuruProfileFragment2(){

    }

    public static GuruProfileFragment2 newInstance(){
        GuruProfileFragment2 fragment = new GuruProfileFragment2();
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
        View view = inflater.inflate(R.layout.guru_profile2, container, false);
        this.etName = view.findViewById(R.id.et_name_profile_guru);
        this.etBorn = view.findViewById(R.id.et_born_guru);
        this.etAddress = view.findViewById(R.id.et_address_guru);
        this.spSex = view.findViewById(R.id.et_sex_guru);
        this.etPhone = view.findViewById(R.id.et_phone_guru);
        this.etEmail = view.findViewById(R.id.et_mail_guru);
        this.etPendidikan = view.findViewById(R.id.et_pendidikan_guru);
        this.etPelajaran = view.findViewById(R.id.et_pelajaran_guru);
        this.btnOk = view.findViewById(R.id.btn_ok_profile_guru);
        this.btnCancel = view.findViewById(R.id.btn_cancel_profile_guru);


        this.listener.sameDataProfile();
        this.listener.sameSpinnerSelect();
        this.btnCancel.setOnClickListener(this);
        this.btnOk.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnOk.getId()){
            this.listener.changeProfile();
            this.listener.changePage(3);
        }
        if(v.getId()==this.btnCancel.getId()){
            this.listener.changePage(3);
        }
    }
}
