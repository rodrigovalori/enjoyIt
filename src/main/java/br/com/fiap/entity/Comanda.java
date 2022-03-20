package br.com.fiap.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_comanda")
public class Comanda {

	@Id
	@SequenceGenerator(name = "comanda", sequenceName = "sq_tb_comanda", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comanda")
	@Column(name = "id_comanda")
	private Long id;

	@Column(name = "dt_comanda", nullable = false)
	private Calendar dataComanda;

	@Column(name = "nr_valor_total_gasto")
	private Double valorTotalGasto;

	@JoinColumn(name = "nr_telefone")
	@ManyToOne
	private Consumidor consumidor;

	@OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL)
	private List<Consumo> consumo;

	public Comanda() {
		super();
	}

	public Comanda(Calendar dataComanda, Double valorTotalGasto, Consumidor consumidor) {
		super();
		this.dataComanda = dataComanda;
		this.valorTotalGasto = valorTotalGasto;
		this.consumidor = consumidor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataComanda() {
		return dataComanda;
	}

	public void setDataComanda(Calendar dataComanda) {
		this.dataComanda = dataComanda;
	}

	public Double getValorTotalGasto() {
		return valorTotalGasto;
	}

	public void setValorTotalGasto(Double valorTotalGasto) {
		this.valorTotalGasto = valorTotalGasto;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

}
