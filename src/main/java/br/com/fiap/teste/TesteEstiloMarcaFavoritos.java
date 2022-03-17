package br.com.fiap.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteEstiloMarcaFavoritos {

	public static void main(String[] args) {

		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

//			String jpql = "select c.estilo, c.marca from Consumo c where litrosConsumidos * precoPorLitro = (select MAX(c.litrosConsumidos * c.precoPorLitro) from Consumo c, Consumidor s where s.numeroTelefone = 1123456789)";
//
//			TypedQuery<Consumo[]> typedQuery = em.createQuery(jpql, Consumo[].class);
//
//			List<Consumo[]> consumo = typedQuery.getResultList();
//
//			for (Object[] item : consumo) {
//				System.out.println(item.getClass().getSimpleName());
//
//			}

			Query query = em.createNativeQuery("select DS_ESTILO, DS_MARCA\n" + "from TB_CONSUMO\n"
					+ "where nr_litros_consumidos * nr_preco_por_litro = (select MAX(nr_litros_consumidos * nr_preco_por_litro)\n"
					+ "                                                   from TB_CONSUMO\n"
					+ "                                                   where NR_TELEFONE = 1123456789)");
			
			List<Object[]> list = query.getResultList();

			for (Object[] obj : list) {
			     String age = (String) obj[0];
			     String name = (String) obj[1];
			     System.out.println(age + " " + name);
			}
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
