function solution(sizes) {
    var answer = 0;
    
    let maxV = 0;
    let minV = 0;
    
    for(let size of sizes){
        var max = Math.max(size[0],size[1]);
        var min = Math.min(size[0],size[1]);
        maxV = (maxV>max)?maxV:max;
        minV = (minV>min)?minV:min;
    }
    answer = maxV*minV;
    
    return answer;
}