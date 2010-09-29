--LA BASE DE DATOS DEBE LLAMARSE Partuzabook

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  username character varying(30) NOT NULL,
  password character varying(50) NOT NULL,
  flags character varying(1) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_USERS" PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;


-- Table: events

-- DROP TABLE events;

CREATE TABLE events
(
  evt_name character varying(30) NOT NULL,
  date date,
  duration integer,
  description character varying(200),
  address character varying(50),
  creator character varying(30) NOT NULL,
  album_url character varying(50),
  flags character varying(1) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_EVENTS" PRIMARY KEY (evt_name),
  CONSTRAINT "FK_CREATOR" FOREIGN KEY (creator)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE events OWNER TO postgres;



-- Table: "content"

-- DROP TABLE "content";

CREATE TABLE content
(
  cnt_id_auto integer NOT NULL,
  creator character varying(30) NOT NULL,
  description character varying(100),
  size integer,
  url character varying(50),
  flags character(1) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_CONTENT" PRIMARY KEY (cnt_id_auto),
  CONSTRAINT "FK_CNT_USR" FOREIGN KEY (creator)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "content" OWNER TO postgres;



-- Table: album

-- DROP TABLE album;

CREATE TABLE album
(
  evt_id character varying(30) NOT NULL,
  cnt_id integer NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_ALBUM" PRIMARY KEY (evt_id, cnt_id),
  CONSTRAINT "FK_ALB_CNT" FOREIGN KEY (cnt_id)
      REFERENCES content (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_ALB_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE album OWNER TO postgres;




-- Table: "comment"

-- DROP TABLE "comment";

CREATE TABLE comment
(
  usr_id character varying(30) NOT NULL,
  cnt_id integer NOT NULL,
  text character varying(500) NOT NULL,
  date date NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_COMMENT" PRIMARY KEY (usr_id, cnt_id, date),
  CONSTRAINT "FK_CMN_CNT" FOREIGN KEY (cnt_id)
      REFERENCES content (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_CMN_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comment OWNER TO postgres;



-- Table: mods

-- DROP TABLE mods;

CREATE TABLE mods
(
  usr_id character varying(30) NOT NULL,
  evt_id character varying(30) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_MODS" PRIMARY KEY (usr_id, evt_id),
  CONSTRAINT "FK_MODS_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_MODS_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mods OWNER TO postgres;



-- Table: notifications

-- DROP TABLE notifications;

CREATE TABLE notifications
(
  not_id_auto integer NOT NULL,
  usr_id character varying(30) NOT NULL,
  text character varying(100) NOT NULL,
  reference character varying(50),
  not_date date NOT NULL,
  read boolean NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_NOTIFICATIONS" PRIMARY KEY (not_id_auto),
  CONSTRAINT "FK_NOT_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE notifications OWNER TO postgres;



-- Table: participants

-- DROP TABLE participants;

CREATE TABLE participants
(
  usr_id character varying(30) NOT NULL,
  evt_id character varying(30) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_PARTICIPANTS" PRIMARY KEY (usr_id, evt_id),
  CONSTRAINT "FK_PRT_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_PRT_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE participants OWNER TO postgres;



-- Table: ratings

-- DROP TABLE ratings;

CREATE TABLE ratings
(
  usr_id character varying(30) NOT NULL,
  cnt_id integer NOT NULL,
  score integer NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_RATINGS" PRIMARY KEY (usr_id, cnt_id),
  CONSTRAINT "FK_RTN_CNT" FOREIGN KEY (cnt_id)
      REFERENCES "content" (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_RTN_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ratings OWNER TO postgres;



-- Table: tags

-- DROP TABLE tags;

CREATE TABLE tags
(
  creator character varying(30) NOT NULL,
  cnt_id integer NOT NULL,
  posX integer,
  posY integer,
  usr_tag_custom character varying(30),
  usr_tag character varying(30),
  tag_id_auto integer NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_TAGS" PRIMARY KEY (tag_id_auto),
  CONSTRAINT "FK_TAG_CRT" FOREIGN KEY (creator)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_TAG_USR" FOREIGN KEY (usr_tag)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tags OWNER TO postgres;
