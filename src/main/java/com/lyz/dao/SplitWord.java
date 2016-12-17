package com.lyz.dao;

public class SplitWord{
    private String word;
    private int isAppearOnTitle = 0;
    private int appearNum = 1;

    @Override
    public int hashCode(){
        return word.hashCode();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
        return word + ":" + appearNum + ":" + isAppearOnTitle;
    }
}
