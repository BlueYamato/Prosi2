package com.example.win10.prosi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment{

    protected MainMuridFragmentListener listener;

    protected ImageView fiturCariGuru;
    protected ImageView fiturChat;
    protected ImageView fiturKelompok;
    protected ImageView fiturAbsen;
    protected Context context;

    public MainFragment(){

    }

    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.content_main, container, false);
        this.context = view.getContext();
        this.fiturCariGuru = view.findViewById(R.id.iv_guru);
        this.fiturChat = view.findViewById(R.id.iv_chat);
        this.fiturKelompok = view.findViewById(R.id.iv_bersama);
        this.fiturAbsen = view.findViewById(R.id.iv_absen);


           this.fiturCariGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FiturPrivatActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    /*@Override
    public void onClick(View v) {
        if(v.getId()==this.fiturCariGuru.getId()){
            Log.d("debugs", "onClick: clicked");
            Intent intent = new Intent(getActivity().getBaseContext() , FiturPrivatActivity.class);
            startActivity(intent);
        }
    }*/
}
