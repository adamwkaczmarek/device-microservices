DROP TABLE IF EXISTS device;

CREATE TABLE device(
  device_id         VARCHAR(100) PRIMARY KEY NOT NULL,
  arn_endpoint      VARCHAR(100) NOT NULL,
  topic             VARCHAR(100) NOT NULL,
  device_desc       TEXT ,
  last_registration_date TIMESTAMP with time zone NOT NULL
  );
