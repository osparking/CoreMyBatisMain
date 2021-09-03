package com.learning.db.mybatis.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.db.mybatis.tx.dao.PetDAO;

public class TxMainAnno {
	public static void main(final String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-tx-annotations.xml" });
		PetDAO petDAO = (PetDAO) ctx.getBean("petDAOImpl");
		try {
			petDAO.doInsertAndUpdateInTxAnno();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("시험 마침");
	}
}
