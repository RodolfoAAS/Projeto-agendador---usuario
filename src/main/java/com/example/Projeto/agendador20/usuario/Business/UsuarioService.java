package com.example.Projeto.agendador20.usuario.Business;

import com.example.Projeto.agendador20.usuario.Business.converter.UsuarioConverter;
import com.example.Projeto.agendador20.usuario.Business.dto.UsuarioDTO;
import com.example.Projeto.agendador20.usuario.Infrastructure.entity.Usuario;
import com.example.Projeto.agendador20.usuario.Infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
