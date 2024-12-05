import java.util.Objects;

public class State {
    int missionariesLeft;
    int cannibalsLeft;
    int missionariesRight;
    int cannibalsRight;
    boolean boatOnLeft;

    public State(int missionariesLeft, int cannibalsLeft, int missionariesRight, int cannibalsRight, boolean boatOnLeft) {
        this.missionariesLeft = missionariesLeft;
        this.cannibalsLeft = cannibalsLeft;
        this.missionariesRight = missionariesRight;
        this.cannibalsRight = cannibalsRight;
        this.boatOnLeft = boatOnLeft;
    }

    public boolean isValid() {
        return (missionariesLeft == 0 || missionariesLeft >= cannibalsLeft) &&
                (missionariesRight == 0 || missionariesRight >= cannibalsRight);
    }

    public boolean isGoalState(int totalMissionaries, int totalCannibals) {
        return missionariesLeft == 0 && cannibalsLeft == 0 &&
                missionariesRight == totalMissionaries && cannibalsRight == totalCannibals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return missionariesLeft == state.missionariesLeft &&
                cannibalsLeft == state.cannibalsLeft &&
                missionariesRight == state.missionariesRight &&
                cannibalsRight == state.cannibalsRight &&
                boatOnLeft == state.boatOnLeft;
    }

    @Override
    public String toString() {
        return String.format("Left: (%dM, %dC), Right: (%dM, %dC), Boat on left: %b",
                missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, boatOnLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, boatOnLeft);
    }
}