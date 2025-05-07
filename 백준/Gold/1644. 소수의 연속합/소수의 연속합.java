import java.io.*;
import java.util.ArrayList;

/**
 * 문제
 * 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.
 *
 * 3 : 3 (한 가지)
 * 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
 * 53 : 5+7+11+13+17 = 53 (두 가지)
 * 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다. 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
 *
 * 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)
 *
 * 출력
 * 첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.
 *
 * 20 0
 * 53 2
 *
 * 에라토스테네스의 채인가를 사용해서 소수들 400만 이하 전부 리스트에 담고
 * 리스트에서 슬라이딩 윈도우를 사용해서 ? 아니면 투 포인터 사용해서
 * 내 생각에는 투포인터가 맞는듯
 * 목표값이랑 같으면 cnt++
 * 보다 작으면 e++
 * 크면 s++
 * */
public class Main {

    static int cnt=0,max = 4000001;
    static ArrayList<Integer> decimal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        int target = Integer.parseInt(br.readLine());
        decimal = new ArrayList<>();
        setDecimal(target);


        int s=0,e=0,sum=0;
        while(s<=e  && e<decimal.size() && decimal.get(e) <= target){
            if(sum <= target){
                // 같으면
                if(sum == target){
                    cnt++;
                }
                // 작으면
                sum+=decimal.get(e++);

            }else{
                // 크면
                sum-=decimal.get(s++);
            }
        }
        System.out.println(cnt);
    }

    private static void setDecimal(int target) {
        boolean[] nums = new boolean[max];
        nums[0] = true;
        nums[1] = true; // 이거 안하면 97퍼에서 틀림...
        for (int i = 2; i <= Math.sqrt(max); i++) {
            if(nums[i]) continue;
            for (int j = i*i; j < nums.length; j+=i) {
                nums[j] = true;
            }
        }
        for (int i = 2; i < nums.length; i++) {
            if(!nums[i]) decimal.add(i);
        }
        if(!nums[target]) cnt++;
    }
}
