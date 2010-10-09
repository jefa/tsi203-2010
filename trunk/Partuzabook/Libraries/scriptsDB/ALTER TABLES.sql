-- LA BASE DE DATOS DEBE LLAMARSE Partuzabook

-- Agregado por Gonzalo el 5/10. Al user le faltaban estos campos de info personal.

ALTER TABLE users ADD COLUMN name CHARACTER VARYING (100) ;
ALTER TABLE users ADD COLUMN img_path CHARACTER VARYING (100);
ALTER TABLE users ADD COLUMN email CHARACTER VARYING (75) ;

ALTER TABLE users alter column name SET NOT NULL;
ALTER TABLE users alter column email SET NOT NULL;


-- Agregado el 08/10 por Vero. Le agrego una posicion en la galería al content.

ALTER TABLE content ADD COLUMN pos INTEGER;

ALTER TABLE content ALTER COLUMN pos SET NOT NULL;

