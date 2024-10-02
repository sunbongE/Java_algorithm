function solution(nums) {
    var answer = 0;
    var dict = new Set();
    for(var i=0; i<nums.length;i++){
        dict.add(nums[i],0)
    }
    // console.log(dict.size)
    answer = dict.size;
    
    var mid = nums.length/2;
    
    if(answer > mid){
        return mid
    }
    
    return answer;
}