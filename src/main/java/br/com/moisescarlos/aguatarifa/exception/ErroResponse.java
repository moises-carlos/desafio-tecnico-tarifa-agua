package br.com.moisescarlos.aguatarifa.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroResponse(
        int status,
        String mensagem,
        LocalDateTime timestamp,
        Map<String, String> campos
) {
    public ErroResponse(int status, String mensagem, LocalDateTime timestamp) {
        this(status, mensagem, timestamp, null);
    }
}