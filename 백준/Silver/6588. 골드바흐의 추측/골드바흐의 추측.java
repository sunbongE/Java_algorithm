import java.io.*;

/**
 * 문제
 * 1742년, 독일의 아마추어 수학가 크리스티안 골드바흐는 레온하르트 오일러에게 다음과 같은 추측을 제안하는 편지를 보냈다.
 *
 * 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
 * 예를 들어 8은 3 + 5로 나타낼 수 있고, 3과 5는 모두 홀수인 소수이다. 또, 20 = 3 + 17 = 7 + 13, 42 = 5 + 37 = 11 + 31 = 13 + 29 = 19 + 23 이다.
 *
 * 이 추측은 아직도 해결되지 않은 문제이다.
 *
 * 백만 이하의 모든 짝수에 대해서, 이 추측을 검증하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력은 하나 또는 그 이상의 테스트 케이스로 이루어져 있다. 테스트 케이스의 개수는 100,000개를 넘지 않는다.
 *
 * 각 테스트 케이스는 짝수 정수 n 하나로 이루어져 있다. (6 ≤ n ≤ 1000000)
 *
 * 입력의 마지막 줄에는 0이 하나 주어진다.
 *
 * 출력
 * 각 테스트 케이스에 대해서, n = a + b 형태로 출력한다. 이때, a와 b는 홀수 소수이다. 숫자와 연산자는 공백 하나로 구분되어져 있다. 만약, n을 만들 수 있는 방법이 여러 가지라면, b-a가 가장 큰 것을 출력한다. 또, 두 홀수 소수의 합으로 n을 나타낼 수 없는 경우에는 "Goldbach's conjecture is wrong."을 출력한다.
 *
 * 예제 입력 1
 * 8
 * 20
 * 42
 * 0
 * 예제 출력 1
 * 8 = 3 + 5
 * 20 = 3 + 17
 * 42 = 5 + 37
 *
 *
 * 짝수만 담긴 리스트를 투포인터로 순회하면서 서로 합하여 input이 되는 값을 찾는다.
 * */
public class Main {

    static int n,k, max = 1000001;
    static boolean decimal[];
    static String fail = "Goldbach's conjecture is wrong.\n";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringBuilder sb = new StringBuilder();

        decimal = new boolean[max];
        setDecimal();
        while(true){

            int input = Integer.parseInt(br.readLine());
            if(input == 0) break;

            int[] result = getResult(input);
            if(result!=null){
                sb.append(input).append(" = ");
                sb.append(result[0] + " + "+result[1]);
                sb.append("\n");

            }else{
                sb.append(fail);
            }
        }
        System.out.println(sb);
    }

    // 홀수로 값을 만들 수 있는지 확인.
    private static int[] getResult(int input) {
        int a = 3;
        int b = input-1;
        while(a<=b){
            int sum = a+b;
            if(input == sum ){
                if(isOddNum(a) && isOddNum(b) && !decimal[a] && !decimal[b]){
                    return new int[]{a,b};
                }
                if(!isOddNum(a) || decimal[a]){
                    a++;
                }
                if(!isOddNum(b) || decimal[b]){
                    b--;
                }
            }else if( sum > input) b--; // 값이 더 크면
            else{
                a++;
            }
        }

        return null;
    }

    private static boolean isOddNum(int num) {
        return num % 2 != 0 ;
    }

    private static void setDecimal(){
        for (int i = 2; i <= Math.sqrt(max); i++) {
            for (int j = i*i; j < decimal.length; j+=i) {
                decimal[j] = true;
            }
        }
    }
}
