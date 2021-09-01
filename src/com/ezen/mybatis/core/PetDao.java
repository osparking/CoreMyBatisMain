package com.ezen.mybatis.core;

import java.util.List;

public interface PetDao {
	void callReadPet(PetDVO petDVO) throws Exception;
	List<PetDVO> callReadAllPets() throws Exception;
}
