import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
    static int n,m,arr[],ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n+1];

        ArrayList<Integer> li = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int cur= arr[i];
            int idx = customBS(li,cur);
            if(idx == li.size()) li.add(cur);
            else li.set(idx,cur);

        }
//        System.out.println(li);

        System.out.println(li.size());
        }

    private static int customBS(ArrayList<Integer> li, int target) {

        int s = 0, e =li.size()-1;
        while(s<=e){
            int mIdx = (s+e)/2;

            if(li.get(mIdx) == target){
                return mIdx;
            }else if(li.get(mIdx) > target){
                e = mIdx-1;
            }else{
                s = mIdx+1;
            }
        }
        return s;
    }
}
