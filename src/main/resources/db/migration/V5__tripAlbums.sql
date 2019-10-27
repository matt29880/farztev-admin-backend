CREATE TABLE trip_album (
  trip_id bigint(20) NOT NULL,
  album_id bigint(20) NOT NULL,
  CONSTRAINT trip_album_pk
  PRIMARY KEY (trip_id, album_id)
);