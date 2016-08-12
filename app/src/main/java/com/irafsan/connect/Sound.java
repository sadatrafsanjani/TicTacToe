package com.irafsan.connect;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

    private MediaPlayer mediaPlayer;

    public void chipDrop(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.drop);
        mediaPlayer.start();

    }

    public void endGame(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.winner);
        mediaPlayer.start();
    }
}
