package kr.or.ddit.basic;

import java.util.Arrays;

public class T07_WildCardTest {

	/*
	 * 	 와일드 카드 예제
	 * 
	 * <? extends T> => 와일드 카드이 상한 제한, T와 그 자손들만 가능
	 * <? super T> => 와일드 카드의 하한 제한, T와 그 조상들만 가능 <Object 제외>
	 * <?> => 모든 타입이 가능 <? extends Object>와 동일
	 * 
	 */
	
	/**
	 * 모든 과정 등록
	 * @param course 모든과정
	 */
	public static void registerCourse(Course<?> course) {
		System.out.println(course.getName() + "수강생" + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생과정 등록
	 * @param course
	 */
	public static void registerCourseStudent(Course<? extends Student> course) {
		System.out.println(course.getName() + "수강생" + Arrays.toString(course.getStudents()));
	}

	/**
	 * 직장인 과정
	 * @param course
	 */
	public static void registerCourseWorker(Course<?super Worker> course) {
		System.out.println(course.getName() + "수강생" + Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		
		Course<Person> personCourse = new Course("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
	
		Course<Worker> workerCourse = new Course("직장인 과정", 5);
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course("학생 과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
				
		Course<HighStudent> highStudentCourse = new Course("고등학생 과정", 5);
		highStudentCourse.add(new HighStudent("직장인1"));
		
		registerCourse(personCourse);
		registerCourse(workerCourse);
		registerCourse(studentCourse);
		registerCourse(highStudentCourse);
		System.out.println("------------------------------------");
	
//		registerCourseStudent(personCourse);
//		registerCourseStudent(workerCourse);
		registerCourseStudent(studentCourse);
		registerCourseStudent(highStudentCourse);
		System.out.println("------------------------------------");
	
		registerCourseWorker(personCourse);
		registerCourseWorker(workerCourse);
//		registerCourseWorker(studentCourse);
//		registerCourseWorker(highStudentCourse);
		System.out.println("------------------------------------");
	
		}
	}	
	

// 일반인
class Person {
	String name; // 이름

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "이름 : " + name;
	}
}

// 근로자
class Worker extends Person{
	public Worker(String name) {
		super(name);
	}
}
	
// 학생
class Student extends Person{
	public Student(String name) {
		super(name);
	}
}

// 고등학생
class HighStudent extends Student{
	public HighStudent(String name) {
		super(name);
	}
}

// 수강코드
class Course<T>{
	private String name; // 코드명
	private T[] students; // 수강생

	public Course(String name, int capacity) {
		this.name = name;
		// 타입 파라미터로 배열을 생성시 오브젝트 배열을 생성 후 , 타입 파라미터 배열로
		// 캐스팅 처리해야 함
		students = (T[])(new Object[capacity]);
		}

	// 코스명 조회
	public String getName() {
		return name;
		}
	
	// 수강생 조회
	public T[] getStudents() {
		return students;
		}
	
	// 수강생 등록
	public void add(T t) {
		for(int i = 0; i < students.length; i++) {
			if(students[i] == null) { // 아직 등록되지 않은(빈) 자리 인지 확인
				students[i] = t;
				break;
				}
			}
		}

	
	
	
	}

