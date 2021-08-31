package com.ezen.mybatis.core;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class PetDAOImpl {
	/**
	 * 프로젝트 구성 설정 값을 읽어 적용하고, sqlSession 을 생성하여 반환한다.
	 * @return SqlSession 객체
	 * @throws Exception
	 */
	private static SqlSession getSqlSession() throws Exception {
		String resource = "java-mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSessionFactory.getConfiguration().addMapper(PetMapper.class);
		return sqlSessionFactory.openSession();
	}

	public List<PetDVO> getAllPetsData() throws Exception {
		PetMapper mapper = getSqlSession().getMapper(PetMapper.class);
		return mapper.getAllPetsData();
	}

	public PetDVO getPetObject(int petId) throws Exception {
		PetMapper mapper = getSqlSession().getMapper(PetMapper.class);
		return mapper.getPetObject(petId);
	}

	/**
	 * 모든 애완동물 목록을 읽어 반환한다.
	 * @return
	 * @throws Exception
	 */
	List<PetDVO> selectAllPets() throws Exception {
		PetMapper mapper = getSqlSession().getMapper(PetMapper.class);
		return mapper.selectAllPets();
	}

	public List<PetDVO> selectPets(String sex) throws Exception {
		PetMapper mapper = getSqlSession().getMapper(PetMapper.class);
		return mapper.selectPets(sex);
	}

	public void updatePetData(PetDVO petDVO) throws Exception {
		SqlSession sqlSession = getSqlSession();
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		boolean updated = mapper.updatePetData(petDVO);
		System.out.println("갱신됨: " +updated);
		sqlSession.commit();
	}

	public void updateData(Date birth, String sex, String name) throws Exception {
		SqlSession sqlSession = getSqlSession();
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		mapper.updateData(birth, sex, name);
		sqlSession.commit();
	}

	public void deletePet(PetDVO petDVO) throws Exception {
		SqlSession sqlSession = getSqlSession();
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		mapper.deletePet(petDVO);
		sqlSession.commit();
	}

	/**
	 * 애완동물을 저장하고 생성된 ID를 출력한다.
	 * @param petDVO 저장할 애완동물 정보
	 * @throws Exception
	 */
	public void insertPet(PetDVO petDVO) throws Exception {
		SqlSession sqlSession = getSqlSession();
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		mapper.insertPet(petDVO);
		sqlSession.commit();
		/**
		 * 생성된 행의 ID 채취하여 콘솔 출력
		 */
//		System.out.println("--- " + petDVO.getName() + " ID : " 
//				+ petDVO.getId());
	}
}
