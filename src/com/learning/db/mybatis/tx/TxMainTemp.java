/**
 * TxMainTemp.java 
 */
package com.learning.db.mybatis.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.db.mybatis.tx.dao.PetDAO;

public class TxMainTemp {
	public static void main(final String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-tx-template.xml" });
		PetDAO petDAO = (PetDAO) ctx.getBean("petDAOImpl");
		petDAO.doInsertAndUpdateUsingTxTemplate();
		System.out.println("실행 종료");
	}
}
