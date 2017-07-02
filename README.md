This application prints the result of the closest points to the designated point for tracking the Unicorns.

It contains the following files:

Main.java:

The entry point of the application. It reads, parses and validates the input, calls the 
Search.java to search the points and print out the results.

Point.java:

This class represents the points with coordinates. It also has a distance member to record 
the distance to the designated point. The member is not actually the distance but the square 
of the distance because it saves the time to run sqrt() and also it keeps the member as an integer. 

SearchResult.java:

This class represents the actual results to be printed after the search. It uses an array 
to store the results and the array is sorted as we are using the method similar to the insertion sort 
to add a new point. The new point can only be inserted to the array if the distance from that point 
is among the closest ones to the designated point.

Search.java:

This class parses the input file, creates points and calculates the distances. The distance of a 
new point is compared with the distances of the existing points in the array. If the distance of 
the new point is less than an existing point, the new point will be added to the array and the existing 
point with the longest distance will be dropped.

build.xml:

This is the build script. In order to build, JDK needs to be installed and JAVA_HOME needs to be configured. 
Ant needs to be installed and ANT_HOME needs to be configured. To build on both Windows and Linux system, 
in the build.xml folder/path, run the following command.

    ant

To run the program:

    ant run

input.txt:

The sample input file for the points.

output.txt:

The result of running the application using the sample input.

To run the program, use the following command:

    java -classpath unicorn.jar com.unicorn.tracking.Main "<path to input.txt>" "(1, 0, 0)" 5 "<path to output.txt>"

On Widows:

    java -classpath "C:\Code\Java\Unicorn\UnicornTracking\build\jar\unicorn.jar" com.unicorn.tracking.Main 
    "C:\Code\Java\Unicorn\UnicornTracking\input.txt" "(1, 0, 0)" 5 "C:\Code\Java\Unicorn\UnicornTracking\output.txt"

On Linux:

    java -classpath "/root/Unicorn/UnicornTracking/build/jar/unicorn.jar" com.unicorn.tracking.Main 
    "/root/Unicorn/UnicornTrackig/input.txt" "(1, 0, 0)" 5 "/root/Unicorn/UnicornTracking/output.txt"

Where

"/root/Unicorn/UnicornTrackig/input.txt" is the input file which contains all the points. Need to be quoted.

"(1, 0, 0)" is the designated point from which the rest points to be calculated. Need to be quoted.

5 is the expected amount of points to be found. Do not need to be quoted.

"/root/Unicorn/UnicornTrackig/output.txt" is the output file of the search result. Need to be quoted.


Discussion:

The program loops through all the points in the input file, calculates the distance of each point immediately 
so that those points with longer distances will be dropped. The information for those dropped points will not 
use the memory. The big O for this operation is O(n) where n is the number of the points listed in the input file. 
The invalid input will be dropped.

After creating a Point object for each point, the distance of the new point will be compared with the distances 
of the existing points in the array. If the distance is less than an existing point, the new point will be inserted 
to the array. As the size of the array is the size of the expected points to be printed, the big O of this operation 
will be O(m) where m is the expected number of the closest points.

The total big O would be O(mn).

