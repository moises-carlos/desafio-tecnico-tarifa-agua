package br.com.moisescarlos.aguatarifa.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "tabela_tarifa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabelaTarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório e não pode ser nulo")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "A data não pode ser nula")
    @Column(name = "data_vigencia", nullable = false)
    private LocalDate dataVigencia;

    @OneToMany(mappedBy = "tabelaTarifa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoriaConsumo> categorias;
}
