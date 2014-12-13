package com.GDT.sidm_2014;

/**
 * Created by Shum on 10/12/2014.
 */
import java.util.ArrayList;

public class Pirate extends Entity{
    ArrayList<Waypoint> thePoints = new ArrayList<Waypoint>();
    Waypoint thePoint = new Waypoint();
    Pirate(){



        thePoint.currentPoint = new Vector2D(300,100);
        thePoint.nextPoint = new Waypoint();
        //thePoint.currentPoint = new Vector2D(1,1);
        thePoints.add(thePoint);
        int size = thePoints.size();

    }
    void update()
    {
        EntityUpdate();
    }
    void goLocation(Vector2D theNewPos, Entity go, float speed){

        Vector2D TheDirection = Vector2D.SubtractVectors(theNewPos,go.Pos).normalized();

        TheDirection.ScaleMultiplication(speed);
        go.Vel = TheDirection;
    }
    boolean ReachedLocation(Vector2D thePosReached, Entity go)
    {
        //return 1 > (thePosReached - go->pos).Length();
         //Vector2D.SubtractVectors(thePosReached,go.Pos).normalize();
        if ( Vector2D.distancesquared(thePosReached, go.Pos) < 1) {
            return (true);
        }
        return(false);
    }
    void update(Vector2D PlayerPos)
    {
        //Dir = Vector2D.SubtractVectors(PlayerPos, Pos).normalized();

        EntityUpdate();
        Vel = goLocation(30, this, 5);
        //Vel = Dir.VScaleMultiplication(10);
    }
}
