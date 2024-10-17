WITH Total_Rentals AS (
    SELECT 
        CAR_ID,                       -- 자동차 ID
        COUNT(HISTORY_ID) AS TOTAL_RECORDS -- 전체 대여 횟수
    FROM 
        CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE 
        START_DATE BETWEEN '2022-08-01' AND '2022-10-31' -- 2022년 8월 ~ 2022년 10월의 대여 기록
    GROUP BY 
        CAR_ID
    HAVING 
        COUNT(HISTORY_ID) >= 5 -- 총 대여 횟수가 5회 이상인 자동차만 선택
)
SELECT 
    MONTH(START_DATE) AS MONTH,   -- 월 추출
    CAR_RENTAL_COMPANY_RENTAL_HISTORY.CAR_ID, -- 자동차 ID
    COUNT(HISTORY_ID) AS RECORDS  -- 월별 대여 횟수
FROM 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
JOIN 
    Total_Rentals ON CAR_RENTAL_COMPANY_RENTAL_HISTORY.CAR_ID = Total_Rentals.CAR_ID -- 총 대여 횟수가 5회 이상인 자동차만 필터링
WHERE 
    START_DATE BETWEEN '2022-08-01' AND '2022-10-31' -- 2022년 8월 ~ 2022년 10월 기간
GROUP BY 
    MONTH(START_DATE), CAR_RENTAL_COMPANY_RENTAL_HISTORY.CAR_ID
ORDER BY 
    MONTH(START_DATE) ASC,  -- 월 기준 오름차순 정렬
    CAR_ID DESC;            -- 자동차 ID 기준 내림차순 정렬
