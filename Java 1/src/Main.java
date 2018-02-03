
public class Main {

	public static int mAge;
	
	public static void main(String[] args) {
		/*
		 * int a; a = 10;
		 * 
		 * int A = 6;
		 * 
		 * int b = 3;
		 * 
		 * double d = 2.22;
		 * 
		 * char c = 'a'; String s = "asdvklsjdlsdfkjh134235434./,.";
		 * 
		 * String str = "Hello World!";
		 */

		// int a = 7;
		// int b = 3;
		// int c = a + b;

		// int d = (3 + 4) * c;

		// System.out.println(d);
		// System.out.println((3 + 4) * c);

		double e = 5 / 2.0;
		// System.out.println(e);

		String s1 = "Hello ";
		String s2 = "World!";
		String s3 = s1 + s2;

		// System.out.println(s3 + (e + 10));

		// String str = "abcabc";
		// System.out.println(str.indexOf("b"));
		// System.out.println(str.indexOf("b", 3));

		// String str = "abcabc";
		// str = str.replace("ab", "AB");

		// System.out.println(str);
		// System.out.println(str2);

		// String str = " abc abc ";
		// System.out.println(str.trim().replace(" ", "").toUpperCase());

		/*
		 * String str = "abcabc"; System.out.println(str.charAt(1));
		 * System.out.println(str.substring(0, 3));
		 * System.out.println(str.substring(2));
		 * 
		 * String email ="vasya@gmail.com"; int dog = email.trim().indexOf("@");
		 * String login = email.substring(0, dog); System.out.println(login);
		 */

		// String str = test("Hello function!", 10);
		// System.out.println(str);

		/// System.out.println(test("Hello function!", 10).trim());

		/*int a = 1;
		a = test(a);
		System.out.println(mAge);*/
		
		int aa = 123;
		String ss1 = String.valueOf(aa);
		String ss2 = Integer.toString(aa);
		
		String sss = "321 ";
		int aaa = Integer.parseInt(sss.trim());
		
		String ssss = "2222.33";
		double dddd = Double.parseDouble(ssss);
		
		
		String str = String.format("Hello %1$s, my name is %1$s, I am %1$s years old", "students", "Alex", 34);
		System.out.println(str);
	}

	public static String test(String str, int a) {
		return str + a;
	}

	public static int test(int a) {
		a = 10;
		int b = 4;
		
		mAge = 333;
	
		
		return a;
	}
}
