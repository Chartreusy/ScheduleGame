import controller.Controller;
import model.Model;
import view.ConsoleView;

import java.io.Console;

/**
 * Created by Chartreuse on 18/09/15.
 *

 */
public class MainClass {
    public static void main(String[] args) throws Exception{
        Model m = new Model();
        ConsoleView v = new ConsoleView();
        m.addObserver(v);
        Controller c = new Controller();
        c.addModel(m);
        c.addView(v);
        v.addController(c);
        c.initModel();
        c.consoleInput();

    }
}
