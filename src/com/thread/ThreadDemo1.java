package com.thread;

/**
 *ʵ�ֶ��̵߳����ַ�ʽ��
 *1.�̳�Thread��
 *2.ʵ��Runnable�ӿڣ��Ƽ���
 *
 *�߳�˯��sleep�������߳��ò�yeild����
 *	��������
 *	sleep�������������߳���ͣʱ�䣬ͬʱ�ó�CPU�����벻������״̬��
 *		�������߳��л���ִ�У����ǲ��ͷ������������̲߳��ܷ��ʹ�������
 *	yeild����Ҳ��ʹ�߳���ͣ��ͬʱ�ó�CPU���������Դ��ڿ�����״̬��
 *		��������ͬ���ȼ����߳��л���ִ�У�������û��ͬ���ȼ����̣߳��������ִ��ԭ�����̣߳���Ҳ���ͷ���
 *
 *�߳�ͬ��join����
 *	join��������ͣ��ǰ�̵߳�ִ�У����ȴ�ָ���߳�ִ����ϻ���ִ��һ��ʱ��
 *
 *	������ѡ��ʱ�䣬��ʾָ���߳�ִ��ʱ��
 *	������start֮��ʹ�ò�������
 *	joinʹ�̵߳Ĳ���ִ�б�ɴ���ִ��
 *	joinʹ�����̵߳�wait������ʵ��ͬ�������A�߳��ڵ���B�̵߳�join������A���̵߳�����B�̵߳�wait������A�߳̽�������״̬��ֱ��B�߳�ִ����
 *
 * @author pycrab
 *
 */
public class ThreadDemo1 {

	static class MyThread extends Thread{
		//��Ҫ��дrun����
		@Override
		public void run() {
			//��ȡ��ǰ�̶߳�������ֺ�ϵͳʱ��
			for(int i = 0;i < 10; ++i) {
				try {
					//�߳����߰�������ִ��
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-" + i + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority() + "-" + System.currentTimeMillis());
			}
		}
	}
	static class MyRunnable implements Runnable{
		//��Ҫ��дrun����
		@Override
		public void run() {
			//��ȡ��ǰ�̶߳�������ֺ�ϵͳʱ��
			for(int i = 0;i < 10; ++i) {
				try {
					//�߳����߰�������ִ��
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-" + i + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority() + "-" + System.currentTimeMillis());
			}		
		}
	}
	
	public static void main(String[] args) {
		
		//����No enclosing instance of type ThreadDemo is accessible. Must qualify the allocation with an enclosing instance of type ThreadDemo (e.g. x.new A() where x is an instance of ThreadDemo).
		//��Ҫʹ�þ�̬�ڲ���
		//1.�����̶߳���
		MyThread tr1 = new MyThread();
		
		//2.��ǰ�߳��Ƿ��ڻ״̬
		System.out.println("isAlive=" + tr1.isAlive());
		
		//3.�����߳����ȼ���ֻ�Ǹı伸��
//		tr1.setPriority(Thread.MAX_PRIORITY);
		
		//4.�����߳�Ϊ�ػ��߳�
		//��Java�̷߳�Ϊ�û��̺߳��ػ��̣߳�һ��ǰ̨һ����̨��
		//��ֻ��һ���ػ��̣߳��û��̶߳����˳�����ʱ��JVM�ͻ����
		//�����ػ��̱߳���������֮ǰ���ػ��߳��в��������߳�Ҳ���ػ��̣߳��������е�Ӧ�ö����Է����ػ��߳�Ϊ֮�������д����������߼�
//		tr1.setDaemon(true);
		
		//5.�����̣߳������������
		tr1.start();
		System.out.println("isAlive=" + tr1.isAlive());

		
		MyRunnable tr = new MyRunnable();

		Thread tr2 = new Thread(tr);
		tr2.start();


		for(int i = 0;i < 10; ++i) {
			System.out.println(i + "-" + Thread.currentThread().getName() + "--" + Thread.currentThread().getPriority());
			try {
				//6.�߳����߰���
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(i == 6) {
				
				//7.��ͣ��ǰִ�е��̶߳����ó�����CPU��ִ��ʱ���ͬ���ȼ����߳�
//				Thread.yield();
				
				try {
					//8.�ȴ����߳�ִ�����
					tr1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
