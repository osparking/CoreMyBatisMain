package com.ezen.mybatis.core;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyBatisMain {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext appContext 
				= new ClassPathXmlApplicationContext(
					new String[] { "applicationContext-myBatis.xml" });

			PetDAO petDaoImpl = (PetDAO) appContext.getBean("petDaoImpl");
			/**
			 * 애완동물 목록 길이 출력
			 */
			List<PetDVO> pets = petDaoImpl.getAllPetsData();
			System.out.println("--- pets ---" + pets.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
