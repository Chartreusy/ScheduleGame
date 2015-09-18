package model;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * Items are awarded by subtask completion.
 * They can then be used to build other items
 * or they can be turned in towards a task.
 *
 * Probability of item drop is dependent on subtask, not per item
 *
 */
public class Item {
    int id;
    String name;
    public Item(int id, String name){
        this.id = id;
        this.name = name;
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
}
