import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {

        HashMap<String, String> memberToGroup = new HashMap<>(); // 맴버명 -> 그룹명 저장
        HashMap<String, List<String>> groupToMembers = new HashMap<>(); // 그룹명 -> 맴버 리스트 저장
        StringBuilder ans = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 그룹 수
        M = Integer.parseInt(st.nextToken()); // 문제 수

        for (int n = 0; n < N; n++) {
            String groupName = br.readLine(); // 그룹명 입력
            int groupCnt = Integer.parseInt(br.readLine()); // 그룹에 속한 맴버 수 입력
            List<String> members = new ArrayList<>(); // 그룹의 맴버 리스트

            for (int cnt = 0; cnt < groupCnt; cnt++) {
                String memberName = br.readLine(); // 맴버명 입력
                memberToGroup.put(memberName, groupName); // 맴버명과 그룹명 저장
                members.add(memberName); // 맴버 리스트에 추가
            }

            // 그룹명으로 맴버 리스트 저장 (사전순 정렬)
            Collections.sort(members);
            groupToMembers.put(groupName, members);
        }

        for (int m = 0; m < M; m++) {
            String query = br.readLine(); // 문제 입력
            int queryType = Integer.parseInt(br.readLine()); // 문제 유형 입력

            if (queryType == 1) { // 맴버명으로 그룹명을 찾는 경우
                ans.append(memberToGroup.get(query)).append("\n");
            } else { // 그룹명으로 맴버 리스트를 찾는 경우
                List<String> nameList = groupToMembers.get(query);
                for (String s : nameList) {
                    ans.append(s).append("\n");
                }
            }
        }

        bw.write(ans.toString());
        bw.flush(); // 결과 출력
        bw.close();
        br.close();
    }
}
