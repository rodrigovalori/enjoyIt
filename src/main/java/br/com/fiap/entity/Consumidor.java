package br.com.fiap.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_consumidor")
public class Consumidor {

	@Id
	@Column(name = "nr_telefone", nullable = false)
	private Long numeroTelefone;

	@Column(name = "nm_consumidor", nullable = false)
	private String nomeConsumidor;

	@Column(name = "ds_email", nullable = false)
	private String email;

	@Column(name = "dt_nascimento", nullable = false)
	private Calendar dataNascimento;

	@Column(name = "nr_cpf")
	private Long cpf;

	@Column(name = "ds_genero")
	private String genero;

	@Column(name = "nr_cep")
	private Integer cep;

	@Column(name = "ds_logradouro")
	private String logradouro;

	@Column(name = "ds_bairro")
	private String bairro;

	@Column(name = "ds_cidade")
	private String cidade;

	@Column(name = "ds_estado")
	private String estado;

	@OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL)
	private List<Comanda> comanda;

	public Consumidor() {
		super();
	}

	public Consumidor(Long numeroTelefone, String nomeConsumidor, String email, Calendar dataNascimento, Long cpf,
			String genero, Integer cep, String logradouro, String bairro, String cidade, String estado) {
		super();
		this.numeroTelefone = numeroTelefone;
		this.nomeConsumidor = nomeConsumidor;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.genero = genero;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Long getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(Long numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getNomeConsumidor() {
		return nomeConsumidor;
	}

	public void setNomeConsumidor(String nomeConsumidor) {
		this.nomeConsumidor = nomeConsumidor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
