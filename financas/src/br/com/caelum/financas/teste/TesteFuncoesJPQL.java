package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(3);

		String jpql = "select distinct avg(m.valor) from Movimentacao m where m.conta = :pConta "
				+ "and m.tipoMovimentacao = :pTipo " + "group by m.data";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<Double> medias = (List<Double>) query.getResultList();

		/*System.out.println("A média do dia 26 é: " + medias.get(0));
		System.out.println("A média do dia 27 é: " + medias.get(1));*/
		
		for (Double media : medias) {
		    System.out.println("A média é: " + media);
		}		

		em.getTransaction().commit();
		em.close();
	}

}
