package com.br.lfmelo.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lfmelo.clients.LogClient;
import com.br.lfmelo.clients.MailSenderClient;
import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.EmailDTO;
import com.br.lfmelo.entities.dtos.TransferenciaDTO;
import com.br.lfmelo.enums.TipoUsuario;
import com.br.lfmelo.services.TransferenciaService;
import com.br.lfmelo.services.UsuarioService;
import static com.br.lfmelo.utils.LogUtil.createLog;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Autowired LogClient logClient;

    @Override
    public void efetuarTransferencia(TransferenciaDTO dto) {
        Usuario pagador = usuarioService.buscarPorId(dto.getPayer());
        Usuario beneficiario = usuarioService.buscarPorId(dto.getPayee());

        try {
            List<Usuario> usuariosEnvolvidos = usuarioService.atualizarSaldo(pagador, beneficiario, dto.getValue());

            String message = "Transferencia de R$ " + dto.getValue() + " realizada do Pagador: " + pagador.getNome().concat(" - " + pagador.getCpfCnpj()) + " para o Beneficiário: " + beneficiario.getNome().concat(" - " + beneficiario.getCpfCnpj());
            String acao = "Transferencia realizada"; 

            System.out.println(acao);
            logClient.ingestLog(createLog(message, acao, null));
            enviaEmailConfirmacao(usuariosEnvolvidos, dto.getValue());
            
        } catch (Exception e) {
            String message = "Falha ao realizar transferência";
            String acao = "Erro transferencia";
            logClient.ingestLog(createLog(message, acao, e.getMessage()));
            throw new RuntimeException(message);
        }
    }

    public void enviaEmailConfirmacao(List<Usuario> usuariosEnvolvidos, BigDecimal valor) {
        for(Usuario usuario: usuariosEnvolvidos) {

            if(usuario.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
                var email = new EmailDTO("noreply@gmail.com", "Transferência recebida!", "Voce recebeu uma transferência de R$ " + valor);
                mailSenderClient.sendMailNotification(email);
            }

            //TODO: Implementar outras regras para envio de email como: Enviar email para Pagador
        }
        
        
    }


}
