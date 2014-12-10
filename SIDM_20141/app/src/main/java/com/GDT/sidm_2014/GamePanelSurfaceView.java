package com.GDT.sidm_2014; // Note: Differs with your project name

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanelSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	// Implement this interface to receive information about changes to the surface.
	
		private GameThread myThread = null; // Thread to control the rendering
		private Bitmap bg, scaledbg;
		private short bgX = 0, bgY = 0;

		int ScreenWidth, ScreenHeight;

        Player thePlayer = new Player();
		Pirate thePirate = new Pirate();
		
		int Hits = 3;
		
		//point
		private Bitmap[ ] star = new Bitmap[1];
		//constructor for this GamePanelSurfaceView class
		public GamePanelSurfaceView (Context context){

			// Context is the current state of the application/object
			super(context);
			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			ScreenWidth = metrics.widthPixels;
			ScreenHeight = metrics.heightPixels;
			// Adding the callback (this) to the surface holder to intercept events
			getHolder().addCallback(this);
			
			// 2)load the image when this class is being instantiated
			bg = BitmapFactory.decodeResource(getResources(),R.drawable.sea);
			scaledbg = Bitmap.createScaledBitmap(bg,  (int)(ScreenWidth),(int)(ScreenHeight), true);

			// 7) Load the images of the spaceships
            thePlayer.EnableBitmap(4);
            thePlayer.Texture[0] = BitmapFactory.decodeResource(getResources(),R.drawable.seaship1);
            thePlayer.Texture[1]  = BitmapFactory.decodeResource(getResources(), R.drawable.seaship2);
            thePlayer.Texture[2]  = BitmapFactory.decodeResource(getResources(), R.drawable.seaship3);
            thePlayer.Texture[3]  = BitmapFactory.decodeResource(getResources(), R.drawable.seaship4);
            thePlayer.Pos.Set(400,300);
			//pirate
            thePirate.EnableBitmap(4);
            thePirate.Texture[0] = BitmapFactory.decodeResource(getResources(),R.drawable.pirate1);
            thePirate.Texture[1] = BitmapFactory.decodeResource(getResources(),R.drawable.pirate2);
            thePirate.Texture[2] = BitmapFactory.decodeResource(getResources(),R.drawable.pirate3);
            thePirate.Texture[3] = BitmapFactory.decodeResource(getResources(),R.drawable.pirate4);

			
			
			star[0] = BitmapFactory.decodeResource(getResources(),R.drawable.star);
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
			boolean retry = true;
			while(retry){
				try{
					myThread.join();
					retry = false;
				}
				catch (InterruptedException e)
				{
				}
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
		
			
		    // sprite animation
			//ship
            thePlayer.update();
			//object
            thePirate.update(thePlayer.Pos);
		}

    // Rendering is done on Canvas
    public void doDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        // 3) Re-draw 2nd image after the 1st image ends
        if (bg != null) {
            canvas.drawBitmap(scaledbg, bgX, bgY, null);
            canvas.drawBitmap(scaledbg, bgX + ScreenWidth, bgY, null);
        }


        // 8) Draw the spaceships
        //canvas.drawBitmap(ship[shipIndex], 100, 100, null);
        thePlayer.EntityDraw(canvas);

        //object
        thePirate.EntityDraw(canvas);

        displaytext(canvas);
    }

		public void displaytext(Canvas canvas)
		{
			Paint paint = new Paint();
			paint.setARGB(255, 0, 0, 0);
			paint.setStrokeWidth(100);
			paint.setTextSize(30);
			canvas.drawText("Lifes:" + " " + thePlayer.LifePoints, 600, 50, paint);
		}
		@Override
		public boolean onTouchEvent(MotionEvent event){
			int action = event.getAction();
		// 10) In event of touch on screen, the spaceship will relocate to the point of touch
			//In the event to locate the location where touch action occurs 
			short X = (short) event.getX(); 
			short Y = (short) event.getY(); 
			switch(action)
			{
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			}
			if(event.getAction() == MotionEvent.ACTION_DOWN){ 
				// New location where the image to land on
                thePlayer.TouchEvent(X,Y);
				}

            if(Entity.CollisionDetection(thePlayer,thePirate))
            {
                thePlayer.LifePoints--;
            }
		
			return super.onTouchEvent(event);
		}
		
}
