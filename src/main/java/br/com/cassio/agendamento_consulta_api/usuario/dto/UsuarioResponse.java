package br.com.cassio.agendamento_consulta_api.usuario.dto;

public record UsuarioResponse(

        Long id,
        String nome,
        String email

) {
}