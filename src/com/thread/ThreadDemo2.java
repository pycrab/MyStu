package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步的三种方式：
 * 	1.同步代码块
 * 	2.同步方法
 * 	3.重入锁
 * 
 * 设置条件对象
 * 	1.前两种使用synchronized关键字，创建隐式内部锁，可以也只能调用一次Object提供的条件对象，即wait()方法和notifyAll()方法
 * 	2.第三种显式锁，可以声明多个条件对象，使用条件的await()方法和signalAll()方法
 * 
 * 同步会牺牲性能取得安全
 * 
 * 同步准则：
 * 	1.同步的代码块尽量简短，不变的移到外面
 * 	2.同步的行为占用时间不要太长，避免阻塞
 * 	3.持有锁时不要调用其它对象的同步方法
 * 
 * 监视器monitor：
 * 	锁和条件并不是面向对象的，解决方案是内置于每一个Object变量中的monitor监视器对象，
 * 	获得监视器就会获得锁，线程才能执行，没有监视器不能执行
 * 	同步代码块其实就是使用了参数的监视器锁
 * 
 * @author pycrab
 *
 */
public class ThreadDemo2 {

	static class MyThread implements Runnable{

		private Object obj = new Object();
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			
			//同步代码块（同步指定对象，this表示当前对象，当方法体过于庞大时用于同步方法内的代码块）
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
		
		//同步代码(同步当前对象，隐式锁)
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
		
		//重入锁（显式锁）
		//锁是可重入的，使用一个数量计数标记来跟踪
		private final ReentrantLock lock = new ReentrantLock();
		public void fun() throws InterruptedException {
			
			//创建条件对象
			Condition condition = lock.newCondition();
			
			//加锁
			lock.lock();
			
			try {
				boolean something = true;
				
				//设置条件
				while (something ) {
					//线程进入挂起状态，本次调用被锁定，释放监视器上的锁
					condition.await();
				}
				
				//do-
				
				//通知所有线程继续运行
				condition.signalAll();
				
			} finally {
				//解锁,放在finally块中，防止异常导致线程一直不被释放，则其他线程一直被阻塞
				lock.unlock();
			}
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
