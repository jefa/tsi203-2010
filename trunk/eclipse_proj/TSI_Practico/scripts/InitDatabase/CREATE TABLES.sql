-- Table: webservice

-- DROP TABLE webservice;

CREATE TABLE webservice
(
  name character varying(100) NOT NULL,
  url character varying(200),
  id integer NOT NULL,
  CONSTRAINT pk_webservice PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE webservice OWNER TO postgres;


-- Table: "cache"

-- DROP TABLE "cache";

CREATE TABLE cache
(
  id integer NOT NULL,
  idws integer,
  params character varying(200),
  result character varying(200),
  CONSTRAINT pk_cache PRIMARY KEY (id),
  CONSTRAINT fk_cachews FOREIGN KEY (idws)
      REFERENCES webservice (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cache OWNER TO postgres;


-- Table: "log"

-- DROP TABLE "log";

CREATE TABLE log
(
  idws integer NOT NULL,
  date date NOT NULL,
  outcome character varying(30),
  reg_date timestamp without time zone,
  CONSTRAINT pk_log PRIMARY KEY (idws, date),
  CONSTRAINT fk_logws FOREIGN KEY (idws)
      REFERENCES webservice (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE log OWNER TO postgres;
