package com.example.win10.prosi;

/**
 * Created by WIN 10 on 4/9/2018.
 */

/**
 * Created by WIN 10 on 3/3/2018.
 */

public class ThreadedActivity implements  Runnable{
    private MainActivityZoom main;
    public ThreadedActivity(MainActivityZoom main){
        this.main=main;
    }
    @Override
    public void run() {
        this.main.initiateCanvas();
    }
}