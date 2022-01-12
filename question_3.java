package Test1;

import java.util.Scanner;

public class question_3 {

	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);

		System.out.println("별찍기 프로그랩입니다. 1 ~ 100 사이의 홀수를 입력해주세요.");
		System.out.println("quit를 입력시 프로그램이 종료됩니다.");

		while(true){ // 무한 반복
			try {	
				String input = sc.nextLine();
				if(input.equals("quit")){ // quit 입력 시 종료
					System.out.println("종료합니다");
					break;
				}
				
				int line = Integer.parseInt(input); // input 값 line(int)로 변환
				
				while( 0 > line || 100 < line || line % 2 == 0) {
					System.out.println("입력값은 1 ~ 100 사이의 홀수여야 합니다. 다시 입력해주세요.");
					line = Integer.parseInt(sc.nextLine()); // 다시 line 값 입력
				}
					
				// 삼각형 출력(처음부터 중간까지)
				for(int i = 0; i < line; i++) { //높이가 line 인 삼각형 생성
					for (int j = 0; j < line - i - 1; j++) { // 공백 문자 j번 출력 
						System.out.print("  ");
					}
					for (int j = 0; j < i * 2 + 1; j++) { //별 출력
						if(i == line - 1 && j == i * 2) { // 만약 맨 가운데 줄 맨 마지막 별일 시
							System.out.print("*"); 		// 별만 출력
						} else {
						System.out.print("* ");
						}
					}
					System.out.println(); // 다음 줄로 이동
				}
				
				// 역삼각형 출력(중간 아래부터 끝까지)
				for (int i = line - 1; i > 0; i--) { // 높이가 line - 1 인 역삼각형 생성
					for (int j = line - i; j > 0; j--) {
						System.out.print("  ");
					}
					for (int j = i * 2 - 1; j > 0; j--) {
						System.out.print("* ");
					}
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} 	
		}
	}
}
