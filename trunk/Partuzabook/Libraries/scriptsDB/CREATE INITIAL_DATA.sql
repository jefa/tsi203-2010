-- Insert Admin and regular users
INSERT INTO users(
            username, "password", flags, reg_date, name, email)
    VALUES ('admin', 'admin', 'A', '2010/10/02 10:30'::timestamp, 'Administrador', 'admin@partuzabook.com');

INSERT INTO users(
            username, "password", flags, reg_date, name, email)
    VALUES ('robi', '10101', 'N', '2010/10/02 10:30'::timestamp, 'Robinho', 'robi@partuzabook.com');


INSERT INTO users(
            username, "password", flags, reg_date, name, email)
    VALUES ('vero', 'vero', 'N', '2010/10/02 10:30'::timestamp, 'Veronica Manduca', 'veromanduk@gmail.com');


INSERT INTO users(
            username, "password", flags, reg_date, name, email)
    VALUES ('rodri', 'rodri', 'N', '2010/10/02 10:30'::timestamp, 'Rodrigo Rivera', 'rrivem@gmail.com');


INSERT INTO users(
            username, "password", flags, reg_date, name, email)
    VALUES ('gonza', 'gonza', 'N', '2010/10/02 10:30'::timestamp, 'Gonzalo Gismero', 'gegismero@gmail.com');



-- Insert new events
INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('Vero se recibe', '2010-11-15', '200', 'Vengan todos', 'Mi dire', 'admin',
 	    	'E','2010/10/02 10:30'::timestamp, '0');

INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('Cumple Vero', '2011-03-08', '200', 'Vengan todos', 'Mi dire', 'admin',
 	    	'M','2010/10/02 17:30'::timestamp, '1');

INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('SUMO.UY', '2010-09-25', '100', 'Vengan todos', 'Aulario', 'admin',
 	    	'E','2010/10/02 17:30'::timestamp, '2');

INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('Cumple Robi', '2010-12-08', '3000', 'Vengan todos', 'Lab Robotica', 'admin', 'M','2010/10/02 17:30'::timestamp, '3');

-- Insert users participant to events
INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('vero', '2010/10/02 17:30'::timestamp, 0);

INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('vero', '2010/10/02 17:30'::timestamp, 1);

INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('vero', '2010/10/02 17:30'::timestamp, 2);

INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('vero', '2010/10/02 17:30'::timestamp, 3);

INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('rodri', '2010/10/02 17:30'::timestamp, 1);

INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('gonza', '2010/10/02 17:30'::timestamp, 1);

INSERT INTO participants(
            usr_id, reg_date, evt_id)
    VALUES ('robi', '2010/10/02 17:30'::timestamp, 1);
  
-- Insert Contents
INSERT INTO "content"(
            cnt_id_auto, creator, description, size, url, flags, reg_date, 
            duration, album, evt_id)
    VALUES ('0', 'admin', 'Esta es la foto del admin', '200', 'miurl', 'P', '2010/10/02 17:30'::timestamp, 
            '100', 'false', '1');

INSERT INTO "content"(
            cnt_id_auto, creator, description, size, url, flags, reg_date, 
            duration, album, evt_id)
    VALUES ('1', 'vero', 'Video sacado por vero', '100', 'miurl', 'V', '2010/10/02 17:30'::timestamp, 
            '100', 'false', '1');



--Insertar usuarios administradores
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('ggadmin', 'ggadmin', 'Administrador Gonzalo', 'gegismero@gmail.com', 'A', now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('vmadmin', 'vmadmin', 'Administrador Veronica', 'veromanduk@gmail.com','A',  now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('rradmin', 'rradmin', 'Administrador Rodrigo', 'rrivem@gmail.com','A',  now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('jfadmin', 'jfadmin', 'Administrador Javier', 'jefa55@yahoo.com','A',  now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('anadmin', 'anadmin', 'Administrador Andres', 'aenavcam@gmail.com','A',  now()::timestamp);

--Insertar usuarios normales
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('ggismero', 'Gonzalo Gismero', 'gegismero@gmail.com', 'ggismero', 'A', now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('veromnaduk', 'veromnaduk', 'Veronica Manduca', 'veromanduk@gmail.com', 'A', now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('rrivem', 'rrivem', 'Rodrigo Rivera', 'rrivem@gmail.com', 'A', now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('jefa55', 'jefa55', 'Javier Fradiletti', 'jefa55@yahoo.com', 'A', now()::timestamp);
--INSERT INTO USERS(username, password, name, email, flags, reg_date) VALUES ('aenavcam', 'aenavcam', 'Andres Navarro', 'aenavcam@yahoo.com', 'A', now()::timestamp);

--Insertar eventos
--INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(1, 'Cumpleaños de quince de Manuela Carolo', '2011-11-15', 240,'', 'Tomás de la Fuente 1845', 'ggadmin','M',now()::timestamp);

--INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(2, 'Despedida de soltero de Lorenzo Lamas', '2011-11-28', 300,'', 'Victoria Celeste 940', 'vmadmin','M',now()::timestamp);

--INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(3, 'Fiesta de Halloween del grupo de TSI2', '2011-10-31', 180,'', 'Armando Esteban Quito 666', 'rradmin','U',now()::timestamp);

--INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(4, 'Fiesta de Halloween del grupo de TSI2', '2011-10-31', 180,'', 'Armando Esteban Quito 666', 'rradmin','U',now()::timestamp);

--INSERT INTO EVENTS(EVT_ID_AUTO, EVT_NAME, DATE, DURATION, DESCRIPTION, ADDRESS, CREATOR, FLAGS, REG_DATE) VALUES(5, 'Previa del Perry vs Juventus', '2011-10-28', 180,'', 'Aquiles Baeza 1025', 'anadmin','M',now()::timestamp);