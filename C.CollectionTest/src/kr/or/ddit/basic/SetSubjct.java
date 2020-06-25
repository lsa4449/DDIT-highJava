package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetSubjct {

	public static void main(String[] args) {
	
		Set<String> snack = new HashSet<>();
		
		System.out.println("----------add 메소드-----------");
		snack.add("홈런볼");
		snack.add("스윙칩");
		snack.add("허니버터칩");
		snack.add("나쵸");
		System.out.println("과자 종류 : " + snack.toString());
		
		System.out.println();
		System.out.println("---------contains 메소드---------");
		System.out.println("오렌지가 포함 되는지 확인: " + snack.contains("오렌지"));
		System.out.println("나쵸가 포함 되는지 확인: " + snack.contains("나쵸"));
		
		System.out.println();
		System.out.println("---------iterator 메소드---------");
		Iterator<String> it = snack.iterator();
			while(it.hasNext())
		System.out.println("과자 종류 : " + it.next());
			
		System.out.println();
		System.out.println("---------size 메소드---------");
		System.out.println("과자의 사이즈(길이) : " + snack.size());
				
			
		System.out.println();
		System.out.println("--------=isEmpty 메소드---------");
		System.out.println("저장된 과자 확인 : " + snack.isEmpty());
		System.out.println(">>clear() 메소드 사용 후 ...");
		snack.clear();
		System.out.println("저장된 과자 확인 : " + snack.isEmpty());
		
		System.out.println();
		System.out.println("---------remove 메소드---------");
		snack.remove("스윙칩");
		System.out.println("과자 종류 : " + snack.toString());
		
		
		List<String> remove = new ArrayList<>();
		System.out.println();
		System.out.println("---------removeAll 메소드---------");
		remove.add("홈런볼");
		remove.add("나쵸");
		remove.add("허니버터칩");
		snack.removeAll(remove);
		System.out.println("과자 종류 : " + snack.toString());

		
		System.out.println();
		System.out.println("---------clear 메소드---------");
		snack.clear();
		System.out.println("과자 종류 : " + snack.toString());
		
		System.out.println();
		System.out.println("---------중복되지 않는 정수 만들기---------");
		HashSet<Integer> hash = new HashSet<>();
		while(hash.size() < 5) { 
			int num = (int)(Math.random() * (100) + 1); 
			hash.add(num);
		} 
		System.out.println("만들어진 난수들  : " + hash);
	
		System.out.println();
		System.out.println("---------TreeSet 만들기---------");
		Set<String> ts = new TreeSet<>();
		for(String str : snack) {
			System.out.println("TreeSet 정렬 : " + ts);		
		}
		
	
	
	}
}
