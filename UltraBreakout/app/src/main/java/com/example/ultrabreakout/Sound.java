package com.example.ultrabreakout;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
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

    private int resourceId;

    private boolean prepared;

    private Sound() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        resourceId = 0;
        prepared = false;

        /*mediaPlayer = MediaPlayer.create(context, R.raw.background_1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();*/
    }

    public void play_background(Context context, int background_music_id) {
        resourceId = background_music_id;
        if (mediaPlayer != null && resourceId == background_music_id) {
            if (prepared) {
                mediaPlayer.start();
            }
            return;
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        AssetFileDescriptor afd = context.getResources().openRawResourceFd(resourceId);
        prepared = false;
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                prepared = true;
            }
        });
        mediaPlayer.prepareAsync();
    }

    private void play_media() {

    }

    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
        }
        return instance;
    }

    public void resume() {
        if (prepared) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (prepared) {
            mediaPlayer.pause();
        }
    }

    public void cleanup() {
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
