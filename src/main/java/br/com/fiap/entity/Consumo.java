package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fiap.entity.enums.Estilo;
import br.com.fiap.entity.enums.Marca;

@Entity
@Table(name = "tb_consumo")
public class Consumo {

	@Id
	@SequenceGenerator(name = "consumo", sequenceName = "sq_tb_consumo", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumo")
	@Column(name = "id_consumo")
	private Integer id;

	@Column(name = "nr_litros_consumidos", nullable = false)
	private Double litrosConsumidos;
	
	@Column(name = "nr_preco_por_litro", nullable = false)
	private Double precoPorLitro;
	
	// Cria��o de alias para defini��o de coluna virtual (para c�lculo de valor total por consumo):
	@Column(name = "nr_valor_consumo", columnDefinition = "AS (nr_litros_consumidos * nr_preco_por_litro)", insertable = false, updatable = false)
	private Double valorConsumo;

	@Enumerated(EnumType.STRING)
	@Column(name = "ds_estilo", nullable = false)
	private Estilo estilo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_marca", nullable = false)
	private Marca marca;

	@JoinColumn(name = "nr_telefone")
	@ManyToOne
	private Consumidor consumidor;

	@JoinColumn(name = "id_comanda")
	@ManyToOne
	private Comanda comanda;

	public Consumo() {
		super();
	}

	public Consumo(Double litrosConsumidos, Estilo estilo, Marca marca, Consumidor consumidor,
			Comanda comanda) {
		super();
		this.litrosConsumidos = litrosConsumidos;
		this.estilo = estilo;
		this.marca = marca;
		this.consumidor = consumidor;
		this.comanda = comanda;
	}

	public Consumo(double d, double e, Estilo lager, Marca amstel, Consumidor consumidor2, Comanda comanda2) {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLitrosConsumidos() {
		return litrosConsumidos;
	}

	public void setLitrosConsumidos(Double litrosConsumidos) {
		this.litrosConsumidos = litrosConsumidos;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
}
