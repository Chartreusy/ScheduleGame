package model;

import controller.Controller;
import global.Constants;
import view.ConsoleView;

import java.util.*;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * We have some number of 'people'
 * We have a task(s) to accomplish
 * We have several subtasks to perform
 * Each subtask consumes some amount of a person's time and energy
 *
 *
 *Do we even need IDs or is object id good enough?
 *
 */
public class Model extends Observable {
    public static enum screen {VENTURE, CRAFT};
    public screen curScreen;
    Selection selection;
    List<Employee> emps;
    Task curTask;
    List<Subtask> availableSubtasks;
    List<Recipe> recipes;
    HashMap<Item, Integer> inventory;
    int time;

    public Model(){

    }
    public void initModel(){
        selection = new Selection();
        curScreen = screen.VENTURE;
        time = 0;
        emps = new ArrayList<Employee>();
        curTask = new Task(0);
        availableSubtasks = new ArrayList<Subtask>();
        inventory = new HashMap<Item, Integer>();
        recipes = new ArrayList<Recipe>();

        emps.add(new Employee(0, "Alice"));
        emps.add(new Employee(1, "Bob"));
        emps.add(new Employee(2, "Cindy"));
        emps.add(new Employee(3, "Deirdre"));
        emps.add(new Employee(4, "Eva"));
        emps.add(new Employee(5, "Frank"));
        emps.add(new Employee(6, "Gordon"));


        Subtask forest = new Subtask(0, "Forest", 10);
        Subtask beach = new Subtask(1, "Beach", 10);
        Subtask junk = new Subtask(2, "Junkyard", 15);
        forest.addReward(ItemList.hItem.LOG.ordinal(), 0.8);
        forest.addReward(ItemList.hItem.WATER.ordinal(), 0.2);
        forest.addReward(ItemList.hItem.VINE.ordinal(), 0.4);
        forest.addReward(ItemList.hItem.NAIL.ordinal(), 0.1);

        beach.addReward(ItemList.hItem.LOG  , 0.1);
        beach.addReward(ItemList.hItem.WATER, 1.0);
        junk.addReward(ItemList.hItem.GLUE  , 0.4);
        junk.addReward(ItemList.hItem.NAIL  , 0.6);

        availableSubtasks.add(forest);
        availableSubtasks.add(beach);
        availableSubtasks.add(junk);

        Recipe rPlank = new Recipe(0, ItemList.synth[ItemList.sItem.PLANK.ordinal()]);
        Recipe rRope = new Recipe(1, ItemList.synth[ItemList.sItem.ROPE.ordinal()]);
        rPlank.addIng(ItemList.hItem.LOG, 1);
        rPlank.addIng(ItemList.hItem.NAIL, 2);
        rRope.addIng(ItemList.hItem.VINE, 1);
        rRope.addIng(ItemList.hItem.GLUE, 1);
        recipes.add(rPlank);
        recipes.add(rRope);

        for(Employee emp : emps){
            emp.setAssigned(forest);
        }

        selection.setCurSel(emps.get(0));

        stateChange();
    }

    // this scales properly
    public void moveCursor(int direction){ // +1/-1
        int index = ((Employee)selection.getCurSel()).getId();
        index += direction;
        if(index <0) {
            index = emps.size()-1;
            selection.setVsliderIndex(index - Constants.BUFFER_HEIGHT);
        }
        else if(index >= emps.size()) {
            index = 0;
            selection.setVsliderIndex(0);
        }
        if(index - selection.getVsliderIndex() >= Constants.BUFFER_HEIGHT){
            selection.setVsliderIndex(index - Constants.BUFFER_HEIGHT+1);
        } else if (index < selection.getVsliderIndex()){
            selection.setVsliderIndex(index);
        }
        selection.setCurSel(emps.get(index));
        stateChange();
    }

    public void moveAssignment(int direction){ // +1/-1
        int index = ((Employee)selection.getCurSel()).getAssigned().getId();
        index += direction;
        if(index < 0) {
            index = availableSubtasks.size()-1;
            selection.setHsliderIndex(index - Constants.BUFFER_WIDTH);
        }
        else if(index >= availableSubtasks.size()) {
            index = 0;
            selection.setHsliderIndex(0);
        }
        if(index - selection.getHsliderIndex() > Constants.BUFFER_WIDTH){
            selection.setHsliderIndex(index - Constants.BUFFER_WIDTH);
        }
        ((Employee)selection.getCurSel()).setAssigned(availableSubtasks.get(index));
        stateChange();
    }

    public void stateChange(){
        setChanged();
        notifyObservers();
    }

    public void setCurScreen(screen curScreen) {
        this.curScreen = curScreen;
        stateChange();
    }


    public screen getCurScreen() {
        return curScreen;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public Task getCurTask() {
        return curTask;
    }

    public void setCurTask(Task curTask) {
        this.curTask = curTask;
    }

    public List<Subtask> getAvailableSubtasks() {
        return availableSubtasks;
    }

    public void setAvailableSubtasks(List<Subtask> availableSubtasks) {
        this.availableSubtasks = availableSubtasks;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Item, Integer> inventory) {
        this.inventory = inventory;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }
}
