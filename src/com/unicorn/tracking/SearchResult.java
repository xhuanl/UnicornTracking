package com.unicorn.tracking;

/**
 * SearchResult class represents the final result to be printed.
 * @author xhuan
 *
 */
public class SearchResult {
    
    int maxSize = 0;
    int actualSize = 0;
    
    Point[] result = null;
    
    /**
     * Constructor.
     * @param size
     */
    public SearchResult(int size) {
    
        maxSize = size;
        result = new Point[maxSize];
    }

    /**
     * Using an algorithm similar to insertion sort
     * to store the qualified points. 
     * @param point
     */
    public void add(Point point) {
        
        // Check if the input is null.
        if (point == null) {
            return;
        }

        // Initialize the first element.
        if (actualSize == 0) {
            result[actualSize] = point;
            actualSize++;
            return;
        }

        /*
         * If the expected number of point is not filled and 
         * the distance to the new point is greater the longest 
         * distance we have currently, add the new point as the
         * last element.
         */
        if (point.getDistanceSquare() >= result[actualSize - 1].getDistanceSquare()) {
            if (actualSize < maxSize) {
                result[actualSize] = point;
                actualSize++;
            }
            return;
        }
        
        int i = actualSize - 1;
        
        /* Similar to the Insertion sort, if the distance to the new point is 
         * between the distances of the existing points, insert the new point
         * to the array with proper order.
         */
        while (i > 0 && point.getDistanceSquare() < result[i].getDistanceSquare()) {
            result[i] = result[i - 1];
            i--;
        }

        result[i] = point;
        return;
    }
    
    public int getSize() {
        return actualSize;
    }
    
    public Point[] getResult() {
        return result;
    }
}
