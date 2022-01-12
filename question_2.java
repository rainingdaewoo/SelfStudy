package Test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class question_2 {
		public static void main(String[] args) {
			Scanner sc =  new Scanner(System.in);

			System.out.println("랜덤 배열 생성");
			System.out.println("30 5 와 같은 형식으로 입력해주세요.");
			System.out.println("quit를 입력시 프로그램이 종료됩니다.");

			while(true){ // 무한 반복
				try {	             
					String input = sc.nextLine();  
					if(input.equals("quit")){ // quit 입력 시 종료
						System.out.println("종료합니다");
						break;
					}
					
				String[] arrayInput = input.split("\\s"); // 공백 기준 나누기
				int arrayLength = Integer.parseInt(arrayInput[0]); // N개의 배열(앞에 입력된 수)
				int howMany = Integer.parseInt(arrayInput[1]); // K번째 큰 수(뒤에 입력된 수)

				Integer[] arrays = new Integer[arrayLength];
				
				for(int i = 0; i < arrayLength; i++) { // 난수 저장
					arrays[i] = (int)(Math.random() * 50) + 1;
				} 
				System.out.println("정렬 전");
				for(int i = 0; i < arrayLength; ) { // 한 줄에 10개씩 출력
					System.out.print("N[" + arrays[i] + "] ");
					i++;
					if(i % 10 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				//
				Arrays.sort(arrays, Collections.reverseOrder()); // 내림차순으로 변환
				System.out.println("정렬 후");
				for(int i = 0; i < arrayLength; ) { // 한 줄에 10개씩 출력
					System.out.print("N[" + arrays[i] + "] ");
					i++;
					if(i % 10 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				System.out.println("결과: " + arrays[howMany - 1]); // K번째 큰 수 출력
				} catch (Exception e) {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				} 	
			}

		}
}
