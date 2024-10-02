function solution(arr)
{
    var answer = [];
    const len = arr.length;
    for(let i=0;i<len;i++){
        var n = arr[i]
        if(n !== answer[answer.length-1]){
            answer.push(n)
        }    
    }
    // console.log(answer)
    
    return answer;
}