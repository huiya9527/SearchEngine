package com.lyz.dao;

/**
 * Created by lyz on 2016/11/26.
 */
public class IndexContentItem {
    private int id;
    private int isAppearOnTitle = 0;
    private int appearNum = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAppearOnTitle() {
        return isAppearOnTitle;
    }

    public void setIsAppearOnTitle(int isAppearOnTitle) {
        this.isAppearOnTitle = isAppearOnTitle;
    }

    public int getAppearNum() {
        return appearNum;
    }

    public void setAppearNum(int appearNum) {
        this.appearNum = appearNum;
    }

    @Override
    public String toString(){
        return id + ":" + appearNum + ":" +  isAppearOnTitle;
    }
}
