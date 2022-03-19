package br.com.fiap.teste.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteConsultaDados {

	private static List<Object[]> findAll() {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery("SELECT nr_telefone, nm_consumidor FROM tb_consumidor");

			List<Object[]> listaConsumidores = query.getResultList();	
			
			return listaConsumidores;

		} catch (Exception e) {
			e.printStackTrace();

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();

			}
		}
		return null;

	}

	public static void main(String[] args) {

		List<Object[]> listaConsumidores = new ArrayList<Object[]>();
		listaConsumidores = findAll();
		
		System.out.println("\nLista de consumidores disponíveis:\n");
		
		for (Object[] obj : listaConsumidores) {
			BigDecimal nrTelefone = (BigDecimal) obj[0];
			String nomeConsumidor = (String) obj[1];
			System.out.println(nrTelefone + " - " + nomeConsumidor + "\n");
		}

		Scanner sc = new Scanner(System.in);
		
		Boolean numeroEncontrado = false;
		
		String numTelefoneDigitado = null;
		String dataInicial = null;
		String dataFinal = null;
		
		System.out.print("Digite um número de telefone dentre as opções da lista: ");
		numTelefoneDigitado = sc.nextLine();
		
		while (!numeroEncontrado) {
			for (Object[] obj : listaConsumidores) {
				System.out.print("Telefone não cadastrado na base de dados. Digite novamente: ");
				numTelefoneDigitado = sc.nextLine();
				BigDecimal nrTelefone = (BigDecimal) obj[0];
				if (numTelefoneDigitado.equals(nrTelefone.toString())) {
					numeroEncontrado = true;
				}
			}
		}
		
		String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
		Pattern pattern = Pattern.compile(regex);	
		
		System.out.print("\nDigite a data inicial (formato: DD/MM/AAAA): ");
		dataInicial = sc.nextLine();
		
		Boolean dataInicialValida = pattern.matcher(dataInicial).matches();
		
		while (!dataInicialValida) {
			System.out.print("Data inicial inválida. Digite-a novamente no formato: DD/MM/AAAA: ");	
			dataInicial = sc.nextLine();
			dataInicialValida = pattern.matcher(dataInicial).matches();
		}
		
		System.out.print("\nDigite a data final (formato: DD/MM/AAAA): ");
		dataFinal = sc.nextLine();
		
		Boolean dataFinalValida = pattern.matcher(dataFinal).matches();
				
		while (!dataFinalValida) {
			System.out.print("Data final inválida. Digite-a novamente no formato: DD/MM/AAAA: ");	
			dataFinal = sc.nextLine();
			dataFinalValida = pattern.matcher(dataFinal).matches();
		}
		
		System.out.println("Data da última visita: " + TesteUltimaVisita.main(numTelefoneDigitado));
		
		System.out.println("Número de visitas entre " + dataInicial + " e " + dataFinal + ": " + TesteFrequenciaVisitas.main(dataInicial, dataFinal));
		
		System.out.println("Ticket médio: R$" + TesteTicketMedio.main(numTelefoneDigitado));
		
		List<Object[]> bebidaFavorita = new ArrayList<Object[]>();
		
		bebidaFavorita = TesteEstiloMarcaFavoritos.main(numTelefoneDigitado);
		
		for (Object[] bebida : bebidaFavorita) {
			String marca = (String) bebida[1];
			String estilo = (String) bebida[0];
			System.out.println("Marca favorita: " + marca);
			System.out.println("Estilo favorito: " + estilo);
		}
		
		sc.close();
	}

}
