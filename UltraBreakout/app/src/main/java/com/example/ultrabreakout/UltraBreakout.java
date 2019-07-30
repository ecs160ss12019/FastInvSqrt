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

    private Input input;

    private ArrayList<Paddle> paddles;
    private ArrayList<Ball> balls;
    private ArrayList<Actor> actors;
    private Level level;
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
        // Actors and functions related to the game.
        stats = new Stats();
        input = new Input(screenWidth, screenHeight);
        generateActors();

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
        paddles.get(0).reposition(
                (screenWidth/2) - Paddle.PADDLE_WIDTH/2,
                paddles.get(0).hitbox.top);
        paddles.get(0).velocity.setSpeed(0);
        balls.get(0).reset(paddles.get(0));
        input = new Input(screenWidth, screenHeight);
        //generateActors();
        stats = new Stats();
    }

    public void update() {
        stats.updatetime();
        for (int i = balls.size() - 1; i >= 0; i--) {
            // First update the paddle velocity based on user input.
            if (balls.get(i).velocity.x == 0 && balls.get(i).velocity.y == 0 && (input.isPressLeft() || input.isPressRight())) {
                balls.get(i).velocity.setVelocity(Ball.X_VELOCITY, -Ball.Y_VELOCITY);
            }
            //checks the bounds of the ball, dies if below the screen
            if (balls.get(i).hasFallen(screenHeight)) {
                stats.decrementLives();
                balls.get(i).die(paddles.get(0), balls.size());
            }
        }

        for (Paddle paddle : paddles) {
            for (Ball ball : balls) {
                //checks if paddle hits the ball, and reflects it by the y axis if it does
                if (paddle.intersects(ball) && ball.velocity.y > 0) {
                    // Change the x velocity based on where the ball hit the paddle.
                    // Ex if the ball hits on the left side of the paddle, it will
                    // move to the left side of the screen.
                    paddle.collide(ball);
                }
            }
        }

        // checkCollisions checks to see if ball is colliding with any bricks, and handle if so.
        //it also checks if the paddle hits an item, so you can get a powerup
        checkCollisions(actors);
    }

    public void checkCollisions(ArrayList<? extends Actor> actor_list){
        for (int i = actor_list.size() - 1; i >= 0; i--){
            Actor curActor = actor_list.get(i);
            for (int j = balls.size() - 1; j >= 0; j--){
                Ball ball = balls.get(j);
                if (curActor.intersects(ball)){
                    switch (curActor.getClass().getSimpleName()){
                        case("Brick"):
                            Brick curBrick = ((Brick)curActor);
                            curBrick.collide(ball);
                            curBrick.decrementHealth();
                            if (curBrick.returnHealth() == 1) {
                                curBrick.setBrokenSprite();
                            }
                            else if(curBrick.returnHealth() <= 0) {
                                if (curBrick.powerup == Brick.PowerUpType.PADDLE_WIDTH_INCREASE){
                                    actors.add(new Item(ball.hitbox.left,ball.hitbox.top,0,450,Item.PowerUpType.PADDLE_WIDTH_INCREASE));
                                }
                                else if (curBrick.powerup == Brick.PowerUpType.GOLDEN_BALL){
                                    actors.add(new Item(ball.hitbox.left,ball.hitbox.top,0,450,Item.PowerUpType.GOLDEN_BALL));
                                }
                                actors.remove(i);
                            }
                            break;
                        case ("Spike"):
                            Spike curSpike = ((Spike)curActor);
                            if (curSpike.intersects(ball)) {
                                stats.decrementLives();
                                ball.die(paddles.get(0), balls.size());
                            }
                            break;
                    }

                }
            }
            for (int k = paddles.size() - 1; k >= 0; k--){
                Paddle paddle = paddles.get(k);
                if (curActor.intersects(paddle)) {
                    switch (curActor.getClass().getSimpleName()) {
                        case ("Item"):
                            if (paddle.intersects(actor_list.get(i))){
                                paddle.powerup(((Item)curActor), balls.get(0));
                                actors.remove(i);
                            }
                            else if(((Item)curActor).hasFallen(screenHeight)){
                                actors.remove(i);
                            }
                    }
                }
            }

        }
        for (Ball ball : balls){
            ball.update(fps, screenWidth);
        }

        for (Paddle paddle : paddles) {
            paddle.update(fps, input, screenWidth);
        }
        for (Actor actor: actors) {
            switch (actor.getClass().getSimpleName()){
                case("Item"):
                    ((Item)actor).update(fps);
            }

        }
    }

    //Generates the obstacles for the ball to hit from a .csv
    public void generateActors(){
        //FIXME Store the balls and the paddles in the level.csv!!!
        //FIXME
        //FIXME
        balls = new ArrayList<>();
        paddles = new ArrayList<>();
        //items = new ArrayList<>();
        actors= new ArrayList<>();
        for (int i = 0; i < level.NUM_ROWS; i++){
            for (int j = 0; j < level.NUM_COLUMNS; j++){
                if (level.csv_file_data.get(i).get(j).equals("1")) {
                    //FIXME: Add Balls, Paddles, Wormholes, etc. here
                    // A random chance to generate a powerup block.
                    if (Math.random() > 0.95) {
                        actors.add(
                                new Brick(
                                Brick.BRICK_WIDTH * j,
                                Brick.BRICK_HEIGHT * i * 2,
                                Brick.PowerUpType.PADDLE_WIDTH_INCREASE,
                                R.drawable.breakout_tiles_48)
                        );
                    } else if (Math.random() > 0.95) {
                        actors.add(
                                new Brick(
                                        Brick.BRICK_WIDTH * j,
                                        Brick.BRICK_HEIGHT * i * 2,
                                        Brick.PowerUpType.GOLDEN_BALL,
                                        R.drawable.goldenball_tile)
                        );
                    } else {
                        actors.add(
                                new Brick(
                                Brick.BRICK_WIDTH * j,
                                Brick.BRICK_HEIGHT * i * 2,
                                Brick.PowerUpType.NONE)
                        );
                    }
                }
                if (level.csv_file_data.get(i).get(j).equals("2")) {
                    actors.add(
                            new Spike(
                                Spike.SPIKE_WIDTH * j,
                                Spike.SPIKE_HEIGHT * i)
                    );
                }
            }
        }
        balls.add(
                new Ball(
                screenWidth/2 - Ball.BALL_WIDTH/2,
                screenHeight - Paddle.PADDLE_HEIGHT * 8,
                0,
                0)
        );
        paddles.add(
                new Paddle(
                (screenWidth/2) - Paddle.PADDLE_WIDTH/2,
                screenHeight - Paddle.PADDLE_HEIGHT * 4)
        );
    }

    //Draws any actor, note the wildcard ?
    public void drawActorList(ArrayList<? extends Actor> actor_list) {
        for (Actor a : actor_list){
            canvas.drawBitmap(a.sprite, null, a.hitbox, null);
        }
    }

    void draw() {
        if (holder.getSurface().isValid()) {
            // Lock the canvas, so we can start drawing.
            canvas = holder.lockCanvas();

            canvas.drawColor(Color.rgb(0, 0, 0));

            drawActorList(balls);
            drawActorList(paddles);
            drawActorList(actors);

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
        for (Paddle paddle : paddles)
            paddle.destroy();
    }
}
