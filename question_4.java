package Test1;

import java.util.Scanner;

public class question_4 {

	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);

		System.out.println("���� ���� ã�� ���α׷��Դϴ�. �Ʒ� ���忡�� ã�� ���� �ܾ �Է����ּ���.");
		System.out.println("quit�� �Է½� ���α׷��� ����˴ϴ�.");
		String data = "The WYCA of Indianapolis still focuses on, supports, and gives empowerment to women and their families.";
		System.out.println(data);
		int result = -1; // �⺻�� ����
		
		while(true){ // ���� �ݺ�
				String input = sc.nextLine();
				if(input.equals("quit")){ // quit �Է� �� ����
					System.out.println("�����մϴ�");
					break;
				} 
				//data�� input �� �ڿ������� ��
				Loop1 :
				for(int i = data.length() - 1; i >= 0; i--) {
					Loop2 :
					for(int j = input.length() - 1; j >= 0;j--) {
						int dataChar = data.charAt(i);//  �ڿ��� i��° ex�� ��
						int inputChar = input.charAt(j); // �ڿ��� j��° input�� ��
						
						if(65 <= dataChar && dataChar <= 90) { // �빮�ڸ� �ҹ��ڷ�
							dataChar += 32; 
						}
						if(65 <= inputChar && inputChar <= 90) { // �빮�ڸ� �ҹ��ڷ�
							inputChar += 32;
						}
						
						if(dataChar == inputChar) { // ���� A�� B�� ���ٸ� ���� ���� ��.
							i--;
						} else { // �ƴϸ� �ٽ� ��
							break Loop2; 
						}
						if(j == 0) { //������ ���ڱ��� ������ �� �ݺ��� Ż��.
							result = i + 1; //�ڿ������Ͱ� �ƴ� �տ��������� �ε����� ����ؾ� ������
							break Loop1;
						}
					}
				}
				System.out.println("���) " + result);
				System.out.println("lastIndexOf ��:" + data.lastIndexOf(input));
				result = -1; // result �ʱ�ȭ	
		}
	}
}
