INSERT INTO account (account_number, owner, balance, create_date)
SELECT '669-7788', 'Kerem Karaca', 950.0, '2020-03-26 06:15:50'
WHERE NOT EXISTS (SELECT 1 FROM account);