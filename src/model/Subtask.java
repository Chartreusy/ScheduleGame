package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * Subtasks are ventures employees can be sent out on.
 * They consume some amount of energy and they have a certain difficulty
 * There is a probability associated with each reward
 * from the subtask.
 *
 * Probability of item drop is dependent on subtask, not per item
 *
 */
public class Subtask {
    int id;
    String name;
    int difficulty; // rate of energy consumption and efficiency application
    List<Item> rewards;
    List<Double> rewardProbs;

    public Subtask(int id, String name, int difficulty){
        this.id = id;
        this.name = name;
        rewards = new ArrayList<Item>();
        rewardProbs = new ArrayList<Double>();
    }
    public void addReward(Item reward, double prob){
        rewards.add(reward);
        rewardProbs.add(prob);
    }
    public void addReward(int reward, double prob){
        rewards.add(ItemList.harvest[reward]);
        rewardProbs.add(prob);
    }
    public void addReward(ItemList.hItem reward, double prob){
        rewards.add(ItemList.harvest[reward.ordinal()]);
        rewardProbs.add(prob);
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Item> getRewards() {
        return rewards;
    }

    public void setRewards(List<Item> rewards) {
        this.rewards = rewards;
    }

    public List<Double> getRewardProbs() {
        return rewardProbs;
    }

    public void setRewardProbs(List<Double> rewardProbs) {
        this.rewardProbs = rewardProbs;
    }
}
