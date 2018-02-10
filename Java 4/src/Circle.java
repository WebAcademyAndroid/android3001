
public class Circle extends BaseShape {
	private int mRadius;

	public Circle(int radius) {
		mRadius = radius;
		mType = Shapes.Circle;
	}
	
	public void test(){
		System.out.println("test");
	}

	@Override
	public double calculate() {
		return 3.14 * mRadius * mRadius;
	}
	
	@Override
	public void printType() {
		super.printType();
		System.out.println("test");
	}
}
