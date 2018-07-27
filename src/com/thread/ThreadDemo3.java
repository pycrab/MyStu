package com.thread;

/**
 * ����������̹߳�����Դʱ��Ҫͬ����ͬ��̫���п����������
 * 
 * ���ñ�ǵķ�ʽ�����жϽ���
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
