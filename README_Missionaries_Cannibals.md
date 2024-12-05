# A* Algorithm for Missionaries and Cannibals Problem
### Project Overview
This project implements the A* algorithm to solve the classic Missionaries and Cannibals problem.<br> The goal is to safely transport missionaries and cannibals across a river using a boat, without ever leaving more cannibals than missionaries on either side of the river.<br> 
The solution is computed under user-defined constraints such as boat capacity and maximum crossings.

### Files Included
1) AStarSolver.java: Core implementation of the A* search algorithm.
2) State.java: Represents the state of the problem, tracking missionaries, cannibals, and the boat's position.
3) Main.java: Entry point to the application, handling user input and output.

### How to Run
* Compile the Java files using the following command:
* javac *.java
* Execute the program with:
* java Main

### Provide the following inputs:
1) Number of missionaries and cannibals (N).
2) Boat capacity (M).
3) Maximum allowed crossings (K).
