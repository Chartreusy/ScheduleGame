package view;

import controller.Controller;
import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static model.Model.screen.*;

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
    public static final int BUFFER_HEIGHT = 5;

    public ConsoleView(){}

    public void addController(Controller c){

    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("View      : Observable is " + o.getClass() + ", object passed is " + ((arg == null) ? arg : arg.getClass()));
        Model m = (Model)o;
        // everything is in here i guess
        // we're going to generate each column as separately
        // then stick them all together at the end.
        // buffer is 80 by BUFFER_HEIGHT
        String[] out = new String[BUFFER_HEIGHT];
        switch(m.curScreen){
            case VENTURE:
                out = employeeColumn(m.getEmps(), (Employee) m.getSelection().getCurSel());
                appendStrarr(out, subtaskColumns(m.getAvailableSubtasks(), ((Employee)m.getSelection().getCurSel()).getAssigned() ));
                break;
            case CRAFT:
                break;
        }
        for(int i = 0; i<out.length; i++){
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
    // employee   |  assignedsubtask
    public String[] employeeColumn(List<Employee> emps, Employee curSel){
        String[] ret = new String[BUFFER_HEIGHT];
        int max = 10;
        ret[0] = "          | "; // 10 char
        for(int i = 1; i < ret.length; i++){
            String emp = (emps.size() <= i-1)?"":emps.get(i-1).getName();
            if(!emp.equals("") && curSel == emps.get(i-1)) emp = "*"+emp+"*";
            ret[i] = normalize(emp, max)+ "| ";
        }
        return ret;
    }
    // subtask | currentlyselectedsubtasks rewards
    public String[] subtaskColumns(List<Subtask> stasks, Subtask curSel){
        String[] ret = new String[BUFFER_HEIGHT];
        int max = 10;
        ret[0] = "";
        for(int i = 0; i < stasks.size(); i++){
            String t = stasks.get(i).getName();
            if(curSel == stasks.get(i)) t = "*"+t+"*";
            ret[0] = ret[0] + normalize(t, max)+ "| ";
        }
        List<Item> rewards = curSel.getRewards();
        for(int i = 1; i< ret.length; i++){
            String r = (rewards.size() <= i-1)?"":rewards.get(i-1).getName();
            ret[i] = normalize(r, max)+ "| ";
        }
        return ret;
    }
    public String[] taskReqColumn(Task curTask){
        String[] ret = new String[BUFFER_HEIGHT];

        return ret;
    }
    public String[] recipeColumns(HashMap<Item, Integer> inventory){
        String[] ret = new String[BUFFER_HEIGHT];

        return ret;
    }
    public String[] inventoryColumn(HashMap<Item, Integer> inventory){
        String[] ret = new String[BUFFER_HEIGHT];

        return ret;
    }



}