package com.example.win10.prosi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


/**
 * Created by i15028 on 08/03/2018.
 */

public class ImageDataAdapter extends BaseAdapter{
    private MainActivityCamera activity;
    private ArrayList<ImageData> id;
    private MainPresenter presenter;

    public ImageDataAdapter(MainActivityCamera activity, MainPresenter presenter) {
        this.activity = activity;
        this.presenter=presenter;
        this.id = presenter.getIdl();

    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return id.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.image_list_item, viewGroup, false);
            vh = new ViewHolder(convertView, this.presenter);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }
        final ImageData curData = (ImageData) this.getItem(i);
        //System.out.println(curData.getText()+" manakutau "+curData.getTittle());
        vh.updateView(curData);
        /*vh.star.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vh.updateView(curData);
            }
        });
        vh.delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteList(index);
            }
        });*/
        return convertView;
    }

    public void updateList(){
        this.id = presenter.getIdl();
        notifyDataSetChanged();
    }

    private class ViewHolder{
        protected TextView title;
        protected ImageView image;
        protected MainPresenter presenter;

        public ViewHolder(View view, MainPresenter presenter) {
            this.image = view.findViewById(R.id.image_data);
            this.title = view.findViewById(R.id.tv_text_title);
            this.presenter = presenter;
        }

        public void updateView(ImageData id){
            //File file = new File(activity.getFilesDir(),id.getTttle()+".jpg");

                byte[] b = Base64.decode(id.getImage(), Base64.DEFAULT);
                Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
                this.title.setText(id.getTittle());
                this.image.setImageBitmap(bm);

        }
    }
}
