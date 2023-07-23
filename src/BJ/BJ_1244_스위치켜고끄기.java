package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author qkrxo
 * <pre>
 * 남 : 1 -> 배수지움
 * 여 : 2 -> 양옆이 같은건 전부 바꿈
 * 인덱스가 1부터 시작이니까 배열을 사이즈를 1 업으로 만듬
 * for문으로 배열에 값 할당 start = 1;
 * for : inp_n
 * gender, IDX 입력
 * ...
 * change메서드 위치를 받으면 값을 변경하는 메서드 생성.
 * 
 * 입력: 속도는 빠른게 좋으니 버퍼리더 사용해보는걸로오
 * </pre>
 * 
 */
public class BJ_1244_스위치켜고끄기 {
	public static int gender;
	public static int idx;
	public static int[] changeSwitch(int gender, int idx, int[] Switch) {
		if (gender == 1) {// 남자인 경우
			for(int i =idx;i<Switch.length;i+=idx) {
				if (Switch[i]==1) { 
					Switch[i]=0;
				}else {
					Switch[i]=1;
				}
			}
		}else { // 여자인 경우
			Switch[idx] = (Switch[idx]==1)?0:1; // 1이면 0, 0이면 1로 변경
			for (int i = 1; i <= Switch.length/2; i++) { // 양옆으로 이동하니까 전체 길이의 절반만큼 
				// 범위내(size == 9 인경우 => 1~8) ,양쪽이 같으면
				if (((idx-i)>=1&&(idx+i)<Switch.length) && (Switch[idx-i] == Switch[idx+i]) ) {
					Switch[idx-i]=(Switch[idx-i]==1)?0:1;
					Switch[idx+i]=(Switch[idx+i]==1)?0:1;
				}else { // 다르면 멈춘다.
					break;
				}
			}
			
		}
		
		return	Switch;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		// 1업해서 배열 선언 및 초기화
		int[] Switch = new int[++size];
		// 배열 입력
		String[] INP = br.readLine().split(" ");
		for (int i = 1; i < Switch.length; i++) {
			Switch[i] = Integer.parseInt(INP[i-1]);
		}
		
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer ST = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(ST.nextToken());
			idx = Integer.parseInt(ST.nextToken());
			Switch = changeSwitch(gender,idx,Switch); // 바뀐 스위치가 할당됨
		}
		for (int i = 1; i < Switch.length; i++) { // 출력 형식에 맞게 20번째에서 개행
			System.out.print(Switch[i]+" ");
			if(i%20==0) {
				System.out.println();
			}
		}
	}

}



