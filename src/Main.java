import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N ; // Number of missionaries and cannibals
        int M ; // Boat capacity
        int K ; // Maximum allowed crossings

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of missionaries and cannibals:");
        N = input.nextInt();
        while (N < 1) {
            System.out.println("Please enter a valid number of missionaries and cannibals:");
            N = input.nextInt();
        }
        System.out.println("Enter the boat capacity:");
        M = input.nextInt();
        while (M < 1) {
            System.out.println("Please enter a valid boat capacity:");
            M = input.nextInt();
        }
        System.out.println("Enter the maximum allowed crossings:");
        K = input.nextInt();
        while (K < 1) {
            System.out.println("Please enter a valid number of maximum allowed crossings:");
            K = input.nextInt();
        }
        input.close();


        AStarSolver solver = new AStarSolver(N, N, M, K);
        long startTimer = System.currentTimeMillis();
        List<State> terminalState = solver.solve();
        long endTimer = System.currentTimeMillis();

        if (terminalState == null) {
            System.out.println("No terminalState found with the given variables.");
        } else {
            System.out.println("Solution found:");
            for (State state : terminalState) {
                System.out.println(state);
            }
        }

        System.out.println("Time: " + (double)(endTimer - startTimer) / 1000 + " sec.");

    }
}