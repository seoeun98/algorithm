package work;

import java.util.HashMap;
import java.util.Scanner;

public class BJ_2632_피자판매 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int order = input.nextInt();
		int firstP = input.nextInt();
		int secondP = input.nextInt();
		
		int[] fp = new int[firstP];
		int[] sp = new int[secondP];
		
		HashMap<Integer, Integer> firstPizza = new HashMap<>();
		HashMap<Integer, Integer> secondPizza = new HashMap<>();

		int AllPizza = 0;
		for(int i = 0; i < firstP; i++) {
			fp[i] = input.nextInt();
			AllPizza += fp[i];
		}
		
		if(AllPizza <= order) {
			firstPizza.put(AllPizza, 1);
		}
		
		AllPizza = 0;
		for(int i = 0; i < secondP; i++) {
			sp[i] = input.nextInt();
			AllPizza += sp[i];
		}
		
		if(AllPizza <= order) {
			secondPizza.put(AllPizza, 1);
		}
		
		int answer = 0;
		
		for(int start = 0; start < firstP; start++) { //탐색 시작 위치
			int pizza = 0;
			
			for(int j = 0; j < firstP - 1; j++) { //시작 위치부터 몇개까지 볼 건지
				int index = start + j;
				
				if(index >= firstP) {
					index -= firstP;
				}
				
				pizza += fp[index];
				
				if(pizza > order) {
					break;
				} else if(pizza == order) {
					answer++;
					break;
				}
				
				if(firstPizza.containsKey(pizza)) {
					firstPizza.put(pizza, firstPizza.get(pizza) + 1);
				} else {
					firstPizza.put(pizza, 1);
				}
			}
		}
		
		for(int start = 0; start < secondP; start++) {
			int pizza = 0;
			
			for(int j = 0; j < secondP - 1; j++) { //시작 위치부터 몇개까지 볼 건지
				int index = start + j;
				
				if(index >= secondP) {
					index -= secondP;
				}
				
				pizza += sp[index];
				
				if(pizza > order) {
					break;
				} else if(pizza == order) {
					answer++;
					break;
				}
				
				if(secondPizza.containsKey(pizza)) {
					secondPizza.put(pizza, secondPizza.get(pizza) + 1);
				} else {
					secondPizza.put(pizza, 1);
				}
			}
		}
		
		for(int i = 1; i < order; i++) {
			
			if(firstPizza.containsKey(i) && secondPizza.containsKey(order - i)) {
				answer += firstPizza.get(i) * secondPizza.get(order - i);
			}
		}
		
		if(firstPizza.containsKey(order)) {
			answer += firstPizza.get(order);
		}
		
		if(secondPizza.containsKey(order)) {
			answer += secondPizza.get(order);
		}
		
		System.out.println(answer);
	}

}
