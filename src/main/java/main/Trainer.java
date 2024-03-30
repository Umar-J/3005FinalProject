//yaman
package main;
import java.util.List;
public class Trainer extends User {
    private String specialty;
    private String schedule; // Assuming a simple string representation for the schedule
    private List<Member> members; // A list of Member objects this trainer is associated with

    // Constructor
    public Trainer(int id, String name, String password, String schedule, List<Member> members) {
        super(id, name, password);
        this.schedule = schedule;
        this.members = members;
    }

    // Getters and Setters

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    // Additional methods can be added here, e.g., to add/remove members, update schedule, etc.
}


