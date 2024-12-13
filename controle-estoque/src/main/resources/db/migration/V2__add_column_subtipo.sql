-- Migração v2 para adicionar o campo 'generico' na tabela existente
ALTER TABLE subtipos
    ADD COLUMN generico BOOLEAN DEFAULT FALSE;