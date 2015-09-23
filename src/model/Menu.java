package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chartreusy on 21/09/15.
 */
public class Menu {
    List<MenuItem> items;
    public Menu(){
        items = new ArrayList<MenuItem>();
    }
    public void add(MenuItem item){
        items.add(item);
    }
}
