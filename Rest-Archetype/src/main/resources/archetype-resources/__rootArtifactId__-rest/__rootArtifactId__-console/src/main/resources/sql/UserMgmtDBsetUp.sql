-- Table: rs.Demo-console.user

-- DROP TABLE rs.user;

CREATE SCHEMA Demo-console;

CREATE TABLE Demo-console.user
(
  user_id integer NOT NULL,
  first_name character(20) NOT NULL,
  last_name character(20) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Demo-console.user
  OWNER TO usermgmt;
  

-- Table: rs.Demo-console.invoice

-- DROP TABLE rs.Demo-console.invoice;

CREATE TABLE Demo-console.invoice
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
ALTER TABLE Demo-console.invoice
  OWNER TO usermgmt;