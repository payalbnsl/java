package com.java.demo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	/*
	 * The interface ReadWriteLock specifies another type of lock maintaining a pair
	 * of locks for read and write access. The idea behind read-write locks is that
	 * it's usually safe to read mutable variables concurrently as long as nobody is
	 * writing to this variable. So the read-lock can be held simultaneously by
	 * multiple threads as long as no threads hold the write-lock. This can improve
	 * performance and throughput in case that reads are more frequent than writes
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		ReadWriteLock lock = new ReentrantReadWriteLock();

		executor.submit(() -> {
		    lock.writeLock().lock();
		    try {
		        map.put("foo", "bar");
		    }
		  finally {
		        lock.writeLock().unlock();
		    }
		});
		
		Runnable readTask = () -> {
		    lock.readLock().lock();
		    try {
		        System.out.println(map.get("foo"));
		    } finally {
		        lock.readLock().unlock();
		    }
		};

		executor.submit(readTask);
		executor.submit(readTask);

		executor.awaitTermination(2000, TimeUnit.SECONDS);
		executor.shutdown();
	}

}
