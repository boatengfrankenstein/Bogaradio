package online.bogradio.bogaradio;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.PersistableBundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import online.bogradio.bogaradio.fragments.QuickContactFragment;
import online.bogradio.bogaradio.service.Music_service;

import static android.support.design.widget.FloatingActionButton.*;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {

    public static final String STATUS_PLAYING = "com.fmvida..action.STATUS_PLAYING";
    public static final String STATUS_STOP = "com.fmvida.action.STATUS_STOP";
    public static final String STATUS_STOPPING = "com.fmvida.action.STATUS_STOPPING";
    public static final String STATUS_CONNECTING = "com.fmvida.action.STATUS_CONNECTING";
    public static final String STATUS_LOST_STREAM = "com.fmvida.action.SATUS_LOST_STREAM";

    public static final String ACTION_START = "com.fmvida.action.ACTION_START";
    public static final String ACTION_STOP = "com.fmvida.action.ACTION_STOP";

    public static final String VOLUME_CHANGE = "com.fmvida.action.VOLUME_CHANGE";
    public static final String MUTE = "com.fmvida.action.MUTE";
    public static final String UNMUTE = "com.fmvida.action.UNMUTE";

    public static final String FACEBOOK_URL = "https://www.facebook.com/juancruz.rubino.12";
    public static final String FACEBOOK_ID = "fb://profile/100005868240617";
    public static final String TWITTER_URL = "https://twitter.com/fmvida1035";
    public static final String TWITTER_ID = "twitter://user?user_id=1395651954";
    public static final String INSTAGRAM_URL = "https://instagram.com/Fmvida103.5";
    public static final String INSTAGRAM_ID = "http://instagram.com/_u/Fmvida103.5";
    public FloatingActionButton mFAB;
  //  public FloatingActionMenu mFABMenu;
    AudioManager audioManager;


    public static final String WHATSAPP_NUMBER = "+5492281572830";

    private String player_status = STATUS_STOP;
    boolean muted = false;
    private ProgressDialog pd;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
         //  ImageView img = (ImageView)findViewById
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   toolbar.setTitle("Bogaradio");
        toolbar.setLogo(R.drawable.logo);
        FloatingActionButton menushare = (FloatingActionButton) findViewById(R.id.fab_playlist);
        menushare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                QuickContactFragment.newInstance().show(getSupportFragmentManager(), "QuickContactFragment");
            }
        });


    //    pd = new ProgressDialog(context);
      //  pd.setTitle("Connecting");
      //  pd.setMessage("Please wait..");
        //pd.setCancelable(false);
        //pd.show();
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //img.setAdjustVieR.wBounds(true);
        //img.setScaleType(ImageView.ScaleType.FIT_XY);
        //  img.setImageDrawable(getResources().getDrawable(R.drawable.anim_late));

        //  this.frameAnimation = (AnimationDrawable) img.getDrawable();
        //  this.frameAnimation.stop();


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


}