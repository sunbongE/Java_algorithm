import java.io.*;
import java.util.*;

public class Main {
    static int N,nums[],ans=0;
    public static void main(String[] args) throws IOException {

  //              BufferedReader br = new BufferedReader(new FileReader("test.txt")); // 입력 파일 열기
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        HashMap<Integer,Integer> usedNum = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }


        if(N==1) {
            System.out.println(1);
            return;
        }


        int kind=0;
        int s=0,e=0;
        while(s<=e && e<N){

            if(kind<3){
                if(!usedNum.containsKey(nums[e])) kind++;
                usedNum.put(nums[e], usedNum.getOrDefault(nums[e],0)+1);
                e++;
            }else{
                usedNum.put(nums[s], usedNum.getOrDefault(nums[s],0)-1);
                if(usedNum.get(nums[s]) <= 0) {
                    kind--;
                    usedNum.remove(nums[s]);
                }
                s++;
            }
//            System.out.println(kind);
            if(kind<=2) ans = Math.max(ans,getLen(usedNum));

        }
        System.out.println(ans);

    }

    private static int getLen(HashMap<Integer, Integer> usedNum) {
        int len=0;
//        System.out.println("size -> "+usedNum.size());
        for(Integer k : usedNum.keySet()){
//            System.out.println("key : "+k);
            len+=usedNum.get(k);
        }

        return len;
    }
}
