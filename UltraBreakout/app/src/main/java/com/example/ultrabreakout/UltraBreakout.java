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

    private Paddle paddle;
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
        paddle = new Paddle(500, 750);
        ball = new Ball(50, 50, 100, 100);
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
        if (input.isPressLeft() && (paddle.hitbox.left > 0)) {
            paddle.velocity.setVelocity(-100, 0);
        } else if (input.isPressRight() && (paddle.hitbox.right < screenWidth)) {
            paddle.velocity.setVelocity(100, 0);
        } else {
            paddle.velocity.setVelocity(0, 0);
        }
        //checks the bounds of the ball, and bounces back when it is about to go out of bounds
        if (ball.hitbox.right > screenWidth || ball.hitbox.left < 0){
            ball.velocity.x = -ball.velocity.x;
        }
        if (ball.hitbox.top > screenHeight || (ball.hitbox.bottom + ball.height) < 0){
            ball.velocity.y = -ball.velocity.y;
        }
        //checks if paddle hits the ball, and reflects it by the y axis if it does
        if (RectF.intersects(paddle.hitbox,ball.hitbox)){
            ball.velocity.y = -ball.velocity.y;
        }
        ball.update(fps);
        paddle.update(fps);


        // TODO: Update all actors
        // TODO: Check to see collisions between actors
    }

    void draw() {
        if (holder.getSurface().isValid()) {
            // Lock the canvas, so we can start drawing.
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.rgb(255, 255, 255));

            paint.setColor(ball.color);
            canvas.drawRect(ball.hitbox.left, ball.hitbox.top, ball.hitbox.right, ball.hitbox.bottom, paint);

            paint.setColor(paddle.color);
            canvas.drawRect(paddle.hitbox.left, paddle.hitbox.top, paddle.hitbox.right, paddle.hitbox.bottom, paint);

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
