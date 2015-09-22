package view;

import controller.Controller;
import model.*;

import java.util.*;

import global.Constants;

/**
 * Created by Chartreuse on 18/09/15.
 *
 *
 * Employees | Subtask   | Task Req | Inventory
 * Alice     |           |          |
 * Bob       |           |
 * Cindy     |           |
 *           |           |etc.
 *           |           |
 *           |           |
 *           |           |
 *           |           |
 *           |           |
 */
public class ConsoleView implements Observer {

    public ConsoleView(){}

    public void addController(Controller c){

    }
    @Override
    public void update(Observable o, Object arg) {
        Model m = (Model)o;
        // everything is in here i guess
        // we're going to generate each column as separately
        // then stick them all together at the end.
        String[] out = new String[Constants.BUFFER_HEIGHT];
        for(int i = 0; i< out.length; i++){
            out[i] = "";
        }
        switch(m.curScreen){
            case VENTURE:
                //out = employeeColumn(m.getEmps(), (Employee) m.getSelection().getCurSel());
                //appendStrarr(out, subtaskColumns(m.getAvailableSubtasks(), ((Employee)m.getSelection().getCurSel()).getAssigned() ));
                out = printVenture(m.getEmps(), m.getSelection());
                break;
            case CRAFT:
                // list of recipes with ingredients.
                // show how many of each recipe is currently owned
                out = printCrafting(m.getRecipes(), m.getInventory(), m.getSelection(), m.getSecondarySel(), m.getControl());
                break;
            case SUMMARY:
                out = printSummary(m.getRecentlyGathered(), m.getSelection());
                break;
            case INVENTORY:
                out = printSummary(m.getInventory(), m.getSelection());
                break;
        }
        for(int i = 0; i<out.length; i++){
            if(out[i] == null) out[i] = "";
            System.out.println(out[i]);
        }
    }
    public String[] appendStrarr(String[] a, String[] b){
        for(int i = 0; i< a.length; i++){
            a[i] = a[i] + b[i];
        }
        return a;
    } 
    public String normalize(String a, int max){
        if(a.length() > max) {a = a.substring(0, max); return a;}
        String ws = "";
        for(int j = 0; j< max-a.length(); j++ ){
            ws = ws + " ";
        }
        return a+ws;
    }
    // a from left, b from right
    public String doubleNorm(String a, String b, int max){
        a = normalize(a, max);
        a = a.substring(0, a.length() - b.length());
        return a + b;
    }

    public String[] printCrafting(List<Recipe> recipes,
                                  Inventory inv, Selection sel,
                                  Selection ingSel, int control){
        String[] ret = new String[Constants.BUFFER_HEIGHT];
        Arrays.fill(ret, "");
        int vindex = sel.getVsliderIndex();
        for(int i = 0; i< ret.length; i++){
            if(vindex+i >= recipes.size()) break;
            Recipe r  = recipes.get(vindex+i);
            String name = r.getName();
            String held = (inv.has(r.getOutput()))?""+inv.get(r.getOutput()): "0";

            if(vindex+i == sel.getCurIndex()){
                name = (control == 0)? "*"+name : "-"+name;
            }
            ret[i] += doubleNorm(name,
                                 held,
                                 Constants.CELL_WIDTH) + "|";
        }
        Recipe selected = recipes.get(sel.getCurIndex());
        HashMap<Item, Integer> ings = selected.getIngredients();
        Item[] ing = new Item[ings.keySet().size()];
        ings.keySet().toArray(ing);
        int ingvindex = ingSel.getVsliderIndex();
        for(int i = 0; i< ret.length; i++){
            if(ingvindex+i >= ing.length) break;
            String name = ing[i].getName();
            String have = (inv.has(ing[i]))?""+inv.get(ing[i]): "0";
            if(ingvindex+i == ingSel.getCurIndex()){
                name = (control ==1)? "*"+name : "-"+name;
            }
            ret[i] += doubleNorm(name,
                                 have +"/"+selected.get(ing[i]),
                                 Constants.CELL_WIDTH);
        }

        return ret;
    }

    public String[] printSummary(Inventory recent, Selection sel){
        String[] ret = new String[Constants.BUFFER_HEIGHT]; // sliding window
        Arrays.fill(ret, "");
        int vindex = sel.getVsliderIndex();

        String[] rec = recent.toStringArr();

        for(int i = 0; i< ret.length; i++){
            if(vindex+i >= rec.length) break;
            if(vindex+i == sel.getCurIndex()){
                ret[i] = "-";
            }
            ret[i] += rec[vindex+i];

        }
        return ret;
    }


    public String[] printVenture(List<Employee> emps, Selection sel){
        Employee selected = emps.get(sel.getCurIndex());
        String[] ret = new String[Constants.BUFFER_HEIGHT]; // sliding window
        int vindex = sel.getVsliderIndex();
        // if selection is past buffer height, move window down to it
        // dealt with by selection class
        // print employees, emp energy/eff and their subtasks
        for(int i = 0; i < ret.length; i++){
            if(vindex+i >= emps.size()) break;
            Employee e = emps.get(vindex+i);
            String name = (selected.getId() == e.getId())? "*"+e.getName()+"*":e.getName();
            ret[i] = normalize(name, Constants.CELL_WIDTH) ;
            ret[i] = ret[i] + normalize(""+e.getEnergy(),3) + "/" + normalize(""+e.getEfficiency(),3);
            ret[i] = ret[i] + " |";
            ret[i] = ret[i] + normalize(e.getAssigned().getName(), Constants.CELL_WIDTH);
            ret[i] = ret[i] + " |";
        }
        // print selected employee's subtask's stuff
        List<Item> list = selected.getAssigned().getRewards();
        for(int i = 0; i< list.size(); i++){
            if(ret[i] == null || ret[i].equals("")){
                ret[i] = normalize("", 3*Constants.CELL_WIDTH) + "|";
            }
            ret[i] += list.get(i).getName();
        }

        return ret;
    }

    // this does not scale...
    // employee   |  assignedsubtask
    public String[] employeeColumn(List<Employee> emps, Employee curSel){
        String[] ret = new String[Constants.BUFFER_HEIGHT];
        ret[0] = "          | "; // 10 char
        for(int i = 1; i < ret.length; i++){
            String emp = (emps.size() <= i-1)?"":emps.get(i-1).getName();
            if(!emp.equals("") && curSel == emps.get(i-1)) emp = "*"+emp+"*";
            ret[i] = normalize(emp, Constants.CELL_WIDTH)+ "| ";
        }
        return ret;
    }
    // subtask | currentlyselectedsubtasks rewards
    public String[] subtaskColumns(List<Subtask> stasks, Subtask curSel){
        String[] ret = new String[Constants.BUFFER_HEIGHT];
        int max = 10;
        ret[0] = "";
        for(int i = 0; i < stasks.size(); i++){
            String t = stasks.get(i).getName();
            if(curSel == stasks.get(i)) t = "*"+t+"*";
            ret[0] = ret[0] + normalize(t, Constants.CELL_WIDTH)+ "| ";
        }
        List<Item> rewards = curSel.getRewards();
        for(int i = 1; i< ret.length; i++){
            String r = (rewards.size() <= i-1)?"":rewards.get(i-1).getName();
            ret[i] = normalize(r, Constants.CELL_WIDTH)+ "| ";
        }
        return ret;
    }
    public String[] taskReqColumn(Task curTask){
        String[] ret = new String[Constants.BUFFER_HEIGHT];

        return ret;
    }
    public String[] recipeColumns(HashMap<Item, Integer> inventory){
        String[] ret = new String[Constants.BUFFER_HEIGHT];

        return ret;
    }
    public String[] inventoryColumn(HashMap<Item, Integer> inventory){
        String[] ret = new String[Constants.BUFFER_HEIGHT];

        return ret;
    }



}