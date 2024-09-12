import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K,L;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        HashMap<String,Integer> students = new HashMap<>();

        for (int i = 0; i < L; i++) {
            String studentNo = br.readLine();
            students.put(studentNo,i);
        }

//        System.out.println(students.keySet());
//        System.out.println(students.values());

        // 수강신청 선착순 k명 가져오기
        List<Integer> vs = new ArrayList<>(students.values());
        Collections.sort(vs);
        HashMap<Integer,Integer> result = new HashMap<>();
        for (int i = 0; i < K; i++) {
            if(i>=vs.size()) break;
            result.put(vs.get(i),0);
        }


        PriorityQueue<AcceptStudent> q = new PriorityQueue<>();
        for (Map.Entry<String, Integer> info : students.entrySet()) {
            // 선정된 번호를 가지고있는 학생이면 큐에 넣는다.
//            System.out.println(info);
            if(result.containsKey(info.getValue())){
                q.offer(new AcceptStudent(info.getKey(), info.getValue()));
            }
        }

        while(!q.isEmpty()){
            AcceptStudent cur = q.poll();
            sb.append(cur.studentNo+"\n");
        }

        System.out.println(sb);



    }

    private static class AcceptStudent implements Comparable<AcceptStudent>{
        String studentNo;
        int idx;

        private AcceptStudent(String studentNo, int idx){
            this.idx = idx;
            this.studentNo = studentNo;
        }

        @Override
        public int compareTo(AcceptStudent o){
            return Integer.compare(this.idx,o.idx);
        }
    }
}
