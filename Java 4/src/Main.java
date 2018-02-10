import university2.Person;
import university2.Student;
import university2.Teacher;

public class Main {

	public static void main(String[] args) {
		/*
		 * Circle c = new Circle(100); calc(c);
		 * 
		 * Square s = new Square(100); calc(s);
		 * 
		 * IShape is = new IShape() {
		 * 
		 * @Override public double calculate() { // TODO Auto-generated method
		 * stub return 0; } };
		 */

		/*
		 * Student student = new Student(); student.Name = "";
		 * 
		 * Teacher teacher = new Teacher(); teacher.Name = "";
		 * 
		 * Person[] persons;
		 */

		/*
		 * Person person = new Person(); person.student = new Student();
		 * person.teacher = new Teacher();
		 */

		/*
		 * try { Circle c = null; c.calculate();
		 * 
		 * int a = 5 / 0; } catch (ArithmeticException e) {
		 * System.out.println(e.toString()); } catch (Exception e) {
		 * System.out.println(e.toString()); }
		 */

		Circle c = null;// new Circle(100);
		test(c);
	}

	public static void test(BaseShape shape) {
		try {
			System.out.println(shape.calculate());
		} catch (Exception e) {
			System.out.println(e.toString());
			return;
		} finally {
			System.out.println("Hello");
		}
	}

	
	
	
	
	public static void calc(BaseShape shape) {
		if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			c.test();
		}
		shape.printType();
		System.out.println(shape.calculate());
	}
}
