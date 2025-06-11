import java.io.*;
import java.util.*;


public class Main {

    static int size = 100001;
    static boolean nums[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        nums = new boolean[size];
        setPrime();

        HashSet<Long> pNums = getPrime();

//        System.out.println(pNums.contains(11245L));
//        System.out.println(pNums);
        while(true){
            Long max = -1L;
            String inp = br.readLine();
            if(inp.equals("0")) break;

            for (int i = 0; i < inp.length(); i++) {
                StringBuilder tmp = new StringBuilder();
                tmp.append(""+inp.charAt(i));
                if(pNums.contains(Long.parseLong(tmp.toString()))){
                    max = Math.max(max, Long.parseLong(tmp.toString()));
                }
                for (int j = i+1; j < inp.length(); j++) {
                    tmp.append(inp.charAt(j));
//                    System.out.println(tmp);
                    if(tmp.length()>6)break;
                    Long target = Long.parseLong(tmp.toString());

                    if(target >= size) continue;
                    if(pNums.contains(target)){
                        max = Math.max(max, target);
                    }
                }
            }
            ans.append(max+"\n");
        }
        System.out.println(ans);
    }

    private static HashSet<Long> getPrime() {
        HashSet<Long> result = new HashSet<>();
        for (int i = 2; i < nums.length; i++) {
            if(!nums[i]) {
//                System.out.print(i+" ");
                result.add((long)i);
            }
        }
        return result;
    }

    private static void setPrime() {
        for (int i = 2; i * i < size; i++) {
            if (!nums[i]) {
                for (int j = i * i; j < size; j += i) {
                    nums[j] = true;
                }
            }
        }
    }

}
