package com.solomon.ostreachegg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by user on 12/10/2015.
 */
public class SplashPage extends Activity {
    private MediaPlayer song1;

    @Override
    public void onCreate(Bundle savedData)
    {
        super.onCreate(savedData);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setBackgroundResource(R.drawable.ostrich);

        Button launchButton = new Button(this);
        launchButton.setBackgroundColor(Color.WHITE);
        launchButton.setTextColor(Color.parseColor("#f23262"));
        launchButton.setText("   Lay Eggs");
        launchButton.setTextSize(40.0f);
        launchButton.setPadding(0, 0, 0, 50);
        launchButton.setOnClickListener(buttonListener);

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        buttonLayout.addView(launchButton);

        FrameLayout outerLayout = new FrameLayout(this);
        outerLayout.addView(innerLayout);
        outerLayout.addView(buttonLayout);

        setContentView(outerLayout);
    }

    public View.OnClickListener buttonListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            Intent launchIntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(launchIntent);
        }
    };

    public void allocateSong1()
    {
        if(song1 == null)
            song1 = MediaPlayer.create(this.getBaseContext(), R.raw.chicken);
        song1.setOnPreparedListener(song1PListener);
        song1.setOnCompletionListener(song1CListener);
    }

    private MediaPlayer.OnPreparedListener song1PListener = new MediaPlayer.OnPreparedListener()
    {
        @Override
        public void onPrepared(MediaPlayer mp)
        {
            playSong1();
        }
    };

    private MediaPlayer.OnCompletionListener song1CListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            playSong1();
        }
    };

    public void playSong1()
    {
        if (song1.isPlaying())
        {
            song1.pause();
        }
        if(song1.getCurrentPosition() != 1)
        {
            song1.seekTo(1);
        }
        song1.start();
    }

    public void deallocateSong1()
    {

        if (song1.isPlaying())
        {
            song1.stop();
        }
        if (!(song1 == null))
        {
            song1.release();
            song1 = null;
        }
        song1CListener = null;
        song1PListener = null;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        allocateSong1();
    }

    @Override
    protected void onPause()
    {
        deallocateSong1();
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
