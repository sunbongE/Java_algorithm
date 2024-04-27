
import java.util.*;

public class Main {
    //    static int limit = 1_000_001;
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();


        if (N <= 10) {
            System.out.println(N);
        } else if (N > 1022) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < 10; i++) {
                sol(i, 1);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }

    }

    private static void sol(long num, int idx) {
//        System.out.println("num : "+num +", idx : "+idx);
        if (idx > 10) return;

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            sol((num * 10) + i, idx + 1);
        }

    }
}
