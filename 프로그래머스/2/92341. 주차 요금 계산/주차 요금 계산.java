import java.util.*;

class Solution {
    static String endTime ="23:59";
    static HashMap<String,Boolean> isOut;
    static HashMap<String,String> parkingInfo;
    static HashMap<String,Integer> parkingTotalM;
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        isOut = new HashMap<>(); // 마지막에 나갔는지 확인용.
        parkingInfo = new HashMap<>(); // 들어온 차 시간 기록.
        parkingTotalM = new HashMap<>(); // 총 주차 분 기록
        
        for(String data : records ){
            String[] dataSet = data.split(" ");
            String time = dataSet[0];
            String number =  dataSet[1];
            String inOut =  dataSet[2];
            
            if(inOut.equals("IN")){
                isOut.put(number,false);
                parkingInfo.put(number,time); // 입차 시간 기록. 
            }else{
                isOut.put(number,true);
                Integer tmpPSumT = getParkingM(number,time); // 추가한 총 분.
                parkingTotalM.put(number, (parkingTotalM.getOrDefault(number,0)+tmpPSumT));
                // System.out.println("k-> "+number+", v-> "+(parkingTotalM.get(number)));
            }
        }
        
        ArrayList<String> nums = new ArrayList();
        
        // 마지막에 안나간 차 찾아서 마지막 정산시간 기준으로 주차이용 분 기록.
        for(String number : isOut.keySet()){
            nums.add(number);
            if(!isOut.get(number)){
                // 안나갔으면, 
                 Integer tmpPSumT = getParkingM(number,endTime); // 추가한 총 분.
                parkingTotalM.put(number, (parkingTotalM.getOrDefault(number,0)+tmpPSumT));
            }
        }
        
        Collections.sort(nums);
        
        answer = new int[nums.size()];
        
        for(int i=0; i<nums.size(); i++){
            String key = nums.get(i);
            Integer totalM = parkingTotalM.get(key);
            if(totalM <=fees[0]){ // 기본 주차 이용시간 이하로 이용한경우. 기본요금만 부과
                answer[i] = fees[1];
            }else{
                Integer plusmin = (totalM-fees[0])/fees[2];
                
                plusmin += ((totalM-fees[0])%fees[2] == 0) ? 0 : 1;

                // System.out.println(key+" -> "+plusmin+", totalM: "+totalM);
                answer[i] = fees[1] + (plusmin * fees[3]);
            }
        }
        
        return answer;
    }
    
    public Integer getParkingM(String number,String time){
        String[] inTime = parkingInfo.get(number).split(":");
        Integer inH = Integer.parseInt(inTime[0]);
        Integer inM = Integer.parseInt(inTime[1]);

        String[] outTime = time.split(":");
        Integer outH = Integer.parseInt(outTime[0]);
        Integer outM = Integer.parseInt(outTime[1]);

        if(outM<inM){
            outH--;
            outM+=60;
        }

        Integer tmpPSumT = (outH - inH)*60 + (outM-inM);
        return tmpPSumT;
    }
}


/**
24시간 => 몇분 14400000분*1만+10만 범위는 int로 충분함.
5961

05:34 in
07:59 out 누적 시간 : 2시간25분 -> 120+25 = 145분
22:59 in 1분 
23:00 out
총 146분 = 5000원

0148
07:59 in 
19:09 out

out의 분이 더 작은경우 시에서 60분 끌어와
69-59 = 10 + (19-1-7)*60
10 + 11*60 = 670분 
180분에 5000원
670-180 = 490분
10분에 600원 -> 49*600 = 29400
29400원+5000원 : 34400원
*/
