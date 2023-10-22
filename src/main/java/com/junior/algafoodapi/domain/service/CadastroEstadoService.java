package com.junior.algafoodapi.domain.service;

import com.junior.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.junior.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.junior.algafoodapi.domain.model.Estado;
import com.junior.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                    "Estado de código %d não pode ser removida, pois está em uso", id
            ));
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe um cadastro de cozinha com código %d", id
            ));
        }
    }

}
