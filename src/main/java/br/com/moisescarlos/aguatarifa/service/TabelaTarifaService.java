package br.com.moisescarlos.aguatarifa.service;

import br.com.moisescarlos.aguatarifa.dto.request.TabelaTarifariaRequest;
import br.com.moisescarlos.aguatarifa.dto.response.TabelaTarifariaResponse;
import br.com.moisescarlos.aguatarifa.exception.TabelaNaoEncontradaException;
import br.com.moisescarlos.aguatarifa.model.TabelaTarifa;
import br.com.moisescarlos.aguatarifa.repository.TabelaTarifaRepository;
import br.com.moisescarlos.aguatarifa.service.mapper.TabelaTarifaMapper;
import br.com.moisescarlos.aguatarifa.service.validator.FaixaConsumoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TabelaTarifaService {

    private final TabelaTarifaRepository repository;
    private final TabelaTarifaMapper mapper;
    private final FaixaConsumoValidator validator;

    @Transactional
    public TabelaTarifariaResponse criar(TabelaTarifariaRequest request) {
        validator.validar(request);
        TabelaTarifa tabela = mapper.toEntity(request);
        TabelaTarifa salva = repository.save(tabela);
        return mapper.toResponse(salva);
    }

    public List<TabelaTarifariaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public void deletar(UUID id) {
        TabelaTarifa tabela = repository.findById(id)
                .orElseThrow(() -> new TabelaNaoEncontradaException(id));
        repository.delete(tabela);
    }
}