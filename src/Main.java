import java.util.List;
import java.util.Scanner;

public class Main {

    private static int N ; // Number of missionaries and cannibals
    private static int M ; // Boat capacity
    private static int K ; // Maximum allowed crossings

    public static void main(String[] args) {


        readInput();// Read input from the user

        AStarSolver solver = new AStarSolver(N, N, M, K);// Create a new AStarSolver object
        long startTimer = System.currentTimeMillis();// Start the timer
        List<State> terminalState = solver.AStarSolve();// Solve the problem
        long endTimer = System.currentTimeMillis();// End the timer

        // Print the solution
        if (terminalState == null) {
            System.out.println("No terminalState found with the given variables.");
        } else {
            System.out.println("Solution found:");
            // Print the path
            for (State state : terminalState) {
                System.out.println(state);
            }
        }

        // Print the time taken to solve the problem
        System.out.println("Time: " + (double)(endTimer - startTimer) / 1000 + " sec.");

    }

    // Function to read input from the user
    public static void readInput() {
        Scanner input = new Scanner(System.in);// Create a new Scanner object
        System.out.println("Enter the number of missionaries and cannibals:");
        N = input.nextInt();// Read the number of missionaries and cannibals
        // Validate the input
        while (N < 1) {
            System.out.println("Please enter a valid number of missionaries and cannibals:");
            N = input.nextInt();
        }
        System.out.println("Enter the boat capacity:");
        M = input.nextInt();// Read the boat capacity
        // Validate the input
        while (M < 1) {
            System.out.println("Please enter a valid boat capacity:");
            M = input.nextInt();
        }
        System.out.println("Enter the maximum allowed crossings:");
        K = input.nextInt();// Read the maximum allowed crossings
        // Validate the input
        while (K < 1) {
            System.out.println("Please enter a valid number of maximum allowed crossings:");
            K = input.nextInt();
        }
        input.close();// Close the Scanner object
    }
}