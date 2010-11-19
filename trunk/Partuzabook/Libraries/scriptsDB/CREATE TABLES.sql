-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  username character varying(30) NOT NULL,
  "password" character varying(50) NOT NULL,
  flags character varying(1) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  "name" character varying(100) NOT NULL,
  img_path character varying(100),
  email character varying(150) NOT NULL,
  facebook_id bigint,
  facebook_user boolean DEFAULT false,
  CONSTRAINT "PK_USERS" PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;


-- Table: "evtCategory"

-- DROP TABLE "evtCategory";

CREATE TABLE "evtCategory"
(
  category character varying(100) NOT NULL,
  CONSTRAINT "PK_EVTCATEGORY" PRIMARY KEY (category)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "evtCategory" OWNER TO postgres;


-- Table: events

-- DROP TABLE events;

CREATE TABLE events
(
  evt_name character varying(100) NOT NULL,
  date timestamp without time zone,
  description character varying(200),
  address character varying(50),
  creator character varying(30) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  evt_id_auto integer NOT NULL,
  category character varying(50),
  cover integer,
  latitude double precision,
  longitude double precision,
  hashtag character varying(20),
  duration character varying(50),
  CONSTRAINT "PK_EVENTS" PRIMARY KEY (evt_id_auto),
  CONSTRAINT "FK_CREATOR" FOREIGN KEY (creator)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_EVTCATEGORY" FOREIGN KEY (category)
      REFERENCES "evtCategory" (category) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE events OWNER TO postgres;


-- Table: "content"

-- DROP TABLE "content";

CREATE TABLE "content"
(
  cnt_id_auto integer NOT NULL,
  creator character varying(30) NOT NULL,
  description character varying(100),
  size integer,
  url character varying(200),
  flags character(1) NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  duration character varying(10),
  evt_id integer NOT NULL,
  pos_gallery integer NOT NULL,
  pos_album integer,
  CONSTRAINT "PK_CONTENT" PRIMARY KEY (cnt_id_auto),
  CONSTRAINT "FK_ALB_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_CNT_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_CNT_USR" FOREIGN KEY (creator)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "content" OWNER TO postgres;

ALTER TABLE events ADD CONSTRAINT "FK_COVER" FOREIGN KEY (cover)
      REFERENCES "content" (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
-- Table: album

-- DROP TABLE album;

CREATE TABLE album
(
  reg_date timestamp without time zone NOT NULL,
  album_url character varying(50),
  evt_id integer NOT NULL,
  alb_id_auto integer NOT NULL,
  CONSTRAINT "PK_ALBUM" PRIMARY KEY (alb_id_auto),
  CONSTRAINT "FK_ALB_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE album OWNER TO postgres;


-- Table: "cntCategory"

-- DROP TABLE "cntCategory";

CREATE TABLE "cntCategory"
(
  evt_id integer NOT NULL,
  category character varying(100) NOT NULL,
  cat_id_auto integer NOT NULL,
  CONSTRAINT "PK_CNTCATEGORY" PRIMARY KEY (cat_id_auto),
  CONSTRAINT "FK_CNTCATEGORY_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "cntCategory" OWNER TO postgres;


-- Table: "comment"

-- DROP TABLE "comment";

CREATE TABLE "comment"
(
  usr_id character varying(30) NOT NULL,
  cnt_id integer NOT NULL,
  "text" character varying(500) NOT NULL,
  date timestamp without time zone NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  CONSTRAINT "PK_COMMENT" PRIMARY KEY (usr_id, cnt_id, date),
  CONSTRAINT "FK_CMN_CNT" FOREIGN KEY (cnt_id)
      REFERENCES "content" (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_CMN_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "comment" OWNER TO postgres;


-- Table: "contentCntCategory"

-- DROP TABLE "contentCntCategory";

CREATE TABLE "contentCntCategory"
(
  cnt_id integer NOT NULL,
  cat_id integer NOT NULL,
  CONSTRAINT "PK_CONTENTCNTCATEGORY" PRIMARY KEY (cnt_id, cat_id),
  CONSTRAINT "FK_CONTENTCNTCATEGORY_CNTCATEGORY" FOREIGN KEY (cat_id)
      REFERENCES "cntCategory" (cat_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_CONTENTCNTCATEGORY_CONTENT" FOREIGN KEY (cnt_id)
      REFERENCES "content" (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "contentCntCategory" OWNER TO postgres;


-- Table: mods

-- DROP TABLE mods;

CREATE TABLE mods
(
  usr_id character varying(30) NOT NULL,
  evt_id integer NOT NULL,
  CONSTRAINT "PK_MODS" PRIMARY KEY (usr_id, evt_id),
  CONSTRAINT "FK_MODS_USR" FOREIGN KEY (usr_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_MOD_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_id_auto) MATCH SIMPLE
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
  usr_frm_id character varying(30) NOT NULL,
  "text" character varying(5000) NOT NULL,
  reference character varying(50),
  not_date timestamp without time zone NOT NULL,
  "read" boolean NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  usr_to_id character varying(30) NOT NULL,
  "type" integer NOT NULL,
  subject character varying(200),
  CONSTRAINT "PK_NOTIFICATIONS" PRIMARY KEY (not_id_auto),
  CONSTRAINT "FK_NOT_USR" FOREIGN KEY (usr_frm_id)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_NOT_USR_TO" FOREIGN KEY (usr_to_id)
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
  evt_id integer NOT NULL,
  CONSTRAINT "PK_PARTICIPANTS" PRIMARY KEY (usr_id, evt_id),
  CONSTRAINT "FK_PRT_EVT" FOREIGN KEY (evt_id)
      REFERENCES events (evt_id_auto) MATCH SIMPLE
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
  "posX" integer,
  "posY" integer,
  usr_tag_custom character varying(30),
  usr_tag character varying(30),
  tag_id_auto integer NOT NULL,
  reg_date timestamp without time zone NOT NULL,
  flags character(1) NOT NULL,
  CONSTRAINT "PK_TAGS" PRIMARY KEY (tag_id_auto),
  CONSTRAINT "FK_TAG_CNT" FOREIGN KEY (cnt_id)
      REFERENCES "content" (cnt_id_auto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
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
