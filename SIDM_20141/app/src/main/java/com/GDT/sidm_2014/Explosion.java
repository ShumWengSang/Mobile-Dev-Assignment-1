package com.GDT.sidm_2014;

import static java.lang.Math.random;

/**
 * Created by Shum on 11/02/2015.
 */
public class Explosion extends Entity
{
    boolean Active = false;
    boolean GetActive(){
        return Active;
    }
    void SetActive(boolean a){
        Active = a;
    }

    Explosion()
    {
        theObject = Objects.Explosion;
        Pos.Set((float)random() * 100, (float)random() * 100);
        double VelX = random();
        double VelY = random();
        VelX *= 10;
        VelY *= 10;
        Vel.Set((float)VelX,(float)VelY);
    }

    int IndexCounter = 0;

    public void update()
    {
        if(Active)
        {
            Index++;
            if (MaxIndex != 0) {
                Index %= MaxIndex;
                if(Index == 0)
                {
                    IndexCounter++;
                }
            }
            if(IndexCounter > 10)
            {
                Active = false;
                IndexCounter = 10;
            }
        }
    }
}
