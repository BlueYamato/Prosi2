package com.example.win10.prosi;

/**
 * Created by i15028 on 28/03/2018.
 */

public class ImageData {
    String image;
    String text;
    String title;

    public ImageData(String image,String title,String text){
        this.image=image;
        this.text=text;
        this.title=title;
    }

    public ImageData(String text,String title){

        this.text=text;
        this.title=title;

    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getTittle(){
        return this.title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String returnEverything(){
        return this.image+","+this.text;
    }


}
