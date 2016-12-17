package com.lyz;

import com.lyz.mapper.NewsIndexMapper;
import com.lyz.mapper.NewsMapper;
import com.lyz.mapper.SplitNewsMapper;
import com.lyz.tools.CreateIndex;
import com.lyz.tools.IkanalyzierSpliter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PreWork {

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    SplitNewsMapper splitNewsMapper;

    @Autowired
    NewsIndexMapper newsIndexMapper;

    /***
     * 对原始新闻进行分词预处理
     */
    @Test
    public void contextLoads() throws Exception {
        //分词预处理
        IkanalyzierSpliter ikanalyzierSpliter = new IkanalyzierSpliter();
        ikanalyzierSpliter.splitWord(newsMapper, splitNewsMapper);
    }

    @Test
    public void createIndex(){
        //建立倒排索引
        CreateIndex createIndex = new CreateIndex();
        createIndex.creatIndex(splitNewsMapper, newsIndexMapper);
    }
}
