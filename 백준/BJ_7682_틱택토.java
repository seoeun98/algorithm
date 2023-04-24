package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BJ_7682_틱택토 {

	static boolean set;
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		StringBuilder sb = new StringBuilder();
		
		String line = st.nextToken();
		while(!line.equals("end")) {	
			set = false;
			int[][] map = new int[3][3];
			
			int index = 0;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(line.charAt(index) == 'X') {
						map[i][j] = 1;
					} else if(line.charAt(index) == 'O') {
						map[i][j] = 2;
					} 
					index++;
				}
			}
			
			if(check(map)) {
				sb.append("valid\n");
			} else {
				sb.append("invalid\n");
			}
			
			st = new StringTokenizer(reader.readLine());
			line = st.nextToken();
		}
		System.out.println(sb.toString());
	}
	
	//(. - 0) (X - 1) (O - 2)
	public static boolean check(int[][] map) {		
		int pairN = check2(map);
		if(pairN == -1) {
			return false;
		} else if(pairN >= 1 || (set && pairN == 0)) {
			return true;
		} else {
			int empty = 0;
			
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {	
					if(map[r][c] == 0) {
						empty++;
					}
				}
			}
			
			if(empty > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	//맞춰지는 짝이 있다면 true
	//짝이 없다면 false
	public static int check2(int[][] map) {
		int pairN = 0;
		
		int cO = 0;
		int cX = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				cX = map[i][j] == 1 ? cX + 1 : cX;
				cO = map[i][j] == 2 ? cO + 1 : cO;
			}
		}
		
		if(cO > cX) {
			return -1;
		}
		
		if(cX - cO >= 2) {
			return -1;
		}
		
		// 가로 검사
		cO = 0;
		cX = 0;
//		boolean check = false;
		boolean o = false;
		boolean x = false;
		for(int r = 0; r < 3; r++) {
			int countO = 0;
			int countX = 0;
			
			for(int c = 0; c < 3; c++) {	
				if(map[r][c] == 1) {
					countX++;
					cX++;
				} else if(map[r][c] == 2) {
					countO++;
					cO++;
				}
			}
			
			if(countX == 3) {
				pairN++;
				x = true;
				countX = 0;
			} else if(countO == 3) {
				pairN++;
				countO = 0;
				
				o = true;
			}
		}
		if(x && cX == cO) {
			return -1;
		}
		
		if(pairN >= 2) {
			return -1;
		}
		
		if(pairN == 1) {
			if(o && cO != cX) {
				return -1;
			}
		}
		
		if(pairN >= 1) {
			set = true;
		}
		
		pairN = 0;
		
		cO = 0;
		cX = 0;
		// 세로 검사
		x = false;
		o = false;
		for(int c = 0; c < 3; c++) {	
			int countO = 0;
			int countX = 0;
			
			for(int r = 0; r < 3; r++) {
				if(map[r][c] == 1) {
					cX++;
					countX++;
				} else if(map[r][c] == 2) {
					countO++;
					cO++;
				}
			}
			
			if(countX == 3) {
				countX = 0;	
				pairN++;
				x = true; 
			} else if(countO == 3) {
				pairN++;
				countO = 0;
			}
		}
		if(x && cX == cO) {
			return -1;
		}
		
		if(pairN >= 2) {
			return -1;
		}
		
		if(pairN == 1) {
			if(o && cO != cX) {
				return -1;
			}
		}
		if(pairN >= 1) {
			set = true;
		}
		pairN = 0;
		
		//대각선 검사
		int countORU = 0, countXRU = 0;
		int countORD = 0, countXRD = 0;
		for(int i = 0; i < 3; i++) {
			if(map[i][i] == 1) {
				countXRD++;
			} else if(map[i][i] == 2) {
				countORD++;				
			}
			
			if(map[i][2 - i] == 1) {
				countXRU++;
			} else if(map[i][2 - i] == 2) {
				countORU++;				
			}
		}
		
		if(countORU == 3) {
			pairN++;
			
			if(cO < cX) {
				return -1;
			}
		}
		if(countXRU == 3) {
			pairN++;
			
			if(cO >= cX) {
				return -1;
			}
		}
		if(countORD == 3) {
			pairN++;
			
			if(cO < cX) {
				return -1;
			}
		}
		if(countXRD == 3) {
			pairN++;
			
			if(cO >= cX) {
				return -1;
			}
		}
		
		return pairN;
	}

}
