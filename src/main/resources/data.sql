-- H2
-- MERGE INTO tb_roles KEY(role_id) VALUES (1, 'admin');
-- MERGE INTO tb_roles KEY(role_id) VALUES (2, 'basic');

-- POSTGRES

INSERT INTO tb_roles (role_id, role_name)
VALUES (1, 'admin')
    ON CONFLICT (role_id) DO UPDATE SET role_name = EXCLUDED.role_name;

INSERT INTO tb_roles (role_id, role_name)
VALUES (2, 'basic')
    ON CONFLICT (role_id) DO UPDATE SET role_name = EXCLUDED.role_name;
