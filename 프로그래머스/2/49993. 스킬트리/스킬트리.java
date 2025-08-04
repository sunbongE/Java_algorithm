class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        boolean[] in = new boolean[27];
        
        for(int i=0; i<skill.length(); i++){
            in[skill.charAt(i)-'A'] = true;
        }
        
        for(String tree : skill_trees){
            int idx =0;
            boolean isValid = true;
            for(int i=0; i<tree.length(); i++){
                if(in[tree.charAt(i)-'A']){ // 선행해야할게 있는 스킬이면, 확인해봄.
                    if(tree.charAt(i) == skill.charAt(idx)){
                        // 같은경우 순서대로 진행된다는 의미.
                        idx++;
                    }else{
                         isValid = false;
                        break;
                    }
                }
            }
            if(isValid) answer++;
        }
        
        return answer;
    }
    
    public int[] copy(int[] target){
        int[] result = new int[target.length];
        for(int i=0; i<target.length;i++){
            result[i] = target[i];
        }
        return result;
    }
}