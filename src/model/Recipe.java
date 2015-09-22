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
    HashMap<Item, Integer> ingredients;
    Item output;
    public Recipe(int id, Item output){
        this.id = id;
        this.output = output;
        ingredients = new HashMap<Item, Integer>();
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
