package Test1;

import java.util.Scanner;

public class question_4 {

	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);

		System.out.println("글자 순서 찾기 프로그랩입니다. 아래 문장에서 찾고 싶은 단어를 입력해주세요.");
		System.out.println("quit를 입력시 프로그램이 종료됩니다.");
		String data = "The WYCA of Indianapolis still focuses on, supports, and gives empowerment to women and their families.";
		System.out.println(data);
		int result = -1; // 기본값 설정
		
		while(true){ // 무한 반복
				String input = sc.nextLine();
				if(input.equals("quit")){ // quit 입력 시 종료
					System.out.println("종료합니다");
					break;
				} 
				//data와 input 값 뒤에서부터 비교
				Loop1 :
				for(int i = data.length() - 1; i >= 0; i--) {
					Loop2 :
					for(int j = input.length() - 1; j >= 0;j--) {
						int dataChar = data.charAt(i);//  뒤에서 i번째 ex의 값
						int inputChar = input.charAt(j); // 뒤에서 j번째 input의 값
						
						if(65 <= dataChar && dataChar <= 90) { // 대문자면 소문자로
							dataChar += 32; 
						}
						if(65 <= inputChar && inputChar <= 90) { // 대문자면 소문자로
							inputChar += 32;
						}
						
						if(dataChar == inputChar) { // 만약 A와 B가 같다면 다음 글자 비교.
							i--;
						} else { // 아니면 다시 비교
							break Loop2; 
						}
						if(j == 0) { //마지막 글자까지 비교했을 시 반복문 탈출.
							result = i + 1; //뒤에서부터가 아닌 앞에서부터의 인덱스를 출력해야 함으로
							break Loop1;
						}
					}
				}
				System.out.println("결과) " + result);
				System.out.println("lastIndexOf 값:" + data.lastIndexOf(input));
				result = -1; // result 초기화	
		}
	}
}
