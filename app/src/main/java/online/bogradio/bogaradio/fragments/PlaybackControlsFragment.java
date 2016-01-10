package online.bogradio.bogaradio.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import online.bogradio.bogaradio.MainActivity;
import online.bogradio.bogaradio.R;
import online.bogradio.bogaradio.service.Music_service;


public class PlaybackControlsFragment
        extends Fragment
        {

   // private static final MorphButton.MorphState STATE_PAUSE = MorphButton.MorphState.START;
   // private static final MorphButton.MorphState STATE_PLAY = MorphButton.MorphState.END;
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


    ImageButton playPauseButton;
    ImageButton mute;
    ImageButton rewindButton;
    ImageButton repeatButton;
    ImageButton shuffleButton;
    private String player_status = STATUS_STOP;
    boolean muted = false;
            public ProgressDialog progressDialog;



            @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playback_controls, container, false);

       playPauseButton =(ImageButton) view.findViewById(R.id.play_pause);


        mute =(ImageButton) view.findViewById(R.id.mute);
      //  mute.setImageResource(R.drawable.mMuteButton);

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Playback zone", Toast.LENGTH_SHORT).show();
            }
        });

        rewindButton =(ImageButton) view.findViewById(R.id.rewind);


     //   LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
       //         mMessageReceiver, new IntentFilter("sendMessage"));

                Thread thread = new Thread(new MyRunnable());
                thread.run(); //in current thread

        playPauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("MyActivity", "player_status = " + player_status);
                if (player_status.equals(STATUS_PLAYING)) {
                    player_status = STATUS_STOP;
                    getActivity().startService(new Intent(getActivity().getApplicationContext(), Music_service.class).setAction(ACTION_STOP));
                    muted = false;
                  //  muteButton.setImageResource(R.drawable.ic_volume_up_black_36dp);
                    playPauseButton.setImageResource(R.drawable.ic_play_dark);
                } else if (player_status.equals(STATUS_STOP)) {
                    getActivity().startService(new Intent(getActivity().getApplicationContext(), Music_service.class).setAction(ACTION_START).putExtra("vol", 1.0));
                   // Log.i("MyActivity", "Arrancando con volumen : " + calcularVolumen());
                    playPauseButton.setImageResource(R.drawable.ic_pause_dark);
                    player_status = STATUS_PLAYING;
                } else {
                    Log.i("MyActivity", "entro en el else " + player_status);
                }
            }
        });
        return view;
    }



    @Override
    public void onStop() {
        super.onStop();
        Log.i("MyActivity", "Activity On Stop ");
        super.onStop();
        if (player_status == STATUS_STOP){
            getActivity().stopService(new Intent(getActivity().getApplicationContext(), Music_service.class));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyActivity", "Activity On Destroy ");
        getActivity().stopService(new Intent(getActivity().getApplicationContext(), Music_service.class));
        getActivity().finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("player_status", player_status);
        outState.putBoolean("mute_button_state", muted);
        outState.putBoolean("volume_bar_state", true);

        Log.i("MyActivity", "saveInstanceState ");
        super.onSaveInstanceState(outState);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {

        switch(intent.getStringExtra("command")) {
            case STATUS_CONNECTING:   updateUI(STATUS_CONNECTING);
                Log.i("MyActivity", "STATUS_CONNECTING ");
                progressDialog.show(getActivity(), "", "Connecting...");

                break;
            case STATUS_STOPPING:   updateUI(STATUS_STOPPING);
                Log.i("MyActivity", "STATUS_STOPPING ");
                progressDialog.dismiss();
                break;
            case STATUS_PLAYING:   updateUI(STATUS_PLAYING);
                Log.i("MyActivity", "STATUS_PLAYING ");
                progressDialog.dismiss();
                break;
            case STATUS_STOP:   updateUI(STATUS_STOP);
                Log.i("MyActivity", "STATUS_STOP ");
                progressDialog.dismiss();
                break;
        }
    }
};


    private void updateUI(String command){
        switch(command) {
            case  STATUS_CONNECTING:
                Log.i("MyActivity", "UPDATE UI CONNECTINGH ");
                //connectingText.setVisibility(View.VISIBLE);
                //connectingText.startAnimation(reconnectBlink);
                //playPauseButton.setEnabled(false);
               // playPauseButton.setAlpha(64);
               // mute.setEnabled(false);
             //   mute.setAlpha(64);
                //volumeControl.setEnabled(false);


                break;
            case  STATUS_STOPPING:
                Log.i("MyActivity", "UPDATE UI STOPPINg ");
                progressDialog.dismiss();
                //connectingText.setVisibility(View.INVISIBLE);
               // playPauseButton.setEnabled(false);
                //playPauseButton.setAlpha(64);
               // mute.setEnabled(false);
               // muteButton.setAlpha(64);
               // volumeControl.setEnabled(false);
             //   pd.setIndeterminate(false);
             //   pd.dismiss();

             //  NowPlayingArtFragment.frameAnimation.stop();
              //  NowPlayingArtFragment. frameAnimation.selectDrawable(0);


                break;
            case  STATUS_PLAYING:
                Log.i("MyActivity", "UPDATE UI PLAYING ");
                //connectingText.clearAnimation();
               // connectingText.setVisibility(View.INVISIBLE);
              //  mute.setEnabled(true);
               // mute.setAlpha(255);
              //  volumeControl.setEnabled(true);
              //  playPauseButton.setEnabled(true);
                //playPauseButton.setAlpha(255);
             //   pd.setIndeterminate(false);
               // pd.dismiss();

                progressDialog.dismiss();

               // NowPlayingArtFragment.frameAnimation.start();

                break;
            case STATUS_LOST_STREAM:
               // Log.i("MyActivity", "UPDATE UI LOST STREAM ");
                //connectingText.setVisibility(View.VISIBLE);
               // connectingText.startAnimation(reconnectBlink);
              //  mute.setEnabled(false);
              //  mute.setAlpha(64);
               // volumeControl.setEnabled(false);
                player_status=STATUS_STOP;
                //pd.setIndeterminate(false);
                //pd.dismiss();
                Toast.makeText(getActivity(), "Lost stream, try reconnecting", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

               // NowPlayingArtFragment.frameAnimation.stop();
               // NowPlayingArtFragment. frameAnimation.selectDrawable(0);

                break;
            case STATUS_STOP:
                Log.i("MyActivity", "UPDATE UI STOP ");
               // connectingText.clearAnimation();
               // connectingText.setVisibility(View.INVISIBLE);
              //  mute.setEnabled(false);
                //mute.setAlpha(64);
                player_status = STATUS_STOP;
                playPauseButton.setImageResource(R.drawable.ic_play_dark);
               // volumeControl.setEnabled(false);
              // playPauseButton.setEnabled(true);
               // playStopButton.setAlpha(255);
                //pd.setIndeterminate(false);
                //pd.dismiss();
                progressDialog.dismiss();

               // NowPlayingArtFragment.frameAnimation.stop();
               // NowPlayingArtFragment.frameAnimation.selectDrawable(0);
                break;
        }
    }

            private class MyRunnable implements Runnable{

                @Override
                public void run() {
                    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                            mMessageReceiver, new IntentFilter("sendMessage"));
                }
            }
}