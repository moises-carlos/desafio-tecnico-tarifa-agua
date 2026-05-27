INSERT INTO tabela_tarifa (id, nome, data_vigencia)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Tabela 2026', '2026-01-01');

INSERT INTO categorias_consumo (id, tipo, tabela_tarifa_id)
VALUES
    ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'INDUSTRIAL',  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
    ('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'COMERCIAL',   'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
    ('d3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 'PARTICULAR',  'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
    ('e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', 'PUBLICO',     'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

INSERT INTO faixas_consumo (id, inicio, fim, valor_unitario, categoria_consumo_id)
VALUES
    -- INDUSTRIAL
    (gen_random_uuid(), 0,  10, 1.00, 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
    (gen_random_uuid(), 11, 20, 2.00, 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
    (gen_random_uuid(), 21, 30, 3.00, 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),
    (gen_random_uuid(), 31, 99999, 4.00, 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22'),

    -- COMERCIAL
    (gen_random_uuid(), 0,  10, 1.50, 'c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33'),
    (gen_random_uuid(), 11, 20, 2.50, 'c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33'),
    (gen_random_uuid(), 21, 30, 3.50, 'c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33'),
    (gen_random_uuid(), 31, 99999, 4.50, 'c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33'),

    -- PARTICULAR
    (gen_random_uuid(), 0,  10, 0.50, 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
    (gen_random_uuid(), 11, 20, 1.50, 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
    (gen_random_uuid(), 21, 30, 2.50, 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),
    (gen_random_uuid(), 31, 99999, 3.50, 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44'),

    -- PUBLICO
    (gen_random_uuid(), 0,  10, 0.75, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55'),
    (gen_random_uuid(), 11, 20, 1.75, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55'),
    (gen_random_uuid(), 21, 30, 2.75, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55'),
    (gen_random_uuid(), 31, 99999, 3.75, 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55');