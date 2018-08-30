package com.example.win10.prosi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BantuanFragment extends Fragment {

    protected MainMuridFragmentListener listener;


    public BantuanFragment(){

    }

    public static BantuanFragment newInstance(){
        BantuanFragment fragment = new BantuanFragment();
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
        View view = inflater.inflate(R.layout.bantuan_fragment, container, false);

        return view;
    }

}
