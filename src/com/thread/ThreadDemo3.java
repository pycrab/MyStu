package com.thread;

/**
 * ����������̹߳�����Դʱ��Ҫͬ����ͬ��̫���п����������
 * 
 * дһ���򵥵�������
 * 	�߳�1����ס����1��Ȼ����ס����2���߳�2����ס����2��Ȼ����ס����1.
 *	 �߳�1���߽�����������ס����2�����Ƕ���2���߳�2��ס
 * 	�߳�2���߽�����������ס����1�����Ƕ���1���߳�1��ס
 * 	�����߳��໥�ȴ����Ӷ�����
 * 
 * ��α�������������
 * 	1.����һ���߳�ͬʱ��ö����
 * 	2.����һ���߳�������ռ�ö����Դ����֤һ����ռ��һ����Դ
 * 	3.ʹ�ö�ʱ����ʹ��lock.tryLock����������
 * 
 * ����жϽ��̣�
 * 	���ñ�ǵķ�ʽ�жϽ���
 * 
 * @author pycrab
 *
 */
public class ThreadDemo3 {

	public static final Object obj1 = new Object();
	public static final Object obj2 = new Object();
	
	//��������
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
	
	//�����ж�
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

		//�жϽ���ʾ��
		myInterrupt tr3 = new myInterrupt();
		Thread t3 = new Thread(tr3);
		t3.start();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(500);
		}
		tr3.status = false;
		
		System.out.println("------------------------");
		
		//����ʾ��
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
