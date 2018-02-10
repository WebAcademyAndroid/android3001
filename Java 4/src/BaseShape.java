
public abstract class BaseShape implements IShape {

	public Shapes mType;

	public void printType() {
		switch (mType) {
		case Circle:
			System.out.println("I am circle");
			break;
		case Square:
			System.out.println("I am square");
			break;
		}
	}
}
