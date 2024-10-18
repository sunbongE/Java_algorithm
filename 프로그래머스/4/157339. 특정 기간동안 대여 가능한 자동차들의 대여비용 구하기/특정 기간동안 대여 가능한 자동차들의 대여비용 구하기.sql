SELECT 
    C.CAR_ID AS CAR_ID, -- 자동차 ID
    C.CAR_TYPE AS CAR_TYPE, -- 자동차 종류
    ROUND(C.DAILY_FEE * 30 * (100 - P.DISCOUNT_RATE) / 100) AS FEE -- 30일 동안의 대여 금액
FROM 
    CAR_RENTAL_COMPANY_CAR C
JOIN 
    CAR_RENTAL_COMPANY_DISCOUNT_PLAN P ON C.CAR_TYPE = P.CAR_TYPE -- 자동차 종류에 따른 할인율
WHERE 
    C.CAR_TYPE IN ('세단', 'SUV') -- 자동차 종류가 '세단' 또는 'SUV'
    AND P.DURATION_TYPE LIKE '30%' -- 대여 기간이 30일 이상인 할인 정책
    AND C.CAR_ID NOT IN ( -- 대여 기간이 2022-11-01 ~ 2022-12-01인 대여 기록이 없는 자동차
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE 
            END_DATE >= '2022-11-01' 
            AND START_DATE <= '2022-12-01'
    )
GROUP BY 
    C.CAR_ID, C.CAR_TYPE, C.DAILY_FEE, P.DISCOUNT_RATE -- 그룹화하여 집계
HAVING 
    FEE >= 500000 AND FEE < 2000000 -- 대여 금액이 50만원 이상 200만원 미만인 조건
ORDER BY 
    FEE DESC, -- 대여 금액 내림차순
    CAR_TYPE ASC, -- 자동차 종류 오름차순
    CAR_ID DESC; -- 자동차 ID 내림차순
