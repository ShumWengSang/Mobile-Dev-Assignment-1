package com.GDT.sidm_2014;

import java.util.Vector;

/**
 * Created by Shum on 10/12/2014.
 */
public class Player extends Entity {
    int LifePoints = 10;
    Vector2D TouchedLocation = new Vector2D(0,0);
    Player()
    {
        theObject = Objects.Player;
    }
    void update()
    {
        if(ReachedLocation(TouchedLocation,this))
        {
            Vel.Set(0,0);
        }
        EntityUpdate();
    }

    void TouchEvent(int x, int y)
    {
        Vector2D TouchPos = new Vector2D(x,y);
        TouchedLocation = TouchPos;
        Dir = Vector2D.SubtractVectors(TouchPos,Pos).normalized();
       //Dir.x = (float) (x - Texture[Index].getWidth());
     //  Dir.y = (float) (y - Texture[Index].getHeight());
       Dir.normalize();
       Vel = Dir.VScaleMultiplication(10);
    }
}
