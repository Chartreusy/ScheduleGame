package model;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * a cursor that hops around columns
 * primary control device
 */
public class Selection {
    Object curSel;
    public Selection(){}

    public Object getCurSel() {
        return curSel;
    }

    public void setCurSel(Object curSel) {
        this.curSel = curSel;
    }
}
