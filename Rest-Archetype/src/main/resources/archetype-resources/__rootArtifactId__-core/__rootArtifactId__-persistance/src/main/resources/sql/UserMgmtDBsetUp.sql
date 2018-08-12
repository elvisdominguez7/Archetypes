-- Table: rs.Demo-persistance.user

-- DROP TABLE rs.user;

CREATE SCHEMA Demo-persistance;

CREATE TABLE Demo-persistance.user
(
  user_id integer NOT NULL,
  first_name character(20) NOT NULL,
  last_name character(20) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Demo-persistance.user
  OWNER TO usermgmt;
  

-- Table: rs.Demo-persistance.invoice

-- DROP TABLE rs.Demo-persistance.invoice;

CREATE TABLE Demo-persistance.invoice
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
ALTER TABLE Demo-persistance.invoice
  OWNER TO usermgmt;