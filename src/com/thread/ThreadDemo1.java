package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author pycrab
 *
 */
public class ThreadDemo1 {

	/**
	 * 方式一
	 */
	static class MyThread extends Thread{
		//需要重写run方法
		@Override
		public void run() {
			//获取当前线程对象的名字和系统时间
			for(int i = 0;i < 10; ++i) {
				try {
					//线程休眠半秒后继续执行
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-" + i + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority() + "-" + System.currentTimeMillis());
			}
		}
	}
	
	/**
	 * 方式二
	 */
	static class MyRunnable implements Runnable{
		//需要重写run方法
		@Override
		public void run() {
			//获取当前线程对象的名字和系统时间
			for(int i = 0;i < 10; ++i) {
				try {
					//线程休眠半秒后继续执行
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-" + i + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority() + "-" + System.currentTimeMillis());
			}		
		}
	}
	
	/**
	 * 方式三
	 */
	static class MyCallable implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			System.out.println("Implement Callable");
			return 1;
		}
		
	}
	
	public static void main(String[] args) {
		
		//报错No enclosing instance of type ThreadDemo is accessible. Must qualify the allocation with an enclosing instance of type ThreadDemo (e.g. x.new A() where x is an instance of ThreadDemo).
		//需要使用静态内部类
		
		//1.创建线程对象
		MyThread tr1 = new MyThread();
		//2.启动线程，进入就绪队列
		tr1.start();
		//3.当前线程是否处于活动状态
		System.out.println("isAlive=" + tr1.isAlive());
		//4.设置线程优先级，只是改变几率
//		tr1.setPriority(Thread.MAX_PRIORITY);
		//5.设置线程为守护线程
		//（Java线程分为用户线程和守护线程，一个前台一个后台）
		//当只有一个守护线程，用户线程都已退出运行时，JVM就会结束
		//设置守护线程必须在启动之前，守护线程中产生的新线程也是守护线程，不是所有的应用都可以分配守护线程为之服务，如读写操作或计算逻辑
//		tr1.setDaemon(true);
		
		MyRunnable tr = new MyRunnable();
		Thread tr2 = new Thread(tr);
		tr2.start();

		MyCallable mc = new MyCallable();
		FutureTask<Integer> fTask = new FutureTask<>(mc);
		new Thread(fTask).start();

		for(int i = 0;i < 10; ++i) {
			System.out.println(i + "-" + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority());
			try {
				//6.线程休眠半秒
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(i == 6) {
				
				//7.暂停当前执行的线程对像，让出当次CPU的执行时间给同优先级的线程
//				Thread.yield();
				
				try {
					//8.等待该线程执行完毕
					tr1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
