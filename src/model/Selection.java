package model;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * a cursor that hops around columns
 * primary control device
 */
public class Selection {
    int hsliderIndex, vsliderIndex, curIndex;
    public Selection(){
        curIndex = 0;
        hsliderIndex = 0;
        vsliderIndex = 0;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
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
}
