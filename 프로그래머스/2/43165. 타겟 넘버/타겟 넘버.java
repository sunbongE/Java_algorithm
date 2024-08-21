class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
    }
    int dfs(int[] numbers, int n, int sum, int target) {
        if(n == numbers.length) {
            if(sum == target) {
                return 1;
            }
            return 0;
        }
        int result1 = dfs(numbers, n + 1, sum + numbers[n], target) ;
          int result2 =   dfs(numbers, n + 1, sum - numbers[n], target);
        return result1+result2;
    }
}