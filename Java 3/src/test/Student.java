package test;

public class Student {
	public String name;
	public int age;
	public String email;
	


	public Student() {

	}
	
	public Student(String name) {
		this.name = name;
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getLogin(){
		return email.split("@")[0];
	}
	
	public static String getLogin2(String email){
		return email.split("@")[0];
	}
}
