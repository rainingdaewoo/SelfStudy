package Test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class question_2 {
		public static void main(String[] args) {
			Scanner sc =  new Scanner(System.in);

			System.out.println("���� �迭 ����");
			System.out.println("30 5 �� ���� �������� �Է����ּ���.");
			System.out.println("quit�� �Է½� ���α׷��� ����˴ϴ�.");

			while(true){ // ���� �ݺ�
				try {	             
					String input = sc.nextLine();  
					if(input.equals("quit")){ // quit �Է� �� ����
						System.out.println("�����մϴ�");
						break;
					}
					
				String[] arrayInput = input.split("\\s"); // ���� ���� ������
				int arrayLength = Integer.parseInt(arrayInput[0]); // N���� �迭(�տ� �Էµ� ��)
				int howMany = Integer.parseInt(arrayInput[1]); // K��° ū ��(�ڿ� �Էµ� ��)

				Integer[] arrays = new Integer[arrayLength];
				
				for(int i = 0; i < arrayLength; i++) { // ���� ����
					arrays[i] = (int)(Math.random() * 50) + 1;
				} 
				System.out.println("���� ��");
				for(int i = 0; i < arrayLength; ) { // �� �ٿ� 10���� ���
					System.out.print("N[" + arrays[i] + "] ");
					i++;
					if(i % 10 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				//
				Arrays.sort(arrays, Collections.reverseOrder()); // ������������ ��ȯ
				System.out.println("���� ��");
				for(int i = 0; i < arrayLength; ) { // �� �ٿ� 10���� ���
					System.out.print("N[" + arrays[i] + "] ");
					i++;
					if(i % 10 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				System.out.println("���: " + arrays[howMany - 1]); // K��° ū �� ���
				} catch (Exception e) {
					System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���");
				} 	
			}

		}
}
