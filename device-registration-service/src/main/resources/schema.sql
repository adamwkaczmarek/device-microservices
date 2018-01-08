DROP TABLE IF EXISTS device;

CREATE TABLE device (
  id                VARCHAR(100) PRIMARY KEY NOT NULL,
  ip_adderss        VARCHAR(100) NOT NULL,
  listening_port    VARCHAR(4) NOT NULL,
  comment   VARCHAR(100));

