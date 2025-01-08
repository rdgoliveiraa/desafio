CREATE TABLE  usuario_permissao (
   id_user bigint NOT NULL,
   id_permission bigint NOT NULL,
  PRIMARY KEY ( id_user , id_permission ),
  FOREIGN KEY ( id_user ) REFERENCES  usuario  ( id ),
  FOREIGN KEY ( id_permission ) REFERENCES  permissao  ( id )
);