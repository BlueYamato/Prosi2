package com.example.win10.prosi;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LesOfflineAdapter extends BaseAdapter {
    protected LesPrivatActivity activity;
    protected PresenterListGuru presenter;
    protected List<String> dataPengajar;

    public LesOfflineAdapter(LesPrivatActivity activity, PresenterListGuru presenter){
        this.presenter = presenter;
        this.activity = activity;
        this.dataPengajar = this.presenter.dataList;
    }

    public void update(List<String> pengajar){
        this.dataPengajar = pengajar;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.dataPengajar.size();
    }

    @Override
    public Object getItem(int i) {
        return this.dataPengajar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder viewholder;

        if(view==null){
            view= this.activity.getLayoutInflater().inflate(R.layout.list_guru_layout,null);
            viewholder = new ViewHolder(view, this.presenter);
            view.setTag(viewholder);
        }else{
            viewholder = (ViewHolder) view.getTag();
        }
        final String currentData = (String) this.getItem(i);
        viewholder.updateView(currentData);

        return view;
    }
}

class ViewHolder{

    protected TextView tvNama;
    protected TextView tvPendidikan;

    protected PresenterListGuru presenter;

    final int size = 64;

    public ViewHolder(View view, PresenterListGuru presenter){
        this.tvNama = view.findViewById(R.id.tv_nama_guru);
        this.tvPendidikan = view.findViewById(R.id.tv_pendidikan);
        this.presenter = presenter;
    }

    public void updateView(String text){
        String temp = text.substring(0,text.indexOf("|"));
        String val = text.substring(1+text.lastIndexOf("|"));
        this.tvNama.setText(temp);
        this.tvPendidikan.setText(val);
    }
}
