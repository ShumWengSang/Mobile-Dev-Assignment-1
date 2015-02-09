package com.GDT.sidm_2014; // Note: Differs with your project name

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.DialogInterface;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;

public class GamePanelSurfaceView extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener
{
    // Implement this interface to receive information about changes to the surface.
    private GameThread myThread = null; // Thread to control the rendering
    private Bitmap bg, scaledbg;
    private short bgX = 0, bgY = 0;
    private final SensorManager sensor;
    int ScreenWidth, ScreenHeight;

    Player thePlayer = new Player();
    Pirate thePirate = new Pirate();
    powerup thePower = new powerup();
    Vector<Entity> theListofEntities;
    int Hits = 3;
    // Pause button state
    private boolean pausepress = true;
    private boolean dead = false;
    // Object
    private Objects shootBtn;
    private Objects PauseB2;

    // Alert Dialog
    AlertDialog.Builder alert = null;
    Activity activityTracker;

    //vector list
    private Vector<powerup>  HealingList = new Vector<powerup>();
    private Vector<Pirate> PirateSpawn = new Vector<Pirate>();
    final EditText input = new EditText(getContext());
    //constructor for this GamePanelSurfaceView class
    public GamePanelSurfaceView (Context context, Activity activity)
    {
        // Context is the current state of the application/object
        super(context);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        // Adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // To track an activity
        activityTracker = activity;
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
        for (int i = 0; i < 3; ++i)// 3 = HealingList.Size()
        {
            Pirate PirateSP = new Pirate();
            PirateSP.Pos.Set((short) PirateSP.r.nextInt(ScreenWidth),(short) PirateSP.r.nextInt(ScreenHeight));
            PirateSpawn.add(PirateSP);
        }

        thePower.EnableBitmap(1);
        thePower.Texture[0] = BitmapFactory.decodeResource(getResources(),R.drawable.healthpack);
        for (int i = 0; i < 3; ++i)// 3 = HealingList.Size()
        {
            powerup HealingObj = new powerup();
            HealingObj.Pos.Set((short) HealingObj.r.nextInt(ScreenWidth),(short) HealingObj.r.nextInt(ScreenHeight));
            HealingList.add(HealingObj);
        }
        // Create the game loop thread
        myThread = new GameThread(getHolder(), this);

        // Make the GamePanel focusable so it can handle events
        setFocusable(true);

        sensor = (SensorManager)getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor.registerListener(this,sensor.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),SensorManager.SENSOR_DELAY_NORMAL);

        thePlayer.GetScreenMetrics(ScreenWidth,ScreenHeight);

        theListofEntities = new Vector<Entity>();

        theListofEntities.add(thePlayer);
        theListofEntities.add(thePirate);
        theListofEntities.add(thePower);




    }
    // Alert Dialogs
    // Create Alert Dialog
    public void AlertMsg()
    {
        alert = new AlertDialog.Builder(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        int maxLength = 20;
        //InputFilter[] FilterArray = new InputFilter[1];
        //FilterArray[0] = new InputFilter.LengthFilter(maxLength);
       // input.setFilters(FilterArray);

        alert.setCancelable(false);
       // alert.setMessage("Please enter your name");
        alert.setView(input);

        alert.setPositiveButton ("Back to main menu", new DialogInterface.OnClickListener()
        {
            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1)
            {
                Intent intent = new Intent();
                intent.setClass(getContext(), MainMenu.class);
                activityTracker.startActivity(intent);
            }
        });


    }
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        //Do something here if accuracy changed
    }

    public void onSensorChanged(SensorEvent SenseEvent)
    {
        //Many sensors return 3 values , one for each axis
        //do something with this sensor values

        float [] SenseData = SenseEvent.values;
        // Check X axis values i.e. 0 = X axis, 1 = Y axis.
        if (SenseData[0] >= 1)
        {
            // if positive x-data exceeds +1,
            thePlayer.Pos.x -= 10;
            // object moves leftwards.
            //Log.d(TAG, "" + SenseEvent.values);
        } else if (SenseData[0] <= -1)
        {
            // if negative x-data exceeds -1,
            thePlayer.Pos.x += 10;
            // object moves rightwards.
        }

        // Check Y axis values
        if(SenseData[1] >= 1){
            thePlayer.Pos.y += 10;
        }
        else if(SenseData[1] <= -1){
            thePlayer.Pos.y -= 10;
        }

        //canvas.drawBitmap(stone[stoneIndex], aX, aY, null);
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
       // alert = new AlertDialog.Builder (getContext());
        for (int j = 0; j < PirateSpawn.size(); ++j) {
            if (PirateSpawn.get(j).GetActive()) {
                if (Entity.CollisionDetection(thePlayer, thePirate)) {
                    if (dead == false) {
                        thePlayer.LifePoints--;
                        if (thePlayer.LifePoints < 0) {
                            // alert.setTitle("No Rating! Try Harder!");
                            dead = true;
                            thePlayer.LifePoints = 0;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < HealingList.size(); ++j) {
            if (HealingList.get(j).GetActive()) {
                if (Entity.CollisionDetection(thePlayer, thePower)) {
                    if (dead == false) {
                        thePlayer.LifePoints += 5;
                        HealingList.get(j).SetActive(false);

                    }
                }
            }
        }

    }

    // Rendering is done on Canvas
    public void doDraw(Canvas canvas)
    {
        if (canvas == null)
        {
            return;
        }
        // 3) Re-draw 2nd image after the 1st image ends
        if (bg != null)
        {
            canvas.drawBitmap(scaledbg, bgX, bgY, null);
            canvas.drawBitmap(scaledbg, bgX + ScreenWidth, bgY, null);
        }


        // 8) Draw the spaceships
        //canvas.drawBitmap(ship[shipIndex], 100, 100, null);
        thePlayer.EntityDraw(canvas);

        //object
        thePirate.EntityDraw(canvas);

        //power up

        for (int j = 0; j < HealingList.size(); ++j)
        {
            if (HealingList.get(j).GetActive())
            {
                thePower.EntityDraw(canvas);
            }
        }
        for(Entity iter: theListofEntities)
        {
            iter.EntityDraw(canvas);
        }

        displaytext(canvas);
    }

    public void displaytext(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setARGB(255, 0, 0, 0);
        paint.setStrokeWidth(100);
        paint.setTextSize(30);
        canvas.drawText("Lifes:" + " " + thePlayer.LifePoints, 600, 50, paint);
        if(dead == true)
        {
            canvas.drawText("You're dead click anywhere to continue", 400, 400, paint);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        // 10) In event of touch on screen, the spaceship will relocate to the point of touch
        //In the event to locate the location where touch action occurs
        short X = (short) event.getRawX();
        short Y = (short) event.getRawY();
        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            // New location where the image to land on
            // thePlayer.TouchEvent(X,Y);
            if(dead == true){
                AlertMsg();
                alert.show();
            }
        }

        return super.onTouchEvent(event);
    }

}
