package com.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步的三种方式：
 * 1.同步代码块
 * 2.同步方法
 * 3.锁
 * 同步会牺牲性能取得安全
 * 
 * 同步准则：
 * 1.同步的代码块尽量简短，不变的移到外面
 * 2.同步的行为占用时间不要太长，避免阻塞
 * 3.持有锁时不要调用其它对象的同步方法
 * @author pycrab
 *
 */
public class ThreadDemo2 {

	static class MyThread implements Runnable{

		private Object obj = new Object();
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			
			//同步代码块（同步指定对象，一般用this表示当前对象）
			synchronized (obj) {
				for (int i = 0; i < 5; ++i) {
					System.out.println("开始-" + "0");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("结束-" + "1");
				}
			}
		}
		
		//同步代码(同步当前对象)
		public synchronized void go() {
			for (int i = 0; i < 5; ++i) {
				System.out.println("开始-" + "0");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("结束-" + "1");
			}
		}
		
		//互斥锁
		private final ReentrantLock lock = new ReentrantLock();
		public void fun() {
			//加锁
			lock.lock();
			
			//行为
			System.out.println();
			
			//解锁
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		
		MyThread tr = new MyThread();
		Thread t1 = new Thread(tr);
		Thread t2 = new Thread(tr);
		Thread t3 = new Thread(tr);

		t1.start();
		t2.start();
		t3.start();
	}
}
