package Test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class question_5 {
	public static void main(String[] args) {
		System.out.println("�ܾ� ã�� ���α׷��Դϴ�. ã�� ���� �ܾ �Է����ּ���.");
		System.out.println("quit�� �Է½� ���α׷��� ����˴ϴ�.");
		try{
			//���� ��ü ����
			File file = new File("C:\\Users\\user\\Desktop\\102CTL001.txt");
			//�Է� ��Ʈ�� ����
			FileReader filereader = new FileReader(file);
			int singleCh = 0;
			
			StringBuffer data = new StringBuffer();
			while((singleCh = filereader.read()) != -1){ // read()�� ���� ȣ���� ��ȯ���� singleCH�� ����
				data.append((char)singleCh); // �� �̻� �ҷ��� �����Ͱ� ������ -1 ��ȯ -> ������ ���� data�� ����
			}
			filereader.close();
			/*------------------------------------------------------------*/
			Scanner sc =  new Scanner(System.in);
			int count = 0; // ���� �ʱ�ȭ 
			System.out.println(data);
			while(true){
				String input = sc.nextLine(); // �Է� ��
				if(input.equals("quit")){ // quit �Է� �� ����
					System.out.println("�����մϴ�");
					break;
				} 

				for(int i = 0; i < data.length(); i++) {
					for(int j = 0; j < input.length();) {
						int dataChar = data.charAt(i);//  i��° data�� ��
						int inputChar = input.charAt(j); // j��° input�� ��

						if(j == input.length() - 1) { // ���� ������ ������ ��
							if(dataChar == inputChar) {				  // A�� B�� ���ٸ� ���� �ܾ�� ���
								count++;
							}
						}
						if(dataChar == inputChar ) { // ���� i��° data�� ����, j��° input�� ���� ������ ���� ���� ��
							j++;
							i++;
						} else { // �ٸ� �� j�� �ٽ� ó������
							j = 0;
							break;
						}
					}
				}
				System.out.println("�Է��Ͻ� �ܾ�� " + input + "�Դϴ�.");
				System.out.println("�Է��Ͻ� �ܾ�� " + count + "�� �ֽ��ϴ�.");
			}
		}catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���");
		} 	
	}
}
