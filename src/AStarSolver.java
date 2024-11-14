import java.util.*;

public class AStarSolver {
    private final int totalMissionaries;
    private final int totalCannibals;
    private final int boatCapacity;
    private final int maxCrossings;

    public AStarSolver(int totalMissionaries, int totalCannibals, int boatCapacity, int maxCrossings) {
        this.totalMissionaries = totalMissionaries;
        this.totalCannibals = totalCannibals;
        this.boatCapacity = boatCapacity;
        this.maxCrossings = maxCrossings;
    }

    private static class Node {
        State state;
        Node parent;
        int cost;
        int heuristic;

        Node(State state, Node parent, int cost, int heuristic) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }
    }

    public static class Heuristic {
        public static int calculate(State state) {
            return state.missionariesLeft + state.cannibalsLeft;
        }
    }

    public List<State> solve() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic));//compare the sum of cost and heuristic value of all the nodes
        List<State> closedSet = new ArrayList<State>() ;//to keep track of the visited nodes

        State initialState = new State(totalMissionaries, totalCannibals, 0, 0, true);
        Node initialNode = new Node(initialState, null, 0, Heuristic.calculate(initialState));

        openSet.add(initialNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();//remove the node with the lowest cost

            if (currentNode.state.isGoalState(totalMissionaries, totalCannibals)) {
                return buildPath(currentNode);//return the path to the goal state
            }

            if (closedSet.contains(currentNode.state)) {
                continue;
                //if the node is already visited, skip it
                //and to avoid infinite loops
            }

            closedSet.add(currentNode.state);//mark the node as visited

            for (State neighbor : generateSuccessors(currentNode.state)) {
                if (closedSet.contains(neighbor)) continue; //skip the visited nodes

                int newCost = currentNode.cost + 1; //increment the cost by 1

                if (newCost <= maxCrossings) {
                    int heuristic = Heuristic.calculate(neighbor); //calculate the heuristic value of the neighbor
                    openSet.add(new Node(neighbor, currentNode, newCost, heuristic)); //add the neighbor to the open set
                }
            }
        }

        return null; // No solution found within the given constraints.
    }

    private List<State> generateSuccessors(State state) {
        List<State> successors = new ArrayList<>();
        int direction = state.boatOnLeft ? -1 : 1;

        for (int missionaries = 0; missionaries <= boatCapacity; missionaries++) {
            for (int cannibals = 0; cannibals <= boatCapacity - missionaries; cannibals++) {
                if (missionaries == 0 && cannibals == 0) continue; //to avoid empty boat
                if (missionaries + cannibals > boatCapacity) continue; //to avoid overloading the boat

                int newMissionariesLeft = state.missionariesLeft + direction * missionaries;
                int newCannibalsLeft = state.cannibalsLeft + direction * cannibals;
                int newMissionariesRight = state.missionariesRight - direction * missionaries;
                int newCannibalsRight = state.cannibalsRight - direction * cannibals;
                //update the new state based on the direction of the boat

                State newState = new State(newMissionariesLeft, newCannibalsLeft, newMissionariesRight, newCannibalsRight, !state.boatOnLeft); //create a new state

                if (newState.isValid()) {
                    successors.add(newState);
                    //add the new state to the list of successors if it is valid
                }
            }
        }

        return successors;
    }

    private List<State> buildPath(Node node) {
        List<State> path = new ArrayList<>();
        while (node != null) {
            path.add(node.state);
            node = node.parent;
        }
        Collections.reverse(path);//reverse the path to get the correct order
        return path;
    }
}