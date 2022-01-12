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

	static File dir = new File("C:\\\\Users\\\\user\\\\Desktop\\\\doc_set_tokenized"); // ���� ���
	static String[] fileNames = dir.list(); // ���� �̸� �迭�� ����
	static private SortData[] sortdata = new SortData[fileNames.length]; // ���� ����ŭ ��ü �迭 ����
	static String match = "[^a-zA-Z0-9\\s]"; //	�� ���� ���Ÿ� ���� ����

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
		String fileText = ""; // ���� �� ����
		int fileLine = 0; // �� ��° ��
		try {
			for(int i = 0; i < fileNames.length; i++) {
				HashMap<Integer, List<String>> lineAndWord = new HashMap<Integer, List<String>>();
				String path =  dir + "\\" + fileNames[i]; // i��° ���� �̸�
				BufferedReader br = new BufferedReader(new FileReader(path));//
				for(int j = 1;((fileText = br.readLine()) != null); j++) {
					fileLine++;
					String[] replaceText = fileText.replaceAll(match, "").split(" "); // �ؽ�Ʈ�� ���� �������� ����
					ArrayList<String> deleteNullList = new ArrayList<String>();// null �� ���ֱ� ���� list�� ����
					for(String s : replaceText) {
						if(s != null && s.length() > 0) {
							deleteNullList.add(s);
						}
					}
					//������ list�� sortdata�� ����
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
		System.out.println("�ܾ� �˻� ���α׷��Դϴ�. ã�� �ܾ �Է����ּ���.");
		int count = 0;

		while(true){ // ���� �ݺ�
			String input = sc.nextLine();  
			long beforeTime = System.currentTimeMillis(); // �ڵ� ���� �� �ð�
			if(input.equals("quit")){ // quit �Է� �� ����
				System.out.println("�����մϴ�");
				break;
			}
			
			// �ܾ� �˻��� ���� �ݺ���
			
			//length�� ���� ���� �����
			for(int i = 0; i < sortdata.length; i++) { // i��° ����
				HashMap<Integer, List<String>> commonLineandWord = sortdata[i].getLineAndWord();
				for(int j = 1; j <= commonLineandWord.size(); j++) { // j��° ��
					if(commonLineandWord.get(j).indexOf(input) != -1) { // j��° �ٿ� input�� ���ԵǾ� ���� ���� ����

						for(int k = 0; k < commonLineandWord.get(j).size(); k++) { //k ��° �ܾ�
							if(commonLineandWord.get(j).get(k).equals(input)) { //k ��° �ܾ� == input�̸�

								String commonResult = j + "��° �� " + input + " / "; // ��� ���� �κ� ����ȭ
								
								if(commonLineandWord.get(j).size() - 1 == k) { //���� ������ �ܾ��� �� ���� �ܾ� ���
									if(commonLineandWord.get(j).size() == 1) { // 1 �ٿ� �ܾ� �ϳ��� ���� �� ���� �� ������ �ܾ� ���
										int countBackLine = 1;// ���� ���� count
										if(commonLineandWord.get(j - 1).size() == 0) { // ���� ���忡 ���鸸 ���� �� ���� ���� ������ �ܾ� ���
											while (countBackLine < j && commonLineandWord.get(j -  countBackLine).size() < 1 ) { // ���� �ƴ� �������� ���� ���� while��
												countBackLine++;
											}
											if(countBackLine == j) {
												System.out.println(commonResult);
												count++;
												countBackLine = 1;
											}else {
												System.out.println(commonResult + commonLineandWord.get(j - countBackLine) 
												.get(commonLineandWord.get(j - countBackLine).size() - 1) + "(�� �ܾ�)"); //���� ���� ������ �ܾ� ���
												count++;
												countBackLine = 1;
											}
										} else { // ���� �ܾ�� �Բ� ���
											System.out.println(commonResult + commonLineandWord.get(j - 1).
													get(commonLineandWord.get(j - 1).size() - 1) + "(�� �ܾ�)"); // ���� �ܾ� ���
											count++;
										}
									} 
								} else { 
									System.out.println(commonResult + sortdata[i].getLineAndWord().get(j).get(k + 1) + "(�� �ܾ�)"); // ���� �ܾ�� ���
									count++;
								}
							}
						} //k��° �ܾ� for end
					} 
				} // j��° �� for end
				if(count > 0) {
					System.out.println();
					System.out.println(sortdata[i].getFileNames() + " / " + count + "��");
					System.out.println();
				}
				count = 0;
			} // i��° ���� for end
			long afterTime = System.currentTimeMillis(); // �ڵ� ���� �� �ð�
			System.out.println();
			System.out.println("findWordInObject ����ð�: " + (afterTime - beforeTime) / 1000.0 + "��");
		} // while end
	}

	static void findWordInFile() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�ܾ� �˻� ���α׷��Դϴ�. ã�� �ܾ �Է����ּ���.");

		String fileText = ""; // ���� �� ����
		ArrayList<String> fileConetents = new ArrayList<>(); // ���� ��ü ����(���� �� ��� ��� ����)
		int fileLine = 0; // �� ��° ��
		int count = 0; //�ܾ� ����

		while(true){ // ���� �ݺ�
			String input = sc.nextLine();  
			long beforeTime = System.currentTimeMillis(); //�ڵ� ���� �� �ð�
			if(input.equals("quit")){ // quit �Է� �� ����
				System.out.println("�����մϴ�");
				break;
			}
			try {
				for(int i = 0; i < fileNames.length; i++) {
					String path =  dir + "\\" + fileNames[i]; // i��° ���� �̸�
					BufferedReader br = new BufferedReader(new FileReader(path));

					for(int j = 1;((fileText = br.readLine()) != null); j++) {
						fileLine++;
						String[] replaceText = fileText.replaceAll(match, "").split(" "); // �ؽ�Ʈ�� ���� �������� ����
						ArrayList<String> deleteNullList = new ArrayList<String>();// null �� ���ֱ� ���� list�� ����
						for(String s : replaceText) {
							if(s != null && s.length() > 0) {
								deleteNullList.add(s);
								fileConetents.add(s);
							}
						}
						
						if((deleteNullList.indexOf(input) != -1)){ // ���� ���忡 input�� ���ԵǾ� ���� �� �ݺ��� ����
							for(int k = 0; k < deleteNullList.size(); k++){
								if(input.equals(deleteNullList.get(k))) {  // ���� input�� ������ ���� �ܾ� ���
									String commonResult = fileLine + "��° �� " + input + " / "; // ���� �κ� ����ȭ

									if(k == deleteNullList.size() - 1) { // ���� ������ �ܾ��� �� ���� �ܾ� ���
										if(deleteNullList.size() <= 1) { // �ϳ��� �ִ� ������ ��� ���� �� ������ �ܾ� ���
											System.out.println(commonResult + fileConetents.get(fileConetents.size() - deleteNullList.size() - 1) + "(�� �ܾ�)");
											count++;
										} else {
											System.out.println(commonResult + deleteNullList.get(deleteNullList.size() - 2) + "(�� �ܾ�)"); 
											count++;
										}
									}else {
										System.out.println(commonResult + deleteNullList.get(k + 1)+ "(�� �ܾ�)");
										count++;
									}
								}
							}
						}
					} // for end
					if(count >= 1) { // �ܾ� 1�� �̻��� ���� ���
						System.out.println();
						System.out.println(fileNames[i] + " " + count + "��");
						System.out.println();
					}
					count = 0;
					fileLine = 0;
					fileConetents.clear();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			long afterTime = System.currentTimeMillis(); // �ڵ� ���� �� �ð�
			System.out.println();
			System.out.println("findWordInFile ����ð�: " + (afterTime - beforeTime) / 1000.0 + "��");
		}
	}
}



	