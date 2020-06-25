package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 *  문제 ) 학번, 이름, 국어 점수, 영어 점수, 수학 점수, 총점, 등수를 멤버변수로 갖는 
 *  	 Student클래스를 만든다.
 *  	 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 *  	
 *  	이 Student객체들은 List에 저장하여 관리한다.
 *  	List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 *  	총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 *  	(총 점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 *		(학번의 정렬기준은 Student클래스 차체에서 제공하도록 하고, 
 *		 총점 정렬 기준은 외부클래스에서 제공하도록 한다.) 
 */

public class T08_StudentTest {
	
	public static void main(String[] args) {
		
		ArrayList<Student> stuList = new ArrayList<>();
		
		stuList.add(new Student("4", "이수아", 70, 80, 70));
		stuList.add(new Student("1", "서한별", 80, 70, 80));
		stuList.add(new Student("2", "김태진", 40, 50, 90));
		stuList.add(new Student("6", "홍성하", 50, 90, 70));
		stuList.add(new Student("5", "김덕년", 30, 80, 100));
		stuList.add(new Student("3", "이승민", 70, 50, 70));

		//등수 구하기
		int count = 1;
		for(int i = 0; i < stuList.size(); i++) {
			count = 1;
			for(int j = 0; j < stuList.size(); j++) {
				if(stuList.get(i).getSum() < stuList.get(j).getSum()) {
					count++;
				}
			}
			stuList.get(i).setRank(count);
		}
		
		Collections.sort(stuList); 

		
		System.out.println("정렬 전");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		System.out.println("==========================================================================");
		
		Collections.sort(stuList, new SortStuNum());
		
		System.out.println("학번 정렬 후");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		System.out.println("===========================================================================");
		
		Collections.sort(stuList, new SumDesc()
				);


		System.out.println("총 점의 역순");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		System.out.println("===========================================================================");
		
	}

}

//학번 정렬
class SortStuNum implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2 ) {
		
		return stu1.getStuNum().compareTo(stu2.getStuNum());
	}
}

//총 점의 역순
class SumDesc implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2 ) {
		
		if(stu1.getSum() == stu2.getSum()) {
			return stu1.getStuNum().compareTo(stu2.getStuNum()) * -1;
		} else {
			return new Integer(stu1.getSum()).compareTo(stu2.getSum()) * -1;
		}
	}	
}


class Student implements Comparable<Student> {

	private String stuNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	public Student(String stuNum, String name, int kor, int eng, int math) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng+ math;
	}

	public String getStuNum() {
		return stuNum;
	}


	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}


	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [stuNum=" + stuNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student stu) {
		return 0;
	}

}