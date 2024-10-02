function solution(participant, completion) {
    var answer = '';
    // 배열에서 제거하는건 시프트연산으로 시간이 비효율적이다.
    // 각 배열을 1번만 순회하면서 골인한 사람을 기록한다.
    // 마지막으로 한번 더 순회하면서 공인못한 사람을 찾고 리턴한다.
    // 맵을 사용하려했지만 중복된 이름이 존재한다.
    // 그럼 처음 돌면서 이름:개수 형태로 기록하고, 
    // 두번째 돌때는 이름:개수-1
    // 세번째에는 개수가 0이 아닌거 찾아서 이름을 리턴한다.
    
    var dict = new Map();
    for(var i=0;i<participant.length;i++){
        if(dict.has(participant[i])){
            dict.set(participant[i], dict.get(participant[i])+1)
        }else{
            dict.set(participant[i],1)
        }
    }
    
    for(var i=0;i<completion.length;i++){
        dict.set(completion[i], dict.get(completion[i])-1)
    }
    
    
    for(let [k,v] of dict){
        if(v!==0) {
            answer = k;
            break;
        }
    }
    // console.log(dict)
    
    return answer;
}