package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Item, Integer> performAssigned(){
        Map<Item, Integer> gathered = new HashMap<Item, Integer>();
        List<Item> potRewards = assigned.getRewards();
        int times = efficiency/assigned.getDifficulty();
        for(int i = 0; i< times; i++){
            double rand = Math.random();
            for(int j = 0; j< potRewards.size(); j++){
                if(rand <= assigned.getRewardProbs().get(j)){
                    if(gathered.containsKey(potRewards.get(j))){
                        gathered.put(potRewards.get(j), gathered.get(potRewards.get(j))+1);
                    }else {
                        gathered.put(potRewards.get(j), 1);
                    }
                }
            }
        }
        this.energy -= assigned.getDifficulty();
        return gathered;
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
