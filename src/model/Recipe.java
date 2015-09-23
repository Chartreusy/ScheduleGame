package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * Consumes Items and spits out new Items
 *
 */
public class Recipe {
    int id;
    int difficulty;
    HashMap<Item, Integer> ingredients;
    Item output;
    public Recipe(int id, Item output){
        this.id = id;
        this.output = output;
        this.difficulty = id; // for now
        ingredients = new HashMap<Item, Integer>();
    }

    public void genIngs(List<Item> items){
        for(int i = 0; i < difficulty; i++){
            int rand = (int)((items.size()-1)*Math.random());
            ingredients.put(items.get(rand), difficulty);
        }
    }


    public boolean craftability(Inventory inv){
        for(Item item : ingredients.keySet()){
            if(!inv.has(item)) return false;
            if(inv.get(item) < ingredients.get(item)){
                return false;
            }
        }
        return true;
    }

    public Integer get(Item item){
        return ingredients.get(item);
    }

    public int size(){
        return ingredients.size();
    }

    public void addIng(ItemList.hItem ing, int count){
        ingredients.put(ItemList.harvest[ing.ordinal()], count);
    }

    public String getName(){
        return output.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Item, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<Item, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Item getOutput() {
        return output;
    }

    public void setOutput(Item output) {
        this.output = output;
    }
}
