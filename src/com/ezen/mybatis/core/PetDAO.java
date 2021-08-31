package com.ezen.mybatis.core;

import java.util.List;

/**
 * 파일 이름: PetDAO.java
 * @author Jongbum Park
 *
 */
public interface PetDAO {
	void callReadPet();
	List <PetDVO> callReadAllPets();
}

