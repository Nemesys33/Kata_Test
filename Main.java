import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws Exception {
		String string;
		try (Scanner in = new Scanner(System.in)) {
			while (true) {
				string = in.nextLine();
				if (string.equals("q"))
					break;
				System.out.println(calc(string));
			}
		}
	}

	public static String calc(String input) throws Exception {
		String[] array = new String[3];
		var pattern = Pattern.compile("[-+*/]");
		var match = pattern.matcher(input);
		int pos = 0;
		if(match.find()) {
			pos = match.start();
		} else throw new Exception("Отсутствует знак выражения");

		array[0] = input.substring(0, pos).trim();
		array[1] = input.substring(pos, pos + 1).trim();
		array[2] = input.substring(pos + 1).trim();

		if (array[0].matches("[IVX]{1,4}") 
				&& array[1].matches("[-+*/]") 
				&& array[2].matches("[IVX]{1,4}") // римская запись
		) {
			int first = convert(array[0]);
			char znak = array[1].charAt(0);
			int second = convert(array[2]);

			if (first > 10 || first < 1 || second > 10 || second < 1)
				throw new Exception("Число(а) не попадает в допустимый интервал");
			var otvet = count(first, znak, second);
			if (otvet < 1)
				throw new Exception("Результат меньше 1");
			return String.valueOf(convert(otvet));
		}

		if (array[0].matches("[0-9]{1,2}") 
				&& array[1].matches("[-+*/]") 
				&& array[2].matches("[0-9]{1,2}") // арабская запись
		) {
			int first = Integer.parseInt(array[0]);
			char znak = array[1].charAt(0);
			int second = Integer.parseInt(array[2]);

			if (first > 10 || first < 1 || second > 10 || second < 1)
				throw new Exception("Число(а) не попадает в допустимый интервал");

			return String.valueOf(count(first, znak, second));
		}

		throw new Exception("Неверный формат ввода");
	}

	/**
	 * Подсчитывает результат выражения
	 */
	public static int count(int first, char znak, int second) throws Exception {
		switch (znak) {
		case '+':
			return first + second;
		case '-':
			return first - second;
		case '*':
			return first * second;
		case '/':
			return first / second;
		}

		throw new Exception();
	}

	/**
	 * 
	 * @param s - римская запись
	 * @return арабская запись
	 */
	public static int convert(String s) throws Exception { 
		switch (s) {
		case "I":
			return 1;
		case "II":
			return 2;
		case "III":
			return 3;
		case "IV":
			return 4;
		case "V":
			return 5;
		case "VI":
			return 6;
		case "VII":
			return 7;
		case "VIII":
			return 8;
		case "IX":
			return 9;
		case "X":
			return 10;
		}
		throw new Exception("Неверная римская запись");
	}


	/**
	 * 
	 * @param i - арабская запись
	 * @return римская запись
	 */
	public static String convert(int i) throws Exception {
		var str = new StringBuilder("");
		if (i >= 100) {
			str.append("C");
			i -= 100;
		}

		if (i >= 90) {
			str.append("XC");
			i -= 90;
		}

		if (i >= 50) {
			str.append("L");
			i -= 50;
		}

		if (i >= 40) {
			str.append("XL");
			i -= 40;
		}

		while (i >= 10) {
			str.append("X");
			i -= 10;
		}

		switch (i) {
		case 0:
			str.append("");
			break;
		case 1:
			str.append("I");
			break;
		case 2:
			str.append("II");
			break;
		case 3:
			str.append("III");
			break;
		case 4:
			str.append("IV");
			break;
		case 5:
			str.append("V");
			break;
		case 6:
			str.append("VI");
			break;
		case 7:
			str.append("VII");
			break;
		case 8:
			str.append("VIII");
			break;
		case 9:
			str.append("IX");
			break;
		}
		return str.toString();
	}
}
