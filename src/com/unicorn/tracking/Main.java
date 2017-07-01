package com.unicorn.tracking;

/**
 * A program to show the closest number of points from the given point.
 * @author xhuan
 *
 */
public class Main {
	
    /**
     * Number of arguments for the command line.
     */
    private static final int ARG_COUNT = 4;
    
    /**
     * Entry point of the application.
     * Input should be in the format of
     * "/Users/netapp/input.txt" "(1, 2, 3)" 100 "/Users/netapp/output.txt"
     * @param args
     */
    public static void main(String[] args) {
        
        /* Parse and validate the arguments. */
        if (args.length < Main.ARG_COUNT) {
            System.out.println("Invalid command line input");
            return;
        };
    
        String inputFile = args[0];
        Point targetPoint = null;
        
        try {
            targetPoint = new Point(args[1]);
        } catch (Exception ex) {
            System.out.println("Invalid target point.");
            return;
        }
        
        int count = 0;
        
        try {
            count = Integer.parseInt(args[2]);
        } catch(Exception ex)  {
            System.out.println("Invalid input for the total points to search.");
            return;
        }
        
        String outputFile = args[3];
        
        // Create a Search object 
        Search search = new Search(inputFile, outputFile, targetPoint, count);
        
        // Search the points. 
        if (search.processInputFile()) {
            /* Print the output. */
            search.printResult();
        }
        
        System.out.println("Finished tracking.");
    }
}