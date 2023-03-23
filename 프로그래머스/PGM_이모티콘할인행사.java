package work;

import java.util.*;

public class PGM_이모티콘할인행사 {

	public static void main(String[] args) {
		int[] answer = solution(new int[][] {
			{40, 10000},
			{25, 10000},
		}, new int[] {7000, 9000});
		
		
		System.out.println(answer[0] + " " + answer[1]);
	}
	
	static class PlusOrEarn {
		int n;
		int earn;
	}

	static ArrayList<PlusOrEarn> plusOrEarn;
    public static int[] solution(int[][] users, int[] emoticons) {
    	plusOrEarn = new ArrayList<>();
        
    	중복순열(0, emoticons.length, new int[users.length], users, emoticons);
    	
    	plusOrEarn.sort(new Comparator<PlusOrEarn>() {
			@Override
			public int compare(PlusOrEarn o1, PlusOrEarn o2) {
				if(o1.n == o2.n) {
					return o2.earn - o1.earn;
				} return o2.n - o1.n;
			}
		});
    	
    	if(plusOrEarn.isEmpty()) {
    		PlusOrEarn tmp = new PlusOrEarn();
    		tmp.earn = 0;
    		tmp.n = 0;
    		plusOrEarn.add(tmp);
    	}
    	
        return new int[] {plusOrEarn.get(0).n, plusOrEarn.get(0).earn};
    }
    
    public static void 중복순열(int index, int length, int[] discount, int[][] users, int[] emoticons) {
    	int[] discountList = {10, 20, 30, 40}; //할인률
    	
    	if(index == length) {
    		//만들어진 리스트로 이모티콘 플러스 가입자와 판매 이득을 List에 넣는다.
    		
    		PlusOrEarn poe = 계산(discount, users, emoticons);
    		
    		if(poe.earn != 0 || poe.n != 0) {
    			plusOrEarn.add(poe);
    		}
    		
    		return;
    	}
    	
    	for(int i = 0; i < 4; i++) {
    		discount[index] = discountList[i];
    		
    		중복순열(index + 1, length, discount, users, emoticons);
    	}
    }
    
    public static PlusOrEarn 계산(int[] discount, int[][] users, int[] emoticons) {
    	PlusOrEarn poe = new PlusOrEarn();
    	
    	a: for(int i = 0; i < users.length; i++) {
    		int buyTotalMoney = 0; //유저가 현재까지 구입한 이모티콘들의 가격 총합
    		int userNeedDis = users[i][0]; //이정도 비율은 되어야 산다
    		int userMaxMoney = users[i][1]; //이거 넘으면 이모티콘 플러스 가입한다
        	
        	for(int j = 0; j < discount.length; j++) {
        		if(userNeedDis > discount[j]) { //유저가 원한만큼 세일 안해? 안사
        			continue;
        		}
        		
        		buyTotalMoney += emoticons[j] / 100 * (100 - discount[j]);
        		
    			if(buyTotalMoney >= userMaxMoney) {
    				poe.n++;
    				continue a;
    			}
        	}

    		
    		poe.earn += buyTotalMoney;
        }
    	
    	return poe;
    }
}
