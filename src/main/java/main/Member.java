//yaman
package main;

public class Member extends User {
    private int goalWeight;
    private String goalWorkout;

    // Default constructor
    public Member() {
        super();
        setUserType(3);
    }

    // Constructor
    public Member(int id, String name, String password, int userType, int goalWeight, String goalWorkout) {
        super(id, name, password, 3);
        this.goalWeight = goalWeight;
        this.goalWorkout = goalWorkout;
    }

    // Getters and Setters for additional attributes
    public int getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getGoalWorkout() {
        return goalWorkout;
    }

    public void setGoalWorkout(String goalWorkout) {
        this.goalWorkout = goalWorkout;
    }
}
