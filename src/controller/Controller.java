package controller;

import model.Model;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Chartreuse on 18/09/15.
 */
public class Controller {
    Model model;
    ConsoleView view;
    public Controller(){}
    public void addModel(Model model){
        this.model = model;
    }
    public void addView(ConsoleView view){
        this.view = view;
    }
    public void initModel(){
        model.initModel();
    }
    public void consoleInput() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while(!line.equalsIgnoreCase("q")){
            parseInput(line);
            line = br.readLine();
        }
    }
    public void parseInput(String line){
        char in = line.charAt(0);
        switch(in){
            case 'w':
                if(model.curScreen == Model.screen.VENTURE){
                    model.moveCursor(-1, model.getEmps().size());
                }else if(model.curScreen == Model.screen.SUMMARY){
                    model.moveCursor(-1, model.getRecentlyGathered().size());
                }else if(model.curScreen == Model.screen.INVENTORY){
                    model.moveCursor(-1, model.getInventory().size());
                }
                break;
            case 's':
                if(model.curScreen == Model.screen.VENTURE){
                    model.moveCursor(1, model.getEmps().size());
                }else if(model.curScreen == Model.screen.SUMMARY){
                    model.moveCursor(1, model.getRecentlyGathered().size());
                }else if(model.curScreen == Model.screen.SUMMARY){
                    model.moveCursor(-1, model.getInventory().size());
                }
                break;
            case 'a':
                if(model.curScreen == Model.screen.VENTURE){
                    model.moveAssignment(-1);
                }
                break;
            case 'd':
                if(model.curScreen == Model.screen.VENTURE){
                    model.moveAssignment(1);
                }
                break;
            case 'p':
                if(model.curScreen == Model.screen.VENTURE){
                    model.executeOrders();
                    model.setCurScreen(Model.screen.SUMMARY);
                }
                break;
            case 'v': // venture
                if(model.curScreen != Model.screen.VENTURE){
                    model.setCurScreen(Model.screen.VENTURE);
                }
                break;
            case 'i': // inventory
                if(model.curScreen != Model.screen.INVENTORY){
                    model.setCurScreen(Model.screen.INVENTORY);
                }
                break;
            case 'c': // craft
                if(model.curScreen != Model.screen.CRAFT){
                    model.setCurScreen(Model.screen.CRAFT);
                }
                break;
        }

    }
}
