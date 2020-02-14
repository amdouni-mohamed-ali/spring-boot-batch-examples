CREATE TABLE app_user
(
  id bigint NOT NULL,
  email character varying(255),
  name character varying(255),
  CONSTRAINT app_user_pkey PRIMARY KEY (id)
);