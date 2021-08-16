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

public class CoreMyBatisMain {
	//@formatter:off
	public PetDVO getPetObject(String petName) throws Exception {
		HashMap<String, String> inputMap 
			= new HashMap<String, String>();
		inputMap.put("name", petName);
		return (PetDVO) getSqlSession().selectOne("getPetObject", 
				inputMap);
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

	private static Date getDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, date);
		return cal.getTime();
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
		try {
			CoreMyBatisMain main = new CoreMyBatisMain();

			String dogName = "깜두";
			PetDVO pet = main.getPetObject(dogName);
			
			System.out.println("삭제 전: " + 
					((pet == null) ? "그런 동물 없습니다."  : pet));
			if (pet != null) {
				main.deletePet("강아지", dogName);
			}
			
			pet = main.getPetObject(dogName);
			System.out.println("삭제 후: " + ((pet == null) 
					? "그런 동물 없습니다."  : pet));
			
			PetDVO petDataObj = new PetDVO();
			String name = "구름이";			 
			petDataObj.setOwner("최주인");
			petDataObj.setName(name); 
			main.updatePetData(petDataObj);
			
			PetDVO petObj = new PetDVO();
			petObj.setName("구름이");
			petObj.setOwner("김재연");
			petObj.setSpecies("강아지");
			petObj.setSex('m');
			Date today = getDate(2020, 11, 15);
			petObj.setBirth(today);
			/**
			 * 생성된 애완동물을 DB pet 테이블에 삽입
			 */
			int id = main.createPet(petObj);
			System.out.println("새 애완동물 ID : " + id);

			System.out.println(main.getAllSpecies());

			System.out.println("암놈 목록");
			for (PetDVO p : main.selectByGender("f")) {
				System.out.println(p);
			}
			System.out.println("숫놈 목록");
			for (PetDVO p : main.selectByGender("m")) {
				System.out.println(p);
			}
			// 누리 소유자 이름 출력
			String petName = "누리";
			pet = main.getPetObject(petName);
			System.out.println(petName + ": " + pet.getOwner());
			// Printing pets data
			List<PetDVO> allPets = main.getAllPetsData();
			System.out.println("--- 애완동물 숫자 ----" + allPets.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<PetDVO> selectByGender(String gender) 
			throws Exception {
		var inputMap = new Properties();
//		HashMap <String, String> inputMap = 
//				new HashMap <String, String>();
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
