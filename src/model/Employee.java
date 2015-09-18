package model;

/**
 * Created by Chartreuse on 18/09/15.
 */
public class Employee {
    int id;
    String name;
    int energy;
    int efficiency;
    Subtask assigned;

    public Employee(int id, String name){
        this.id = id;
        this.name = name;
        this.energy = 100;
        this.efficiency = 100;
        this.assigned = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public Subtask getAssigned() {
        return assigned;
    }

    public void setAssigned(Subtask assigned) {
        this.assigned = assigned;
    }
}
