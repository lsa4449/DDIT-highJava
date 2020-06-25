package kr.or.ddit.basic;

import java.awt.print.Printable;
import java.util.List;
import java.util.Vector;

public class T01_VectorTest {
	public static void main(String[] args) {
		// Vector -> List계열 클래스

		Vector v1 = new Vector();

		System.out.println("처음 크기 : " + v1.size());

		// Vector는 add() 메소드를 이용하여 데이터를 추가할 수 있다.
		v1.add("aaa");
		v1.add(111);
		v1.add(new Integer(123));
		v1.add('a');
		v1.add(true);
		v1.add(3.14);

		System.out.println("현재 크기 : " + v1.size());

		// Vector는 addElement()메소드를 이용하여 추가할 수도 있는데
		// 이 메소드는 기본적으로 add()메소드와 같은 기능을 수행한다.
		
		v1.addElement("ccc");
		System.out.println("v1 => " + v1.toString());

		// add(index,데이터) => 벡터의 index번째의 '데이터'를 키워 넣는다.
		// => index는 0부터 시작된다.
		
		v1.add(1, "kkk");
		System.out.println("v1 => " + v1);

		// set(index, 데이터) => 벡터의 index번째의 값을 주어진 '데이터'로 덮어쓴다.
		// => 반환값 : 원래의 데이터

		String temp = (String) v1.set(0, "zzz");
		System.out.println("set 명령 후 v1 => " + v1);
		System.out.println("원래의 데이터 : " + temp);

		// remove(index) => 벡터의 index번째 자료를 삭제한다.
		// => 자료가 삭제되면 index번째 이후의 데이터들이 앞으로 자동으로 당져겨서
		// => 채워진다.
		// => 반환값 : 삭제된 데이터

		// remove(삭제할 데이터) => '삭제할 데이터'를 찾아 삭제한다.
		// => 만약 '삭제할 데이터'가 여러개이면 앞에서부터 삭제한다.
		// => 삭제할 데이터가 '정수형'이거나 'char'형 일 경우에는
		// => 삭제할 데이터를 객체로 변환해서 사용해야 한다.(아니면 인덱스로 인식함)

		v1.remove(0);
		System.out.println("삭제 후 : " + v1);
		System.out.println();

		temp = (String) v1.remove(0);
		System.out.println("삭제된 자료 : " + temp);
		System.out.println("삭제 후 : " + v1);
		System.out.println();

		v1.add(123);
		v1.remove(true);
		System.out.println("삭제 후 : " + v1);
		System.out.println();

		v1.remove(new Integer(123));
		System.out.println("삭제 후  : " + v1);
		System.out.println();

		v1.remove(new Character('a'));
		System.out.println("삭제 후 : " + v1);
		System.out.println();

		v1.remove(3.14);
		System.out.println("삭제 후 : " + v1);
		System.out.println();

		// get(index) => index번째 자료를 반환한다.
		int data = (int) v1.get(0);
		System.out.println("0번째 자료 : " + data);
		System.out.println("-----------------------------------");

		/*
			  제너릭 타입(generic type) 
			  => Collection객체를 선언할 때 <> 안에 그 Collection이 저장할 데이터 타입을 정해주는 것을 말한다. 
			  => 이런식으로 선언하게 되면 그 데이터 타입 이외의 데이터를 저장할 수 없다. 
			  	 (제너릭 타입으로 선언할 수 있는 데이터 타입은 클래스 이어야한다.) 
			  	 (예 : int -> Integer, boolean -> Boolean, char -> Character등) 
			  => 제너릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		  
		 */

		Vector<String> v2 = new Vector<String>(); // String만 저장가능
		Vector<Integer> v3 = new Vector<Integer>(); // Integer만 저장가능

		v2.add("안녕하세여");
		// v2.add(123); //오류 : 다른 종류의 데이터를 추가할 수 없다.

		String temp2 = v2.get(0);
		System.out.println("temp2 => " + temp2);

		Vector<Vector> vv = new Vector<Vector>();
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();

		// --------------------------------------------------------

		v2.clear(); // 벡터의 모든 데이터를 삭제한다.
		System.out.println("v2의 size = " + v2.size());

		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");

		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");

		System.out.println("삭제 되기 전 v2 = " + v2);

		// removeAll(Collection객체) => 벡터의 데이터 중에서 'Collection객체'가
		// => 가지고 있는 데이터를 모두 삭제한다.

		v2.removeAll(v4);
		System.out.println("삭제 된 후 v2 => " + v2);
		System.out.println("---------------------------------------");

		v2.clear();

		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");

		// 벡터의 데이터들을 순서대로 가져와서 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문을 사용함)

		for (int i = 0; i < v2.size(); i++) {
			System.out.println(i + "번째 자료 " + v2.get(i));
		}

		System.out.println("=======================================");
		
		/*
		  	향상된 for문
		  	형식 ) for(자료형명 변수명 : 배열변수나  Collection계열 변수){
		  			처리할 내용들;
		  			....
		  		}
		  		
		  		=> 주어진 '배열변수나 Collection계열 변수'의 데이터 개수 만큼 반복한다.
		  		=> 반복할 때마다 '배열변수나 Collection계열변수'의 데이터를 하나씩 꺼내와
		  			'변수명'에 저장한 후 '처리할 내용들'을 처리한다.
		 */
		
		for(String s : v2) {
			System.out.println(s);
		}
			System.out.println("---------------------------------------");
			
			// 만약 제너릭을 사용하지 않은 Collection을 향상된 for문으로 처리할 경우에는
			// Object형으로 간주한다.
			
			for(Object obj : v1) {
				System.out.println(obj);
			}
		
			System.out.println("---------벡터 사이즈 및 용량 메소드 예제----------");
			
			Vector v = new Vector(5);
			v.add("홍길동");
			v.add("박찬호");
			v.add(3);
			print(v);
			
			
			v.trimToSize(); //벡터의 용량을 현재 벡터 사이즈로 줄인다.
			System.out.println("==== After trimToSize() ====");
			print(v);
			
			v.ensureCapacity(5); //현재 용량이 최소 용량보다 작다면.. 신규용량  = 현재용량 * 2;
								 //그래도 최소용량보다 작다면 신규 용량 = 최소용량으로 설정된다.
			
			System.out.println("==== After v.ensureCapacity(5) ====");
			print(v);
			
			v.setSize(7); //현재 용량이 설정 사이즈보다 작으면 신규용량 = 현재용량 * 2;
						  //그래도 설정 사이즈보다 작다면 신규용량 = 설정 사이즈로 설정된다.
			
			System.out.println("==== After v.setSize(7) ====");
			print(v);
			
			v.clear();
			
			System.out.println("==== After v.clear() ====");
			print(v);
	}

	public static void print(Vector v) {
		System.out.println(v);
		System.out.println("size : " + v.size());
		System.out.println("capacity : " + v.capacity());
	}
	
}
