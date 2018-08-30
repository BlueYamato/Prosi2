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

public class ProfileFragment2 extends Fragment implements View.OnClickListener{

    protected MainMuridFragmentListener listener;


    protected TextView etName;
    protected TextView etBorn;
    protected TextView etAddress;
    protected Spinner spSex;
    protected TextView etPhone;
    protected TextView etEmail;
    protected Button btnOk;
    protected Button btnCancel;

    public ProfileFragment2(){

    }

    public static ProfileFragment2 newInstance(){
        ProfileFragment2 fragment = new ProfileFragment2();
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
        View view = inflater.inflate(R.layout.profile_fragment2, container, false);
        this.etName = view.findViewById(R.id.et_name_profile);
        this.etBorn = view.findViewById(R.id.et_born);
        this.etAddress = view.findViewById(R.id.et_address);
        this.spSex = view.findViewById(R.id.et_sex);
        this.etPhone = view.findViewById(R.id.et_phone);
        this.etEmail = view.findViewById(R.id.et_mail);
        this.btnOk = view.findViewById(R.id.btn_ok_profile);
        this.btnCancel = view.findViewById(R.id.btn_cancel_profile);
        this.listener.sameDataProfile();
        this.listener.sameSpinnerSelect();
        this.btnCancel.setOnClickListener(this);
        this.btnOk.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnOk.getId()){
            Murid m = ((MainActivity)listener).m;
            m.setNama(etName.getText().toString());
            m.setAlamat(etAddress.getText().toString());
            m.setNo_Telp(etPhone.getText().toString());
            m.setEmail(etEmail.getText().toString());
            ((MainActivity)listener).m = m;
            UbahProfileAsyncTask upat = new UbahProfileAsyncTask(((MainActivity)listener));
            upat.execute(m.ID_Account+","+etName.getText().toString()+","+etEmail.getText().toString()+","+etAddress.getText().toString());
            this.listener.changeProfile();
            this.listener.changePage(3);
        }
        if(v.getId()==this.btnCancel.getId()){
            this.listener.changePage(3);
        }
    }
}
