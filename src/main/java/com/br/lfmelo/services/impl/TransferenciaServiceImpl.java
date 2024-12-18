package com.br.lfmelo.services.impl;

import java.math.BigDecimal;
import java.util.List;

import com.br.lfmelo.MailSenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.EmailDTO;
import com.br.lfmelo.entities.dtos.TransferenciaDTO;
import com.br.lfmelo.enums.TipoUsuario;
import com.br.lfmelo.services.TransferenciaService;
import com.br.lfmelo.services.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Override
    public void efetuarTransferencia(TransferenciaDTO dto) {
        Usuario pagador = usuarioService.buscarPorId(dto.getPayer());
        Usuario beneficiario = usuarioService.buscarPorId(dto.getPayee());

        List<Usuario> usuariosEnvolvidos = usuarioService.atualizarSaldo(pagador, beneficiario, dto.getValue());

        System.out.println("Transferencia de R$ " + dto.getValue() + " realizada com sucesso!");

        for(Usuario usuario: usuariosEnvolvidos) {
           enviaEmailConfirmacao(usuario, dto.getValue());
        }
    }

    public void enviaEmailConfirmacao(Usuario usuario, BigDecimal valor) {
        if(usuario.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
            var email = new EmailDTO("noreply@gmail.com", "Transferência recebida!", "Voce recebeu uma transferência de R$ " + valor);
            mailSenderClient.sendMailNotification(email);
        }
    }


}
