package com.ezen.mybatis.core;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class PetDaoImpl implements PetDAO {
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void callReadPet() {
		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("name", "스륵이");
		inputMap.put("owner", "");
		inputMap.put("species", "");
		inputMap.put("sex", "");
		inputMap.put("birth", "");
		inputMap.put("death", "");
		sqlSessionTemplate.selectOne("callReadPet", inputMap);

		// Prints the procedure output data
		System.out.println("--- 소유자 : " + inputMap.get("owner"));
		System.out.println("--- 종류   : " + inputMap.get("species"));
		System.out.println("--- 암/수  : " + inputMap.get("sex"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth = sdf.format(inputMap.get("birth"));
		Object dObj = inputMap.get("birth");
		String death = (dObj == null ? "(생존)" : sdf.format(dObj));

		System.out.println("--- 출생 : " + birth);
		System.out.println("--- 사망 : " + death);
	}

	@Override
	public List<PetDVO> callReadAllPets() {
		List<PetDVO> outputData = sqlSessionTemplate.selectList(
				"callReadAllPets");
		return outputData;
	}


}
