package com.GDT.sidm_2014;

/**
 * Created by Seventh on 8/2/2015.
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Math.random;

public class powerup extends Entity{
    Random r = new Random();
    boolean Active = true;
    boolean GetActive(){
        return Active;
    }
    void SetActive(boolean a){
        Active = a;
    }

    powerup()
    {
        theObject = Objects.PowerUps;
        Pos.Set((float)random() * 100, (float)random() * 100);
        double VelX = random();
        double VelY = random();
        VelX *= 10;
        VelY *= 10;
        Vel.Set((float)VelX,(float)VelY);
    }
       // theObject = Objects.PowerUps;
       // powerup PowerType1 = new powerup();
       // PowerType1.Pos.Set((short) r.nextInt(ScreenWidth),(short) r.nextInt(ScreenHeight));

    public void update()
    {
        Index ++;
        if(MaxIndex != 0 )
        {
            Index %= MaxIndex;
        }

        Pos = Vector2D.AddVectors(Pos,Vel);
        if(Pos.x < 0)
        {
            Pos.x = 0;
            Vel.x = -Vel.x;
        }
        else if(Pos.x > ScreenWidth - Texture[Index].getWidth())
        {
            Pos.x = ScreenWidth - Texture[Index].getWidth();
            Vel.x = -Vel.x;
        }
        if(Pos.y < 0)
        {
            Pos.y = 0;
            Vel.y = -Vel.y;
        }
        else if(Pos.y > ScreenHeight - Texture[Index].getHeight())
        {
            Pos.y = ScreenHeight - Texture[Index].getHeight();
            Vel.y = -Vel.y;
        }
    }

}
