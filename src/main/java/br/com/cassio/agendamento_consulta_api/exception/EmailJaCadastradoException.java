package br.com.cassio.agendamento_consulta_api.exception;

public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String message) {
        super(message);
    }
}