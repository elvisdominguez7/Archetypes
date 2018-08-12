-- Table: rs.${artifactId}.user

-- DROP TABLE rs.user;

CREATE SCHEMA ${artifactId};

CREATE TABLE ${artifactId}.user
(
  user_id integer NOT NULL,
  first_name character(20) NOT NULL,
  last_name character(20) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ${artifactId}.user
  OWNER TO usermgmt;
  

-- Table: rs.${artifactId}.invoice

-- DROP TABLE rs.${artifactId}.invoice;

CREATE TABLE ${artifactId}.invoice
(
  invoice_id integer NOT NULL,
  user_id integer NOT NULL,
  price  double precision,
  title character(20),
  first_name character(20),
  last_name character(20),
  CONSTRAINT invoice_pkey PRIMARY KEY (invoice_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ${artifactId}.invoice
  OWNER TO usermgmt;