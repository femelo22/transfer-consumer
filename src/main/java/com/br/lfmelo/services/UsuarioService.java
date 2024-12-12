package com.br.lfmelo.services;

import com.br.lfmelo.entities.Usuario;

import java.math.BigDecimal;
import java.util.List;

public interface UsuarioService {
    Usuario buscarPorId(Long id);

    List<Usuario> atualizarSaldo(Usuario pagador, Usuario beneficiario, BigDecimal valor);
}
