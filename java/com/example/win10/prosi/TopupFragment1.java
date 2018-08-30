package com.example.win10.prosi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TopupFragment1 extends Fragment implements View.OnClickListener{

    protected TopupFragmentListener listener;

    protected Button addButton;
    protected TextView tvSaldo;
    protected ImageView ivProfile;

    public TopupFragment1(){

    }

    public static TopupFragment1 newInstance(){
        TopupFragment1 fragment = new TopupFragment1();
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof TopupFragmentListener){
            this.listener = (TopupFragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString()+" must implement FragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.topup1, container, false);
        this.addButton = view.findViewById(R.id.btn_add_saldo);
        this.tvSaldo = view.findViewById(R.id.tv_saldo);
        this.ivProfile = view.findViewById(R.id.iv_profile_coin);
        this.addButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.addButton.getId()){
            this.listener.changePage(2);
        }
    }
}
