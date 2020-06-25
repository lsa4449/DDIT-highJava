package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayListsSubject {

	public static void main(String[] args) {

		System.out.println("List를 사용한 메소드 ");

		List list1 = new ArrayList();

		list1.add(0, "김태진");
		list1.add(1, 0714);
		list1.add(2, 'L');
		list1.add(3, true);
		list1.add(4, 12.34);
		
		
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
		System.out.println();
		System.out.println("size => " + list1.size());

		System.out.println("-----------------------------------");
		System.out.println("ArrayList를 사용한 메소드");
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		list2.add(10);
		list2.add(20);
		list2.add(30);
		list2.add(40);

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}

		System.out.println("-----------------------------------");
		System.out.println("ArrayList 응용");
		System.out.println();
		System.out.println("ArrayList에 데이터를 저장 - add");
		System.out.println("ArrayList에 정보 얻기 - get");
		ArrayList arrList = new ArrayList();
		arrList.add(10);
		arrList.add(20);
		arrList.add(30);
		arrList.add(40);
		arrList.add(50);

		for (int i = 0; i < arrList.size(); i++) {
			System.out.println(arrList.get(i));
		}
		
		System.out.println("-----------------------------------");
		System.out.println("ArrayList에 항목 값을 판별 - contains");

		for (int i = 0; i < arrList.size(); i++) {
	
		}
	
		System.out.println("숫자 10을 넣었을 때 : " + arrList.contains(10));
		System.out.println("숫자 60을 넣었을 때 : " + arrList.contains(60));
		
		
		System.out.println("-----------------------------------");
		System.out.println("ArrayList에 인덱스 위치 찾기 - indexOf");
		System.out.println("지정된 객체(10)의 위치 : " + arrList.indexOf(10));
		System.out.println("지정된 객체(20)의 위치 : " + arrList.indexOf(20));
		System.out.println("지정된 객체(30)의 위치 : " + arrList.indexOf(30));
		System.out.println("지정된 객체(40)의 위치 : " + arrList.indexOf(40));
		System.out.println("지정된 객체(50)의 위치 : " + arrList.indexOf(50));
		
		System.out.println("-----------------------------------");
		System.out.println("ArrayList에 인덱스 위치 찾기 - lastIndexOf");
		System.out.println("지정된 객체(50)의 위치 : " + arrList.lastIndexOf(50));
		System.out.println("지정된 객체(40)의 위치 : " + arrList.lastIndexOf(40));
		System.out.println("지정된 객체(30)의 위치 : " + arrList.lastIndexOf(30));
		System.out.println("지정된 객체(20)의 위치 : " + arrList.lastIndexOf(20));
		System.out.println("지정된 객체(10)의 위치 : " + arrList.lastIndexOf(10));
		
		System.out.println("-----------------------------------");
		System.out.println("ArrayList에 접근할 수 있는 listlterator 반환 - listlterator");
		System.out.println("listlterator사용 : "+ arrList.listIterator(0));
		System.out.println("-----------------------------------");
		
		System.out.println("ArrayList에 지정된 범위에 있는 객체 반환  - subList");
		System.out.println("10부터 반환  : "+ arrList.subList(1, arrList.size()));
		System.out.println("-----------------------------------");
		
		System.out.println("ArrayList에 항목 값 삭제 - remove");
		System.out.println("숫자 10을 삭제 하였을 때 : " + arrList.remove(0));
		System.out.println("-----------------------------------");

		addAll();
	
		}
	
	
	public static boolean addAll() {	
		
		System.out.println("addAll 사용");
		ArrayList<String> people = new ArrayList<>();
		people.add("김태진");
		people.add("서한별");
		people.add("이수아");
		System.out.println("사람들 : " + people.toString());
		
		ArrayList<String> people2 = new ArrayList<>();
		people2.add("이승민");
		people2.add("김덕년");
		people2.add("홍성하");
		System.out.println("추가된 사람들: " + people2.toString());

		people2.addAll(2, people);
		System.out.println("모든 사람들: " + people2.toString());
		
		return true;

	}
	
}
