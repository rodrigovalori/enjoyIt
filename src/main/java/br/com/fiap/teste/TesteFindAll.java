package br.com.fiap.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteFindAll {

	public static void main(String[] args) {
		EntityManager em = null;
		
		try {
			em = Persistence.createEntityManagerFactory("enjoyIt").createEntityManager();

			EntityManager session = null;
			
			Query query = em.createNativeQuery("SELECT nr_telefone, nm_consumidor FROM tb_consumidor");
			
			List<Object[]> list = query.getResultList();

			for (Object[] obj : list) {
			     BigDecimal nrTelefone = (BigDecimal) obj[0];
			     String nomeConsumidor = (String) obj[1];
			     System.out.println(nrTelefone + " " + nomeConsumidor);
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
