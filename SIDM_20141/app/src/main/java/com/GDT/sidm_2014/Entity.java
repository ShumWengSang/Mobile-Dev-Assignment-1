package com.GDT.sidm_2014;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Shum on 10/12/2014.
 */


public class Entity {
    Vector2D Pos;
    Vector2D Dir;
    Vector2D Vel;
    Vector2D Accel;
    int Index;
    int MaxIndex;
    int Health;
    Objects theObject;

    int ScreenHeight;
    int ScreenWidth;

    //Store bitmap
    public Bitmap[] Texture;

    Entity() {
        Pos = new Vector2D(0, 0);
        Dir = new Vector2D(0, 1);
        Vel = new Vector2D(0, 0);
        Accel = new Vector2D(0,0);
        Index = 0;
        MaxIndex = 0;
        Health = 100;
    }

    Entity(int number)
    {
        Pos = new Vector2D(0, 0);
        Dir = new Vector2D(0, 1);
        Vel = new Vector2D(0, 0);
        Index = 0;
        MaxIndex = 0;
        EnableBitmap(number);
        Health = 100;

    }

    void EnableBitmap(int number)
    {
        Texture = new Bitmap[number];
        MaxIndex = number;
    }

    public static Boolean CollisionDetection(Entity a, Entity b)
    {
        // Start to detect collision of the top left corner
        if (b.Pos.x >=a.Pos.x && b.Pos.x<=a.Pos.x+a.Texture[a.Index].getWidth())
        {
            if (b.Pos.y>=a.Pos.y&& b.Pos.y<=a.Pos.y+a.Texture[a.Index].getHeight()) // Comparing yellow box to blue box
                return true;
        }

        // Top right corner
        if (b.Pos.x+b.Texture[b.Index].getWidth()>= a.Pos.x && b.Pos.x + b.Texture[b.Index].getWidth() <= a.Pos.x + a.Texture[a.Index].getWidth())
        {
            if (b.Pos.y >= a.Pos.y && b.Pos.y <= a.Pos.y + a.Texture[a.Index].getHeight())
                return true;
        }

        //Bottom left corner
        if (b.Pos.x >=a.Pos.x && b.Pos.x<=a.Pos.x+a.Texture[a.Index].getWidth())
        {
            if (b.Pos.y + b.Texture[b.Index].getHeight() >= a.Pos.y && b.Pos.y + b.Texture[b.Index].getHeight() <=a.Pos.y+a.Texture[a.Index].getHeight()) // Comparing yellow box to blue box
                return true;
        }

        // Bottom right corner
        if (b.Pos.x+b.Texture[b.Index].getWidth()>= a.Pos.x && b.Pos.x + b.Texture[b.Index].getWidth() <= a.Pos.x + a.Texture[a.Index].getWidth())
        {
            if (b.Pos.y + b.Texture[b.Index].getHeight() >= a.Pos.y && b.Pos.y + b.Texture[b.Index].getHeight() <=a.Pos.y+a.Texture[a.Index].getHeight()) // Comparing yellow box to blue box
                return true;
        }

        return false;
    }

    void EntityUpdate()
    {
        Index ++;
        if(MaxIndex != 0 )
         Index %= MaxIndex;
        Vel = Vector2D.AddVectors(Vel,Accel);
        Pos = Vector2D.AddVectors(Pos,Vel);

        if(Pos.x < 0)
        {
            Pos.x = 0;
        }
        else if(Pos.x > ScreenWidth - Texture[Index].getWidth())
        {
            Pos.x = ScreenWidth - Texture[Index].getWidth();
        }
        if(Pos.y < 0)
        {
            Pos.y = 0;
        }
        else if(Pos.y > ScreenHeight - Texture[Index].getHeight())
        {
            Pos.y = ScreenHeight - Texture[Index].getHeight();
        }
    }

    void GetScreenMetrics(int width, int height)
    {
        ScreenHeight = height;
        ScreenWidth = width;
    }


    protected boolean isOutsidePlayfield(int width, int height) {
        return Pos.x < 0 || Pos.x  >= width
                || Pos.y  < 0 || Pos.y  >= height;
    }
    ///////////////////////////////////////////////////////////////
    //Done by Jia Wang

    void goLocation(Vector2D theNewPos, Entity go, float speed)
    {

        Vector2D TheDirection = Vector2D.SubtractVectors(theNewPos,go.Pos).normalized();

        TheDirection.ScaleMultiplication(speed);
        go.Vel = TheDirection;
    }
    boolean ReachedLocation(Vector2D thePosReached, Entity go)
    {
        //return 1 > (thePosReached - go->pos).Length();
        //Vector2D.SubtractVectors(thePosReached,go.Pos).normalize();
        if ( Vector2D.distance(thePosReached, go.Pos) < 1) {
            return (true);
        }
        return(false);
    }
    //////////////////////////////////////////////////////////////////

    void EntityDraw(Canvas canvas)
    {
        canvas.drawBitmap(Texture[Index], Pos.x, Pos.y, null);
    }

    static void Instantiate(){};
}
