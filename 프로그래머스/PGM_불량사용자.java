package work;

import java.util.Arrays;
import java.util.HashSet;

public class PGM_불량사용자 {

	public static void main(String[] args) {
		solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"});
	}
	
	static boolean[] check;
	static HashSet<String> answerList = new HashSet<>();
	public static int solution(String[] user_id, String[] banned_id) {
        int banN = banned_id.length; //밴 된 아이디의 개수
        check = new boolean[user_id.length];
        dfs(user_id, banned_id, new String[banN], 0);
        
        return answerList.size();
    }
	
	static void dfs(String[] user_id, String[] banned_id, String[] list, int count) {
		if(count == banned_id.length) {
			if(check(list, banned_id)) {
				
				/*
				 *  HashSet<String> temp = new HashSet<>(hs);
            	    answer.add(temp);
            	    은 되는데
            	    
            	    String[] tmp = Arrays.copyOfRange(list, 0, list.length);
            	    Arrays.sort(tmp);
            	    answerList.add(tmp);
            	    는 중복으로 들어간다...왜지?
				 * 
				 */
				
				String[] tmp = Arrays.copyOfRange(list, 0, list.length);
				Arrays.sort(tmp);
				String ids = "";
				for(int i = 0; i < list.length; i++) {
					ids += tmp[i] + ", ";
				}
				
				answerList.add(ids);
			}
			
			return;
		}
		
		for(int i = 0; i < user_id.length; i++) {
			if(!check[i]) {
				list[count] = user_id[i];
				check[i] = true;
				dfs(user_id, banned_id, list, count + 1);
				check[i] = false;
			}
				
		}
	}
	
	static boolean check(String[] ids, String[] banned_id) {
		
		for(int i = 0; i < ids.length; i++) {
			char[] banId = banned_id[i].toCharArray();
			char[] userId = ids[i].toCharArray();
			
			if(banId.length == userId.length) {
				for(int j = 0; j < banId.length; j++) {
					if(banId[j] != userId[j] && banId[j] != '*') {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		
		return true;
	}
}



/*
 * 
 * package work;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class PGM_비밀지도 {
	static HashSet<HashSet<String>> answer;
	
	public static void main(String[] args) {		
		System.out.println(solution(new String[] {"abc123", "frodo", "fradi", "crodo", "frodoc"}, new String[] {"fr*d*", "abc1**"}));
	}
	
    public static int solution(String[] user_id, String[] banned_id) {
        answer = new HashSet<>();

        dfs(new LinkedHashSet<>(), user_id, banned_id);

        return answer.size();
    }
    
    *
     * 디버깅을 돌려보니.. 분명히 hs는 [frodo, crodo, abc123]인데 answer에는 [crodo, abc123, frodo]로 들어갔다.
     * HashSet은 정렬을 지원하지 않는다고 알고 있는데 이게 어찌된 일일까 해서 찾아보니,
     * 정렬을 하는 것은 아니지만 HashSet 내부에서 데이터를 쌓고 정리하는 과정이 따로 있는 듯 했다.
     * hs는 LinkedHashSet이라서 순서가 지켜지기에 넣는대로 데이터가 쌓이지만, answer에 집어넣는 과정(33번 줄)에서 
     * hs를 이용해 새로운 HashSet 객체를 생성하는데, 이 때 hs에 있던 데이터가 아까 말했던 내부 과정을 거쳐 지멋대로 정렬됐던 것이다.
     * 그런데 같은 데이터는 같은 순서로 정렬될 것이고(ex: [frodo, crodo, abc123] 과 [crodo, frodo, abc123]는 내부 과정에 의해 같은 순서로 정렬된다)
     * 그래서 36번줄 add에서 중복 데이터로 처리될 수 있는 것이다.
     * 



    private static void dfs(HashSet<String> hs, String[] user_id, String[] banned_id) {
        if (hs.size() == banned_id.length) {
            if (isBanList(hs, banned_id)) {
            	HashSet<String> temp = new HashSet<>(hs);
//                answer.add(new HashSet<>(hs));
            	answer.add(temp);
            }
            return;
        }

        for (String userId : user_id) {
            if (hs.add(userId)) {
                dfs(hs, user_id, banned_id);
                hs.remove(userId);
            }
        }
    }


    private static boolean isBanList(HashSet<String> hs, String[] banned_id) {
        int idx = 0;
        for (String userID : hs) {
            String banID = banned_id[idx++];
            if (userID.length() != banID.length()) {
                return false;
            }
            for (int i = 0; i < banID.length(); i++) {
                if (banID.charAt(i) == '*') {
                    continue;
                }
                if (userID.charAt(i) != banID.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

}

     */