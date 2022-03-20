package br.com.fiap.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConsumidorService {

	public static List<Object[]> findAll() {

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

	public static List<Object[]> verificarEstiloMarcaFavoritos(String numeroTelefone) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery("select DS_ESTILO, DS_MARCA\n" + "from TB_CONSUMO\n"
					+ "where nr_litros_consumidos * nr_preco_por_litro = (select MAX(nr_litros_consumidos * nr_preco_por_litro)\n"
					+ "                                                   from TB_CONSUMO\n"
					+ "                                                   where NR_TELEFONE = :numeroTelefone)");

			query.setParameter("numeroTelefone", numeroTelefone);
			List<Object[]> list = query.getResultList();

			return list;

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

	public static BigDecimal verificarFrequenciaVisita(String dataInicio, String dataFim, String numeroTelefone) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery("SELECT COUNT(DT_COMANDA)\n" + "FROM TB_COMANDA\n"
					+ "WHERE DT_COMANDA BETWEEN TO_DATE(:dataInicio, 'DD/MM/YYYY') AND TO_DATE(:dataFim, 'DD/MM/YYYY') AND NR_TELEFONE = :numeroTelefone");

			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFim", dataFim);
			query.setParameter("numeroTelefone", numeroTelefone);

			BigDecimal numeroVisitas = (BigDecimal) query.getSingleResult();

			return numeroVisitas;

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

	public static Double verificarTicketMedio(String numeroTelefone) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			/*
			 * Query query = em.createNativeQuery(
			 * "SELECT ROUND(SUM(TB_CONSUMO.nr_litros_consumidos * TB_CONSUMO.nr_preco_por_litro)/COUNT(TB_COMANDA.id_comanda), 2)\n"
			 * + "FROM TB_COMANDA\n" + "         JOIN TB_CONSUMO\n" +
			 * "              ON TB_COMANDA.id_comanda = TB_CONSUMO.id_comanda\n" +
			 * "WHERE TB_COMANDA.nr_telefone = :numeroTelefone\n" +
			 * "  AND TB_CONSUMO.id_comanda = :idComanda");
			 */

			Query querySum = em.createNativeQuery(
					"SELECT SUM(TB_CONSUMO.NR_LITROS_CONSUMIDOS * TB_CONSUMO.NR_PRECO_POR_LITRO)"
					+ " from TB_CONSUMO where NR_TELEFONE = :numeroTelefone");
			
			Query queryCount = em.createNativeQuery(
					"select count(NR_TELEFONE) from TB_COMANDA where NR_TELEFONE = :numeroTelefone");

			querySum.setParameter("numeroTelefone", numeroTelefone);
			queryCount.setParameter("numeroTelefone", numeroTelefone);
			//query.setParameter("idComanda", "1");
			

			BigDecimal sum = (BigDecimal) querySum.getSingleResult();
					
			BigDecimal count =(BigDecimal) queryCount.getSingleResult();
			
			Double ticketMedio =  sum.doubleValue() / count.doubleValue();

			return ticketMedio;

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

	public static String verificarUltimaVisita(String numeroTelefone) {

		EntityManager em = null;

		String dataUltimaVisita = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em
					.createNativeQuery("SELECT MAX(dt_comanda) FROM tb_comanda WHERE nr_telefone = :numeroTelefone");

			query.setParameter("numeroTelefone", numeroTelefone);

			Timestamp result = (Timestamp) query.getSingleResult();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataUltimaVisita = sdf.format(new Date(result.getTime()));

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
		return dataUltimaVisita;
	}

}
