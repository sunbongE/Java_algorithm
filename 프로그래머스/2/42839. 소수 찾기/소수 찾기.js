let visited,answer=0;
let dict = new Map();
function solution(numbers) {
    
    // 1~n까지 선택한 순열구하고
    // 만들어진 순열이 소수인지 확인하는 로직을 만든다.
    // 소수라면 answer++;
  
    for(let i=1;i<=numbers.length;i++){
        visited = new Array(numbers.length).fill(false);
        let per = getPer(0,i,numbers,new Array(i));
     }
    return answer;
}

// 순열 구하기.
function getPer(cnt,N,numbers,tmp){
    if(cnt === N) {
        // 순열을 구했다면 소수인지 판별한다.
        if(isDecimal(tmp)){
            answer++;
        };
        return;
    }
    
    for(let i =0 ;i<numbers.length;i++){
        if(!visited[i]){
            visited[i] = true;
            tmp[cnt] = numbers.charAt(i)
            getPer(cnt+1,N,numbers,tmp);
            visited[i] = false;
            
        }
    }
}
function isDecimal(tmp){
    let s = "";
    for(let n of tmp){
        s+=n;
    }
    
    var k = Number(s)
    // 이미 처리했으면 리턴하기.
    if(dict.has(k) || k <= 1){
        return false;
    }
    
    for(let n = 2; n < k;n++){
        if(k%n==0) return false;
    }
    
    // console.log(k)
    dict.set(k,0);
    return true;
    
}