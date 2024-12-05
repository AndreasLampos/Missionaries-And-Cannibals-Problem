import java.util.*;

public class AStarSolver {
    private final int totalMissionaries; // Number of missionaries
    private final int totalCannibals; // Number of cannibals
    private final int boatCapacity; // Boat capacity
    private final int maxCrossings; // Maximum allowed crossings

    // Constructor to initialize the variables
    public AStarSolver(int totalMissionaries, int totalCannibals, int boatCapacity, int maxCrossings) {
        this.totalMissionaries = totalMissionaries;
        this.totalCannibals = totalCannibals;
        this.boatCapacity = boatCapacity;
        this.maxCrossings = maxCrossings;
    }

    // Node class
    private static class Node {
        State state;// State of the node
        Node parent;// Parent of the node
        int cost;// Cost to reach the node
        int heuristic;// Heuristic value of the node

        // Constructor to initialize the node
        Node(State state, Node parent, int cost, int heuristic) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }
    }

    // A* algorithm
    public List<State> AStarSolve() {
        // Priority queue to store nodes
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic));// Compare nodes based on cost and heuristic
        Map<State, Integer> ClosedMap = new HashMap<>(); // Tracks the best cost to reach each state

        State initialState = new State(totalMissionaries, totalCannibals, 0, 0, true);// Initial state
        Node initialNode = new Node(initialState, null, 0, heuristic(initialState));// Initial node
        openSet.add(initialNode);// Add initial node to the open set
        ClosedMap.put(initialState, 0);// Add initial state to the closed map

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();// Get the node with the lowest cost(head)

            // Check if the current node is the goal state
            if (currentNode.state.isGoalState(totalMissionaries, totalCannibals)) {
                return buildPath(currentNode);
            }

            // Generate children of the current node
            for (State neighbor : getChildren(currentNode.state)) {
                // Calculate the cost to reach the neighbor
                int newCost = currentNode.cost + 1;

                // Skip states exceeding max crossings or higher-cost revisits
                if (newCost > maxCrossings || (ClosedMap.containsKey(neighbor) && ClosedMap.get(neighbor) <= newCost)) {
                    continue;
                }

                // Add the neighbor to the open set and update the closed map
                int heuristicValue = heuristic(neighbor);
                Node neighborNode = new Node(neighbor, currentNode, newCost, heuristicValue);
                openSet.add(neighborNode);
                ClosedMap.put(neighbor, newCost); // Update the best cost for this state
            }
        }
        return null; // No solution found within the given constraints.
    }

    // Generate children of a state
    private List<State> getChildren(State state) {
        List<State> children = new ArrayList<>();// List to store children of the state
        int direction = state.boatOnLeft ? -1 : 1;// Direction of the boat

        // Generate all possible combinations of missionaries and cannibals
        for (int missionaries = 0; missionaries <= boatCapacity; missionaries++) {
            for (int cannibals = 0; cannibals <= boatCapacity - missionaries; cannibals++) {
                if (missionaries == 0 && cannibals == 0) continue;// Skip the case where both are zero
                if (missionaries + cannibals > boatCapacity) continue; // Skip invalid combinations

                if (missionaries > 0 && cannibals > missionaries) continue;// When more cannibals than missionaries on boat skip

                int newMissionariesLeft = state.missionariesLeft + direction * missionaries; // Update missionaries on the left
                int newCannibalsLeft = state.cannibalsLeft + direction * cannibals; // Update cannibals on the left
                int newMissionariesRight = state.missionariesRight - direction * missionaries;// Update missionaries on the right
                int newCannibalsRight = state.cannibalsRight - direction * cannibals;// Update cannibals on the right

                State newState = new State(newMissionariesLeft, newCannibalsLeft, newMissionariesRight, newCannibalsRight, !state.boatOnLeft);// Create a new state

                // Check if the new state is valid
                if (newState.isValid()) {
                    children.add(newState);
                }
            }
        }
        // Return the list of valid children
        return children;
    }

    // Heuristic function
    private int heuristic(State state) {
        // Heuristic function: Number of crossings required to move all missionaries and cannibals
        return (state.missionariesLeft + state.cannibalsLeft + boatCapacity - 1) / boatCapacity;
    }

    // Build path from the goal state to the initial state
    private List<State> buildPath(Node node) {
        List<State> path = new ArrayList<>();
        // Traverse the path from the goal state to the initial state
        while (node != null) {
            path.add(node.state);
            node = node.parent;
        }
        // Reverse the path to get the correct order
        Collections.reverse(path);
        return path;
    }
}
