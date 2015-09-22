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
    public static enum screen {VENTURE, CRAFT, SUMMARY, INVENTORY};
    public screen curScreen;
    Selection selection;
    Selection secondarySel;
    int control; // which selection. should scale infinitely but i thin i only need 2
    List<Employee> emps;
    Task curTask;
    List<Subtask> availableSubtasks;
    List<Recipe> recipes;
    Inventory inv;
    Inventory recentlyGathered;
    int time;

    public Model(){

    }
    public void initModel(){
        selection = new Selection();
        secondarySel = new Selection();
        control = 0;
        curScreen = screen.VENTURE;
        time = 0;
        emps = new ArrayList<Employee>();
        curTask = new Task(0);
        availableSubtasks = new ArrayList<Subtask>();
        recentlyGathered = new Inventory();
        inv = new Inventory();
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
        forest.setDifficulty(10);
        beach.setDifficulty(20);
        junk.setDifficulty(40);
        forest.addReward(ItemList.hItem.LOG.ordinal(), 0.8);
        forest.addReward(ItemList.hItem.WATER.ordinal(), 0.2);
        forest.addReward(ItemList.hItem.VINE.ordinal(), 0.4);
        forest.addReward(ItemList.hItem.NAIL.ordinal(), 0.1);

        beach.addReward(ItemList.hItem.LOG  , 0.1);
        beach.addReward(ItemList.hItem.WATER, 1.0);
        beach.addReward(ItemList.hItem.SAND, 1.0);
        beach.addReward(ItemList.hItem.SALT, 0.2);

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
            int i = (int)(Math.random()*3);
            if(i == 0) emp.setAssigned(forest);
            else if(i==1) emp.setAssigned(beach);
            else emp.setAssigned(junk);
        }
        resetSelection(emps.size());
        stateChange();
    }

    public void executeOrders(){
        Map<Item, Integer> gathered;
        for(Employee emp : emps){
            gathered = emp.performAssigned();
            for(Item item : gathered.keySet()){
                inv.addItem(item, gathered.get(item));
                recentlyGathered.addItem(item, gathered.get(item));
            }
        }
    }
    public void executeRecipe(){
        Recipe recipe = recipes.get(selection.getCurIndex());
        if(!recipe.craftability(inv)) return;
        HashMap<Item, Integer> ings = recipe.getIngredients();
        for(Item item : ings.keySet()){
            inv.use(item, ings.get(item));
        }
        inv.addItem(recipe.getOutput());
    }

    public void moveCursor(int direction){
        if(control == 0){
            selection.moveCursor(direction);
            resetSecondarySel();
        }else if(control == 1) {
            secondarySel.moveCursor(direction);
        }
    }


    public void moveAssignment(int direction){ // +1/-1
        Employee curEmp = emps.get(selection.getCurIndex());
        int index = curEmp.getAssigned().getId();
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
        curEmp.setAssigned(availableSubtasks.get(index));
    }

    // since only two.
    public void swapControl(){
        control = (control == 0) ? 1:0;
    }

    public void stateChange(){
        setChanged();
        notifyObservers();
    }

    public void resetSelection(int size){
        selection.setCurIndex(0);
        selection.setVsliderIndex(0);
        selection.setScrollsize(size);
        secondarySel.setCurIndex(0);
        secondarySel.setVsliderIndex(0);
        control = 0;
    }
    public void resetSecondarySel(){
        secondarySel.setCurIndex(0);
        secondarySel.setVsliderIndex(0);
        secondarySel.setScrollsize(recipes.get(secondarySel.getCurIndex()).size());
    }


    public void setCurScreen(screen curScreen) {
        if(this.curScreen == screen.SUMMARY) recentlyGathered.clear();
        this.curScreen = curScreen;
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

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory inventory) {
        this.inv = inventory;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Selection getSecondarySel() {
        return secondarySel;
    }

    public void setSecondarySel(Selection secondarySel) {
        this.secondarySel = secondarySel;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public Inventory getRecentlyGathered() {
        return recentlyGathered;
    }

    public void setRecentlyGathered(Inventory recentlyGathered) {
        this.recentlyGathered = recentlyGathered;
    }
}
