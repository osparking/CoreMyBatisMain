package com.ezen.mybatis.core;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import jbpark.utility.Util;


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
			
			/**
			 * 모든 애완 동물 종류를 출력
			 */
			List <String> species = petDaoImpl.getAllSpecies();
			for (String animal : species) {
				System.out.println(animal);
			}
			
			/**
			 * 모든 애완 동물 암수 구별 목록 채취
			 */
			List <PetDVO> genderPets = petDaoImpl.petsByGender("f");
			for (PetDVO p : genderPets) {
				System.out.println(p);
			}
			
			/**
			 * 애완동물 생성하여 삽입
			 */
			PetDVO newPet = new PetDVO();

			newPet.setBirth(Util.getDate("1992-01-03"));
			newPet.setDeath(null);
			newPet.setName("Rolf");
			newPet.setOwner("종범3");;
			newPet.setSex('m');;
			newPet.setSpecies("개");
			
			int petId = petDaoImpl.insertPet(newPet);
			System.out.println("ID: " + petId);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
