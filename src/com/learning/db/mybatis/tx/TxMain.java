/**
 * TxMain.java 
 */
package com.learning.db.mybatis.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.db.mybatis.tx.dao.PetDAO;

public class TxMain {
	public static void main( final String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String [] {"applicationContext-tx.xml"});
		PetDAO petDAO = (PetDAO) ctx.getBean("petDAOImpl");
		petDAO.doInsertAndUpdateInTx();
		System.out.println("실행 종료");
	}
}
