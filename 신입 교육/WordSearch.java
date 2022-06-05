package Test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordSearch {

	static File dir = new File("C:\\\\Users\\\\user\\\\Desktop\\\\doc_set_tokenized"); // 폴더 경로
	static String[] fileNames = dir.list(); // 파일 이름 배열로 저장
	static private SortData[] sortdata = new SortData[fileNames.length]; // 파일 수만큼 객체 배열 생성
	static String match = "[^a-zA-Z0-9\\s]"; //	수 문자 제거를 위한 조건

	public static void main(String[] args) {
		//findWordInFile();
		addSortData();
		//findWordInObject();
		System.out.println(sortdata[0]);
	}
	/*
	static void test() {
		InputReader ir = null;
		BufferedReader br = null;
		
		try {
			ir = 
		}
	}*/

	static void addSortData() {
		String fileText = ""; // 파일 줄 내용
		int fileLine = 0; // 몇 번째 줄
		try {
			for(int i = 0; i < fileNames.length; i++) {
				HashMap<Integer, List<String>> lineAndWord = new HashMap<Integer, List<String>>();
				String path =  dir + "\\" + fileNames[i]; // i번째 파일 이름
				BufferedReader br = new BufferedReader(new FileReader(path));//
				for(int j = 1;((fileText = br.readLine()) != null); j++) {
					fileLine++;
					String[] replaceText = fileText.replaceAll(match, "").split(" "); // 텍스트를 공백 기준으로 나눔
					ArrayList<String> deleteNullList = new ArrayList<String>();// null 값 없애기 위해 list에 저장
					for(String s : replaceText) {
						if(s != null && s.length() > 0) {
							deleteNullList.add(s);
						}
					}
					//정제된 list를 sortdata에 저장
					lineAndWord.put(fileLine, deleteNullList);
				} // for end
				sortdata[i] = new SortData(lineAndWord, fileNames[i]);
				fileLine = 0;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static void findWordInObject() {
		Scanner sc = new Scanner(System.in);
		System.out.println("단어 검색 프로그램입니다. 찾을 단어를 입력해주세요.");
		int count = 0;

		while(true){ // 무한 반복
			String input = sc.nextLine();  
			long beforeTime = System.currentTimeMillis(); // 코드 실행 수 시간
			if(input.equals("quit")){ // quit 입력 시 종료
				System.out.println("종료합니다");
				break;
			}
			
			// 단어 검색을 위한 반복문
			
			//length을 위한 변수 만들기
			for(int i = 0; i < sortdata.length; i++) { // i번째 파일
				HashMap<Integer, List<String>> commonLineandWord = sortdata[i].getLineAndWord();
				for(int j = 1; j <= commonLineandWord.size(); j++) { // j번째 줄
					if(commonLineandWord.get(j).indexOf(input) != -1) { // j번째 줄에 input이 포함되어 있을 때만 실행

						for(int k = 0; k < commonLineandWord.get(j).size(); k++) { //k 번째 단어
							if(commonLineandWord.get(j).get(k).equals(input)) { //k 번째 단어 == input이면

								String commonResult = j + "번째 줄 " + input + " / "; // 결과 공통 부분 변수화
								
								if(commonLineandWord.get(j).size() - 1 == k) { //문장 마지막 단어일 시 이전 단어 출력
									if(commonLineandWord.get(j).size() == 1) { // 1 줄에 단어 하나만 있을 시 이전 줄 마지막 단어 출력
										int countBackLine = 1;// 이전 문장 count
										if(commonLineandWord.get(j - 1).size() == 0) { // 이전 문장에 공백만 있을 시 이전 문장 마지막 단어 출력
											while (countBackLine < j && commonLineandWord.get(j -  countBackLine).size() < 1 ) { // 공백 아닌 문장으로 가기 위한 while문
												countBackLine++;
											}
											if(countBackLine == j) {
												System.out.println(commonResult);
												count++;
												countBackLine = 1;
											}else {
												System.out.println(commonResult + commonLineandWord.get(j - countBackLine) 
												.get(commonLineandWord.get(j - countBackLine).size() - 1) + "(앞 단어)"); //이전 문장 마지막 단어 출력
												count++;
												countBackLine = 1;
											}
										} else { // 이전 단어와 함께 출력
											System.out.println(commonResult + commonLineandWord.get(j - 1).
													get(commonLineandWord.get(j - 1).size() - 1) + "(앞 단어)"); // 이전 단어 출력
											count++;
										}
									} 
								} else { 
									System.out.println(commonResult + sortdata[i].getLineAndWord().get(j).get(k + 1) + "(뒤 단어)"); // 다음 단어와 출력
									count++;
								}
							}
						} //k번째 단어 for end
					} 
				} // j번째 줄 for end
				if(count > 0) {
					System.out.println();
					System.out.println(sortdata[i].getFileNames() + " / " + count + "개");
					System.out.println();
				}
				count = 0;
			} // i번째 파일 for end
			long afterTime = System.currentTimeMillis(); // 코드 종료 후 시간
			System.out.println();
			System.out.println("findWordInObject 수행시간: " + (afterTime - beforeTime) / 1000.0 + "초");
		} // while end
	}

	static void findWordInFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("단어 검색 프로그램입니다. 찾을 단어를 입력해주세요.");

		String fileText = ""; // 파일 줄 내용
		ArrayList<String> fileConetents = new ArrayList<>(); // 파일 전체 내용(이전 줄 결과 출력 위해)
		int fileLine = 0; // 몇 번째 줄
		int count = 0; //단어 개수

		while(true){ // 무한 반복
			String input = sc.nextLine();  
			long beforeTime = System.currentTimeMillis(); //코드 실행 전 시간
			if(input.equals("quit")){ // quit 입력 시 종료
				System.out.println("종료합니다");
				break;
			}
			try {
				for(int i = 0; i < fileNames.length; i++) {
					String path =  dir + "\\" + fileNames[i]; // i번째 파일 이름
					BufferedReader br = new BufferedReader(new FileReader(path));

					for(int j = 1;((fileText = br.readLine()) != null); j++) {
						fileLine++;
						String[] replaceText = fileText.replaceAll(match, "").split(" "); // 텍스트를 공백 기준으로 나눔
						ArrayList<String> deleteNullList = new ArrayList<String>();// null 값 없애기 위해 list에 저장
						for(String s : replaceText) {
							if(s != null && s.length() > 0) {
								deleteNullList.add(s);
								fileConetents.add(s);
							}
						}
						
						if((deleteNullList.indexOf(input) != -1)){ // 만약 문장에 input이 포함되어 있을 때 반복문 실행
							for(int k = 0; k < deleteNullList.size(); k++){
								if(input.equals(deleteNullList.get(k))) {  // 만약 input과 같으면 다음 단어 출력
									String commonResult = fileLine + "번째 줄 " + input + " / "; // 공통 부분 변수화

									if(k == deleteNullList.size() - 1) { // 문장 마지막 단어일 시 이전 단어 출력
										if(deleteNullList.size() <= 1) { // 하나만 있는 문장일 경우 이전 줄 마지막 단어 출력
											System.out.println(commonResult + fileConetents.get(fileConetents.size() - deleteNullList.size() - 1) + "(앞 단어)");
											count++;
										} else {
											System.out.println(commonResult + deleteNullList.get(deleteNullList.size() - 2) + "(앞 단어)"); 
											count++;
										}
									}else {
										System.out.println(commonResult + deleteNullList.get(k + 1)+ "(뒤 단어)");
										count++;
									}
								}
							}
						}
					} // for end
					if(count >= 1) { // 단어 1개 이상일 때만 출력
						System.out.println();
						System.out.println(fileNames[i] + " " + count + "개");
						System.out.println();
					}
					count = 0;
					fileLine = 0;
					fileConetents.clear();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			long afterTime = System.currentTimeMillis(); // 코드 종료 후 시간
			System.out.println();
			System.out.println("findWordInFile 수행시간: " + (afterTime - beforeTime) / 1000.0 + "초");
		}
	}
}



	
