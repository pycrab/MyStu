package com.thread;

/**
 * 死锁：多个线程共享资源时需要同步，同步太多有可能造成死锁
 * 
 * 采用标记的方式进行中断进程
 * 
 * @author pycrab
 *
 */
public class ThreadDemo3 {

	static class MyThread implements Runnable{
		public boolean flag;
		public MyThread() {
			flag = true;
		}
		
		@Override
		public void run() {
			int i = 0;
			while (flag) {
				System.out.println("i=" + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(++i == 20) {
					break;
				}
			}
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		MyThread tr = new MyThread();
		Thread thread = new Thread(tr);
		thread.start();
		
		for(int i = 0;i < 10; ++i) {
			thread.sleep(500);
		}
		tr.flag = false;
	}

}
