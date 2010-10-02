-- Insert Admin and regular users
INSERT INTO users(
            username, "password", flags, reg_date)
    VALUES ('admin', 'admin', 'A', '2010/10/02 10:30'::timestamp);

INSERT INTO users(
            username, "password", flags, reg_date)
    VALUES ('robi', '10101', 'N', '2010/10/02 10:30'::timestamp);


INSERT INTO users(
            username, "password", flags, reg_date)
    VALUES ('vero', 'vero', 'N', '2010/10/02 10:30'::timestamp);


INSERT INTO users(
            username, "password", flags, reg_date)
    VALUES ('rodri', 'rodri', 'N', '2010/10/02 10:30'::timestamp);


INSERT INTO users(
            username, "password", flags, reg_date)
    VALUES ('gonza', 'gonza', 'N', '2010/10/02 10:30'::timestamp);



-- Insert new events
INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('Vero se recibe', '2010-11-15', '200', 'Vengan todos', 'Mi dire', 'admin',
 	    	'U','2010/10/02 10:30'::timestamp, '0');

INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('Cumple Vero', '2011-03-08', '200', 'Vengan todos', 'Mi dire', 'admin',
 	    	'M','2010/10/02 17:30'::timestamp, '1');

INSERT INTO events(
            evt_name, date, duration, description, address, creator, flags, 
            reg_date, evt_id_auto)
    VALUES ('SUMO.UY', '2010-09-25', '100', 'Vengan todos', 'Aulario', 'admin',
 	    	'U','2010/10/02 17:30'::timestamp, '2');

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

