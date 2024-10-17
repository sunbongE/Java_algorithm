# -- 코드를 입력하세요
# -- 
# SELECT 
#     A.AUTHOR_ID AS AUTHOR_ID,
#     B.AUTHOR_NAME,
#     A.CATEGORY  ,  
#     (A.PRICE * SUM(C.SALES) ) AS TOTAL_SALES
# FROM BOOK A JOIN AUTHOR B
# ON A.AUTHOR_ID = B.AUTHOR_ID
# JOIN BOOK_SALES C
# ON A.BOOK_ID = C.BOOK_ID
# WHERE C.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
# GROUP BY A.AUTHOR_ID, A.CATEGORY
# ORDER BY A.AUTHOR_ID, A.CATEGORY DESC

SELECT  C.AUTHOR_ID,C.AUTHOR_NAME, A.CATEGORY, SUM(A.PRICE*B.SALES) AS TOTAL_SALES
FROM BOOK A JOIN BOOK_SALES B
ON A.BOOK_ID = B.BOOK_ID
JOIN AUTHOR C ON A.AUTHOR_ID = C.AUTHOR_ID
WHERE B.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
GROUP BY A.CATEGORY, A.AUTHOR_ID
ORDER BY A.AUTHOR_ID, A.CATEGORY DESC
