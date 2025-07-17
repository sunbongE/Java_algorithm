/*
가장 최근에 사용한 캐시를 관리하는 방법..
캐시 정보 객체 CachedData 생성
useTime, city 관리.

공통으로 serverTime 기록.
새로운 값을 캐시에 넣어야 할 때, serverTime-useTime이 가장 큰 값을 타겟으로 변경한다.

정보가 캐시에 있으면 answer증가 및 사용된 캐시데이터 useTime업데이트

*/
import java.util.*;
import java.io.*;

class Solution {
    private static ArrayList<CachedData> cachedData;
    public int solution(int cacheSize, String[] cities) {
        
        for(int i=0; i<cities.length; i++){
            cities[i] = cities[i].toLowerCase();
        }
        
        boolean isHit ;
        
        int serverTime = 0;
        int answer = 0;
        cachedData = new ArrayList<>();
        
        for(int i=0; i<cities.length;i++){
            serverTime++;
            isHit = false;
            String city = cities[i];
            int idx = isInCity(city);
            if(idx != -1){ // 도시가 캐시에 있는 경우.
                isHit = true;
                cachedData.get(idx).useTime = serverTime; // 사용한 캐시 시간 갱신
            }else{ // 캐시에 없는경우, city를 캐시에 넣는다.
                if(cachedData.size() < cacheSize){ // 캐시에 공간이 남았으면 걍 넣기.
                    cachedData.add(new CachedData(serverTime, city));
                }else{
                    // 캐시에 있는 데이터 중 사용한지 오래된 데이터를 삭제한다.
                    if(cacheSize>0){
                        int oldIdx = getOldIdx(serverTime);
                        cachedData.remove(oldIdx);
                        cachedData.add(new CachedData(serverTime, city));    
                    }
                }
            }

            answer += (isHit) ? 1 : 5;
        }
        return answer;
    }

    public int getOldIdx(int serverTime){
        int oldIdx = -1;
        int maxTime = -1;
        
        for(int i=0; i<cachedData.size(); i++){
             int tmp = serverTime-cachedData.get(i).useTime;
            if(tmp>maxTime){
                maxTime = tmp;
                oldIdx = i;
            }
        }
        
        return oldIdx;
    }
    
    public int isInCity(String city){
        
        for(int i=0; i<cachedData.size(); i++){
            if(cachedData.get(i).city.equals(city)) return i;
        }
        return -1;
    }
    
    public class CachedData{
        int useTime;
        String city;
        public CachedData(int serverTime, String city){
            this.useTime=serverTime;
            this.city=city;
        }
    }
}