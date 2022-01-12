package Test1;

import java.util.Scanner;
//input +와 -로 구분해서 배열에 담기 -> 2자리 이상 숫자도 연산하기 위해 
		// inputArray 의 값
		//-> 숫자 다음 글자가 -면 result 다음 숫자 - // -> result 출력
		//최종 : 입력값 + result
		// 예외처리 해야 하는 목록 
		// 1. 실수 입력 시
		// 2. 문자 입력 시
		// 3. 음수 입력 시
		// 4. +, - 외 다른 기호 입력 시

public class question_1 {

	public static void main(String[] args) {
		Scanner sc; 
		sc = new Scanner(System.in);

		System.out.println("+, - 연산 프로그램입니다.");
		System.out.println("quit를 입력시 프로그램이 종료됩니다.");
		
		while(true){ // 무한 반복
			
			try {
				String input = sc.nextLine(); 
				if(input.equals("quit")){ // 만약 quit 입력 시 종료
					System.out.println("종료합니다");
					break;
			}
			
			String inputTrim = input.replace(" ", ""); // input 공백 제거
			String[] inputArray = inputTrim.split("\\+|#|\\-"); // +, -를 기준으로 숫자 자르기
															//ex 1 + 3 - 4 + 4 -> {1, 3, 4, 4}
			int result = Integer.parseInt(inputArray[0]); // result 초기값은 첫 번째 inputArray의 첫번째 값
			int count = 1; // 배열 값을 가리키기 위한 count
			
			for(int i = 1; i < inputTrim.length(); i++) { 
				if(inputTrim.charAt(i) == '+') { //i번째 자리가 + 이면 result에서 더하고
					result += Integer.parseInt(inputArray[count]);
					count++;
				} else if(inputTrim.charAt(i) == '-') { //i번째 자리가 - 이면 result에서 빼고 
					result -= Integer.parseInt(inputArray[count]); 
					count++;
				}
			}
				System.out.println(input + " = " + result); // 유저가 입력한 값과 계산식을 출력
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} 	
		}
	}
}
