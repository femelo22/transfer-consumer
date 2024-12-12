package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.TransferenciaDTO;
import com.br.lfmelo.repositories.UsuarioRepository;
import com.br.lfmelo.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID: " + id + " not found"));
    }

    @Override
    @Transactional
    public List<Usuario> atualizarSaldo(Usuario pagador, Usuario beneficiario, BigDecimal valor) {
        BigDecimal saldoPagador = pagador.getCarteira().getSaldo();
        BigDecimal saldoBeneficiario = beneficiario.getCarteira().getSaldo();

        pagador.getCarteira().setSaldo(saldoPagador.subtract(valor));
        beneficiario.getCarteira().setSaldo(saldoBeneficiario.add(valor));

        return repository.saveAll(Arrays.asList(pagador, beneficiario));
    }

}
