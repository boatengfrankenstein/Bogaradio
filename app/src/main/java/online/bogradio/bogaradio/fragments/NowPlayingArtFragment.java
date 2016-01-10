package online.bogradio.bogaradio.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;


import java.io.File;
import java.io.IOException;

import online.bogradio.bogaradio.R;
import online.bogradio.bogaradio.service.Music_service;
import online.bogradio.bogaradio.utils.TunnelPlayerWorkaround;
import online.bogradio.bogaradio.visualizer.VisualizerView;
import online.bogradio.bogaradio.visualizer.renderer.BarGraphRenderer;
import online.bogradio.bogaradio.visualizer.renderer.CircleBarRenderer;
import online.bogradio.bogaradio.visualizer.renderer.CircleRenderer;
import online.bogradio.bogaradio.visualizer.renderer.LineRenderer;

public class NowPlayingArtFragment extends Fragment {
    private ImageView artView;
    private ImageView backView;
    AnimationDrawable frameAnimation;
    Animation reconnectBlink;
    public static final String STATUS_PLAYING = "com.fmvida.action.STATUS_PLAYING";
    public static final String STATUS_STOP = "com.fmvida.action.STATUS_STOP";
    public static final String STATUS_STOPPING = "com.fmvida.action.STATUS_STOPPING";
    public static final String STATUS_CONNECTING = "com.fmvida.action.STATUS_CONNECTING";
    public static final String STATUS_LOST_STREAM = "com.fmvida.action.SATUS_LOST_STREAM";

    public static final String ACTION_START = "com.fmvida.action.ACTION_START";
    public static final String ACTION_STOP = "com.fmvida.action.ACTION_STOP";

    public static final String VOLUME_CHANGE = "com.fmvida.action.VOLUME_CHANGE";
    public static final String MUTE = "com.fmvida.action.MUTE";
    public static final String UNMUTE = "com.fmvida.action.UNMUTE";
    private MediaPlayer mPlayer;
    private MediaPlayer mSilentPlayer;  /* to avoid tunnel player issue */
    private VisualizerView mVisualizerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nowplaying_art, container, false);

       // initTunnelPlayerWorkaround();
        //init();


        artView = (ImageView) view.findViewById(R.id.art);
        backView = (ImageView) view.findViewById(R.id.back);
        //equalizer = (EqualizerView) findViewById(R.id.equalizer_view);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                mMessageReceiver, new IntentFilter("sendMessage"));


        return view;
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            switch(intent.getStringExtra("command")) {
                case STATUS_CONNECTING:  // updateUI(STATUS_CONNECTING);
                    Log.i("MyActivity", "STATUS_CONNECTING ");
                    break;
                case STATUS_STOPPING:  // updateUI(STATUS_STOPPING);
                    Log.i("MyActivity", "STATUS_STOPPING ");
                   // frameAnimation.stop();
                   // frameAnimation.selectDrawable(0);
                    break;
                case STATUS_PLAYING:  // updateUI(STATUS_PLAYING);
                    Log.i("MyActivity", "STATUS_PLAYING ");
                    // frameAnimation.start();

                    break;
                case STATUS_STOP:   //updateUI(STATUS_STOP);
                    Log.i("MyActivity", "STATUS_STOP ");

                    //frameAnimation.stop();
                    //frameAnimation.selectDrawable(0);
                    break;

                case STATUS_LOST_STREAM:
                   // frameAnimation.stop();
                   // frameAnimation.selectDrawable(0);
            }
        }
    };

}

////////////////////////////////////////////////////Audio Visualizaer

