package com.GDT.sidm_2014;

import android.text.format.Time;

import static java.lang.Math.random;

/**
 * Created by Shum on 11/02/2015.
 */
public class Bomb extends Entity{
    boolean Active = true;
    boolean GetActive(){
        return Active;
    }
    void SetActive(boolean a){
        Active = a;
    }
    Bomb()
    {
        theObject = Objects.PowerUps;
        Pos.Set((float)random() * 100, (float)random() * 100);
        double VelX = random();
        double VelY = random();
        VelX *= 10;
        VelY *= 10;
        Vel.Set((float)VelX,(float)VelY);
    }
    public long curr;
    public void update()
    {
        if(Active) {
            Pos = Vector2D.AddVectors(Pos, Vel);
            if (Pos.x < 0) {
                Pos.x = 0;
                Vel.x = -Vel.x;
            } else if (Pos.x > ScreenWidth - Texture[Index].getWidth()) {
                Pos.x = ScreenWidth - Texture[Index].getWidth();
                Vel.x = -Vel.x;
            }
            if (Pos.y < 0) {
                Pos.y = 0;
                Vel.y = -Vel.y;
            } else if (Pos.y > ScreenHeight - Texture[Index].getHeight()) {
                Pos.y = ScreenHeight - Texture[Index].getHeight();
                Vel.y = -Vel.y;
            }
        }
        else
        {
            if(System.currentTimeMillis() - curr > 3000)
            {
                Active = true;
            }
        }
    }
}
