package com.unicorn.tracking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Point class to represent the points.
 * @author xhuan
 *
 */
public class Point {

    private int X = 0;
    private int Y = 0;
    private int Z = 0;
    
    private int distanceSquare = 0;
    
    /**
     * Constructor.
     * @param x
     * @param y
     * @param z
     */
    public Point(int x, int y, int z) {
        X = x;
        Y = y;
        Z = z;
    }
    
    /**
     * Point class constructor. 
     * Parse the co-ordinates from the input. 
     * The exceptions should be handled by the caller.
     * @param input
     * @throws Exception
     */
    public Point(String input) throws Exception {

        // Create a Pattern object
        Pattern regex = Pattern.compile("\\((.+)\\)");

        // Create matcher object.
        Matcher matcher = regex.matcher(input);

        // Find the result.
        String result = null;
        
        if (matcher.find()) {
            result = matcher.group(0);
            result = result.replaceAll("\\(|\\)", "");

            String[] split = result.split(",");
            
            if (split.length != 3) {
                throw new Exception("Invalid input: " + input);
            }

            X = Integer.parseInt(split[0].trim());
            Y = Integer.parseInt(split[1].trim());
            Z = Integer.parseInt(split[2].trim());
        } else {
            throw new Exception("Invalid input: " + input);
        }
    }
    
    public int getX() {
        return  X;
    }
    
    public int getY() {
        return Y;
    }
    
    public int getZ() {
        return Z;
    }
    
    public int getDistanceSquare() {
        return distanceSquare;
    }
    
    public void setDistanceSquare(int ds) {
        distanceSquare = ds;
    }
    
    /* Using the distance square to avoid the sqrt operation
       and keep the integer result. */
    public int distanceSquareFrom(Point point) {
        return (point.getX() - X) * (point.getX() - X) +
                (point.getY() - Y) * (point.getY() - Y) +
                (point.getZ() - Z) * (point.getZ() - Z);
    }
}
