package com.GDT.sidm_2014;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{

	private Button btn_start;
	private Button btn_help;
	private Button btn_option;
    MediaPlayer theMedia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		requestWindowFeature(Window.FEATURE_NO_TITLE);// hide title
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide top bar
		setContentView(R.layout.mainmenu);


        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        
        btn_help = (Button)findViewById(R.id.btn_help);
        btn_help.setOnClickListener(this);
        
        btn_option = (Button)findViewById(R.id.btn_option);
        btn_option.setOnClickListener(this);

        theMedia = MediaPlayer.create(this , R.raw.sound);
        theMedia.start();
	}

	 @Override
     public void onClick(View v){
      	Intent intent = new Intent();
      		
      	if(v == btn_start)
      	{

      		intent.setClass(this, GamePage.class);
      	}
      	 else if (v == btn_option)	
 	   	{
 	   		intent.setClass(this, OptionPage.class);
 	   	}
      	else if (v == btn_help)	
      	{
      		intent.setClass(this, HelpPage.class);
      	}
      		
      		startActivity(intent);
      	}

	 
	 
	 @Override
     public void onBackPressed()
     {
     	finish();
 		android.os.Process.killProcess(android.os.Process.myPid());
 		
     }
	 
     public void onStop() {
         super.onStop();
         
     }
     
     public void onDestroy() {
         super.onDestroy();
         
     }
}
