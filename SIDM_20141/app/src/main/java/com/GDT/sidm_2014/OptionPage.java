package com.GDT.sidm_2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OptionPage extends Activity implements OnClickListener{

	private Button btn_backtomain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		requestWindowFeature(Window.FEATURE_NO_TITLE);// hide title
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide top bar
		setContentView(R.layout.optionpage);
		btn_backtomain = (Button)findViewById(R.id.btn_backtomain);
		btn_backtomain.setOnClickListener(this);
		 
        
	}

	 @Override
     public void onClick(View v){
      	Intent intent = new Intent();
      		
      	if(v == btn_backtomain)
      	{
      		intent.setClass(this, MainMenu.class);
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