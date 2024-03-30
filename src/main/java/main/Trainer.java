//yaman
package main;
import java.util.List;
import java.util.ArrayList;
public class Trainer extends User {
    private String schedule;
    private List<Member> members; // A list of Member objects this trainer is associated with

    // Default constructor
    public Trainer() {
        super();
        setUserType(Trainer);
        this.members = new ArrayList<>(); // Initializes the list to avoid null pointer exceptions
    }

    // Constructor
    public Trainer(int id, String name, String password, int userType, String schedule, List<Member> members) {
        super(id, name, password, 2);
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

}


