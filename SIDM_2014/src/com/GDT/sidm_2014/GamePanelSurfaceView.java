package com.GDT.sidm_2014; // Note: Differs with your project name

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanelSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	// Implement this interface to receive information about changes to the surface.
	
		private GameThread myThread = null; // Thread to control the rendering
		private Bitmap bg;
		private short bgX = 0, bgY = 0;
		private short mX = 0, mY = 0;
		// 1) Variables used for background rendering 
		
		

		// 5) bitmap array to stores 4 images of the spaceship
		private Bitmap[ ] ship = new Bitmap[4];
		
		// 6) Variable as an index to keep track of the spaceship images
		private short shipIndex = 0;
		
		//constructor for this GamePanelSurfaceView class
		public GamePanelSurfaceView (Context context){

			// Context is the current state of the application/object
			super(context);
			
			// Adding the callback (this) to the surface holder to intercept events
			getHolder().addCallback(this);
			
			// 2)load the image when this class is being instantiated
			bg = BitmapFactory.decodeResource(getResources(),R.drawable.sea);
			
			// 7) Load the images of the spaceships
			ship[0] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2_1);
			ship[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ship2_2);
			ship[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ship2_3); 
			ship[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ship2_4);

			// Create the game loop thread
			myThread = new GameThread(getHolder(), this);
			
			// Make the GamePanel focusable so it can handle events
			setFocusable(true);
		}
		
		//must implement inherited abstract methods
		public void surfaceCreated(SurfaceHolder holder){
			// Create the thread 
			if (!myThread.isAlive()){
				myThread = new GameThread(getHolder(), this);
				myThread.startRun(true);
				myThread.start();
			}
		}
		
		public void surfaceDestroyed(SurfaceHolder holder){
			// Destroy the thread 
			if (myThread.isAlive()){
				myThread.startRun(false);
			}
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

		}
		
		public void update(){
		    // 4) An update function to update the game 
			bgX-=8; // Change the number of panning speed if number is larger, it moves faster. 
			if (bgX<-1280) { 
				// Check if reaches 1280, if does, set bgX = 0.
				bgX=0; 
			}
		
			
		    // 9) Update the spaceship images / shipIndex so that the animation will occur.
			shipIndex++; 
			shipIndex%=4;
		}
		
		// Rendering is done on Canvas
		public void doDraw(Canvas canvas){

		// 3) Re-draw 2nd image after the 1st image ends	
		if(bg != null)
		{
			canvas.drawBitmap(bg, bgX, bgY, null);
			canvas.drawBitmap(bg,bgX + 1280, bgY, null);
		}
				
			
		// 8) Draw the spaceships
		//canvas.drawBitmap(ship[shipIndex], 100, 100, null);
		canvas.drawBitmap(ship[shipIndex], mX, mY, null);
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event){
		
		// 10) In event of touch on screen, the spaceship will relocate to the point of touch
			//In the event to locate the location where touch action occurs 
			short X = (short) event.getX(); 
			short Y = (short) event.getY(); 
			if(event.getAction() == MotionEvent.ACTION_DOWN){ 
				// New location where the image to land on 
				mX = (short)(X - ship[shipIndex].getWidth()/2);
				mY = (short)(Y - ship[shipIndex].getHeight()/2); 
				}
			
		
			return super.onTouchEvent(event);
		}
}
