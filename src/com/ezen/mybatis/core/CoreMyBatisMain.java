package com.ezen.mybatis.core;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CoreMyBatisMain {
	//@formatter:off
	public PetDVO getPetObject(String petName) 
			throws Exception {
		
		HashMap<String, String> inputMap 
			= new HashMap<String, String>();
		inputMap.put("name", petName);
		return (PetDVO) getSqlSession().selectOne(
				"getPetObject", inputMap);
	}

	public int createPet(PetDVO petDVO) throws Exception {
		HashMap<String, Object> inputMap 
			= new HashMap<String, Object>();
		inputMap.put("name", petDVO.getName());
		inputMap.put("owner", petDVO.getOwner());
		inputMap.put("species", petDVO.getSpecies());
		inputMap.put("sex", petDVO.getSex());
		inputMap.put("birth", petDVO.getBirth());

		/**
		 * Get the sql session and commit the data
		 */
		SqlSession sqlSession = getSqlSession();
		sqlSession.insert("createPet", inputMap);
		sqlSession.commit();

		Long newID = (Long) inputMap.get("id");
		return newID.intValue();
	}
	
	public void updatePetData(PetDVO petDVO) throws Exception {
		var inputMap = new HashMap <String, Object>();
		
		inputMap.put("owner", petDVO.getOwner());
		inputMap.put("species", petDVO.getSpecies());
		inputMap.put("sex", petDVO.getSex());		
		inputMap.put("birth", petDVO.getBirth());
		inputMap.put("death", petDVO.getDeath());
		inputMap.put("name", petDVO.getName());
		
		SqlSession sqlSession = getSqlSession(); 
		sqlSession.update("updatePetData", inputMap); 
		sqlSession.commit(); 
	}
	/**
	 * @param species 삭제할 동물의 종류(예, 고양이, 강아지, 등)
	 * @param name 애완동물 애칭(나비, 힌둥이, 등)
	 * @throws Exception
	 */
	public void deletePet(String species, String name)
			throws Exception {
		HashMap <String, String> inputMap	= 
				new HashMap <String, String>(); 
		inputMap.put("species", species);
		inputMap.put("name", name);
		System.out.println("--- 애완동물 삭제 ---" + inputMap);
		SqlSession sqlSession = getSqlSession();
		sqlSession.update("deletePet", inputMap);
		sqlSession.commit(); 
	}

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext appContext = new 
				ClassPathXmlApplicationContext(new String[] 
						{ "applicationContext-myBatis.xml" })) {

			PetDAO petDaoImpl =
					(PetDAO) appContext.getBean("petDaoImpl");
			/**
			 * 애완동물 목록 길이 출력
			 */
			List<PetDVO> pets = petDaoImpl.getAllPetsData();
			System.out.println("--- pets ---" + pets.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<PetDVO> selectByGender(String gender) 
			throws Exception {
		var inputMap = new Properties();
		inputMap.put("sex", gender);
		return getSqlSession().selectList("selectByGender",
					inputMap);
	}

	public List<String> getAllSpecies() throws Exception {
		return getSqlSession().selectList("getAllSpecies");
	}

	private static SqlSession getSqlSession() throws Exception {
		String resource = "core-mybatis-config.xml";
		InputStream inputStream = 
				Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory.openSession();
	}

	public List<PetDVO> getAllPetsData() throws Exception {
		return getSqlSession().selectList("getAllPets");
	}
}
