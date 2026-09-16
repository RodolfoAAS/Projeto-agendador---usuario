package com.example.Projeto.agendador20.usuario.Business;

import com.example.Projeto.agendador20.usuario.Business.converter.UsuarioConverter;
import com.example.Projeto.agendador20.usuario.Business.dto.UsuarioDTO;
import com.example.Projeto.agendador20.usuario.Infrastructure.entity.Usuario;
import com.example.Projeto.agendador20.usuario.Infrastructure.exceptions.ResourceNotFoundException;
import com.example.Projeto.agendador20.usuario.Infrastructure.repository.UsuarioRepository;
import com.example.Projeto.agendador20.usuario.Infrastructure.exceptions.ConflictException;
import com.example.Projeto.agendador20.usuario.Infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);

        if (usuario.getEnderecos() != null) {
            usuario.getEnderecos().forEach(endereco -> {
                endereco.setUsuario(usuario);
            });
        }

        if (usuario.getTelefones() != null) {
            usuario.getTelefones().forEach(telefone -> {
                telefone.setUsuario(usuario);
            });
        }

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }
    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado" + email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO usuarioDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não encontrado"));
        Usuario usuario = usuarioConverter.updateUsuario(usuarioDTO, usuarioEntity);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
