package com.GDT.sidm_2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
public class GamePage extends Activity implements OnClickListener{

	/**
	 * @param args


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
	 Button btn_fire;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		requestWindowFeature(Window.FEATURE_NO_TITLE);// hide title
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide top bar
	    setContentView(new GamePanelSurfaceView(this,this));

        //btn_fire = (Button)findViewById(R.id.btn_fire);
       // btn_fire.setOnClickListener(this);
	}

    public void onClick(View v){
        Intent intent = new Intent();

        if(v == btn_fire)
        {
            Log.i("App_Name", "FIREFIREFIREFIREg");
        }

        startActivity(intent);
    }
}
