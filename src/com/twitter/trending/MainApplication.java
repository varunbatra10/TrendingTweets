package com.twitter.trending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @description This application is to get top trending HashTags from inputs
 *              taken from the user
 * @author varunbatra
 *
 */
public class MainApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MainApplication app = new MainApplication();
		System.out.print("Enter Tweet or Press N/n to terminate:");
		String input = sc.nextLine().trim();
		HashMap<String, Integer> countMap = new HashMap<>();
		while (!(input.equals("N") || input.equals("n"))) {
			if (input.contains("#")) {
				countMap = app.getHashTagsInMap(input, countMap);
			}
			System.out.print("Enter another Tweet or Press N/n to terminate:");
			input = sc.nextLine().trim();
		}
		sc.close();

		// Display Trending Hashtags
		if (!countMap.isEmpty()) {
			System.out.println("Trending Hashtags on Twitter:");
			ArrayList<String> trend = app.getTrendingHashTags(countMap);
			for (int i = 0; i < trend.size(); i++) {
				System.out.println((i+1) + ". #" + trend.get(i));
			}
		}
	}

	/**
	 * @description Method to extract HashTags from string and maintain count
	 * @param input string
	 * @return HashMap with count of HashTags
	 */
	HashMap<String, Integer> getHashTagsInMap(String input, HashMap<String, Integer> countMap) {
		String hash = "#";
		HashSet<String> unique = new HashSet<>();
		String splitArray[] = input.split(hash);
		for (int i = 0; i < splitArray.length; i++) {
			String str = splitArray[i].trim();
			if (!str.isEmpty() && input.contains(hash + str)) {
				String a = str.split(" ")[0];
				unique.add(a.trim());
			}
		}
		Iterator<String> itr = unique.iterator();
		while (itr.hasNext()) {
			String a = itr.next().toLowerCase();
			countMap.put(a, countMap.get(a) != null ? countMap.get(a) + 1 : 1);
		}
		return countMap;
	}

	/**
	 * @description Method to get trending HashTags by count in map --I didn't
	 *              mention 10 as the inputs could contain less than 10 HashTags
	 * @param countMap
	 * @return type is list which has 10 or less than 10 trending HashTags based on user input
	 */
	ArrayList<String> getTrendingHashTags(HashMap<String, Integer> countMap) {
		ArrayList<String> trend = new ArrayList<String>();
		if (!countMap.isEmpty()) {
			// Sort Map by values
			LinkedHashMap<String, Integer> sortedByCount = countMap.entrySet().stream()
					.sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).collect(Collectors
							.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			if (!sortedByCount.isEmpty()) {
				int count = 1;
				for (Map.Entry<String, Integer> entryMap : sortedByCount.entrySet()) {
					if (count <= 10) {
						trend.add(entryMap.getKey());
						count++;
					}
				}
			}
		}
		return trend;
	}
}
