package com.ezen.mybatis.core;

import java.util.List;

public class AnnoCursorMain {
	public static void main(String[] args) {
		try {
			AnnoCursorMain main = new AnnoCursorMain();
			main.callReadAllPets();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void callReadAllPets() throws Exception {
		PetDao petDAO = new PetDaoImpl();
		System.out.println("-- 애완 목록 --");
		List<PetDVO> petList = petDAO.callReadAllPets();
		for (PetDVO pet : petList) {
			System.out.println("\t" + pet);
		}
	}

}
