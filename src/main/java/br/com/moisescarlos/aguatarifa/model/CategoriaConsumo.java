package br.com.moisescarlos.aguatarifa.model;

import br.com.moisescarlos.aguatarifa.model.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categorias_consumo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCategoria tipo;

    @ManyToOne
    @JoinColumn(name = "tabela_tarifa_id", nullable = false)
    private TabelaTarifa tabelaTarifa;

    @OneToMany(mappedBy = "categoriaConsumo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FaixaConsumo> faixas;

}
