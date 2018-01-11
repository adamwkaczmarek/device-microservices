DROP TABLE IF EXISTS device_state;

CREATE TABLE device_state(
  device_id         VARCHAR(100) NOT NULL,
  pin_number        INTEGER NOT NULL,
  activated         BOOLEAN NOT NULL,
  CONSTRAINT device_state_pkey PRIMARY KEY (device_id,pin_number)
  );

