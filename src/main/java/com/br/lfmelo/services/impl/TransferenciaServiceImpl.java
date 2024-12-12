package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.TransferenciaDTO;
import com.br.lfmelo.services.TransferenciaService;
import com.br.lfmelo.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void efetuarTransferencia(TransferenciaDTO dto) {
        Usuario pagador = usuarioService.buscarPorId(dto.getPayer());
        Usuario beneficiario = usuarioService.buscarPorId(dto.getPayee());

        List<Usuario> usuariosEnvolvidos = usuarioService.atualizarSaldo(pagador, beneficiario, dto.getValue());

        System.out.println("Transferencia de R$ " + dto.getValue() + " realizada com sucesso!");
    }


}
