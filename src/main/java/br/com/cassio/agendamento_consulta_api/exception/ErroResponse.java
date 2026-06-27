package br.com.cassio.agendamento_consulta_api.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String erro,
        String mensagem,
        String path
) {
}