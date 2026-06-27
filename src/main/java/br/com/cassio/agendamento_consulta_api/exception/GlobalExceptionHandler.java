package br.com.cassio.agendamento_consulta_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(EmailJaCadastradoException.class)
@ResponseStatus(HttpStatus.CONFLICT)
public ErroResponse tratarEmailJaCadastrado(
        EmailJaCadastradoException exception,
        HttpServletRequest request
) {
    return new ErroResponse(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            exception.getMessage(),
            request.getRequestURI()
    );
}
}