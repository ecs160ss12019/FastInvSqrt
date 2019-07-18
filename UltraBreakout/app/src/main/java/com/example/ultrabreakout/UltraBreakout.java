package com.example.ultrabreakout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class UltraBreakout extends SurfaceView implements Runnable {

    private int screenWidth;
    private int screenHeight;

    // Keeps track of fps for physics and updating purposes.
    private float fps;

    // Used for drawing objects on screen.
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;

    private Ball ball;
    private Input input;

    // Keeps track whether the main thread should be running or not.
    // Volatile so that it is thread-safe.
    private volatile boolean playing;

    // Keeps track if the game is paused for whatever reason (lost focus, etc).
    private boolean paused;

    // The main thread running the game.
    private Thread gameThread;

    long frameTimeNow, frameTimePrev;

    public UltraBreakout(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Initialize for drawing objects on screen.
        holder = getHolder();
        paint = new Paint();

        // Actors and functions related to the game.
        ball = new Ball(50, 50, 50, 50);
        input = new Input(screenWidth, screenHeight);

        fps = 0;

        // Initialize paused game.
        paused = true;
        gameThread = null;

        frameTimeNow = frameTimePrev = System.currentTimeMillis();

        System.out.println("INITIALIZING THE GAME");
    }

    @Override
    public void run() {
        while(playing) {
            if (!paused) {
                frameTimeNow = System.currentTimeMillis();
                fps = 1000 / ((float)(frameTimeNow - frameTimePrev));

                if (fps > 0) {
                    update();
                }


                draw();
                frameTimePrev = frameTimeNow;
            }
        }
    }

    public void update() {
        ball.update(fps);

        // TODO: Update all actors
        // TODO: Check to see collisions between actors
    }

    void draw() {
        if (holder.getSurface().isValid()) {
            // Lock the canvas, so we can start drawing.
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.rgb(255, 255, 255));

            paint.setColor(Color.rgb(255, 0, 0));
            canvas.drawRect(ball.hitbox.left, ball.hitbox.top, ball.hitbox.right, ball.hitbox.bottom, paint);

            holder.unlockCanvasAndPost(canvas);
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
        paused = false;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
