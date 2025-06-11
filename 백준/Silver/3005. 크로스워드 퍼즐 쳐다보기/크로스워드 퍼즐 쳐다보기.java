import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] puzzle = new char[r][c];

        for (int i = 0; i < r; i++) {
            String inp = br.readLine();
            for (int j = 0; j < c; j++) {
                puzzle[i][j] = inp.charAt(j);
            }
        }

        ArrayList<String> li = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String word = "";
            for (int j = 0; j < c; j++) {
                if(puzzle[i][j] != '#') word+= puzzle[i][j];
                else {
                    if(word.length() > 1) li.add(word);
                    word="";
                };
            }
            if(word.length() > 1) li.add(word);
        }

        for (int i = 0; i < c; i++) {
            String word = "";
            for (int j = 0; j < r; j++) {
                if(puzzle[j][i] != '#') word += puzzle[j][i];
                else {
                    if(word.length() > 1) li.add(word);
                    word="";
                };
            }
            if(word.length() > 1) li.add(word);
        }

        Collections.sort(li);
//        System.out.println(li);
        System.out.println(li.get(0));

    }
}
