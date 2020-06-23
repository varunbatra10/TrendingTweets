package com.twitter.trending;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @description This application is to get top trending hashtags from inputs taken from the user
 * @author varunbatra
 *
 */
public class MainApplication {
 static HashMap<String, Integer> countMap;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MainApplication app = new MainApplication();
		System.out.print("Enter Tweet or Press N/n to terminate:");
		String input = sc.nextLine().trim();
		countMap = new HashMap<>();
		while (!(input.equals("N") || input.equals("n"))) {
			if (input.contains("#")) {
				countMap = app.getHashTagsInMap(input);
			}
			System.out.print("Enter another Tweet or Press N to terminate:");
			input = sc.nextLine().trim();
		}
		sc.close();
		app.showTrendingHashTags();
	}

	/**
	 * @description Method to extract hashtags from string and maintain count
	 * @param input string
	 * @return
	 */
	HashMap<String, Integer> getHashTagsInMap(String input) {
		String splitArray[] = input.split("#");
		for (int i = 0; i < splitArray.length; i++) {
			String str = splitArray[i].trim();
			if (!str.isEmpty() && (i > 0 || (input.startsWith("#") && i == 0))) {
				String a = str.split(" ")[0];
				countMap.put(a.trim(), countMap.get(a) != null ? countMap.get(a) + 1 : 1);
			}
		}
		return countMap;
	}

	/**
	 * @description Method to get trending hashtags by count in map --I didn't
	 *              mention 10 as the inputs could contain less than 10 hashtags
	 * @param countMap
	 * @return type is null as we need to only show trending hashtags
	 */
	void showTrendingHashTags() {
		if (!countMap.isEmpty()) {
			// Sort Map by values
			Map<String, Integer> sortedByCount = countMap.entrySet().stream()
					.sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).collect(Collectors
							.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

			int count = 1;
			// Display Trending Hashtags
			System.out.println("Trending Hashtags on Twitter:");
			for (Map.Entry<String, Integer> entryMap : sortedByCount.entrySet()) {
				if (count <= 10) {
					System.out.println(count + ". " + entryMap.getKey());
					count++;
				}
			}
		}
	}

}
