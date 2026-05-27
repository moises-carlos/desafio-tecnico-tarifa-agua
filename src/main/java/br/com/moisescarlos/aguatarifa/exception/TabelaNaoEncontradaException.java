package br.com.moisescarlos.aguatarifa.exception;

import java.util.UUID;

public class TabelaNaoEncontradaException extends RuntimeException {

    public TabelaNaoEncontradaException(UUID id) {
        super("Tabela tarifária não encontrada com o id: " + id);
    }
}