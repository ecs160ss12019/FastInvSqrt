package com.example.ultrabreakout;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class UltraBreakout extends SurfaceView implements Runnable {

    private int screenWidth;
    private int screenHeight;

    private Input input;

    // Keeps track whether the main thread should be running or not.
    // Volatile so that it is thread-safe.
    private volatile boolean playing;

    // Keeps track if the game is paused for whatever reason (lost focus, etc).
    private boolean paused;

    // The main thread running the game.
    private Thread gameThread;

    public UltraBreakout(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        input = new Input(screenWidth, screenHeight);

        // Initialize paused game.
        paused = true;
        gameThread = null;

        System.out.println("INITIALIZING THE GAME");
    }

    @Override
    public void run() {
        while(playing) {
            // Testing purposes
            System.out.println(Boolean.toString(input.isPressLeft()) + " " + Boolean.toString(input.isPressRight()));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                input.touchDownEvent(motionEvent.getX(), motionEvent.getY());
                break;
            case MotionEvent.ACTION_UP:
                input.touchUpEvent(motionEvent.getX(), motionEvent.getY());
                break;
        }

        return true;
    }

    void draw() {
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            System.err.println("Could not pause game, error joining thread: " + e.getMessage());
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
