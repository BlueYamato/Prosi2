package com.example.win10.prosi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TopupFragment2 extends Fragment {

    protected ListView lstPaket;

    protected TopupFragmentListener listener;

    public TopupFragment2(){

    }

    public static TopupFragment2 newInstance(){
        TopupFragment2 fragment = new TopupFragment2();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.topup2, container, false);
        this.lstPaket = view.findViewById(R.id.list_coin);

        return view;
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
}
