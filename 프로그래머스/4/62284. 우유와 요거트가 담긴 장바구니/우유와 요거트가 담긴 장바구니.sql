-- 코드를 입력하세요
SELECT CART_ID
FROM (
    SELECT CART_ID, GROUP_CONCAT(NAME) AS NAME
    FROM CART_PRODUCTS
    GROUP BY CART_ID
) P
WHERE NAME LIKE '%Milk%' AND NAME LIKE '%Yogurt%'
ORDER BY CART_ID ASC;