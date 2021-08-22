package com.ezen.mybatis.core;

import java.util.Date;
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
			
			/**
			 * 투캅스 정보를 일부 수정한다. 투캅스 두 마리 청와대 입양 반영
			 * 수정(갱신) 후, 그 동물의 정보를 출력
			 */
			PetDVO petDataObj = new PetDVO();
			String name = "Rolf";
			
			petDataObj.setOwner("천지인");
			petDataObj.setName(name);
			petDataObj.setDeath(null);
			petDataObj.setId(petId);
			
			petDaoImpl.updatePetData(petDataObj);	
//			PetDVO changedPet = petDaoImpl.getPetObject(petId);
			System.out.println("--- 애완동물 ---");
//			System.out.println(changedPet);
			
			/**
			 * 특정 종(species)과 이름이 주어졌을 때 애완동물을 삭제하기
			 */
			int delCnt = petDaoImpl.deletePet("개", "Rolf");
			System.out.println("삭제된 애완 동물 수: " + delCnt);
			
			/**
			 * 남씨가 키우는 뱀을 나열하자.
			 */
			List<PetDVO> snakes = petDaoImpl.findAllSnakes();
			for (PetDVO snake: snakes) 
				System.out.println(snake);
			
			/**
			 * 남씨가 키우는 뱀을 나열하자 - switch
			 */
			List<PetDVO> snakePets = petDaoImpl.findSnakePets();
			for (PetDVO snake : snakePets)
				System.out.println(snake);	
			
			/**
			 * 다음 종에 속한 애완동물 나열 마바-스프링-foreach
			 */
			List<PetDVO> petsIn = petDaoImpl.selectPetsIn();
			for (PetDVO p : petsIn)
				System.out.println(p);

			/** 
			 * 누리 가 오늘 죽었다는 것을 갱신하자
			 */
			PetDVO petDVO = new PetDVO();
			petDVO.setName("누리");
			petDVO.setDeath(new Date());
			petDaoImpl.updatePetDynamically(petDVO);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
