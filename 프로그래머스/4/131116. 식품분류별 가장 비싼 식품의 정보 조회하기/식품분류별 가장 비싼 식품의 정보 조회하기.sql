SELECT 
    FP.CATEGORY, 
    FP.PRICE AS MAX_PRICE, 
    FP.PRODUCT_NAME
FROM 
    FOOD_PRODUCT FP
JOIN 
    (
        SELECT 
            CATEGORY, 
            MAX(PRICE) AS MAX_PRICE
        FROM 
            FOOD_PRODUCT
        WHERE 
            CATEGORY IN ('과자', '국', '김치', '식용유')
        GROUP BY 
            CATEGORY
    ) AS MAX_PRICE_TABLE
ON 
    FP.CATEGORY = MAX_PRICE_TABLE.CATEGORY AND FP.PRICE = MAX_PRICE_TABLE.MAX_PRICE
WHERE 
    FP.CATEGORY IN ('과자', '국', '김치', '식용유')
ORDER BY 
    MAX_PRICE DESC;
