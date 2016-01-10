package online.bogradio.bogaradio.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import online.bogradio.bogaradio.R;
import online.bogradio.bogaradio.service.Music_service;


public class PlayerSeekbarFragment
        extends Fragment
         {

    private SeekBar seekbar;
    private Handler handler;
             AudioManager audioManager;
             public static final String VOLUME_CHANGE = "com.fmvida.action.VOLUME_CHANGE";
   /* private final Runnable syncSeekbar = new Runnable() {
        @Override
        public void run() {
            seekbar.setProgress(getService().getPlayer().getSeek());
            handler.postDelayed(syncSeekbar, 1000);
        }
    };
*/
    /*
     * BroadcastReceivers
     */
/*
    private final BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            syncSeekbar.run();
        }
    };
    private final BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handler.removeCallbacks(syncSeekbar);
        }
    };
    private final BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            seekbar.setMax(getService().getPlayer().getDuration());
            syncSeekbar.run();
        }
    };*/

    /*
     * Fragment members
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seekbar, container);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        seekbar = (SeekBar) view.findViewById(R.id.seekbar);

        seekbar.setMax(maxVolume);
        handler = new Handler();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                {
                    Log.i("MyActivity", "Progress " + progress);
                    Intent serviceIntent = new Intent(getActivity().getApplicationContext(), Music_service.class);
                    serviceIntent.setAction(VOLUME_CHANGE);
                    serviceIntent.putExtra("vol", calcularVolumen());
                    getActivity().startService(serviceIntent);
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
      /*  getActivity().registerReceiver(
                playReceiver,
                new IntentFilter(MusicPlayerService.SEND_PLAY));
        getActivity().registerReceiver(
                pauseReceiver,
                new IntentFilter(MusicPlayerService.SEND_PAUSE));
        getActivity().registerReceiver(
                newTrackReceiver,
                new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
        getActivity().getApplicationContext().bindService(
                new Intent(getActivity(), MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
*/
        super.onResume();
    }

    @Override
    public void onPause() {
     /*   getActivity().unregisterReceiver(playReceiver);
        getActivity().unregisterReceiver(pauseReceiver);
        getActivity().unregisterReceiver(newTrackReceiver);
        getActivity().getApplicationContext().unbindService(this);*/

        super.onPause();
    }


/*
    @Override
    public void onServiceConnected() {
        seekbar.setMax(getService().getPlayer().getDuration());
        syncSeekbar.run();
    }

    *//*
     * SeekBar.OnSeekBarChangeListener members
     *//*

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(syncSeekbar);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        getService().getPlayer().seekTo(seekBar.getProgress());
        syncSeekbar.run();
    }*/


             private Float calcularVolumen(){
                 int maxVolume = 16;
                 float log1 = (float) (Math.log(maxVolume - seekbar.getProgress()) / Math.log(maxVolume));
                 return 1-log1;
             }
}
