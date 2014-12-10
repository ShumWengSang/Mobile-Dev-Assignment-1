package com.GDT.sidm_2014;

/**
 * Created by Shum on 10/12/2014.
 */
public class Vector2D {

    public float x;
    public float y;

    // Constructors

    public Vector2D() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }
    // Compare two vectors

    public boolean equals(Vector2D other) {
        return (this.x == other.x && this.y == other.y);
    }

    //Get distance between two vectors
    public static double distance(Vector2D a, Vector2D b) {
        float v0 = b.x - a.x;
        float v1 = b.y - a.y;
        return Math.sqrt(v0*v0 + v1*v1);
    }
    public static double distancesquared(Vector2D a, Vector2D b) {
        float v0 = b.x - a.x;
        float v1 = b.y - a.y;
        return (v0*v0 + v1*v1);
    }

    // sets length to 1
    public void normalize() {
        double length = Math.sqrt(x*x + y*y);
        if (length != 0.0) {
            float s = 1.0f / (float)length;
            x = x*s;
            y = y*s;
        }
    }

    public Vector2D normalized() {
        Vector2D temp = new Vector2D();
        double length = Math.sqrt(x*x + y*y);
        if (length != 0.0) {
            float s = 1.0f / (float) length;
            temp.x = x * s;
            temp.y = y * s;
        }
        return temp;
    }

    public void ScaleMultiplication(float number)
    {
        this.x *= number;
        this.y *= number;
    }

    public Vector2D VScaleMultiplication(float number)
    {
        Vector2D temp = new Vector2D();
        temp.x = x * number;
        temp.y = y * number;
        return temp;
    }

    public static Vector2D AddVectors (Vector2D a, Vector2D b)
    {
        Vector2D newVector = new Vector2D();
        newVector.x = a.x + b.x;
        newVector.y = a.y + b.y;
        return newVector;
    }

    public static Vector2D SubtractVectors(Vector2D a, Vector2D b)
    {
        Vector2D newVector = new Vector2D();
        newVector.x = a.x - b.x;
        newVector.y = a.y - b.y;
        return newVector;
    }

    public void Set (float a, float b)
    {
        x = a;
        y = b;
    }

}
