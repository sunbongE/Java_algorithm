import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    public static void main(String[] args) throws IOException {

        HashMap<String, String> info = new HashMap<>(); // k :맴버명, v : 그룹명
        StringBuilder ans = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            String groupName = br.readLine();
            int groupCnt = Integer.parseInt(br.readLine());
            for (int cnt = 0; cnt < groupCnt; cnt++) {
                String memberName = br.readLine();
                info.put(memberName, groupName);
            }
        }

        for (int m = 0; m < M; m++) {
            String query = br.readLine();
            int queryType = Integer.parseInt(br.readLine());
            if(queryType == 1){ // 그룹명을 찾는다.
                ans.append(info.get(query)+"\n");
            }else{ // 그룹에 속한 맴버들을 찾는다.
                ArrayList<String> nameList = new ArrayList<>();
                for (Map.Entry<String, String> keyValue : info.entrySet()) {
                    if(keyValue.getValue().equals(query)){ //
                        nameList.add(keyValue.getKey()); // 맴버 명을 추출한다.
                    }
                }
                Collections.sort(nameList); // 사전순 정렬.
                for (String s : nameList) {
                    ans.append(s+"\n");
                }
            }
        }

        System.out.println(ans);
    }
}
