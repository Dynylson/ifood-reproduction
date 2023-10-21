package com.junior.algafoodapi.domain.service;

import com.junior.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.junior.algafoodapi.domain.model.Cozinha;
import com.junior.algafoodapi.domain.model.Restaurante;
import com.junior.algafoodapi.domain.repository.CozinhaRepository;
import com.junior.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        if (cozinha.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId)
            );
        }

        restaurante.setCozinha(cozinha.get());

        return restauranteRepository.save(restaurante);
    }

}
