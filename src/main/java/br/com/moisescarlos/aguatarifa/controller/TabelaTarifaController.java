package br.com.moisescarlos.aguatarifa.controller;

import br.com.moisescarlos.aguatarifa.dto.request.TabelaTarifariaRequest;
import br.com.moisescarlos.aguatarifa.dto.response.TabelaTarifariaResponse;
import br.com.moisescarlos.aguatarifa.service.TabelaTarifaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tabelas-tarifarias")
@RequiredArgsConstructor
public class TabelaTarifaController {

    private final TabelaTarifaService service;

    @PostMapping
    public ResponseEntity<TabelaTarifariaResponse> criar(@RequestBody @Valid TabelaTarifariaRequest request) {
        TabelaTarifariaResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TabelaTarifariaResponse>> listar() {
        List<TabelaTarifariaResponse> response = service.listar();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}