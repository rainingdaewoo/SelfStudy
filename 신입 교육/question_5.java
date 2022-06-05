package Test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class question_5 {
	public static void main(String[] args) {
		System.out.println("단어 찾기 프로그랩입니다. 찾고 싶은 단어를 입력해주세요.");
		System.out.println("quit를 입력시 프로그램이 종료됩니다.");
		try{
			//파일 객체 생성
			File file = new File("C:\\Users\\user\\Desktop\\102CTL001.txt");
			//입력 스트림 생성
			FileReader filereader = new FileReader(file);
			int singleCh = 0;
			
			StringBuffer data = new StringBuffer();
			while((singleCh = filereader.read()) != -1){ // read()를 통해 호출한 반환값을 singleCH에 저장
				data.append((char)singleCh); // 더 이상 불러올 데이터가 없으면 -1 반환 -> 파일의 값을 data에 저장
			}
			filereader.close();
			/*------------------------------------------------------------*/
			Scanner sc =  new Scanner(System.in);
			int count = 0; // 개수 초기화 
			System.out.println(data);
			while(true){
				String input = sc.nextLine(); // 입력 값
				if(input.equals("quit")){ // quit 입력 시 종료
					System.out.println("종료합니다");
					break;
				} 

				for(int i = 0; i < data.length(); i++) {
					for(int j = 0; j < input.length();) {
						int dataChar = data.charAt(i);//  i번째 data의 값
						int inputChar = input.charAt(j); // j번째 input의 값

						if(j == input.length() - 1) { // 만약 마지막 글자일 때
							if(dataChar == inputChar) {				  // A와 B가 같다면 같은 단어로 취급
								count++;
							}
						}
						if(dataChar == inputChar ) { // 만약 i번째 data의 값과, j번째 input의 값이 같으면 다음 글자 비교
							j++;
							i++;
						} else { // 다를 시 j는 다시 처음부터
							j = 0;
							break;
						}
					}
				}
				System.out.println("입력하신 단어는 " + input + "입니다.");
				System.out.println("입력하신 단어는 " + count + "개 있습니다.");
			}
		}catch (Exception e) {
			System.out.println("잘못된 입력입니다. 다시 입력해주세요");
		} 	
	}
}
