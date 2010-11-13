--Insertamos los usuarios administradores
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('ggadmin', 'ggadmin', 'Administrador Gonzalo', 'gegismero@gmail.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('vmadmin', 'vmadmin', 'Administrador Veronica', 'veromanduk@gmail.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('rradmin', 'rradmin', 'Administrador Rodrigo', 'rrivem@gmail.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('jfadmin', 'jfadmin', 'Administrador Javier', 'jefa55@yahoo.com', 'A', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('anadmin', 'anadmin', 'Administrador Andres', 'aenavcam@gmail.com', 'A', now()::timestamp);

--Insertamos los usuarios normales
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('ggismero', 'ggismero', 'Gonzalo Gismero', 'gegismero@gmail.com', 'N', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('veromanduk', 'veromanduk', 'Veronica Manduca', 'veromanduk@gmail.com', 'N', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('rrivem', 'rrivem', 'Rodrigo Rivera', 'rrivem@gmail.com', 'N', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('jefa55', 'jefa55', 'Javier Fradiletti', 'jefa55@yahoo.com', 'N', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, REG_DATE) VALUES ('aenavcam', 'aenavcam', 'Andres Navarro', 'aenavcam@yahoo.com', 'N', now()::timestamp);

--Insertamos las categorias
insert into "evtCategory" values('Casamiento');
insert into "evtCategory" values('Cumplea�os de quince');
insert into "evtCategory" values('Aniversario');
insert into "evtCategory" values('Otro');

--Insertamos los eventos
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE, CATEGORY) VALUES(1001, 'Cumplea�os de quince de Valentina Rosas', '2011-11-15', 240,'', '8 de Octubre 1845', 'ggadmin','M',now()::timestamp, 'Cumplea�os de quince');
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE, CATEGORY) VALUES(1002, 'Despedida de soltero de Lorenzo Lamas', '2011-11-28', 300,'', '18 de Julio 1221', 'vmadmin','M',now()::timestamp, 'Otro');
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE, CATEGORY) VALUES(1003, 'Fiesta de Halloween del grupo de TSI2', '2011-10-31', 180,'', 'Armando Esteban Quito 665', 'rradmin','E',now()::timestamp, 'Otro');
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE, CATEGORY) VALUES(1004, 'Previa de Perry vs Juventus', '2011-10-28', 180,'', 'Aquiles Baeza 1025', 'anadmin','M',now()::timestamp, 'Otro');
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE, CATEGORY) VALUES(1005, 'Fiesta Rave de fin de a�o', '2010-12-31', 300,'', 'Tunel de 8 de Octubre', 'jfadmin','E',now()::timestamp, 'Otro');

--Insertamos los moderadores de los eventos	
INSERT INTO MODS(USR_ID, EVT_ID) VALUES('ggismero', 1001);
INSERT INTO MODS(USR_ID, EVT_ID) VALUES('rrivem', 1002);
INSERT INTO MODS(USR_ID, EVT_ID) VALUES('veromanduk', 1004);

--Insertamos los participantes de los eventos
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('ggismero', 1001);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('rrivem', 1001);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('rrivem', 1002);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('aenavcam', 1002);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('jefa55', 1003);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('ggismero', 1003);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('veromanduk', 1004);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('jefa55', 1004);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('aenavcam', 1005);
INSERT INTO PARTICIPANTS(USR_ID, EVT_ID) VALUES('veromanduk', 1005);
	
--Insertamos los contenidos
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1001, 1001, 0, 'ggismero', 'La foto 1001', NULL, false, NULL, 100,  '1001/6980b2c7-d084-4a5e-a17a-c7c68bac251d.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1002, 1002, 0, 'rrivem', 'La foto 1002', NULL, false, NULL, 100, '1002/88077e95-4542-4b96-9b0c-9c62c74617f0.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1003, 1003, 0, 'jefa55', 'La foto 1003', NULL, false, NULL, 100, '1003/10415d41-6208-437d-9cd2-e694976b1616.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1004, 1004, 0, 'veromanduk', 'La foto 1004', NULL, false, NULL, 100, '1004/75f64201-0eda-43c5-b00f-022d18411639.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1005, 1005, 0, 'aenavcam', 'La foto 1005', NULL, false, NULL, 100, '1005/c18462e3-ee1f-4dc9-b8cc-257751cd9032.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1006, 1001, 1, 'rrivem', 'La foto 1006', NULL, false,  NULL, 100, '1001/4272b42e-7b8b-4e1f-8d36-d771dd06f8f5.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1007, 1002, 1, 'aenavcam', 'La foto 1007', NULL, false, NULL, 100, '1002/d8388749-00f3-4340-9c43-53cc72490f4a.PNG', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1008, 1003, 1, 'ggismero', 'La foto 1008', NULL, false, NULL, 100, '1003/4748301a-5f66-4306-9992-6138c4b3f1c0.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1009, 1004, 1, 'jefa55', 'La foto 1009', NULL, false, NULL, 100, '1004/b5b5f33f-70a6-4245-96cc-75ddb4955b6e.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, ALBUM, POS_ALBUM, SIZE, URL, FLAGS, REG_DATE) VALUES(1010, 1005, 1, 'veromanduk', 'La foto 1010', NULL, false, NULL, 100, '1005/edf09de3-7523-495b-a935-84e27a90569c.png', 'P', now()::timestamp);

--Seteamos el cover para los eventos
UPDATE EVENTS SET COVER = 1001 WHERE EVT_ID_AUTO = 1001;
UPDATE EVENTS SET COVER = 1002 WHERE EVT_ID_AUTO = 1002;
UPDATE EVENTS SET COVER = 1003 WHERE EVT_ID_AUTO = 1003;
UPDATE EVENTS SET COVER = 1004 WHERE EVT_ID_AUTO = 1004;
UPDATE EVENTS SET COVER = 1005 WHERE EVT_ID_AUTO = 1005;

--Insertamos las categorias de contenidos para los contenidos
           
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1001, 'Todas', 1001);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1002, 'Familiares', 1001);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1003, 'Amigos', 1001);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1004, 'Todas', 1002);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1005, 'Todas', 1003);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1006, 'Todas', 1004);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1007, 'Barra', 1004);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1008, 'Todas', 1005);

--Agregamos los contenidos a las categorias
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1001, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1006, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1001, 1002);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1006, 1003);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1002, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1007, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1003, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1008, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1004, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1009, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1004, 1007);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1005, 1008);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1010, 1008);

--Insertamos comentarios para los contenidos
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('rrivem', 1001, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('ggismero', 1001, 'Si, est� muy buena. :D', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('aenavcam', 1002, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('rrivem', 1002, 'Si, est� muy buena. :)', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('ggismero', 1003, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('jefa55', 1003, 'Si, est� muy buena. ;)', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('jefa55', 1004, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('veromanduk', 1004, 'Si, est� muy buena. :O', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('veromanduk', 1005, 'Que buena foto!!!', now()::timestamp, now()::timestamp);
INSERT INTO COMMENT(USR_ID, CNT_ID, TEXT, DATE, REG_DATE) VALUES('aenavcam', 1005, 'Si, est� muy buena. :$', now()::timestamp, now()::timestamp);
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


--Profile pictures para los usuarios creados
update users set img_path = 'profile/avatar-logos-msn-122.png' where username = 'ggismero';
update users set img_path = 'profile/527635avatar-mortal-relieve.png' where username = 'rrivem';
update users set img_path = 'profile/msn.png' where username = 'veromanduk';
update users set img_path = 'profile/AVATAR0021.png' where username = 'jefa55';
update users set img_path = 'profile/pinguino_avatar_msn.png' where username = 'aenavcam';
