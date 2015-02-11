package com.GDT.sidm_2014;

import java.util.Vector;

/**
 * Created by Shum on 10/12/2014.
 */
public class Player extends Entity {
    int LifePoints = 10;
    Player()
    {
        theObject = Objects.Player;
    }
    void update()
    {
        EntityUpdate();
    }

}
