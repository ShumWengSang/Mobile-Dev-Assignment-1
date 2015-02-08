package com.GDT.sidm_2014;

/**
 * Created by Seventh on 8/2/2015.
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Vector;

public class powerup extends Entity{
    Random r = new Random();
    boolean Active = true;
    boolean GetActive(){
        return Active;
    }
    void SetActive(boolean a){
        Active = a;
    }
       // theObject = Objects.PowerUps;
       // powerup PowerType1 = new powerup();
       // PowerType1.Pos.Set((short) r.nextInt(ScreenWidth),(short) r.nextInt(ScreenHeight));

    public void update(){

    }

}
