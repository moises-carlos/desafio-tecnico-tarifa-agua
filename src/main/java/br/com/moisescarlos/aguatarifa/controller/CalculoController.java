package br.com.moisescarlos.aguatarifa.controller;

import br.com.moisescarlos.aguatarifa.dto.request.CalculoRequest;
import br.com.moisescarlos.aguatarifa.dto.response.CalculoResponse;
import br.com.moisescarlos.aguatarifa.service.CalculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/calculos")
@RequiredArgsConstructor
public class CalculoController {

    private final CalculoService service;

    @PostMapping
    public ResponseEntity<CalculoResponse> calcular(
            @RequestBody @Valid CalculoRequest request
    ) {
        CalculoResponse response = service.calcular(request);
        return ResponseEntity.ok(response);
    }
}