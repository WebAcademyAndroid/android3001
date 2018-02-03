import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		/*
		 * int salary = 100; int time = 1;
		 * 
		 * if ((salary < 500 || salary > 2000) && time > 2) { //
		 * System.out.println("NO!"); } else { // System.out.println("YES"); }
		 */

		/*
		 * boolean b = (salary < 500 || salary > 2000) && time > 2; if
		 * (!((salary < 500 || salary > 2000) && time > 2)) { //
		 * System.out.println("YES"); }
		 * 
		 * if (salary > 500) { System.out.println("YES1"); }
		 * 
		 * 
		 * System.out.println(salary < 500 ? "NO" : salary > 2000 ? "NO" :
		 * "YES");
		 */

		/*
		 * switch (salary) { case 100: case 2000: System.out.println("NO");
		 * break; case 500: case 1000: System.out.println("YES"); break;
		 * default: System.out.println("DONT KNOW"); break; }
		 */

		/*
		 * int salary = 1000; int money = 0;
		 * 
		 * for (int i = 0; i < 3; i++) { // money = money + salary; money +=
		 * salary; System.out.println(String.format("i: %d, money: %d", i,
		 * money)); }
		 * 
		 * System.out.println("result: " + money);
		 * 
		 * int a = 0; int b = 0;
		 * 
		 * System.out.println(a++); System.out.println(++b);
		 * 
		 * System.out.println(a); System.out.println(b);
		 */

		/*
		 * int salary = 1000; int money = 0;
		 * 
		 * int count = 0; while (money < 10000) { //money += salary;
		 * 
		 * count++; if (count > 99999) { break; } }
		 * 
		 * System.out.println(money);
		 */

		/*
		 * int[] arr = new int[10]; for (int i = 0; i < arr.length; i++) {
		 * arr[i] = i; } arr[arr.length - 1] = 77; for (int i = 0; i <
		 * arr.length; i++) { System.out.print(arr[i]);
		 * 
		 * if (i < arr.length - 1) { System.out.print("-"); } }
		 * 
		 * String str = "a,b,c,a,b,c"; String[] items = str.split(","); for (int
		 * i = 0; i < items.length; i++) { System.out.print(items[i]);
		 * 
		 * if (i < items.length - 1) { System.out.print("-"); } }
		 * 
		 * String email = "vasya@mail.com.ua"; String[] parts =
		 * email.split("[@\\.]"); String login = parts[3];
		 * System.out.print(login);
		 */

		/*
		 * ArrayList<String> arr = new ArrayList<String>(); arr.add("AAA");
		 * arr.add("BBB"); arr.add(1, "CCC"); arr.set(1, "DDD"); arr.add("EEE");
		 * arr.add("FFF"); arr.add("GGG"); arr.remove(3);
		 * 
		 * for (int i = 0; i < arr.size(); i++) { System.out.print(arr.get(i));
		 * 
		 * if (i < arr.size() - 1) { System.out.print("-"); } }
		 */

		/*
		 * ArrayList<String> arr = new ArrayList<String>(); arr.add("AAA");
		 * arr.add("BBB"); arr.add("CCC"); arr.add("BBB"); arr.add("BBB");
		 * arr.add("BBB"); arr.add("GGG");
		 * 
		 * 
		 * for (int i = arr.size() - 1; i >= 0; i--) { if
		 * (arr.get(i).equals("BBB")) { arr.remove(i); } }
		 * 
		 * 
		 * int position = findB(arr, 0); while (position >= 0) {
		 * arr.remove(position); position = findB(arr, position); }
		 * 
		 * 
		 * for (int i = 0; i < arr.size(); i++) { System.out.print(arr.get(i));
		 * if (i < arr.size() - 1) { System.out.print("-"); } }
		 * 
		 * 
		 * int count = 0; for (String str : arr) { System.out.print(str);
		 * count++; if (count < arr.size()) { System.out.print("-"); } }
		 * 
		 * 
		 * 
		 * int[][][][][][][] aaa = new int[10][8][2][3][4][5][7];
		 * 
		 * aaa[0][0][0][1][1][2][2] = 55;
		 * 
		 * ArrayList<ArrayList<ArrayList<[]>>> bbb; bbb.get(0).get(3).add("");
		 * 
		 * 
		 * ArrayList<String>[] ccc; ArrayList<int[]> ddd;
		 */

		HashMap<String, Integer> map = new HashMap<>();
		map.put("G", 3);
		map.put("B", 1);
		map.put("W", 2);

		if (map.containsKey("B")) {
			int count = map.get("B");
			count++;
			map.put("B", count);
		} else {
			map.put("B", 1);
		}

		map.put("B", map.get("B") + 1);

		// map.remove("G");

		if (map.containsKey("G")) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

		for (String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}
	}

	public static int findB(ArrayList<String> arr, int position) {
		for (int i = position; i < arr.size(); i++) {
			if (arr.get(i).equals("BBB")) {
				return i;
			}
		}

		return -1;
	}

	public static boolean test(int salary) {
		/*
		 * boolean b = true;
		 * 
		 * if (salary < 500 || salary > 2000) { b = false; }
		 * 
		 * return b;
		 */

		switch (salary) {
		case 100:
		case 2000:
		default:
			return false;
		case 500:
		case 1000:
			return true;
		}

	}
}
