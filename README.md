### Missionaries and Cannibals Solver Using A\* Search

This project implements the classic **Missionaries and Cannibals** problem using the A\* search algorithm. The solution is designed to find a safe sequence of crossings to move all missionaries and cannibals from the left bank to the right bank under given constraints.

#### Files Included

*   **AStarSolver.java**: Core implementation of the A\* search algorithm.
    
*   **State.java**: Represents the state of the problem, tracking missionaries, cannibals, and the boat's position.
    
*   **Main.java**: Entry point to the application, handling user input and output.
    

#### How It Works

*   **State.java**This class models the problem's state by maintaining:
    
    *   The number of missionaries and cannibals on both the left and right banks.
        
    *   The boat's position (whether it's on the left or right side).It includes methods to:
        
    *   Validate states (ensuring that missionaries are never outnumbered by cannibals on either bank).
        
    *   Check if a state is the goal state (all missionaries and cannibals safely on the right bank).
        
    *   Override equals, hashCode, and toString for proper state comparison and display.
        
*   **AStarSolver.java**This class implements the A\* search algorithm:
    
    *   It defines an inner Node class that stores the current state, a reference to its parent, the cost to reach that state, and a heuristic value.
        
    *   A priority queue is used to explore states based on the sum of the cost and heuristic, ensuring the most promising states are processed first.
        
    *   The getChildren method generates valid moves by considering possible combinations of missionaries and cannibals that can be transported by the boat.
        
    *   A heuristic function estimates the number of crossings required to reach the goal, guiding the search process.
        
    *   Upon finding a goal state, the path is reconstructed by backtracking through the parent nodes.
        
*   **Main.java**This is the entry point of the application:
    
    *   It prompts the user to input the number of missionaries/cannibals (N), the boat capacity (M), and the maximum allowed crossings (K).
        
    *   It then creates an instance of AStarSolver with these parameters, executes the search, and measures the time taken.
        
    *   Finally, it prints the solution path if one is found or notifies the user if no solution exists within the given constraints.
        

#### How to Run
```bash
1.  javac \*.java
```
```bash  
2.  java Main
``` 
3.  **Provide the following inputs when prompted**:
    
    *   Number of missionaries and cannibals (N).
        
    *   Boat capacity (M).
        
    *   Maximum allowed crossings (K).
  
<br/>


## For more details, please refer to the accompanying ![report PDF](Missionaries_And_Cannibals_Report.pdf)

