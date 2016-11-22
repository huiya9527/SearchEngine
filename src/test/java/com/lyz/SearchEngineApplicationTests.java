package com.lyz;

import com.lyz.dao.Item;
import com.lyz.mapper.SportsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchEngineApplicationTests {

	@Autowired
	private SportsMapper sportsMapper;

	@Test
	public void contextLoads() {
		Item item = sportsMapper.findById(535);
		System.out.println(item.getTitle());
	}

}
