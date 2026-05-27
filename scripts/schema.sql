CREATE TABLE IF NOT EXISTS tabela_tarifa (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome        VARCHAR(255) NOT NULL,
    data_vigencia DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS categorias_consumo (
    id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tipo VARCHAR(50) NOT NULL,
    tabela_tarifa_id    UUID NOT NULL,
    CONSTRAINT fk_tabela_tarifa
    FOREIGN KEY (tabela_tarifa_id)
    REFERENCES tabela_tarifa(id)
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS faixas_consumo (
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    inicio                INTEGER NOT NULL,
    fim                   INTEGER NOT NULL,
    valor_unitario        NUMERIC(10, 2) NOT NULL,
    categoria_consumo_id  UUID NOT NULL,
    CONSTRAINT fk_categoria_consumo
    FOREIGN KEY (categoria_consumo_id)
    REFERENCES categorias_consumo(id)
    ON DELETE CASCADE
    );