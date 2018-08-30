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

/**
 * Created by i15028 on 28/02/2018.
 */
//tampilan untuk murid

public class FragmentGuruPilihPel extends Fragment implements View.OnClickListener{

    private Spinner pel1,pel2,pel3;
    private FragmentListener listener;
    private Button btn;
    PelajaranAsyncTask pel;
    static String namaPelajaran="kosong";
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterProv;
    public FragmentGuruPilihPel(){

    }
    public static FragmentGuruPilihPel newInstance(String title){
        FragmentGuruPilihPel fragment = new FragmentGuruPilihPel();
        Bundle args = new Bundle();
        args.putString("Murid",title);
        namaPelajaran=title;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){



        View view = inflater.inflate(R.layout.daftarguru_pilihpelajaran,container,false);
        this.btn = view.findViewById(R.id.selesai);
        this.btn.setOnClickListener(this);
        this.pel1 = view.findViewById(R.id.spinner_pelajaran1);
        this.pel2 = view.findViewById(R.id.spinner_pelajaran2);
        this.pel3 = view.findViewById(R.id.spinner_pelajaran3);
        pel = new PelajaranAsyncTask(this);
        pel.execute(namaPelajaran);
        return view;
    }
    public void isiArray(String[] tipe){

            adapter = new ArrayAdapter<CharSequence>(this.getActivity(),android.R.layout.simple_spinner_item,tipe);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        this.pel1.setAdapter(adapter);
        this.pel2.setAdapter(adapter);
        this.pel3.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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

    public void setText(String id){
        this.namaPelajaran=id;
        pel = new PelajaranAsyncTask(this);
        pel.execute(namaPelajaran);
    }

    public String getPel(){
        String res ="";
        if(!pel1.getSelectedItem().toString().equals(pel2.getSelectedItem().toString())){
            if(!pel1.getSelectedItem().toString().equals(pel3.getSelectedItem().toString())&&!pel2.getSelectedItem().toString().equals(pel3.getSelectedItem().toString())){
                    return pel1.getSelectedItem().toString()+","+pel2.getSelectedItem().toString()+","+pel3.getSelectedItem().toString();
                }
                else{
                    return pel1.getSelectedItem().toString()+","+pel2.getSelectedItem().toString();
                }
            }
            else if(pel2.getSelectedItem().toString()!=pel3.getSelectedItem().toString()){
                return  pel2.getSelectedItem().toString()+","+pel3.getSelectedItem().toString();
            }
            else{
                return pel1.getSelectedItem().toString();
            }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn.getId()) {
            //listener.changePage(4);
            System.out.println(this.getPel());

            ((MainActivityDaftar)this.listener).startIntent();
            //PostAsyncTask post = new PostAsyncTask(this.getContext());
            //String val = username.getText().toString()+","+password.getText().toString()+","+nama.getText()+",murid"+","+email.getText();
            //System.out.println(val);
            // post.execute(val);
            //validasi menyusul
            //Murid m = new Murid(username.getText().toString(),password.getText().toString(),alamat.getText().toString(),kota.getSelectedItem().toString(),provinsi.getSelectedItem().toString(),nohp.getText().toString());
            //tutup kirim data
        }
        //else if(v.getId()==btnPhoto.getId(){
        //MainActivityDaftar mad=(MainActivityDaftar)listener;
        //mad.startIntent();
        //}
        //else if(v.getId()==btnGaleri.getId(){
        //MainActivityGalery mad=(MainActivityDaftar)listener;
        //mad.startIntent();
        //}
    }
}
