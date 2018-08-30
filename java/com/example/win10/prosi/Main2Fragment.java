package com.example.win10.prosi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Fragment extends Fragment{
        protected MainGuruFragmentListener listener;

        protected Context context;

        public Main2Fragment(){

        }

        public static Main2Fragment newInstance(){
            Main2Fragment fragment = new Main2Fragment();
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
            View view = inflater.inflate(R.layout.content_main2, container, false);
            this.context = view.getContext();

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

