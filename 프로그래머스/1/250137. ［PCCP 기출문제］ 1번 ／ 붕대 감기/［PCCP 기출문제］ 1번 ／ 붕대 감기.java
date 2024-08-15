class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHp = health;
        int Hp = health;
        int preAttackTime=0;
        
        for(int[] info : attacks){
            int attackTime = info[0];
            int damage = info[1];
            
            //  공격 받기 전 회복된거 적용.
                int diff = attackTime - preAttackTime-1; // 전 공격과 현재 공격의 텀을 기록한다.
                if(diff >= bandage[0]){ // 공격받은 텀이 시전시간보다 더 길었다면 
                    
                    int repeat = diff/bandage[0]; // 총회복시간 / 시전시간 
                    
                    Hp += (bandage[1] * diff);
                    Hp+= bandage[2]*repeat;
                    
                }else{ 
                    // 시전시간 마지막에 공격을 받았거나 || 연속으로 공격을 받았다면
                    
                    Hp += (bandage[1]*(diff));
                }
                if(Hp > maxHp) Hp = maxHp; // 회복은 최대체력 이상으로 할 수 없다.
            // System.out.println("공격 전 =  "+Hp);
            
            // 공격 받기
            Hp -= damage;
            
            // System.out.println("공격 후 =  "+Hp);

            preAttackTime = attackTime; // 현재 공격시간을 이전으로 바꿔준다.
            // System.out.println(preAttackTime);
            if(Hp <= 0 ) return -1; // 만약 죽었다면 멈춘다.
        }
        
        return Hp;
    }
}