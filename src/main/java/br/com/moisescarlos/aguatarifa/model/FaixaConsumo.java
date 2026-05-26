package br.com.moisescarlos.aguatarifa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "faixas_consumo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaixaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "'inicio' não pode ser nulo")
    @Column(nullable = false)
    private Integer inicio;

    @NotNull(message = "'fim' não pode ser nulo")
    @Column(nullable = false)
    private Integer fim;

    @NotNull(message = "Valor unitario não pode ser nulo")
    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @ManyToOne
    @JoinColumn(name = "categoria_consumo_id", nullable = false)
    private CategoriaConsumo categoriaConsumo;
}
