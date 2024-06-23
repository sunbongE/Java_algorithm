import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {

            int num = Integer.parseInt(bf.readLine());

            if(maxQ.size() > minQ.size()){
                minQ.offer(num);
            }else{
                maxQ.offer(num);
            }

            if(!maxQ.isEmpty()&&!minQ.isEmpty()){

                if(maxQ.peek() > minQ.peek() ){
                    int tmp = minQ.poll();
                    minQ.offer(maxQ.poll());
                    maxQ.offer(tmp);
                }

            }
            sb.append(maxQ.peek()+"\n");
        }
        System.out.println(sb);
    }
}
