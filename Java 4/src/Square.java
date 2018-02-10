
public class Square extends BaseShape {
	private int mSide;

	public Square(int side) {
		mSide = side;
		mType = Shapes.Square;
	}

	@Override
	public double calculate() {
		return mSide * mSide;
	}
}
