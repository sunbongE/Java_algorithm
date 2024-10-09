function solution(phone_book) {
    var answer = true;
    const map = new Map();
    for(let i=0;i<phone_book.length;i++){
        map.set(phone_book[i],0);
    }
    
    for( let tmp of phone_book){
        for(let i =1 ; i<tmp.length;i++){
            if(map.has(tmp.substring(0,i))){
                return false;
            }
        }
    }
    
    return answer;
}
