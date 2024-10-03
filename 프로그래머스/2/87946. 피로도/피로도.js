let answer = 0;
let visited,K;
function solution(k, dungeons) {// 현재 피로도, 던전피로도 표
    const n = dungeons.length;
    K=k;
    // 던전을 어떤 순서로 돌아야할지 모든 경우를 계산해야하는데
    visited = new Array(n).fill(false);
    getOrder(0,n,new Array(n),dungeons);
    // 순열로 각 던전인덱스를 뽑고 
    return answer;
}

function getOrder(cnt,n,order,dungeons){
    if(cnt === n ){
        // console.log(order)
        let dungeonCnt = check(order,K,dungeons);
        answer = (answer < dungeonCnt) ? dungeonCnt : answer;
        return ;
    }
    
    for(let i=0;i<n;i++){
        if(!visited[i]){
            visited[i] = true;
            order[cnt] = i;
            getOrder(cnt+1,n,order,dungeons)
            visited[i] = false;          
        }
    }
}

function check(order,K,dungeons){
    let clearCnt = 0 ;
    for(let index of order){
        let dungeon = dungeons[index]
        
        // 던전 입장이 가능하면 입장하고 피로도 감소시킴. 
        if(dungeon[0] <= K){
            // console.log("오나", K, dungeon)
            K-=dungeon[1];
            clearCnt++;
        }else{
            // console.log("end")
            break;
        }
        
    }
    
    return clearCnt;
}