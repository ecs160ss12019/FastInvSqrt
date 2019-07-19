package com.example.ultrabreakout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import static java.lang.Math.abs;

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
    private ArrayList<Brick> bricks;
    private Level level;

    // Keeps track whether the main thread should be running or not.
    // Volatile so that it is thread-safe.
    private volatile boolean playing;

    // Keeps track if the game is paused for whatever reason (lost focus, etc).
    private boolean paused;

    // The main thread running the game.
    private Thread gameThread;

    long frameTimeNow, frameTimePrev;

    public UltraBreakout(Context context, int screenWidth, int screenHeight, Level level) {
        super(context);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.level = level;

        Brick.BRICK_WIDTH = screenWidth/Level.NUM_COLUMNS;
        Brick.BRICK_HEIGHT = screenHeight/Level.NUM_ROWS;

        // Initialize for drawing objects on screen.
        holder = getHolder();
        paint = new Paint();
        bricks = new ArrayList<>();

        // Actors and functions related to the game.
        paddle = new Paddle(500, 900);
        ball = new Ball(screenWidth - 500, 800, 300, -300);
        input = new Input(screenWidth, screenHeight);
        generateBricks();

        fps = 0;

        // Initialize paused game.
        paused = false;
        gameThread = null;

        frameTimeNow = frameTimePrev = System.currentTimeMillis();

        System.out.println("INITIALIZING THE GAME");
    }

    @Override
    public void run() {
        while(playing) {
            if (!paused) {
                // Calculate the frame rate for physics purposes.
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
        // First update the paddle velocity based on user input.
        if (input.isPressLeft() && (paddle.hitbox.left > 0)) {
            paddle.velocity.setVelocity(-Paddle.PADDLE_SPEED, 0);
        } else if (input.isPressRight() && (paddle.hitbox.right < screenWidth)) {
            paddle.velocity.setVelocity(Paddle.PADDLE_SPEED, 0);
        } else {
            paddle.velocity.setVelocity(0, 0);
        }
        //checks the bounds of the ball, and bounces back when it is about to go out of bounds
        if (ball.hitbox.right > screenWidth || ball.hitbox.left < 0){
            //FIXME: Ball can get stuck in infinite loop on left side
            ball.velocity.reverseX();
        }
        if (ball.hitbox.top > screenHeight || ball.hitbox.top < 0){
            ball.velocity.reverseY();
        }
        //checks if paddle hits the ball, and reflects it by the y axis if it does
        if (RectF.intersects(paddle.hitbox,ball.hitbox)){
            ball.velocity.reverseY();
        }

        ball.update(fps);
        paddle.update(fps);

        for (int i = bricks.size() - 1; i >= 0; i--) {
            if (RectF.intersects(bricks.get(i).hitbox, ball.hitbox)) {
                //Calculate which side of the brick the ball hit more
                //There's only vertical and horizontal hits; we always
                    //reverse y if we hit either top or bottom regardless
                //Take the min because we won't intersect more than halfway
                float vertical_dist = Math.min (
                        Math.abs(bricks.get(i).hitbox.bottom - ball.hitbox.top),
                        Math.abs(bricks.get(i).hitbox.top - ball.hitbox.bottom)
                        );
                float horizontal_dist = Math.min (
                        Math.abs(bricks.get(i).hitbox.left - ball.hitbox.right),
                        Math.abs(bricks.get(i).hitbox.right - ball.hitbox.left)
                        );
                if (vertical_dist >= horizontal_dist){
                    ball.velocity.reverseX();
                }
                else{
                    ball.velocity.reverseY();
                }
                bricks.remove(bricks.get(i));
                break;
            }
        }

        // TODO: Update all actors
        // TODO: Check to see collisions between actors
    }

    public void generateBricks(){
        for (int i = 0; i < level.NUM_ROWS; i++){
            for (int j = 0; j < level.NUM_COLUMNS; j++){
                if (level.csv_file_data.get(i).get(j).equals("1")) {
                    bricks.add(new Brick(Brick.BRICK_WIDTH * j, Brick.BRICK_HEIGHT * i));
                }
            }
        }
    }

    public void drawBricks() {
        for (Brick b : bricks) {
            paint.setColor(b.color);
            canvas.drawRect(b.hitbox.left, b.hitbox.top, b.hitbox.right, b.hitbox.bottom, paint);
        }
    }

    void draw() {
        if (holder.getSurface().isValid()) {
            // Lock the canvas, so we can start drawing.
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.rgb(255, 255, 255));

            drawBricks();

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
        gameThread = new Thread(this);
        gameThread.start();
    }
}
