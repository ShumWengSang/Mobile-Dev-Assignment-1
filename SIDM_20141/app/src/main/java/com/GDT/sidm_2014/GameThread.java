package com.GDT.sidm_2014; // Note: Differs with your project name
import android.view.SurfaceHolder;
import android.graphics.Canvas;

import android.view.SurfaceHolder;
import android.graphics.Canvas;

public class GameThread extends Thread {
	// The actual view that handles inputs and draws to the surface
			private GamePanelSurfaceView myView;
			
			// Surface holder that can access the physical surface
			private SurfaceHolder holder;
			
			// Flag to hold game state 
			private boolean isRun;
			
			long curr = 0;
			long prev = 0;
			short fps = 30;
			
			// Constructor for this class 
			public GameThread(SurfaceHolder holder, GamePanelSurfaceView myView){
				super();
				isRun = true;
				this.myView = myView;
				this.holder = holder;
			}
			
			public void startRun(boolean r){
				isRun = r;
			}
				
			@Override
			public void run(){
				while (isRun){  
				//Update game state and render state to the screen
					Canvas c = null;
					curr = System.currentTimeMillis();
					if ((long)(curr-prev)>(long)(1000/fps)){
						try {
							c = holder.lockCanvas();
							synchronized(holder){
								if(myView != null){
								myView.update();
								myView.doDraw(c);

								}
							}		
						}
						finally{
							if (c!=null){
								holder.unlockCanvasAndPost(c);
							}
						}
						prev = curr;
					}
					else{
						try{
							Thread.sleep((long)((1000/fps)-prev));
						}
						catch (Exception e){
							
						}
					}
				}

			}
}