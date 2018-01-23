CREATE TABLE [fowl] (
  [id]          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  [name]        VARCHAR(100)                      NOT NULL UNIQUE,
  [description] VARCHAR(256),
  [number]      INTEGER                           NOT NULL DEFAULT 0,
  [deleted]     INTEGER                                     DEFAULT 0
);
