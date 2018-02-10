package university;

public class Boss extends Teacher {

	@Override
	public int calculate() {
		return super.calculate() * 5;
	}
}
