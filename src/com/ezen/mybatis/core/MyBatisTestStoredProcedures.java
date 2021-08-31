package com.ezen.mybatis.core;

import java.util.List;

/**
 * 파일 이름: MyBatisTestStoredProcedures.java
 */
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBatisTestStoredProcedures {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
					new String[] { "applicationContext-myBatis.xml" });
			PetDAO petDAOImpl = (PetDAO) appContext.getBean("petDaoImpl");
			petDAOImpl.callReadPet();

			List<PetDVO> petList = petDAOImpl.callReadAllPets();

			System.out.println("숫자: " + petList.size());
			petList.forEach(System.out::println);
			
			petDAOImpl.callPetOwnerFunction();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
