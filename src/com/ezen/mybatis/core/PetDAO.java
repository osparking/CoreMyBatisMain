package com.ezen.mybatis.core;

import java.util.List;

/**
 * 파일 이름: PetDAO.java
 * 
 * @author Jongbum Park
 *
 */
public interface PetDAO {
	default void callReadPet() {
		System.out.println("기본 callReadPet");
	};

	List<PetDVO> callReadAllPets() throws Exception;

	default public void callPetOwnerFunction() {
		System.out.println("기본 callPetOwnerFunction");
	};
}
