package br.com.fiap.teste.custom;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteUltimaVisita {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery(
					"select MAX(DT_COMANDA)\n" + "from TB_COMANDA\n" + "where NR_TELEFONE = :numeroTelefone");

			query.setParameter("numeroTelefone", "1123456789");

			Timestamp result = (Timestamp) query.getSingleResult();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String obj = sdf.format(new Date(result.getTime()));

			System.out.println(obj);

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
