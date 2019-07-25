package com.example.ultrabreakout;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

/* A singleton class to handle all of the sound for the game.
 *
 * Sound is a singleton instance in order to enable sharing between all of the activities
 * within the app.
 *
 * Sound is split into two categories, background music and sound effects.
 * Background music is simply set on a screen and set to loop when the activity is started.
 * Sound effects are manually called by the application when certain events happen.
 */
public class Sound {
    private static Sound instance;

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    // Ids for all the different sounds.
    public int background_1 = -1;

    private Sound(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        mediaPlayer = MediaPlayer.create(context, R.raw.background_1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();;
    }

    public static Sound getInstance(Context context) {
        if (instance == null) {
            instance = new Sound(context);
        }
        return instance;
    }

    public void resume() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void cleanup() {
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
