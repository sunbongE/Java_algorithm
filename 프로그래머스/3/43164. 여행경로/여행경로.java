import java.util.*;

class Solution {
    // 출발지와 도착지의 우선순위를 관리하는 HashMap
    HashMap<String, PriorityQueue<String>> info = new HashMap<>();
    ArrayList<String> answer;
    final String START = "ICN"; // 시작점
    
    public String[] solution(String[][] tickets) {
        answer = new ArrayList<>();
        
        // 정보를 순회하면서 출발지와 도착지 정보를 입력한다.
        for (String[] ticket : tickets) {
            String from = ticket[0];  // 출발지
            String to = ticket[1];    // 도착지
            
            // 출발지가 이미 존재하면 해당 출발지의 우선순위 큐에 추가
            info.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }
        
        // DFS 방식으로 경로 탐색
        dfs(START);
        
        // ArrayList를 배열로 변환하여 반환
        return answer.toArray(new String[0]);
    }
    
    // DFS 방식의 경로 탐색
    private void dfs(String cur) {
        // 현재 위치에서 가능한 모든 경로를 순차적으로 탐색 (사전순으로 자동 정렬됨)
        while (info.containsKey(cur) && !info.get(cur).isEmpty()) {
            dfs(info.get(cur).poll());  // 재귀적으로 다음 경로 탐색
        }
        // 경로를 역순으로 쌓아가야 하므로 마지막에 추가
        answer.add(0, cur);
    }
}
