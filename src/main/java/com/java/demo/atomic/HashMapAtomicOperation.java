package com.java.demo.atomic;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class HashMapAtomicOperation {

	public static void main(String[] args) throws InterruptedException {

		ConcurrentMap<String, Integer> mapWordCount = new ConcurrentHashMap<>();
		
		List<String> wordsList = getWordsList();
		for (String word : wordsList) {
			/*//Suppose one thread is interrupted after this line and another thread starts execution
	        Integer prevValue = mapWordCount.get(word); 
	        Integer newValue = (prevValue == null ? 1 : prevValue + 1);
	        // Here the value may not be correct after the execution of both threads
	        mapWordCount.put(word, newValue);  */
			Thread t= new Thread(()->mapWordCount.compute(word, (k, v) -> v == null ? 1 : v + 1));
			t.start();
		}
		Thread.sleep(2000);
		System.out.println(mapWordCount);
	}

	private static List<String> getWordsList() {

		List<String> list = new CopyOnWriteArrayList<>();
		list.add("AA");
		list.add("AA");
		list.add("AA");
		list.add("AA");
		list.add("DD");
		list.add("AA");
		list.add("DD");
		list.add("DD");
		list.add("CC");
		list.add("CC");
		list.add("CC");
		list.add("AA");
		list.add("AA");
		list.add("AA");
		list.add("AA");
		list.add("DD");
		list.add("AA");
		list.add("DD");
		list.add("DD");
		list.add("CC");
		list.add("CC");
		list.add("CC");
		list.add("AA");
		list.add("AA");
		list.add("AA");
		list.add("AA");
		list.add("DD");
		list.add("AA");
		list.add("DD");
		list.add("DD");
		list.add("CC");
		list.add("CC");
		list.add("CC");
		return list;
	}

}
