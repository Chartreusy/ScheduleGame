package model;

import global.Constants;

/**
 * Created by Chartreuse on 18/09/15.
 *
 * a cursor that hops around columns
 * primary control device
 */
public class Selection {
    // should theoretically cascade infinitely
    // but i think i only need 2.
    int hsliderIndex, vsliderIndex, curIndex;
    int scrollsize;
    public Selection(){
        curIndex = 0;
        hsliderIndex = 0;
        vsliderIndex = 0;
        scrollsize = 0;
    }

    public void moveCursor(int direction){

        curIndex += direction;
        // curIndex and vsliderIndex can never be negative.
        if(curIndex <0){
            curIndex = scrollsize -1;
            vsliderIndex = (curIndex - Constants.BUFFER_HEIGHT);

        } else if(curIndex >= scrollsize){
            curIndex = 0;
            setVsliderIndex(0);
        }
        if(vsliderIndex <0) vsliderIndex = 0;

        // if we go beyond the sliding window, slide down
        if(curIndex - vsliderIndex >= Constants.BUFFER_HEIGHT){
            vsliderIndex = curIndex - Constants.BUFFER_HEIGHT+1;
        // slide up
        } else if (curIndex < vsliderIndex){
            vsliderIndex = curIndex;
        }
    }


    public int getScrollsize() {
        return scrollsize;
    }

    public void setScrollsize(int scrollsize) {
        this.scrollsize = scrollsize;
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
