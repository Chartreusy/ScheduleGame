package controller;

import model.Model;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static model.Model.screen.*;

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
        if(line == null) line = "";
        while(!line.equalsIgnoreCase("q")){
            parseInput(line);
            line = br.readLine();
        }
    }
    public void parseInput(String line){
        if(line.equalsIgnoreCase("")) return;
        char in = line.charAt(0);
        switch(in){
            case 'w':
                switch(model.curScreen){
                    case VENTURE:
                        model.moveCursor(-1);
                        break;
                    case SUMMARY:
                        model.moveCursor(-1);
                        break;
                    case INVENTORY:
                        model.moveCursor(-1);
                        break;
                    case CRAFT:
                        model.moveCursor(-1);
                        break;
                }
                break;
            case 's':
                switch(model.curScreen){
                    case VENTURE:
                        model.moveCursor(1);
                        break;
                    case SUMMARY:
                        model.moveCursor(1);
                        break;
                    case INVENTORY:
                        model.moveCursor(1);
                        break;
                    case CRAFT:
                        model.moveCursor(1);
                        break;
                }
                break;
            case 'a':
                switch(model.curScreen){
                    case VENTURE:
                        model.moveAssignment(-1);
                        break;
                    case CRAFT:
                        model.swapControl();
                        break;
                }
                break;
            case 'd':
                switch(model.curScreen){
                    case VENTURE:
                        model.moveAssignment(1);
                        break;
                    case CRAFT:
                        model.swapControl();
                        break;
                }
                break;
            case 'p':
                switch(model.curScreen){
                    case VENTURE:
                        model.executeOrders();
                        model.resetSelection(model.getRecentlyGathered().size());
                        model.setCurScreen(SUMMARY);
                        break;
                    case CRAFT:
                        model.executeRecipe();
                        break;
                }
                break;
            case 'v': // venture
                if(model.curScreen != VENTURE){
                    model.resetSelection(model.getEmps().size());
                    model.setCurScreen(VENTURE);
                }
                break;
            case 'i': // inventory
                if(model.curScreen != INVENTORY){
                    model.resetSelection(model.getInventory().size());
                    model.setCurScreen(INVENTORY);
                }
                break;
            case 'c': // craft
                if(model.curScreen != CRAFT){
                    model.resetSelection(model.getRecipes().size());
                    model.resetSecondarySel();
                    model.setCurScreen(CRAFT);
                }
                break;
        }
        model.stateChange();
    }
}
