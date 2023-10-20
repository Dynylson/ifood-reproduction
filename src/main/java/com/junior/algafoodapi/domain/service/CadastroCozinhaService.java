package com.junior.algafoodapi.domain.service;

import com.junior.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.junior.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.junior.algafoodapi.domain.model.Cozinha;
import com.junior.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void remover(Long id) {
        try {
            cozinhaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe um cadastro de cozinha com código %d", id
            ));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                    "Cozinha de código %d não pode ser removida, pois está em uso", id
            ));

        }
    }

}
