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
    public void addIng(ItemList.hItem ing, int count){
        ingredients.put(ItemList.harvest[ing.ordinal()], count);
    }
}
