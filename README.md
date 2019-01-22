# AI--15-Puzzle-IDAStar
This program is written in Java. This program solves the 15-puzzle using IDAStar.
It supports the following goal state,

1  2  3  4
5  6  7  8
9  10 11 12
13 14 15 0

The main function is written in Main.java and the IDA Star algorithm is written in IDAStar.java
The code for intermediate state structure and the movement of the blank tile is written in Frame.java and Equal.java

The output of the program will print the following if the solution exists, else will print no solution found;
-Moves to reach goal state
-Number of nodes expanded
-Time taken
-Memory Used

To run:

You can compile the files and run the file Main.java in the terminal by giving the input as arguments.
Or else put the files in a default package in Eclipse and run the Main.java by specifying its arguments in the run configuration.

Sample input:
5 2 4 8 10 3 11 14 6 0 9 12 13 1 15 7

Sample output:
Number of Nodes expaned: 10836
Time Taken: 32ms
Moves: DRRUULLDRRUULLDLDRRDLUULURRDDRD
Memory used: 6502kb
