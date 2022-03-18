package br.com.fiap.teste.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			BigDecimal nrTelefone = (BigDecimal) obj[1];
			String nomeConsumidor = (String) obj[0];
			System.out.println(nrTelefone + " - " + nomeConsumidor + "\n");
		}

		Scanner sc = new Scanner(System.in);
		
		Boolean numeroEncontrado = false;
		
		String numTelefoneDigitado = null;
		String dataInicial = null;
		String dataFinal = null;
		
		do {
			System.out.println("\nDigite um número de telefone dentre as opções da lista:");
			numTelefoneDigitado = sc.nextLine();
			for (Object[] obj : listaConsumidores) {
				BigDecimal nrTelefone = (BigDecimal) obj[0];
				if (numTelefoneDigitado.equals(nrTelefone.toString())) {
					System.out.println("Telefone válido");
					numeroEncontrado = true;
				} else {
					System.out.println("Telefone inválido");	
				}
			}
			
		} while (!numeroEncontrado);
		
		System.out.println("\nDigite a data inicial (formato: DD/MM/AAAA):");
		dataInicial = sc.nextLine();
		
		System.out.println("\nDigite a data final (formato: DD/MM/AAAA):");
		dataFinal = sc.nextLine();
		
		System.out.println("Data da última visita: " + TesteUltimaVisita.main(numTelefoneDigitado));
		
		System.out.println("Número de visitas entre " + dataInicial + " e " + dataFinal + ": " + TesteFrequenciaVisitas.main(dataInicial, dataFinal));
		
		System.out.println("Ticket médio: " + TesteTicketMedio.main(numTelefoneDigitado));
		
		List<Object[]> bebidaFavorita = new ArrayList<Object[]>();
		
		bebidaFavorita = TesteEstiloMarcaFavoritos.main(numTelefoneDigitado);
		
//		String estilo = null;
//		String marca = null;
		
		for (Object[] bebida : bebidaFavorita) {
			String marca = (String) bebida[0];
			String estilo = (String) bebida[1];
			System.out.println("Marca favorita: " + marca);
			System.out.println("Estilo favorito: " + estilo);
		}
		
//		System.out.println("Marca favorita: " + marca);
//		System.out.println("Estilo favorito: " + estilo);
		
		sc.close();
	}

}
