package com.ezen.mybatis.core;

public class AnnoMain {
	public static void main(String[] args) {
		try {
			AnnoMain main = new AnnoMain();
			main.callReadPet();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void callReadPet()throws Exception {
		PetDVO pet = new PetDVO();
		pet.setName("스륵이");
		PetDao petDao = new PetDaoAnno();
		petDao.callReadPet(pet);
		System.out.println(pet);
	}


}
