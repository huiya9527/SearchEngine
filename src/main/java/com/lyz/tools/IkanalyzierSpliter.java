package com.lyz.tools;

import com.lyz.dao.Item;
import com.lyz.dao.SplitWord;
import com.lyz.mapper.NewsMapper;
import com.lyz.mapper.SplitNewsMapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

@Service
public class IkanalyzierSpliter {

    public void splitWord(NewsMapper newsMapper, SplitNewsMapper splitNewsMapper){
        int maxId = newsMapper.findMaxId();
        Analyzer analyzer = new IKAnalyzer(true);
        for (int id = 1; id <= maxId; id++) {
            splitOneItemById(id, analyzer, newsMapper, splitNewsMapper);
        }
    }

    private void splitOneItemById(int id, Analyzer analyzer,NewsMapper newsMapper, SplitNewsMapper splitNewsMapper){
        Item item = newsMapper.findById(id);
        if(item == null){
            System.out.println("item is null");
            return;
        }
        List<SplitWord> titleList = getResultList(analyzer,item.getTitle(), 1);
        List<SplitWord> contentList = getResultList(analyzer,item.getContent(),0);
        titleList.addAll(contentList);
        Map<String, SplitWord> map = new HashMap<>();
        for(SplitWord sw: titleList){
            if(map.containsKey(sw.getWord())){
                SplitWord s = map.get(sw.getWord());
                s.setAppearNum(s.getAppearNum()+1);
            } else {
                map.put(sw.getWord(),sw);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String word: map.keySet()) {
            SplitWord sw = map.get(word);
            sb.append(sw.toString()+" ");
        }
        splitNewsMapper.insert(id, sb.toString());
    }

    private List<SplitWord> getResultList(Analyzer analyzer, String s, int isTitle) {
        List<SplitWord> resultList = new LinkedList<>();
        TokenStream ts = null;
        try {
            ts = analyzer.tokenStream("", new StringReader(s));
            // 获取词元位置属性
            CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
            ts.reset();
            while (ts.incrementToken()) {
                SplitWord sw = new SplitWord();
                // 获取词元文本属性
                sw.setWord(term.toString());
                sw.setIsAppearOnTitle(isTitle);
                resultList.add(sw);
            }
            // 重置TokenStream（重置StringReader）
            ts.reset();
            // 关闭TokenStream（关闭StringReader）
            ts.end(); // Perform end-of-stream operations, e.g. set the final offset.
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (ts != null) {
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultList;
    }
}
