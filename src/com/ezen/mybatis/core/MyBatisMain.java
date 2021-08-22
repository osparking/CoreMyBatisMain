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
			
			/**
			 * 애완동물 이름으로 찾아 출력
			 */
			PetDVO pet = petDaoImpl.getPetObject("누리");
			System.out.println("--- 애완동물 ---");
			System.out.println(pet);
			
			/**
			 * 모든 애완 동물 정보 출력
			 */
			List <PetDVO> allPets = petDaoImpl.getAllPetsData();
			for (PetDVO aPet : allPets) {
				System.out.println(aPet);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
