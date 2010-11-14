--Insertamos los usuarios administradores
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('ggadmin', 'ggadmin', 'Administrador Gonzalo', 'gegismero@gmail.com', 'A', 'profile/ggadmin/083ab683-c98a-4934-b7ef-56515bfce102.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('vmadmin', 'vmadmin', 'Administrador Veronica', 'veromanduk@gmail.com', 'A', 'profile/vmadmin/msn.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('rradmin', 'rradmin', 'Administrador Rodrigo', 'rrivem@gmail.com', 'A', 'profile/rradmin/527635avatar-mortal-relieve.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('jfadmin', 'jfadmin', 'Administrador Javier', 'jefa55@yahoo.com', 'A', 'profile/jfadmin/AVATAR0021.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('anadmin', 'anadmin', 'Administrador Andres', 'aenavcam@gmail.com', 'A', 'profile/anadmin/pinguino_avatar_msn.png', now()::timestamp);

--Insertamos los usuarios normales
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('ggismero', 'ggismero', 'Gonzalo Gismero', 'gegismero@gmail.com', 'N', 'profile/ggismero/083ab683-c98a-4934-b7ef-56515bfce102.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('veromanduk', 'veromanduk', 'Veronica Manduca', 'veromanduk@gmail.com', 'N', 'profile/veromanduk/msn.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('rrivem', 'rrivem', 'Rodrigo Rivera', 'rrivem@gmail.com', 'N', 'profile/rrivem/527635avatar-mortal-relieve.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('jefa55', 'jefa55', 'Javier Fradiletti', 'jefa55@yahoo.com', 'N', 'profile/jefa55/AVATAR0021.png', now()::timestamp);
INSERT INTO USERS(USERNAME, PASSWORD, NAME, EMAIL, FLAGS, IMG_PATH, REG_DATE) VALUES 
	('aenavcam', 'aenavcam', 'Andres Navarro', 'aenavcam@yahoo.com', 'N', 'profile/aenavcam/pinguino_avatar_msn.png', now()::timestamp);

--Insertamos las categorias de los eventos	
insert into "evtCategory" values('Aniversario');
insert into "evtCategory" values('Casamiento');
insert into "evtCategory" values('Cumpleaños de quince');
insert into "evtCategory" values('Otro');

--Insertamos los eventos TODO: Falata asignar los covers.
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DESCRIPTION, ADDRESS, CREATOR, REG_DATE, CATEGORY, DURATION, HASHTAG, COVER, LATITUDE, LONGITUDE) VALUES
	(1001, 'Cumpleaños de quince de Valentina Rosas', '2011-11-15', 'Los esperamos a todos en la fiesta de Valentina. Confirmar asistencia.', '8 de Octubre 1845, Montevideo, Uruguay', 'ggadmin', now()::timestamp, 'Cumpleaños de quince', 'De 22 a 06', '#quinceaniera', NULL, -34.8852282, -56.1567276);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DESCRIPTION, ADDRESS, CREATOR, REG_DATE, CATEGORY, DURATION, HASHTAG, COVER, LATITUDE, LONGITUDE) VALUES
	(1002, 'Despedida de soltero de Lorenzo Lamas', '2011-11-28', 'Festejemos el último día de libertad de Lorenzo.', '18 de Julio 1221, Montevideo, Uruguay', 'vmadmin', now()::timestamp, 'Otro', 'De 23 a 04', '#lorenzoLamas', NULL, -34.9040901, -56.1817973);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DESCRIPTION, ADDRESS, CREATOR, REG_DATE, CATEGORY, DURATION, HASHTAG, COVER, LATITUDE, LONGITUDE) VALUES
	(1003, 'Fiesta de Halloween del grupo de TSI2', '2011-10-31', 'Se debe concurrir con disfraz.', 'Cerrito 665, Montevideo, Uruguay', 'rradmin', now()::timestamp, 'Otro', 'A partir de las 22', '#halloween', NULL, -34.9034126, -56.201218);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DESCRIPTION, ADDRESS, CREATOR, REG_DATE, CATEGORY, DURATION, HASHTAG, COVER, LATITUDE, LONGITUDE) VALUES
	(1004, 'Previa de Perry vs Juventus', '2011-10-28', 'Recuerden que es fiesta lluvia', 'Av. Italia 1025, Montevideo, Uruguay', 'anadmin', now()::timestamp, 'Otro', 'Una hora antes del partido', '#perry', NULL, -34.8915139674325, -56.1580753326416);
INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DESCRIPTION, ADDRESS, CREATOR, REG_DATE, CATEGORY, DURATION, HASHTAG, COVER, LATITUDE, LONGITUDE) VALUES
	(1005, 'Fiesta Rave de fin de año', '2010-12-31', 'DJ invitado: DJ Rulo.', 'Tunel de 8 de Octubre, Montevideo, Uruguay', 'jfadmin', now()::timestamp, 'Otro', 'A partir de las 2 de la mañana', '#rave', NULL, -34.8938723829159, -56.1636972427368);
	
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
--Fotos
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1001, 1001, 0, 'ggismero', 'Foto del salón de fiestas.', NULL, 100,  '1001/6980b2c7-d084-4a5e-a17a-c7c68bac251d.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1002, 1002, 0, 'rrivem', 'Lorenzo Lamas.', NULL, 100, '1002/88077e95-4542-4b96-9b0c-9c62c74617f0.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1003, 1003, 0, 'jefa55', 'Una calabaza.', NULL, 100, '1003/10415d41-6208-437d-9cd2-e694976b1616.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1004, 1004, 0, 'veromanduk', 'Lionel Messi.', NULL, 100, '1004/75f64201-0eda-43c5-b00f-022d18411639.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1005, 1005, 0, 'aenavcam', 'Foto de la fiesta.', NULL, 100, '1005/c18462e3-ee1f-4dc9-b8cc-257751cd9032.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1006, 1001, 1, 'rrivem', 'Foto grupal.',  NULL, 100, '1001/4272b42e-7b8b-4e1f-8d36-d771dd06f8f5.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1007, 1002, 1, 'aenavcam', 'Foto de la pareja.', NULL, 100, '1002/d8388749-00f3-4340-9c43-53cc72490f4a.PNG', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1008, 1003, 1, 'ggismero', 'Dos calabazas.', NULL, 100, '1003/4748301a-5f66-4306-9992-6138c4b3f1c0.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1009, 1004, 1, 'jefa55', 'Otra foto.', NULL, 100, '1004/b5b5f33f-70a6-4245-96cc-75ddb4955b6e.png', 'P', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1010, 1005, 1, 'veromanduk', 'Otra foto de la fiesta.', NULL, 100, '1005/edf09de3-7523-495b-a935-84e27a90569c.png', 'P', now()::timestamp);
--Videos
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1011, 1001, 2, 'ggismero', 'Entrada de la cumpleañera.', NULL, 100,  'http://www.youtube.com/v/cOs4cDmOMuw?fs=1&amp;hl=es_ES', 'V', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1012, 1002, 2, 'aenavcam', 'Introducción de la serie.', NULL, 100,  'http://www.youtube.com/v/Q19wcWo45ck?fs=1&amp;hl=es_ES', 'V', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1013, 1003, 2, 'ggismero', 'Ideas para decoraciones.', NULL, 100,  'http://www.youtube.com/v/2zc3qJ56bdE?fs=1&amp;hl=es_ES', 'V', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1014, 1004, 2, 'veromanduk', 'Video de un partido anterior.', NULL, 100,  'http://www.youtube.com/v/pI_vJ8PFrGI?fs=1&amp;hl=es_ES', 'V', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1015, 1005, 2, 'veromanduk', 'Video de la fiesta.', NULL, 100,  'http://www.youtube.com/v/TZemiehQPJI?fs=1&amp;hl=es_ES', 'V', now()::timestamp);
--Imagenes externas
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1016, 1001, 3, 'rrivem', 'Torta del cumpleaños.', NULL, 100,  '1001/4087d900-82aa-4838-b464-b07d6bfaf79d.jpg', 'Q', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1017, 1002, 3, 'rrivem', 'Autógrafo de Lorenzo.', NULL, 100,  '1002/42c10948-70d2-4f5b-ba6f-620f1dbc42c9.jpg', 'Q', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1018, 1003, 3, 'jefa55', 'De google images.', NULL, 100,  '1003/19efbce0-2876-4e51-9fec-44159fa0082f.jpg', 'Q', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1019, 1004, 3, 'jefa55', 'El escudo de Perry.', NULL, 100,  '1004/perry.jpeg', 'Q', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1020, 1005, 3, 'aenavcam', 'Foto tomada de google images.', NULL, 100,  '1005/bfb05027-b5f7-44f7-bcbb-172644847809.jpg', 'Q', now()::timestamp);
--Videos externos
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1021, 1001, 4, 'ggismero', 'Canción de los 15.', NULL, 100,  'http://www.youtube.com/v/I9OpVLqBvTM?fs=1&amp;hl=es_ES', 'W', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1022, 1002, 4, 'aenavcam', 'Reclamen de la serie.', NULL, 100,  'http://www.youtube.com/v/eRqAWQLpRDU?fs=1&amp;hl=es_ES', 'W', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1023, 1003, 4, 'ggismero', 'Perros!!!.', NULL, 100,  'http://www.youtube.com/v/j6UoVlfGnv8?fs=1&amp;hl=es_ES', 'W', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1024, 1004, 4, 'jefa55', 'La mano de Suarez.', NULL, 100,  'http://www.youtube.com/v/yyr05rVen1Q?fs=1&amp;hl=es_ES', 'W', now()::timestamp);
INSERT INTO CONTENT(CNT_ID_AUTO, EVT_ID, POS_GALLERY, CREATOR, DESCRIPTION, DURATION, SIZE, URL, FLAGS, REG_DATE) VALUES
	(1025, 1005, 4, 'aenavcam', 'Video de uno de los DJs.', NULL, 100,  'http://www.youtube.com/v/__uVTxkbcp8?fs=1&amp;hl=es_ES', 'W', now()::timestamp);
	
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
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1009, 'DJ', 1005);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1010, 'Album', 1001);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1011, 'Album', 1002);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1012, 'Album', 1003);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1013, 'Album', 1004);
INSERT INTO "cntCategory"(CAT_ID_AUTO, CATEGORY, EVT_ID) VALUES (1014, 'Album', 1005);

--Agregamos los contenidos a las categorias
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1001, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1006, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1011, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1016, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1021, 1001);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1001, 1002);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1006, 1003);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1002, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1007, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1012, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1017, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1022, 1004);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1003, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1008, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1013, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1018, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1023, 1005);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1004, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1009, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1004, 1007);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1014, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1019, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1024, 1006);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1005, 1008);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1010, 1008);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1015, 1008);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1020, 1008);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1025, 1008);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1025, 1009);

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
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1001, 1001, 'ggismero', 0, 0, NULL, 'rrivem', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1002, 1002, 'rrivem', 0, 0, NULL, 'aenavcam', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1003, 1003, 'jefa55', 0, 0, NULL, 'ggismero', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1004, 1004, 'veromanduk', 0, 0, NULL, 'jefa55', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1005, 1005, 'aenavcam', 0, 0, NULL, 'veromanduk', 'U', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1006, 1006, 'rrivem', 0, 0, 'usuario_externo_1', NULL, 'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1007, 1007, 'aenavcam', 0, 0, 'usuario_externo_2', NULL,'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1008, 1008, 'ggismero', 0, 0, 'usuario_externo_3', NULL,'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1009, 1009, 'jefa55', 0, 0, 'usuario_externo_4', NULL,'N', now()::timestamp);
INSERT INTO TAGS(TAG_ID_AUTO, CNT_ID, CREATOR, "posX", "posY", USR_TAG_CUSTOM, USR_TAG, FLAGS, REG_DATE) VALUES
	(1010, 1010, 'veromanduk', 0, 0, 'usuario_externo_5', NULL,'N', now()::timestamp);
	
--Cerramos 2 albumes
UPDATE CONTENT SET POS_ALBUM = 0 WHERE CNT_ID_AUTO = 1001;
UPDATE CONTENT SET POS_ALBUM = 1 WHERE CNT_ID_AUTO = 1006;
UPDATE CONTENT SET POS_ALBUM = 2 WHERE CNT_ID_AUTO = 1016;
UPDATE CONTENT SET POS_ALBUM = 3 WHERE CNT_ID_AUTO = 1021;
UPDATE CONTENT SET POS_ALBUM = 0 WHERE CNT_ID_AUTO = 1005;
UPDATE CONTENT SET POS_ALBUM = 1 WHERE CNT_ID_AUTO = 1010;
UPDATE CONTENT SET POS_ALBUM = 2 WHERE CNT_ID_AUTO = 1015;
UPDATE CONTENT SET POS_ALBUM = 4 WHERE CNT_ID_AUTO = 1020;
UPDATE CONTENT SET POS_ALBUM = 3 WHERE CNT_ID_AUTO = 1025;

INSERT INTO ALBUM(EVT_ID, ALB_ID_AUTO, REG_DATE) VALUES(1001, 1001, now()::timestamp);
INSERT INTO ALBUM(EVT_ID, ALB_ID_AUTO, REG_DATE) VALUES(1005, 1002, now()::timestamp);

INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1001, 1010);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1006, 1010);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1016, 1010);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1021, 1010);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1005, 1014);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1010, 1014);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1015, 1014);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1020, 1014);
INSERT INTO "contentCntCategory"(CNT_ID, CAT_ID) VALUES (1025, 1014);