package com.GDT.sidm_2014;

/**
 * Created by Shum on 10/12/2014.
 */
public class Pirate extends Entity{
    void update()
    {
        EntityUpdate();
    }

    void update(Vector2D PlayerPos)
    {
        Dir = Vector2D.SubtractVectors(PlayerPos, Pos).normalized();
        EntityUpdate();
        Vel = Dir.VScaleMultiplication(10);
    }
}
