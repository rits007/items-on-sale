INSERT INTO USERS(ID, PASSWORD, enabled) VALUES ('ritu','pass',true);
INSERT INTO USERS(ID, PASSWORD, enabled) VALUES ('bruce','pass',false);
INSERT INTO ORDER_HISTORY (USER_ID , USER_RATING ) VALUES ('bruce', 4);
INSERT INTO ORDER_HISTORY (USER_ID , USER_RATING ) VALUES ('bruce', 1);
INSERT INTO ORDER_HISTORY (USER_ID , USER_RATING ) VALUES ('ritu', 1);
INSERT INTO ITEMS(ID, ITEM_GLOBAL_RATING, ORDER_ID ,ON_SALE) VALUES (222, 3, 1, true);
INSERT INTO ITEMS(ID, ITEM_GLOBAL_RATING, ORDER_ID ,ON_SALE) VALUES (333, 3,3, false);
INSERT INTO ITEMS(ID, ITEM_GLOBAL_RATING, ORDER_ID ,ON_SALE) VALUES (444, 3,2, true);
INSERT INTO WISH_LIST (USER_ID , ITEM_LIST ) VALUES ('ritu', 222);
INSERT INTO WISH_LIST (USER_ID , ITEM_LIST ) VALUES ('bruce', 333);
INSERT INTO authorities(ID,AUTHORITY)values('ritu','ROLE_ADMIN');
