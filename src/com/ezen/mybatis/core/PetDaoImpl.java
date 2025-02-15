package com.ezen.mybatis.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class PetDaoImpl implements PetDAO {

	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<PetDVO> getAllPetsData() {
		List<PetDVO> result = sqlSessionTemplate.selectList("getAllPets"); 
		return result;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate
			sqlSessionTemplate) { 
		this.sqlSessionTemplate = sqlSessionTemplate; 
	}

	@Override
	public PetDVO getPetObject(String petName) throws Exception {
		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("name", petName);
		return (PetDVO) sqlSessionTemplate.selectOne("getPetObject", inputMap);
	}

	@Override
	public List<String> getAllSpecies() {
		return sqlSessionTemplate.selectList("getAllSpecies");
	}
	
	@Override
	public List<PetDVO> petsByGender(String gender) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("gender", gender);
		List<PetDVO> result = sqlSessionTemplate.selectList("petsByGender",
				param);
		return result;
	}

	@Override
	public int insertPet(PetDVO petDVO) {
		Map<String, Object> petMap = new HashMap<String, Object>();

		petMap.put("birth", petDVO.getBirth());
		petMap.put("death", petDVO.getDeath());
		petMap.put("name", petDVO.getName());
		petMap.put("owner", petDVO.getOwner());
		petMap.put("sex", petDVO.getSex());
		petMap.put("species", petDVO.getSpecies());
		sqlSessionTemplate.insert("insertPet", petMap);
		Long longId = (Long)petMap.get("id");

		return longId.intValue();
	}

	@Override
	public void updatePetData(PetDVO petDVO) throws Exception {
		Map<String, Object> inputMap = new HashMap<String, Object>();
	
		inputMap.put("owner", petDVO.getOwner());
		inputMap.put("death", petDVO.getDeath());
		inputMap.put("name", petDVO.getName());
		inputMap.put("id", petDVO.getId());
	
		sqlSessionTemplate.update("updatePetData", inputMap);
	}

	@Override
	public int deletePet(String species, String name) throws Exception {
		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("species", species);
		inputMap.put("name", name);

		return sqlSessionTemplate.delete("deletePet", inputMap);
	}

	@Override
	public List<PetDVO> findAllSnakes() {
		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("species", "뱀");
		inputMap.put("sex", "m");
		inputMap.put("owner", "남%");

		return sqlSessionTemplate.selectList("findAllSnakes", inputMap); 
	}

	@Override
	public List<PetDVO> findSnakePets() {
		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("species", "뱀");
		inputMap.put("sex", "m");
		inputMap.put("owner", "남%");
		return sqlSessionTemplate.selectList("findSnakePets", inputMap);
	}

	@Override
	public List<PetDVO> selectPetsIn() {
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		List<String> speciesList = new ArrayList<String>();

		speciesList.add("강아지");
		speciesList.add("고양이");
		speciesList.add("뱀");
		inputMap.put("speciesList", speciesList);
		return sqlSessionTemplate.selectList("selectPetsIn", inputMap); 
	}

	@Override
	public void updatePetDynamically(PetDVO petDVO) {
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		
		inputMap.put("birth", petDVO.getBirth());
		inputMap.put("death", petDVO.getDeath());
		inputMap.put("sex", petDVO.getSex());
		inputMap.put("name", petDVO.getName());
		
		sqlSessionTemplate.update("updatePetDynamically", inputMap); 
	}
}
