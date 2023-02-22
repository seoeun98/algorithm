package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PGM_다단계칫솔판매 {
	public static void main(String[] args) {
		solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
				new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
				new String[] {"young", "john", "tod", "emily", "mary"}, 
				new int[] {12, 4, 2, 5, 10});
		
		
	}
	
	/*
	 * 배열 두 개를 놓는다.
	 * 1. 내가 직접 판매해서 얻은 수익
	 * 2. 밑단계로부터 들어오는 수익
	 * 
	 * HashMap을 이용하여 사용자 이름과 인덱스를 매칭시켜 이름으로 인덱스 찾을 수 있게 한다. 이 때 "-"와 0을 미리 매칭시켜 끝 점을 미리 만들어놓는다.
	 * int 배열을 하나 선언하여(parent배열) 나를 끌어들인(내 부모) 사람의 인덱스를 저장한다.
	 * 
	 * for문 돌면서 물건을 팔아서 번 이득을 계산하면서 부모에게 일부를 전달한다. 이때 부모에게 줄 수 있는 돈은 무조건! 1원 이상이기에 검사할 필요도 없다
	 * -> 처음에는 100원씩 10번이라면 1000원으로 한 번에 계산했는데 이러면 안됨. 돈이 들어올 때마다 계산해줘야 함.
	 * 
	 * 일부를 보내고 난 뒤에 남은 돈(내 몫)을 1번(내가 직접 판매해서 얻은 수익) 배열에 저장한다.
	 * 
	 * -부모에게 일부 전달-
	 * 받은 돈의 10퍼센트는 또 내 부모에게 보내고(재귀 이용, parent 배열 이용해서 타고타고 올라감) 남은 돈을 2번(밑단계로부터 들어오는 수익) 배열에 넣는다.
	 * 이때는 부모에게 넘길 수 있는 돈이 1원 미만이 될 때도 있으므로 조건 걸어서 검사해줘야 한다.
	 * 
	 * 마지막으로 for문 돌면서 1번 배열과 2번 배열의 값을 더해 답 return
	 */
	
	
	static int[] parent;
	static HashMap<String, Integer> map;
	static int[] earnFromSale; //물건 판매 수익
	static int[] earnFromChild; //밑단계로부터 나오는 수익
	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {		
        map = new HashMap<>(); //해당 이름의 index가 몇 번인지
        parent = new int[enroll.length + 1]; //나를 끌어들인 사람의 번호는 몇 번인지
        
        map.put("-", 0);
        for(int i = 0; i < enroll.length; i++) {
        	map.put(enroll[i], i + 1);
        }
        
        ArrayList<String>[] child = new ArrayList[enroll.length + 1]; //내가 끌어들인 사람들

        for(int i = 0; i < referral.length + 1; i++) {
        	child[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < referral.length; i++) {
        	int parentI = map.get(referral[i]);
        	
        	child[parentI].add(enroll[i]);
        	parent[i + 1] = parentI;
        }
        
        //물건 판매로 낸 수익
        earnFromSale = new int[enroll.length + 1];
        earnFromChild = new int[enroll.length + 1];
        for(int i = 0; i < amount.length; i++) {
        	int sellerIndex = map.get(seller[i]);
        	
        	int earnMoney = amount[i] * 100;
        	
        	if(parent[sellerIndex] != 0) { //부모가 존재한다면
        		giveToParent(parent[sellerIndex], (int)Math.floor(earnMoney * 0.1));
        	}
        	
        	earnFromSale[sellerIndex] += earnMoney - Math.floor(earnMoney * 0.1);
        	
//        	earnFromSale[sellerIndex] += amount[i] * 100;
        }
        
//        dfs(child, 0);

//        int[] answer = Arrays.copyOfRange(earn, 1, enroll.length + 1);
        int[] answer = new int[enroll.length];
        for(int i = 1; i <= enroll.length; i++) {
        	answer[i - 1] = earnFromChild[i] + earnFromSale[i];
        }
        
        return answer;
    }
	
	public static void giveToParent(int me, int money) {
		if(parent[me] != 0 && money * 0.1 >= 1) {
			giveToParent(parent[me], (int)Math.floor(money * 0.1));
		}
		
		int moneyFromChild = money - (int)Math.floor(money * 0.1);
		earnFromChild[me] += moneyFromChild;
	}
	
//	public static void dfs(ArrayList<String>[] child, int me) {
//		if(child[me].size() != 0) { //자식이 있다면
//			for(String name : child[me]) {
//				dfs(child, map.get(name));
//			}
//		}
//	
//		//부모에게 수익 전달
//		int myEarn = earn[me];
//		
//		if(myEarn * 0.1 >= 1) {
//			if(parent[me][0] != 0) {
//				earn[parent[me][0]] += myEarn * 0.1;
//			}
//			earn[me] -= Math.floor(myEarn * 0.1);
//		}
//	}
}
