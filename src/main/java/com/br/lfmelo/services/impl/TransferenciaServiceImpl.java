package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import com.br.lfmelo.services.TransferenciaService;
import com.br.lfmelo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaServiceImpl implements TransferenciaService{
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void validarDadosEnviarMensagem(TranferenciaDTO dto) {

    }
}
