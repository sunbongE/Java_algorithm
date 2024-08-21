class Solution {
    boolean[] visited;
    int ans=0;
    int N;
    int[] nums;
    public int solution(int[] numbers, int target) {
        visited = new boolean[numbers.length];
        this.N = numbers.length;
        this.nums = numbers;
        dfs(0,0,target); //idx, 현재 빼거나 더한 값
        return ans;
    }
    private void dfs(int idx, int t,int target){
        if(idx >= N){
            if(t==target) ans++;
            return;
        }
        
        dfs(idx+1, t+nums[idx],target);
        dfs(idx+1, t-nums[idx],target);
    }
}