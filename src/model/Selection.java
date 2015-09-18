package model;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * a cursor that hops around columns
 * primary control device
 */
public class Selection {
    Object curSel;
    int hsliderIndex, vsliderIndex;
    public Selection(){
        hsliderIndex = 0;
        vsliderIndex = 0;
    }

    public int getHsliderIndex() {
        return hsliderIndex;
    }

    public void setHsliderIndex(int hsliderIndex) {
        this.hsliderIndex = hsliderIndex;
    }

    public int getVsliderIndex() {
        return vsliderIndex;
    }

    public void setVsliderIndex(int vsliderIndex) {
        this.vsliderIndex = vsliderIndex;
    }

    public Object getCurSel() {
        return curSel;
    }

    public void setCurSel(Object curSel) {
        this.curSel = curSel;
    }
}
