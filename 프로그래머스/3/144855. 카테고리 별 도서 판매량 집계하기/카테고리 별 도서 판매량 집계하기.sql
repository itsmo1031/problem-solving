-- 코드를 입력하세요
WITH MONTHLY_SALES AS (
    SELECT BOOK_ID, EXTRACT(YEAR_MONTH FROM SALES_DATE) AS SALES_MONTH, SUM(SALES) AS SALES
    FROM BOOK_SALES
    WHERE EXTRACT(YEAR_MONTH FROM SALES_DATE) = 202201
    GROUP BY BOOK_ID, SALES_MONTH
)

SELECT B.CATEGORY, SUM(M.SALES) AS TOTAL_SALES
FROM BOOK B
INNER JOIN MONTHLY_SALES M
ON B.BOOK_ID = M.BOOK_ID
GROUP BY B.CATEGORY
ORDER BY B.CATEGORY ASC;