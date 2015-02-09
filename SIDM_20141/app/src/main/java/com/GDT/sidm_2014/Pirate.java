package com.GDT.sidm_2014;

/**
 * Created by Shum on 10/12/2014.
 */

//Done by Jia Wang
//The Entire class.
import java.util.ArrayList;
import java.util.Random;

public class Pirate extends Entity{
    ArrayList<Waypoint> thePoints = new ArrayList<Waypoint>();
    int WayPointTracker = 0;
    Random r = new Random();
    boolean Active = true;
    boolean GetActive(){
        return Active;
    }
    void SetActive(boolean a){
        Active = a;
    }
    Pirate()
    {
        theObject = Objects.Pirates;
        Waypoint thePoint = new Waypoint();
        thePoint.currentPoint = new Vector2D(500,100);
        thePoints.add(thePoint);

        Waypoint thePointAgain = new Waypoint();
        thePointAgain.currentPoint = new Vector2D(100,100);
        thePoints.add(thePointAgain);

        //Sets the waypoints in a proper fashion
        for(int i = 0; i < thePoints.size(); i++)
        {
            if(i != (thePoints.size()-1))
            {
                thePoints.get(i).nextPoint = thePoints.get(i+1);
            }
            else
            {
                thePoints.get(i).nextPoint = thePoints.get(0);
            }
        }
    }
    void update()
    {
        EntityUpdate();
    }

    void update(Vector2D PlayerPos)
    {
        //Dir = Vector2D.SubtractVectors(PlayerPos, Pos).normalized();

        EntityUpdate();

        //If you have not reached the waypoint, keep moving
        if(!ReachedLocation(thePoints.get(WayPointTracker).currentPoint,this))
        {
            goLocation(thePoints.get(WayPointTracker).currentPoint,this,5);
        }
        else
        {
            WayPointTracker++;
            if(WayPointTracker == (thePoints.size()))
            {
                WayPointTracker = 0;
            }
        }
        //Vel = Dir.VScaleMultiplication(10);
    }
}
