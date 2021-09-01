package com.ezen.mybatis.core;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class PetDaoAnno implements PetDao {
	private static SqlSession getSqlSession() throws Exception {
		String resource = "java-mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		sqlSessionFactory.getConfiguration().addMapper(PetMapper.class);
		return sqlSessionFactory.openSession();
	}

	public List<PetDVO> callReadAllPets() throws Exception {
		SqlSession sqlSession = getSqlSession();
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
//		HashMap<String, List<PetDVO>> outputMap =
//				new HashMap<String, List<PetDVO>>();
		
		return mapper.callReadAllPets();		
	}

	@Override
	public void callReadPet(PetDVO petDVO) throws Exception {
		SqlSession sqlSession = getSqlSession();
		PetMapper mapper = sqlSession.getMapper(PetMapper.class);
		mapper.callReadPet(petDVO);
	}
}
