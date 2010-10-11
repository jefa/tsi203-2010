--Insertamos los usuarios administradores
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('ggadmin', 'ggadmin', 'Administrador Gonzalo', 'gegismero@gmail.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('vmadmin', 'vmadmin', 'Administrador Veronica', 'veromanduk@gmail.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('rradmin', 'rradmin', 'Administrador Rodrigo', 'rrivem@gmail.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('jfadmin', 'jfadmin', 'Administrador Javier', 'jefa55@yahoo.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('anadmin', 'anadmin', 'Administrador Andres', 'aenavcam@gmail.com', 'A', now()::timestamp);

--Insertamos los usuarios normales
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('ggismero', 'ggismero', 'Gonzalo Gismero', 'gegismero@gmail.com', 'U', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('veromanduk', 'veromanduk', 'Veronica Manduca', 'veromanduk@gmail.com', 'U', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('rrivem', 'rrivem', 'Rodrigo Rivera', 'rrivem@gmail.com', 'U', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('jefa55', 'jefa55', 'Javier Fradiletti', 'jefa55@yahoo.com', 'U', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('aenavcam', 'aenavcam', 'Andres Navarro', 'aenavcam@yahoo.com', 'U', now()::timestamp);

--Insertamos los eventos
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(1001, 'Cumpleaños de quince de Valentina Rosas', '2011-11-15', 240,'', '8 de Octubre 1845', 'ggadmin','M',now()::timestamp);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(1002, 'Despedida de soltero de Lorenzo Lamas', '2011-11-28', 300,'', '18 de Julio 1221', 'vmadmin','M',now()::timestamp);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(1003, 'Fiesta de Halloween del grupo de TSI2', '2011-10-31', 180,'', 'Armando Esteban Quito 665', 'rradmin','E',now()::timestamp);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(1004, 'Previa de Perry vs Juventus', '2011-10-28', 180,'', 'Aquiles Baeza 1025', 'anadmin','M',now()::timestamp);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(1005, 'Fiesta Rave de fin de año', '2010-12-31', 300,'', 'Tunel de 8 de Octubre', 'jfadmin','E',now()::timestamp);

--Insertamos los moderadores de los eventos	
INSERT INTO MODS(USR_ID, EVT_ID, REG_DATE) VALUES('ggismero', 1001, now()::timestamp);
INSERT INTO MODS(USR_ID, EVT_ID, REG_DATE) VALUES('rrivem', 1002, now()::timestamp);
INSERT INTO MODS(USR_ID, EVT_ID, REG_DATE) VALUES('veromanduk', 1004, now()::timestamp);

--Insertamos los participantes de los eventos
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('ggismero', 1001, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('rrivem', 1001, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('rrivem', 1002, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('aenavcam', 1002, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('jefa55', 1003, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('ggismero', 1003, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('veromanduk', 1004, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('jefa55', 1004, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('aenavcam', 1005, now()::timestamp);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID, REG_DATE) VALUES('veromanduk', 1005, now()::timestamp);
	
--Insertamos los contenidos
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1001, 1001, 0, 'ggismero', 'La foto 1001', NULL, false, 100, 'url_de_1001', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1002, 1002, 0, 'rrivem', 'La foto 1002', NULL, false, 100, 'url_de_1002', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1003, 1003, 0, 'jefa55', 'La foto 1003', NULL, false, 100, 'url_de_1003', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1004, 1004, 0, 'veromanduk', 'La foto 1004', NULL, false, 100, 'url_de_1004', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1005, 1005, 0, 'aenavcam', 'La foto 1005', NULL, false, 100, 'url_de_1005', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1006, 1001, 1, 'rrivem', 'La foto 1006', NULL, false, 100, 'url_de_1006', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1007, 1002, 1, 'aenavcam', 'La foto 1007', NULL, false, 100, 'url_de_1007', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1008, 1003, 1, 'ggismero', 'La foto 1008', NULL, false, 100, 'url_de_1008', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1009, 1004, 1, 'jefa55', 'La foto 1009', NULL, false, 100, 'url_de_1009', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS, CREATOR, DESCRIPTION, DURATION, ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1010, 1005, 1, 'veromanduk', 'La foto 1010', NULL, false, 100, 'url_de_1010', 'P', now()::timestamp);

--Insertamos comentarios para los contenidos
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('rrivem', 1001, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('ggismero', 1001, 'Si, está muy buena. :D', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('aenavcam', 1002, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('rrivem', 1002, 'Si, está muy buena. :)', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('ggismero', 1003, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('jefa55', 1003, 'Si, está muy buena. ;)', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('jefa55', 1004, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('veromanduk', 1004, 'Si, está muy buena. :O', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('veromanduk', 1005, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('aenavcam', 1005, 'Si, está muy buena. :$', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('ggismero', 1006, 'Para el recuerdo.', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('rrivem', 1006, 'Totalmente. :(', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('rrivem', 1007, 'Para el recuerdo.', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('aenavcam', 1007, 'Totalmente. T.T', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('jefa55', 1008, 'Para el recuerdo.', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('ggismero', 1008, 'Totalmente. *.*', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('veromanduk', 1009, 'Para el recuerdo.', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('jefa55', 1009, 'Totalmente. n.n', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('aenavcam', 1010, 'Para el recuerdo.', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('veromanduk', 1010, 'Totalmente. u.u', now()::timestamp, now()::timestamp);

--Insertamos los rating para los contenidos
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('ggismero', 1001, 1, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('rrivem', 1001, 1, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('rrivem', 1002, 2, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('aenavcam', 1002, 2, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('jefa55', 1003, 3, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('ggismero', 1003, 3, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('veromanduk', 1004, 4, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('jefa55', 1004, 4, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('aenavcam', 1005, 5, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('veromanduk', 1005, 5, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('ggismero', 1006, 2, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('rrivem', 1006, 4, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('rrivem', 1007, 1, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('aenavcam', 1007, 3, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('jefa55', 1008, 5, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('ggismero', 1008, 4, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('veromanduk', 1009, 4, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('jefa55', 1009, 3, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('aenavcam', 1010, 2, now()::timestamp);
INSERT INTO RATINGS(USR_ID, CNT_ID, SCORE, REG_DATE) VALUES('veromanduk', 1010, 1, now()::timestamp);

--Insertamos los tags para los contenidos
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1001, 1001, 'ggismero', NULL, NULL, NULL, 'rrivem', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1002, 1002, 'rrivem', NULL, NULL, NULL, 'aenavcam', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1003, 1003, 'jefa55', NULL, NULL, NULL, 'ggismero', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1004, 1004, 'veromanduk', NULL, NULL, NULL, 'jefa55', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1005, 1005, 'aenavcam', NULL, NULL, NULL, 'veromanduk', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1006, 1006, 'rrivem', NULL, NULL, 'usuario_externo_1', NULL, 'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1007, 1007, 'aenavcam', NULL, NULL, 'usuario_externo_2', NULL,'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1008, 1008, 'ggismero', NULL, NULL, 'usuario_externo_3', NULL,'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1009, 1009, 'jefa55', NULL, NULL, 'usuario_externo_4', NULL,'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES(1010, 1010, 'veromanduk', NULL, NULL, 'usuario_externo_5', NULL,'N', now()::timestamp);
