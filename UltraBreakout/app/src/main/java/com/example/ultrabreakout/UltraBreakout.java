package com.example.ultrabreakout;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import static com.example.ultrabreakout.Actor.sprites;
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
    private ArrayList<Spike> spikes;
    private Level level;
    private int lives;
    private Stats stats;

    private Sound sound;

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
        sprites = getResources();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.level = level;

        Brick.BRICK_WIDTH = screenWidth/Level.NUM_COLUMNS;
        Brick.BRICK_HEIGHT = screenHeight / (Level.NUM_ROWS * 2);
        Spike.SPIKE_WIDTH = screenWidth/Level.NUM_COLUMNS;
        Spike.SPIKE_HEIGHT = (screenHeight * 4) / (Level.NUM_ROWS * 2);
        // Initialize for drawing objects on screen.
        holder = getHolder();
        paint = new Paint();
        bricks = new ArrayList<>();
        spikes = new ArrayList<>();
        // Actors and functions related to the game.
        stats = new Stats();
        ball = new Ball(screenWidth/2 - ball.BALL_WIDTH/2, screenHeight - paddle.PADDLE_HEIGHT * 8, 0, 0);
        ball.sprite = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        paddle = new Paddle((screenWidth/2) - paddle.PADDLE_WIDTH/2, screenHeight - paddle.PADDLE_HEIGHT * 4);
        input = new Input(screenWidth, screenHeight);
        generateBricks();

        sound = Sound.getInstance();
        sound.play_background(context, R.raw.background_2);

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
            if (stats.lives <= 0){
                gameOver();
            }
        }
    }
    public void gameOver(){
        System.out.println("GAME OVER");
        restart();
    }


    public void restart(){
        paddle.reposition((screenWidth/2) - paddle.PADDLE_WIDTH/2, paddle.hitbox.top);
        paddle.velocity.setSpeed(0);
        ball.reset(paddle);
        input = new Input(screenWidth, screenHeight);
        generateBricks();
        stats = new Stats();
    }

    /* On collision with a special brick, handle any powerup drops.
     *
     * The different powerup types are documented in the Paddle class.
     */
    public void handlePowerup(Brick.PowerUpType powerup) {
        switch (powerup) {
            case PADDLE_WIDTH_INCREASE:
                paddle.paddleWidthIncrease();
                break;
        }
    }

    public void update() {
        stats.updatetime();

        // First update the paddle velocity based on user input.
        if (ball.velocity.x == 0 && ball.velocity.y == 0 && (input.isPressLeft() || input.isPressRight())){
            ball.velocity.setVelocity(Ball.X_VELOCITY, -Ball.Y_VELOCITY);
        }
        //checks the bounds of the ball, and bounces back when it is about to go out of bounds
        if (ball.hasFallen(screenHeight)){
            stats.lives -= 1;
            ball.reset(paddle);
        }

        //checks if paddle hits the ball, and reflects it by the y axis if it does
        if (paddle.intersects(ball) && ball.velocity.y > 0){
            // Change the x velocity based on where the ball hit the paddle.
            // Ex if the ball hits on the left side of the paddle, it will
            // move to the left side of the screen.
            float x_diff = ball.hitbox.centerX() - paddle.hitbox.centerX();
            float x_velocity = (x_diff / (paddle.width / 2)) * Ball.X_VELOCITY;
            ball.velocity.setVelocity(x_velocity, ball.velocity.y);

            ball.velocity.reverseY();
        }

        // Check to see if ball is colliding with any bricks, and handle if so.
        //this double forloop checks if the brick hits 2 bricks, and if it does it will reverse its velocity once
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick1 = bricks.get(i);
            for (int j = 0; j < bricks.size(); j++){
                Brick brick2 = bricks.get(j);
                if (i != j && brick1.intersects(ball)
                        && brick2.intersects(ball)) {
                    handlePowerup(brick1.powerup);
                    handlePowerup(brick2.powerup);
                    brick1.Update(ball);
                    bricks.remove(i);
                    bricks.remove(j);
                    break;
                } else if (brick1.intersects(ball)) {
                    handlePowerup(brick1.powerup);
                    brick1.Update(ball);
                    bricks.remove(i);
                    break;
                } else {
                    break;
                }

            }

        }

        // Check to see if ball is colliding with spikes, and handle if so.
        for (int i = 0; i < spikes.size(); i++) {
            if (spikes.get(i).intersects(ball)) {
                stats.lives -= 1;
                ball.reset(paddle);
                break;
            }
        }

        ball.update(fps, screenWidth);
        paddle.update(fps, input, screenWidth);


        // TODO: Update all actors
        // TODO: Check to see collisions between actors
    }

    public void generateBricks(){
        for (int i = 0; i < level.NUM_ROWS; i++){
            for (int j = 0; j < level.NUM_COLUMNS; j++){
                if (level.csv_file_data.get(i).get(j).equals("1")) {

                    // A random chance to generate a powerup block.
                    if (Math.random() > 0.9) {
                        Brick brick = new Brick(Brick.BRICK_WIDTH * j, Brick.BRICK_HEIGHT * i * 2, Brick.PowerUpType.PADDLE_WIDTH_INCREASE);
                        brick.setSprite(BitmapFactory.decodeResource(sprites,R.drawable.breakout_tiles_48));
                        bricks.add(brick);
                    } else {
                        Brick brick = new Brick(Brick.BRICK_WIDTH * j, Brick.BRICK_HEIGHT * i * 2, Brick.PowerUpType.NONE);
                        bricks.add(brick);
                    }
                }
                if (level.csv_file_data.get(i).get(j).equals("2")) {
                    spikes.add(new Spike(Spike.SPIKE_WIDTH * j, Spike.SPIKE_HEIGHT * i));
                }
            }
        }
    }


    public void drawBricks() {
        for (Brick b : bricks) {
            canvas.drawBitmap(b.sprite, null, b.hitbox, null);
        }
    }
    public void drawSpikes() {
        for (Spike s : spikes) {
            canvas.drawBitmap(s.sprite, null, s.hitbox, null);
        }
    }

    void draw() {
        if (holder.getSurface().isValid()) {
            // Lock the canvas, so we can start drawing.
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.rgb(0, 0, 0));

            drawBricks();
            drawSpikes();
            canvas.drawBitmap(ball.sprite, null, ball.hitbox,null);
            canvas.drawBitmap(paddle.sprite, null, paddle.hitbox,null);

            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            canvas.drawText("Lives: " + stats.lives,
                    screenWidth/2 - 870,
                    screenHeight/2 + 450, paint);

//            canvas.drawText("TimeElapsed: " + stats.timeelpased,
//                    screenWidth/2 - 870,
//                    screenHeight/2 + 450, paint);

            holder.unlockCanvasAndPost(canvas);


        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int index = motionEvent.getActionIndex();
        float x = motionEvent.getX(index);
        float y = motionEvent.getY(index);

        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                input.touchDownEvent(x, y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                input.touchUpEvent(x, y);
                break;
        }

        return true;
    }

    public void pause() {
        sound.pause();
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            System.err.println("Could not pause game, error joining thread: " + e.getMessage());
        }
    }

    public void resume() {
        sound.resume();
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void destroy() {
        paddle.destroy();
    }
}
