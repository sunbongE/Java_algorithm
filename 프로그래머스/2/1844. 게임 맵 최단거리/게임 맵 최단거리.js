let visited ;
let row,col;
let map;
function solution(maps) {
    var answer = 0;
    map = maps;
    row = maps.length;
    col = maps[0].length;
    visited = Array.from({length :row}, ()=> Array(col).fill(0))

    // console.log(maps)
    BFS();
    // console.log(visited)
    answer = (visited[row-1][col-1] === 0) ? -1 : visited[row-1][col-1];
    return answer;
}

function BFS(){
    // console.log("?")
    const di = [-1,1,0,0];
    const dj = [0,0,-1,1];
    let q = [];
    q.push([0,0]);
    visited[0][0]=1;
    
    console.log(q, q.length)
    
    
    
    while(q.length!==0){
        let cur = q.shift();
        let ci = cur[0], cj = cur[1];
        
        if(ci === row-1 && cj === col-1){
            return;
        }
        
        for(let i = 0 ; i<4;i++){
            let ni = ci+di[i];
            let nj = cj+dj[i];
            
            if(isIn(ni,nj) && map[ni][nj]===1 && visited[ni][nj]===0){
                visited[ni][nj] = visited[ci][cj]+1;
                q.push([ni,nj]);
            }
        }
        
    }
}
function isIn(ni,nj){
    // console.log("?")
    return 0<= ni && ni<row && 0<= nj && nj < col;
}