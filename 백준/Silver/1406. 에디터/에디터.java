
import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String in = br.readLine();
        int n = Integer.parseInt(br.readLine());
        LinkedList<Character> li = new LinkedList<>();
        for (int i = 0; i < in.length(); i++) {
            li.add(in.charAt(i));
        }

        ListIterator<Character> corser = li.listIterator();

        while(corser.hasNext()) corser.next(); // 커서를 마지막으로 이동시킨다.

        for (int i = 0; i < n; i++) {
            String comm = br.readLine();
            char command = comm.charAt(0);
            switch (command) {
                case 'P':
                    //값 추가.
                    char c = comm.charAt(2);
                    corser.add(c);
//                    corser.next();
                    break;
                case 'L':
                    // 왼쪽으로 커서 이동.
                    if (corser.hasPrevious()) {
                        corser.previous();
                    }
                    break;
                case 'D':
                    if (corser.hasNext()) {
                        corser.next();
                    }
                    // 오른쪽으로 이동.
                    break;
                case 'B':
                    // 커서 왼쪽 문자 삭제
                    if (corser.hasPrevious()) {
                        corser.previous();
                        corser.remove();
                    }
                    break;
            }
        }

        for (Character c : li) {
            bw.write(c);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
