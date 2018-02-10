package university;

public class Teacher extends Person {

	public int hours;

	public int salary;

	public int calculate() {
		return hours * salary;
	}
}
