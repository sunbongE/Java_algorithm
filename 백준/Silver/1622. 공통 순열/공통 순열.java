
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {



        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String first = bf.readLine();
            if (first == null) break;
            String second = bf.readLine();

            HashMap<Character, Integer> firstMap = new HashMap<>();
            HashMap<Character, Integer> secondMap = new HashMap<>();

            settingMap(firstMap, first);
            settingMap(secondMap, second);

            Set<Character> characters = new HashSet<>();
            characters.addAll(firstMap.keySet());

            PriorityQueue<Character> pq = new PriorityQueue<>();

            pq.addAll(characters);


            while (!pq.isEmpty()){
                Character c = pq.poll();
                if (secondMap.get(c) != null && firstMap.get(c) != null) {

                    int rcnt = (secondMap.get(c) < firstMap.get(c) ? secondMap.get(c) : firstMap.get(c));

                    for (int i = 0; i < rcnt; i++) {

                        sb.append(c);
                    }
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void settingMap(HashMap<Character, Integer> curMap, String curWord) {

        for (int i = 0; i < curWord.length(); i++) {
            Character tmpChar = curWord.charAt(i);
            if (!curMap.containsKey(tmpChar)) {
                curMap.put(tmpChar, 1);
            } else {
                Integer curValue = curMap.get(tmpChar);
                curMap.put(tmpChar, (curValue + 1));
            }

        }
    }
}
