package com.lyz.tools;

import com.lyz.dao.IndexContentItem;
import com.lyz.mapper.NewsIndexMapper;
import com.lyz.mapper.SplitNewsMapper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CreateIndex {
    public void creatIndex(SplitNewsMapper splitNewsMapper, NewsIndexMapper newsIndexMapper){
        Map<String, List<IndexContentItem>> indexMap = new HashMap<>();
        int maxId = splitNewsMapper.findMaxId();
        for(int id = 1; id <= maxId; id++){
            String splitWordList = splitNewsMapper.selectById(id);
            splitWordList = splitWordList.trim();
            String[] splitWords = splitWordList.split(" ");
            for(String s: splitWords){
                String[] ss = s.split(":");
                String word = ss[0];
                int appearNum = Integer.parseInt(ss[1]);
                int isAppearOnTitle = Integer.parseInt(ss[2]);
                IndexContentItem item = new IndexContentItem();
                item.setId(id);
                item.setAppearNum(appearNum);
                item.setIsAppearOnTitle(isAppearOnTitle);
                List<IndexContentItem> itemList;
                if(indexMap.containsKey(word)) {
                    itemList = indexMap.get(word);
                } else{
                    itemList = new LinkedList<>();
                    indexMap.put(word, itemList);
                }
                itemList.add(item);
            }
        }
        for(String word: indexMap.keySet()){
            List<IndexContentItem> list = indexMap.get(word);
            int appearNum = 0;
            StringBuilder content = new StringBuilder();
            for(IndexContentItem indexContentItem: list) {
                content.append(indexContentItem.toString()+" ");
                appearNum += indexContentItem.getAppearNum();
            }
            //System.out.println(word + " " + appearNum);
            newsIndexMapper.insert(word,appearNum,content.toString());
        }

    }

}
