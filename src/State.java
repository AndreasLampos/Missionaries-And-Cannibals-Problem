import java.util.Objects;

public class State {
    int missionariesLeft;// Number of missionaries on the left side
    int cannibalsLeft;// Number of cannibals on the left side
    int missionariesRight;// Number of missionaries on the right side
    int cannibalsRight;// Number of cannibals on the right side
    boolean boatOnLeft;// Boat position

    // Constructor to initialize the state
    public State(int missionariesLeft, int cannibalsLeft, int missionariesRight, int cannibalsRight, boolean boatOnLeft) {
        this.missionariesLeft = missionariesLeft;
        this.cannibalsLeft = cannibalsLeft;
        this.missionariesRight = missionariesRight;
        this.cannibalsRight = cannibalsRight;
        this.boatOnLeft = boatOnLeft;
    }

    // Check if the state is valid
    public boolean isValid() {
        return (missionariesLeft == 0 || missionariesLeft >= cannibalsLeft) &&
                (missionariesRight == 0 || missionariesRight >= cannibalsRight);
    }

    // Check if the state is the goal state
    public boolean isGoalState(int totalMissionaries, int totalCannibals) {
        // Check if all missionaries and cannibals are on the right side
        return missionariesLeft == 0 && cannibalsLeft == 0 &&
                missionariesRight == totalMissionaries && cannibalsRight == totalCannibals;
    }

    // Override equals method to compare two states
    @Override
    public boolean equals(Object o) {
        // Check if the object is the same
        if (!(o instanceof State other)) return false;
        return missionariesLeft == other.missionariesLeft &&
                cannibalsLeft == other.cannibalsLeft &&
                missionariesRight == other.missionariesRight &&
                cannibalsRight == other.cannibalsRight &&
                boatOnLeft == other.boatOnLeft;
    }

    // Override toString method to print the state
    @Override
    public String toString() {
        return String.format(
                "Left side - Missionaries: %d👤, Cannibals: %d🍖 | Right side - Missionaries: %d👤, Cannibals: %d🍖 | Boat is on the %s side",
                missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, boatOnLeft ? "left" : "right"
        );
    }

    // Override hashCode method to generate a hash code for the state
    @Override
    public int hashCode() {
        return Objects.hash(missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, boatOnLeft);
    }
}
