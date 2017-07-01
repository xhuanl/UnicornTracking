package com.unicorn.tracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Search all the points imported from the input file 
 * to find the required number of the closest points.
 * @author xhuan
 *
 */
public class Search {
	
    private String inputFile = null;
    private String outputFile = null;

    private Point centraPoint = null;
    private SearchResult result = null;
    
    /**
     * Constructor.
     * @param input The path of the input file.
     * @param output The path of the output file.
     * @param point The point where the distance to be calculated from other points.
     * @param targetSize The number of the closest points to be find.
     */
    public Search(String input, String output, Point point, int targetSize) {
        inputFile = input;
        outputFile = output;
        centraPoint = point;
        result = new SearchResult(targetSize);
    }
    
    /**
     * Read the input file and add the points to the array 
     * if its distance to the central point is close.
     * @return
     */
    public boolean processInputFile() {
        
        // Create a reader to read the input file.
        BufferedReader bufferedReader = null;
    
    	try {
    	    bufferedReader = new BufferedReader(new FileReader(inputFile));
    	} catch (FileNotFoundException  ex) {
    	    System.out.println("Invalid input file path.");
    	    return false;
        }
    
        String line;
    
        try {
            // Read each line of the input.
            while ((line = bufferedReader.readLine()) != null) {

                // Ignore the line if it is empty.
                if (line.length() == 0) {
                    continue;
                }
                
                try {
                    // Create the Point object from the input.
                    Point point = new Point(line);
                    // Calculate the distance.
                    point.setDistanceSquare(centraPoint.distanceSquareFrom(point));
                    // Try to add the result to the final list.
                    result.add(point);
                } catch(Exception ex) {
                    System.out.println(ex.toString());
                    continue;
                }
            }
        } catch (IOException ex) {
            System.out.println("Failed to read the input file. Exception=" + ex.toString());
            return false;
        } finally {
            // Close the BufferedReader.
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch(IOException ex) {
                    System.out.println("Failed to close the buffer reader. Exception=" + ex.toString());
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Print the result.
     */
    public void printResult() {
    	
        // Check if the array is null.
        if (result == null) {
            System.out.println("No result to print.");
            return;
        }
       
        File output = null;

        // Validate the output file path.
        try {
            output = new File(outputFile);
        } catch(NullPointerException ex) {
            System.out.println("Invalid output file.");
            return;
        }
        
        // Remove the existing output file.
        if (output.exists()) {
            output.delete();
            output = new File(outputFile);
        }
    
        // Create a new output file.
        try {
            output.createNewFile();
        } catch(IOException ex) {
            System.out.println("Cannot create the output file.");
            return;
        } catch(SecurityException ex) {
            System.out.println("Not allowed to create the output file.");
            return;
        }

        // Write the result to the output file.
        try {
            // Create the writer object.
            BufferedWriter bufferedWriter = null;
            bufferedWriter = new BufferedWriter(new FileWriter(output));
    
            if (bufferedWriter != null) {
                
                for (int i = 0; i < result.getSize(); i++) {
                    Point point = result.getResult()[i];
                    
                    /* Point can be null the the number of the available points
                       is less than the required number. */
                    if (point != null) {
                        bufferedWriter.write("(" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")");
                        bufferedWriter.newLine();
                    }
                }
            }
            // Close the writer.
            bufferedWriter.close();
            
            } catch(Exception ex){
                System.out.println("Failed to write the output. Exception=" + ex.toString());
            }
        }
    }
