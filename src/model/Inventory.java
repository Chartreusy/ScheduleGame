package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chartreusy on 21/09/15.
 */
public class Inventory {
    Map<Item, Integer> itemList;
    public Inventory(){
        itemList = new HashMap<Item, Integer>();
    }
    public void addItem(Item item){
        put(item, 1);
    }
    public void addItem(Item item, Integer num){
        put(item, num);
    }
    public int size(){
        return itemList.size();
    }

    public int get(Item item){
        return itemList.get(item);
    }
    public boolean has(Item item){
        return itemList.containsKey(item);
    }
    public void use(Item item, Integer i){
        if(itemList.containsKey(item)){
            itemList.put(item, itemList.get(item)-i);
        }
    }

    public void put(Item item, Integer i){
        if(itemList.containsKey(item)){
            itemList.put(item, itemList.get(item) + i);
        } else {
            itemList.put(item, i);
        }
    }

    public void clear(){
        itemList.clear();
    }

    public String[] toStringArr(){
        Set<Item> keyset = itemList.keySet();
        String[] ret = new String[keyset.size()];
        int c = 0;
        for(Item i : keyset){
            ret[c] = i.getName() + " " + itemList.get(i);
            c++;
        }
        return ret;
    }


}
