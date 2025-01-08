INSERT INTO usuario_permissao (id_user, id_permission)
SELECT id as id_user, 1 as id_permission
FROM usuario WHERE user_name = 'admin';