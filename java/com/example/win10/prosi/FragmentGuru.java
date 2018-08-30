package com.example.win10.prosi;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by i15028 on 28/02/2018.
 */
//daftarguru
public class FragmentGuru extends Fragment implements View.OnClickListener,DatePickerDialog.OnDateSetListener,KotaProvListener {
    private EditText username,password,alamat,nohp,nama,tanggal,email;
    protected Spinner kota,provinsi,pendidikan,mengajar;
    private FragmentListener listener;
    private Button btn,btn_murid;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterKot;
    ArrayAdapter<CharSequence> adapterProv;
    ProvKotaAsyncTask prov;
    private int _day;
    private int _month;
    private int _birthYear;
    Calendar myCalendar = Calendar.getInstance();

    public FragmentGuru(){

    }
    public static FragmentGuru newInstance(String title){
        FragmentGuru fragment = new FragmentGuru();
        Bundle args = new Bundle();
        args.putString("guru",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_guru,container,false);
        this.btn = view.findViewById(R.id.btn_daftar);
        this.btn_murid=view.findViewById(R.id.btn_murid);
        this.btn_murid.setOnClickListener(this);
        this.btn.setOnClickListener(this);
        this.username = view.findViewById(R.id.et_username);
        this.nama = view.findViewById(R.id.et_nama);
        this.password = view.findViewById(R.id.et_password);
        this.alamat = view.findViewById(R.id.et_alamat);
        this.nohp = view.findViewById(R.id.et_hp);
        this.kota=view.findViewById(R.id.spinner_kota);
        this.provinsi=view.findViewById(R.id.spinner_provinsi);
        this.pendidikan=view.findViewById(R.id.spinner_pendidikan);
        this.mengajar=view.findViewById(R.id.spinner_mengajar);
        this.tanggal=view.findViewById(R.id.date_tanggal);
        this.email=view.findViewById(R.id.et_email);

        this.tanggal.setOnClickListener(this);

        ProvKotaAsyncTask temp = new ProvKotaAsyncTask(this);
        temp.execute("kosong");
        prov = new ProvKotaAsyncTask(this);


        PendidikanAsyncTask pat = new PendidikanAsyncTask(this);
        pat.execute("null");

        this.tanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                DatePickerDialog dialog = new DatePickerDialog(FragmentGuru.this.getContext(), FragmentGuru.this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
        }});

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


    public void isiArrayPendidikan(String[] tipe){
        ArrayAdapter<CharSequence> adapter;

            adapter = new ArrayAdapter<CharSequence>(this.getActivity(),android.R.layout.simple_spinner_item,tipe);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.pendidikan.setAdapter(adapter);
        this.mengajar.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public void isiArray(String[] tipe){

        adapterKot = new ArrayAdapter<CharSequence>(this.getActivity(),android.R.layout.simple_spinner_item,tipe);

        adapterKot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        this.kota.setAdapter(adapterKot);
        adapterKot.notifyDataSetChanged();

    }
    @Override
    public void isiProv(String[] tipe){

        adapterProv = new ArrayAdapter<CharSequence>(this.getActivity(),android.R.layout.simple_spinner_item,tipe);

        adapterProv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.provinsi.setAdapter(adapterProv);
        adapterProv.notifyDataSetChanged();

    }

    private void updateDisplay() {

        tanggal.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("-").append(_month + 1).append("-").append(_birthYear));
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==btn.getId()){
            //MainActivityDaftar mad=(MainActivityDaftar)listener;
            //mad.startIntent();
            listener.changePage(3);


            PostAsyncTask post = new PostAsyncTask(this.getContext());
            String val = username.getText().toString()+","+password.getText().toString()+","+alamat.getText()+"guru";
            System.out.println(val);
            post.execute(val);
            //validasi menyusul
            //Guru g = new Guru(username.getText().toString(),password.getText().toString(),alamat.getText().toString(),kota.getSelectedItem().toString(),provinsi.getSelectedItem().toString(),nohp.getText().toString());
            //tutup kirim data
        }
        if(v.getId()==btn_murid.getId()){
            listener.changePage(1);
        }
    }

    public String getData(){
        return username.getText().toString()+","+password.getText().toString()+","+nama.getText()+","+kota.getSelectedItem().toString()+","+pendidikan.getSelectedItem().toString()+","+mengajar.getSelectedItem().toString()+","+tanggal.getText().toString()+","+email.getText().toString()+","+alamat.getText().toString()+","+nohp.getText();
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
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        _birthYear = i;
        _month = i1;
        _day = i2;
        updateDisplay();
    }
}
