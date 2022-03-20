package br.com.fiap.teste.custom;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteUltimaVisita {

	public static String main(String numTelefone) {

		EntityManager em = null;

		String dataUltimaVisita = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery(
					"select MAX(DT_COMANDA)\n" + "from TB_COMANDA\n" + "where NR_TELEFONE = :numeroTelefone");

			query.setParameter("numeroTelefone", numTelefone);

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
