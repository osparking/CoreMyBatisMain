package com.ezen.mybatis.core;

import java.util.List;

public interface PetDAO {
	List<PetDVO> getAllPetsData();
	PetDVO getPetObject(String petName) throws Exception;
	public List<String> getAllSpecies();
	
	List<PetDVO> petsByGender(String gender);
	int insertPet(PetDVO petDVO);
	void updatePetData(PetDVO petDVO) throws Exception;
}
