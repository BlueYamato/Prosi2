package com.example.win10.prosi;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i15028 on 28/02/2018.
 */
//tampilan untuk murid

public class FragmentMurid extends Fragment implements View.OnClickListener,KotaProvListener{
    private EditText username,password,alamat,nohp,nama,email,konfirmasi;
    private Spinner kota,provinsi;
    private FragmentListener listener;
    private Button btn,btnGuru;
    ProvKotaAsyncTask prov;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterProv;
    public FragmentMurid(){

    }
    public static FragmentMurid newInstance(String title){
        FragmentMurid fragment = new FragmentMurid();
        Bundle args = new Bundle();
        args.putString("Murid",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){



        View view = inflater.inflate(R.layout.fragment_murid,container,false);
        this.btn = view.findViewById(R.id.btn_daftar);
        this.btn.setOnClickListener(this);
        this.btnGuru = view.findViewById(R.id.btn_guru);
        this.btnGuru.setOnClickListener(this);
        this.username = view.findViewById(R.id.et_username);
        this.password = view.findViewById(R.id.et_password);
        this.alamat = view.findViewById(R.id.et_alamat);
        this.nohp = view.findViewById(R.id.et_hp);
        this.nama = view.findViewById(R.id.et_nama);
        this.email = view.findViewById(R.id.et_email);
        this.kota=view.findViewById(R.id.spinner_kota);
        this.provinsi=view.findViewById(R.id.spinner_provinsi);
        this.konfirmasi=view.findViewById(R.id.et_konfirmasi);

        ProvKotaAsyncTask temp = new ProvKotaAsyncTask(this);
        temp.execute("kosong");
        prov = new ProvKotaAsyncTask(this);



        provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                prov=new ProvKotaAsyncTask(prov.main);
                    prov.execute(parentView.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return view;
    }
    @Override
    public void isiArray(String[] tipe){

            adapter = new ArrayAdapter<CharSequence>(this.getActivity(),android.R.layout.simple_spinner_item,tipe);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        this.kota.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public void isiProv(String[] tipe){

        adapterProv = new ArrayAdapter<CharSequence>(this.getActivity(),android.R.layout.simple_spinner_item,tipe);

        adapterProv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.provinsi.setAdapter(adapterProv);
        adapterProv.notifyDataSetChanged();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==btn.getId()){
            System.out.println(username.getText().toString());
            System.out.println(password.getText().toString());
            if(username.getText().toString().length()<4 || username.getText().toString().length()>10){
                Toast.makeText(this.getActivity(), "Please fill username matching required length", Toast.LENGTH_SHORT).show();
            }
            else if(password.getText().toString().length()<4 || password.getText().toString().length()>10) {
                Toast.makeText(this.getActivity(), "Please fill password matching required length", Toast.LENGTH_SHORT).show();
            }
            else{

                if (password.getText().toString().equals(konfirmasi.getText().toString())) {
                    PostAsyncTask post = new PostAsyncTask(this.getContext());
                    String val = username.getText().toString() + "," + password.getText().toString() + "," + nama.getText() + ",murid" + "," + email.getText() + "," + kota.getSelectedItem().toString() + "," + nohp.getText() + "," + alamat.getText();
                    System.out.println(val);
                    post.execute(val);
                    listener.tutup();
                }else{
                    System.out.println(password.getText().toString()+" "+konfirmasi.getText().toString());
                }
            }
            //validasi menyusul
            //Murid m = new Murid(username.getText().toString(),password.getText().toString(),alamat.getText().toString(),kota.getSelectedItem().toString(),provinsi.getSelectedItem().toString(),nohp.getText().toString());
            //tutup kirim data
        }
        if(v.getId()==btnGuru.getId()){
            listener.changePage(2);
        }
    }
}
