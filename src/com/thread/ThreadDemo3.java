package com.thread;

/**
 * 死锁：多个线程共享资源时需要同步，同步太多有可能造成死锁
 * 
 * 写一个简单的死锁：
 * 	线程1先锁住对象1，然后锁住对象2；线程2先锁住对象2，然后锁住对象1.
 *	 线程1休眠结束后请求锁住对象2，但是对象2被线程2锁住
 * 	线程2休眠结束后请求锁住对象1，但是对象1被线程1锁住
 * 	两个线程相互等待，从而死锁
 * 
 * 如何避免这种死锁：
 * 	1.避免一个线程同时获得多个锁
 * 	2.避免一个线程在锁内占用多个资源，保证一个锁占用一个资源
 * 	3.使用定时锁，使用lock.tryLock代替内置锁
 * 
 * 如何中断进程？
 * 	采用标记的方式中断进程
 * 
 * @author pycrab
 *
 */
public class ThreadDemo3 {

	public static final Object obj1 = new Object();
	public static final Object obj2 = new Object();
	
	//死锁进程
	static class MyThread implements Runnable{
		public boolean flag;
		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		
		@Override
		public void run() {
			while (true) {
				if (isFlag()) {
					synchronized (obj1 ) {
						System.out.println("lock1");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (obj2) {
							System.out.println("wait2");
						}
					}
				}else {
					synchronized (obj2 ) {
						System.out.println("lock2");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (obj1) {
							System.out.println("wait1");
						}
					}
				}
			}
		}
		
	}
	
	//设置中断
	static class myInterrupt implements Runnable{
		public boolean status;
		int i = 0;
		public myInterrupt() {
			status = true;
		}
		@Override
		public void run() {
			while (status) {
				System.out.println("i=" + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(i++ == 20) {
					break;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {

		//中断进程示范
		myInterrupt tr3 = new myInterrupt();
		Thread t3 = new Thread(tr3);
		t3.start();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(500);
		}
		tr3.status = false;
		
		System.out.println("------------------------");
		
		//死锁示范
		MyThread tr1 = new MyThread();
		MyThread tr2 = new MyThread();
		tr1.setFlag(true);
		tr2.setFlag(false);
		Thread t1 = new Thread(tr1);
		Thread t2 = new Thread(tr2);
		t1.start();
		t2.start();
	}

}
