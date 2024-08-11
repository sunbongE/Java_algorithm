import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] isLeafNode;
    static int n;
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        isLeafNode = new boolean[n];
        Arrays.fill(isLeafNode, true); // 처음은 모두 리프노드로 초기화.

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) continue;
            adjList.get(parent).add(i);
            if (!isLeafNode[parent]) continue;
            isLeafNode[parent] = false;
        }


        int delNode = Integer.parseInt(br.readLine());

        bfs(delNode);

//        System.out.println(Arrays.toString(isLeafNode));

        for (ArrayList<Integer> adjNodes : adjList) {
            for (int i = 0; i < adjNodes.size(); i++) {
                if(adjNodes.get(i) == delNode && adjNodes.size() ==1){
                    isLeafNode[i] = true;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(isLeafNode[i]) ans++;
        }
        System.out.println(ans);
    }

    private static void bfs(int delNode) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(delNode);

        boolean[] v = new boolean[n];
        v[delNode] = true;

        while (!q.isEmpty()) {
            int del = q.poll();
            if (isLeafNode[del]) isLeafNode[del] = false;
            for (Integer nextDel : adjList.get(del)) {
                v[nextDel] = true;
                q.offer(nextDel);
            }
        }
    }
}
