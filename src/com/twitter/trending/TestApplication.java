package com.twitter.trending;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @description A class to test our main application
 * @author varunbatra
 *
 */
class TestApplication {

	/**
	 * @description Method to test the application
	 */
	@Test
	public void testMain() {
		MainApplication app = new MainApplication();
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		countMap = app.getHashTagsInMap("Worlds best cricketer is #sachin ### #sachin #sachin #sachin #sachin",
				countMap);
		countMap = app.getHashTagsInMap("#dhOni ###hArdik # #   ##  #   ", countMap);
		countMap = app.getHashTagsInMap("# love###dhoni  #hardik #Yuvi", countMap);
		countMap = app.getHashTagsInMap(" I love #dhoni #sAchin", countMap);
		countMap = app.getHashTagsInMap("Love #workout #motivation", countMap);
		countMap = app.getHashTagsInMap(" I love #photography", countMap);
		countMap = app.getHashTagsInMap(" I love #dhoni #happybirthday", countMap);
		countMap = app.getHashTagsInMap(" I love #pets #petlover", countMap);
		ArrayList<String> list = app.getTrendingHashTags(countMap);
		Assert.assertArrayEquals(new Object[] { "dhoni", "hardik", "sachin", "pets", "petlover",
				"happybirthday", "workout", "yuvi", "motivation", "photography" }, list.toArray());
	}

}
