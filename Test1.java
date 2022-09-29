package CalculatorTest;

import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("첫번째 숫자 입력");
		int num1 = Integer.parseInt(input.nextLine());
		
		System.out.println("연산자 입력");
		String math = input.nextLine();
		
		System.out.print("두번째 숫자 입력");
		int num2 = Integer.parseInt(input.nextLine());
		
		int result = math.equals("+") ? num1 + num2
				: (math.equals("-") ? num1 - num2
				: (math.equals("*") ? num1 * num2
				: (math.equals("/") ? num1 / num2
				: null ) ) );
		
		System.out.println(num1 + math + num2 + "=" + result);
	}
}