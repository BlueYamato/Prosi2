package com.example.win10.prosi;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by i15028 on 28/02/2018.
 */
//daftarguru
public class FragmentSuccess extends Fragment implements View.OnClickListener {

    private FragmentListener listener;
    private Button btn_selesai;
    public FragmentSuccess(){

    }
    public static FragmentSuccess newInstance(String title){
        FragmentSuccess fragment = new FragmentSuccess();
        Bundle args = new Bundle();
        args.putString("guru",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.daftarguru_success,container,false);

        this.btn_selesai=view.findViewById(R.id.selesai);
        this.btn_selesai.setOnClickListener(this);

        return view;
    }




    @Override
    public void onClick(View v) {

        if(v.getId()==btn_selesai.getId()){
            listener.tutup();
        }
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
}
