package br.com.fiap.teste.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteEstiloMarcaFavoritos {

	public static List<Object[]> main(String numTelefone) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			Query query = em.createNativeQuery("select DS_ESTILO, DS_MARCA\n" + "from TB_CONSUMO\n"
					+ "where nr_litros_consumidos * nr_preco_por_litro = (select MAX(nr_litros_consumidos * nr_preco_por_litro)\n"
					+ "                                                   from TB_CONSUMO\n"
					+ "                                                   where NR_TELEFONE = :numeroTelefone)");

			query.setParameter("numeroTelefone", "1123456789");
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
}
