package kr.or.ddit.basic;

/*
 * 	wait()메서드 => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로
 				   이동시킨다.
 	notify() 또는 notifyAll()메서드 
 			=> Wait-Set영역에 있는 스레드를 깨워서 실행될 수  있도록 한다.
 			   (notify()는 하나, notifyAll은 Wait-Set에 있는 전부를 깨운다.)
 			=> wait()와 notify(), notifyAll()메서드는 동기화 영역에서만 실행 할 수 있고,
 			   Object클래스에서 제공하는 메서드이다.	   			
 */
public class T19_WaitNotifyTest {

	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);

		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()에서 작업즁~");

		notify();

		try {
			wait();

		} catch (InterruptedException e) {

		}
	}

	public synchronized void methodB() {

		System.out.println("methodB()에서 작업즁~");

		notify();

		try {
			wait();

		} catch (InterruptedException e) {

		}
	}
}

//WorkObject의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workobj;
	
	public ThreadA(WorkObject workObject) {
		this.workobj = workObject;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workobj.methodA();
		}
		System.out.println("ThreadA 종료/...");
	}
}

//WorkObject의 methodA()메서드만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workobj;
	
	public ThreadB(WorkObject workObject) {
		this.workobj = workObject;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workobj.methodB();
		}
		System.out.println("ThreadB 종료/...");
	}	
}