package Test1;

import java.util.Scanner;
//input +�� -�� �����ؼ� �迭�� ��� -> 2�ڸ� �̻� ���ڵ� �����ϱ� ���� 
		// inputArray �� ��
		//-> ���� ���� ���ڰ� -�� result ���� ���� - // -> result ���
		//���� : �Է°� + result
		// ����ó�� �ؾ� �ϴ� ��� 
		// 1. �Ǽ� �Է� ��
		// 2. ���� �Է� ��
		// 3. ���� �Է� ��
		// 4. +, - �� �ٸ� ��ȣ �Է� ��

public class question_1 {

	public static void main(String[] args) {
		Scanner sc; 
		sc = new Scanner(System.in);

		System.out.println("+, - ���� ���α׷��Դϴ�.");
		System.out.println("quit�� �Է½� ���α׷��� ����˴ϴ�.");
		
		while(true){ // ���� �ݺ�
			
			try {
				String input = sc.nextLine(); 
				if(input.equals("quit")){ // ���� quit �Է� �� ����
					System.out.println("�����մϴ�");
					break;
			}
			
			String inputTrim = input.replace(" ", ""); // input ���� ����
			String[] inputArray = inputTrim.split("\\+|#|\\-"); // +, -�� �������� ���� �ڸ���
															//ex 1 + 3 - 4 + 4 -> {1, 3, 4, 4}
			int result = Integer.parseInt(inputArray[0]); // result �ʱⰪ�� ù ��° inputArray�� ù��° ��
			int count = 1; // �迭 ���� ����Ű�� ���� count
			
			for(int i = 1; i < inputTrim.length(); i++) { 
				if(inputTrim.charAt(i) == '+') { //i��° �ڸ��� + �̸� result���� ���ϰ�
					result += Integer.parseInt(inputArray[count]);
					count++;
				} else if(inputTrim.charAt(i) == '-') { //i��° �ڸ��� - �̸� result���� ���� 
					result -= Integer.parseInt(inputArray[count]); 
					count++;
				}
			}
				System.out.println(input + " = " + result); // ������ �Է��� ���� ������ ���
			} catch (Exception e) {
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���");
			} 	
		}
	}
}
