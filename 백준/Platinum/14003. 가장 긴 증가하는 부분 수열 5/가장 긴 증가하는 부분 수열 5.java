import java.io.*;
import java.util.*;
/*
* 문제
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

둘째 줄에는 정답이 될 수 있는 가장 긴 증가하는 부분 수열을 출력한다.
*
*
* 풀이
* 1. 입력받은 순서대로 순회하며, tmpLIS 리스트에 추가하여 LIS의 최대 길이를 구할 수 있다.
* 1-1. tmpLIS에 값을 넣을때는 해당 값이 어느 위치에 들어가야 순서가 오름차순이 될수있는지 확인하며,
*       삽입 혹은 변경해야한다. 위치를 찾을 때는 이분탐색으로 시간을 단축시킬수있다.
* 2. 1을 하는 과정에서 각 요소를 추적할 수 있게 해야함.
*    순회하며, 1을 수행할 때 이전에 들어온 값(앞에 값)이 무엇인지 확인하기 위해 인덱스를 기록해야함.
*     preNumIdxArr
* 3. 마지막으로 역추적을 하기 위해서는 마지막 숫자가 뭔지 알아야함.
*       그래서 각 길이별로 어떤 숫자가 들어가있는지를 기록하는 배열이 있어야함.
*       마찬가지로 1번을 수행하면서 기록한다.
*
* 역추적
* lastIdxByLen 마지막 값 : 최대 길이에 들어와있는 마지막 숫자의 인덱스 (LIS의 최대값)을 이용함. LIB라고 하고,
* A[LIB] : 처음에는 가장 긴 길이에 들어간 수를 찾을 수 있다.
* STACK.PUSH(A[LIB]) : LIS를 기록함.
* preNumIdxArr[LIB] : A[LIB] 수의 앞 NUM의 IDX를 추적할 수 있다.
* 추적한 값을 이용해서 다시 STACK에 쌓고 이를 반복하며, LIB가 -1 인경우 종료한다.
* LIB가 -1인 경우 : LIS의 첫 번째 수로 더이상 앞에 수를 찾을 수 없음을 의미함.
*
*
* */
public class Main {
    static int n, A[],preNumIdxArr[];
    static ArrayList<Integer> tmpLIS,lastIdxByLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[n];
        preNumIdxArr = new int[n];          // 앞에 값 기록
        tmpLIS = new ArrayList<>();         // 임시 LIS기록.
        lastIdxByLen = new ArrayList<>();   // 길이별 마지막 수

        // 입력
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 1번 수행.
        for (int i = 0; i < n; i++) {
            int num = A[i];

            int idx = getIdx(num);

            if(idx == tmpLIS.size()){ // 사이즈가 같으면 값을 새로 추가
                tmpLIS.add(num);
                lastIdxByLen.add(i);
            }else{ // 아니면 값 교체
                tmpLIS.set(idx,num);
                lastIdxByLen.set(idx, i);
            }

                              // 갱신된 위치에 어떤 값이 들어갔는지 인덱스로 기록.
            preNumIdxArr[i] = idx > 0 ? lastIdxByLen.get(idx-1) : -1;  // 앞에 값 기록, -1: 처음으로 들어온 값의 앞에 값은 없음.
        }

//        System.out.println("tmpLIS : "+tmpLIS);
//        System.out.println("lastIdxByLen : "+lastIdxByLen);
//        System.out.println("preNumIdxArr : "+Arrays.toString(preNumIdxArr));

        int idx= lastIdxByLen.get(lastIdxByLen.size()-1);
        Stack<Integer> stack = new Stack<>();

        while(idx >= 0){
            stack.push(A[idx]);
            idx = preNumIdxArr[idx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(stack.size()).append("\n");

        while(!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }

        System.out.println(sb);

    }

    private static int getIdx(int num) {
        int s =0, e = tmpLIS.size()-1;

        while(s<=e){

            int targetIdx = (s+e)>>1;

            if(tmpLIS.get(targetIdx) >= num){
                e = targetIdx-1;
            }else{
                s = targetIdx+1;
            }
        }

        return s;
    }
}
