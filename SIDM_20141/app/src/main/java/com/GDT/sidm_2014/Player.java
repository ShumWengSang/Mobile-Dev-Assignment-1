package com.GDT.sidm_2014;

/**
 * Created by Shum on 10/12/2014.
 */
public class Player extends Entity {
    int LifePoints = 10;
    void update()
    {
        EntityUpdate();
    }

    void TouchEvent(int x, int y)
    {
        Vector2D TouchPos = new Vector2D(x,y);
        Dir = Vector2D.SubtractVectors(Pos,TouchPos).normalized();
       //Dir.x = (float) (x - Texture[Index].getWidth());
     //  Dir.y = (float) (y - Texture[Index].getHeight());
       Dir.normalize();
       Vel = Dir.VScaleMultiplication(10);
    }
}
