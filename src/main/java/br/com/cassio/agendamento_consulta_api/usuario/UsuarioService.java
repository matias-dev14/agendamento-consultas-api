package br.com.cassio.agendamento_consulta_api.usuario;

import br.com.cassio.agendamento_consulta_api.exception.EmailJaCadastradoException;
import br.com.cassio.agendamento_consulta_api.usuario.dto.UsuarioRequest;
import br.com.cassio.agendamento_consulta_api.usuario.dto.UsuarioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
public UsuarioResponse cadastrar(UsuarioRequest request) {
    if (repository.existsByEmail(request.email())) {
        throw new EmailJaCadastradoException("E-mail já cadastrado");
    }

    Usuario usuario = new Usuario();
    usuario.setNome(request.nome());
    usuario.setEmail(request.email());
    usuario.setSenha(request.senha());

    Usuario usuarioSalvo = repository.save(usuario);

    return toResponse(usuarioSalvo);
}
    

    public List<UsuarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return toResponse(usuario);
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());

        Usuario usuarioAtualizado = repository.save(usuario);

        return toResponse(usuarioAtualizado);
    }

    public void deletar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(usuario);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}