package Test1;

import java.util.Scanner;

public class question_3 {

	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);

		System.out.println("����� ���α׷��Դϴ�. 1 ~ 100 ������ Ȧ���� �Է����ּ���.");
		System.out.println("quit�� �Է½� ���α׷��� ����˴ϴ�.");

		while(true){ // ���� �ݺ�
			try {	
				String input = sc.nextLine();
				if(input.equals("quit")){ // quit �Է� �� ����
					System.out.println("�����մϴ�");
					break;
				}
				
				int line = Integer.parseInt(input); // input �� line(int)�� ��ȯ
				
				while( 0 > line || 100 < line || line % 2 == 0) {
					System.out.println("�Է°��� 1 ~ 100 ������ Ȧ������ �մϴ�. �ٽ� �Է����ּ���.");
					line = Integer.parseInt(sc.nextLine()); // �ٽ� line �� �Է�
				}
					
				// �ﰢ�� ���(ó������ �߰�����)
				for(int i = 0; i < line; i++) { //���̰� line �� �ﰢ�� ����
					for (int j = 0; j < line - i - 1; j++) { // ���� ���� j�� ��� 
						System.out.print("  ");
					}
					for (int j = 0; j < i * 2 + 1; j++) { //�� ���
						if(i == line - 1 && j == i * 2) { // ���� �� ��� �� �� ������ ���� ��
							System.out.print("*"); 		// ���� ���
						} else {
						System.out.print("* ");
						}
					}
					System.out.println(); // ���� �ٷ� �̵�
				}
				
				// ���ﰢ�� ���(�߰� �Ʒ����� ������)
				for (int i = line - 1; i > 0; i--) { // ���̰� line - 1 �� ���ﰢ�� ����
					for (int j = line - i; j > 0; j--) {
						System.out.print("  ");
					}
					for (int j = i * 2 - 1; j > 0; j--) {
						System.out.print("* ");
					}
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���");
			} 	
		}
	}
}
