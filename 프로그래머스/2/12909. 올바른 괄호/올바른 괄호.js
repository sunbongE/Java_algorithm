function solution(s){
    var answer = true;

    var cnt = 0 ;
    for(let tmp of s){
        if(tmp === "("){
            cnt++;
        }else{
            cnt--;
        }
        if(cnt<0) return false;
    }
    
    if(cnt === 0 ) return true;
    else return false;
    return answer;
}