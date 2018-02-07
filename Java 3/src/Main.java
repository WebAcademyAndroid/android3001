import test.Student;

public class Main {

	public static void main(String[] args) {
		/*
		 * Student s = new Student(); s.name = "Ivan"; s.age = 22;
		 * System.out.println(s.name + " " + s.age);
		 * 
		 * Student s2 = new Student("Ivan", 22); System.out.println(s2.name +
		 * " " + s2.age);
		 * 
		 * Student s3 = new Student("Ivan"); s3.age = 22;
		 * System.out.println(s3.name + " " + s3.age);
		 */

		/*int a;
		Student s;

		if (s != null) {
			s.name = "ivan";
		}*/
		
		/*int a = 0;
		int b = a;
		a = 10;
		
		Student s = new Student();
		Student s2 = s;
		s.age = 10;
		
		Student s3 = new Student();
		s3.age = s.age;
		
		String str = "abc";*/
		
/*		int a = 3;
		int b = 3;
		if(a == b){
			System.out.println("equal");
		}
		
		Student s1 = new Student();
		Student s2 = new Student();
		if(s1 == s2){
			System.out.println("equal");
		}
		
		String str1 = "qwerty";
		String str2 = "qwerty";
		if(str1.equals(str2)){
			System.out.println("equal strings");
		}
		
		int a = 3;
		Integer aa = a;
		Integer aaa = new Integer(3);
		Integer aaaa;
		test(a);
		
		Student s = new Student();
		s.name = "qwe";
		s.age = 33;
		test(s);
		
		Student[] arr = new Student[10];
		arr[0] = new Student();
		arr[0].age = 10;
		
		arr[1] = s;
		arr[2] = s;
		
		Student.INN = 123;
		Student.test();
		
		
		Student ss = new Student();
		ss.email = "ivan@mail.com";
		String login = ss.getLogin();
		
		String login2 = Student.getLogin2("ivan@mail.com");*/
		
		int small = 3;
		int big = 2;
		int goal = 9;
		
		int bigCount = Math.min(big, goal/5);
		System.out.println("big blocks = " + bigCount);
		
		int bigLength = bigCount * 5;
		System.out.println("big blocks length = " + bigLength);
		
		if(goal - Math.min(big, goal/5)*5 <= small){
			System.out.println(true);
		}else{
			System.out.println(false);
		}
	}
	
	public static void test(Integer a){
		a = 10;
	}
	
	public static void test(Student qwe){
		qwe.age = 10;
	}

}
