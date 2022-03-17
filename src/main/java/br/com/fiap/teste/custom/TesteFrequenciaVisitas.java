package br.com.fiap.teste.custom;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteFrequenciaVisitas {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery("SELECT COUNT(DT_COMANDA)\n" + "FROM TB_COMANDA\n"
					+ "WHERE DT_COMANDA BETWEEN TO_DATE(:dataInicio, 'DD/MM/YYYY') AND TO_DATE(:dataFim, 'DD/MM/YYYY') and NR_TELEFONE = :numeroTelefone");

			query.setParameter("dataInicio", "01/03/2022");
			query.setParameter("dataFim", "31/03/2022");
			query.setParameter("numeroTelefone", "1123456789");

			BigDecimal result = (BigDecimal) query.getSingleResult();

			System.out.println(result);

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
	}
}
