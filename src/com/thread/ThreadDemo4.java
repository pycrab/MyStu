package com.thread;

/**
 * 生产者-消费者问题
 * 
 * wait方法和sleep方法的区别：前者释放当前锁，后者不释放当前锁
 * @author pycrab
 *
 */
public class ThreadDemo4 {

	public static void main(String[] args) {

		Product pro = new Product();
		Producer p = new Producer(pro);
		Consumer c = new Consumer(pro);
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();
		
	}

}

class Producer implements Runnable{
	private Product pro;

	public Producer(Product pro) {
		this.pro = pro;
	}
	@Override
	public void run() {
		while(true) {
			try {
				pro.set();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class Consumer implements Runnable{
	private Product pro;

	public Consumer(Product pro) {
		this.pro = pro;
	}
	@Override
	public void run() {
		while(true) {
			try {
				pro.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class Product{
	//定义缓冲区
	private static Integer size = 0;
	public int getSize() {
		return size;
	}
	
	public synchronized void set() throws InterruptedException {
		if(size >= 10) {
			//当前线程进入等待状态，释放CPU，释放该监视器上的锁
			this.wait();
		}
		this.notifyAll();

		size++;
		System.out.println("producer-" + Thread.currentThread().getName() + "-size=" + size);
		Thread.sleep(500);

		//唤醒该监视器上的一个线程
	}

	public synchronized void get() throws InterruptedException {
		if(size <= 0) {
			this.wait();
		}
		this.notifyAll();

		size--;
		System.out.println("consumer-" + Thread.currentThread().getName() + "-size=" + size);
		Thread.sleep(500);

	}
}
