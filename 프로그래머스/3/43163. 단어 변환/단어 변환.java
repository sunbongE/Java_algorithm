import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // target이 words에 없는 경우 변환 불가
        if(!Arrays.asList(words).contains(target)) return 0;

        // BFS를 위한 큐 선언
        Queue<WordNode> queue = new LinkedList<>();
        queue.offer(new WordNode(begin, 0)); // 시작 단어와 변환 횟수
        
        boolean[] visited = new boolean[words.length];

        while(!queue.isEmpty()) {
            WordNode current = queue.poll();
            
            // target 단어에 도달하면 현재까지의 변환 횟수 반환
            if(current.word.equals(target)) return current.step;

            // words 배열에서 하나씩 비교하여 1글자만 다른 경우 큐에 추가
            for(int i = 0; i < words.length; i++) {
                if(!visited[i] && getChangeCnt(current.word, words[i]) == 1) {
                    visited[i] = true;
                    queue.offer(new WordNode(words[i], current.step + 1));
                }
            }
        }
        
        return 0; // target으로 변환할 수 없는 경우
    }

    // 다른 자리수 개수를 리턴하는 함수
    private int getChangeCnt(String word1, String word2) {
        int cnt = 0;
        for(int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) cnt++;
        }
        return cnt;
    }

    // 단어와 변환 횟수를 담는 클래스
    class WordNode {
        String word;
        int step;

        WordNode(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
}
