package com.thread;

/**
 *实现多线程的两种方式：
 *1.继承Thread类
 *2.实现Runnable接口（推荐）
 *
 *线程睡眠sleep方法和线程让步yeild方法
 *	两者相似
 *	sleep方法可以设置线程暂停时间，同时让出CPU，进入不可运行状态，
 *		让其他线程有机会执行，但是不释放锁，即其他线程不能访问共享数据
 *	yeild方法也是使线程暂停，同时让出CPU，但是它仍处于可运行状态，
 *		它是让相同优先级的线程有机会执行，如果检测没有同优先级的线程，它会继续执行原来的线程，它也不释放锁
 *
 *线程同步join方法
 *	join方法是暂停当前线程的执行，而等待指定线程执行完毕或者执行一定时间
 *
 *	参数可选填时间，表示指定线程执行时间
 *	必须在start之后使用才有意义
 *	join使线程的并发执行变成串行执行
 *	join使用了线程的wait方法来实现同步，如果A线程内调用B线程的join，就是A当线程调用了B线程的wait方法，A线程进入阻塞状态，直到B线程执行完
 *
 * @author pycrab
 *
 */
public class ThreadDemo1 {

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
	
	public static void main(String[] args) {
		
		//报错No enclosing instance of type ThreadDemo is accessible. Must qualify the allocation with an enclosing instance of type ThreadDemo (e.g. x.new A() where x is an instance of ThreadDemo).
		//需要使用静态内部类
		//1.创建线程对象
		MyThread tr1 = new MyThread();
		
		//2.当前线程是否处于活动状态
		System.out.println("isAlive=" + tr1.isAlive());
		
		//3.设置线程优先级，只是改变几率
//		tr1.setPriority(Thread.MAX_PRIORITY);
		
		//4.设置线程为守护线程
		//（Java线程分为用户线程和守护线程，一个前台一个后台）
		//当只有一个守护线程，用户线程都已退出运行时，JVM就会结束
		//设置守护线程必须在启动之前，守护线程中产生的新线程也是守护线程，不是所有的应用都可以分配守护线程为之服务，如读写操作或计算逻辑
//		tr1.setDaemon(true);
		
		//5.启动线程，进入就绪队列
		tr1.start();
		System.out.println("isAlive=" + tr1.isAlive());

		
		MyRunnable tr = new MyRunnable();

		Thread tr2 = new Thread(tr);
		tr2.start();


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
