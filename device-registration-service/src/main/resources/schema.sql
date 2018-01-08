DROP TABLE IF EXISTS device;

CREATE TABLE device(
  device_id         VARCHAR(100) PRIMARY KEY NOT NULL,
  ip_address        VARCHAR(100) NOT NULL,
  listening_port    VARCHAR(4) NOT NULL,
  comment       TEXT );

