package com.learning.db.mybatis.tx.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class PetDAOImpl implements PetDAO {
	private SqlSessionTemplate sqlSessionTemplate;
	private TransactionTemplate transactionTemplate;

	@Override
	public void doInsertAndUpdateInTx() {
		try {
			/**
			 * 행 삽입
			 */
			insertPet();
			/**
			 * Create an error to get the exception
			 */
			int i = 0;
			int j = 100 / i;
			/**
			 * 행 갱신
			 */
			updatePetData();
			System.out.println("삽입-갱신 성공");
		} catch (Exception ex) {
			TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
			System.out.println("** 삽입&갱신 성공인가? : " + status.isCompleted());
			status.setRollbackOnly();
		}
	}

	@Override
	public void insertPet() {
		// Data to be inserted
		PetDVO petDVO = new PetDVO();
		petDVO.setName("Slimmy");
		petDVO.setOwner("Steve");
		petDVO.setSpecies("Snake");
		petDVO.setSex("f");
		petDVO.setBirth(new Date());

		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("name", petDVO.getName());
		inputMap.put("owner", petDVO.getOwner());
		inputMap.put("species", petDVO.getSpecies());
		inputMap.put("sex", petDVO.getSex());
		inputMap.put("birth", petDVO.getBirth());
//		inputMap.put("death", petDVO.getDeath());
		sqlSessionTemplate.insert("createPet", inputMap);
	}

	@Override
	public void updatePetData() {
		// Updating the data
		PetDVO petDVO = new PetDVO();
		petDVO.setName("Slimmy");
		petDVO.setDeath(new Date());

		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("death", petDVO.getDeath());
		inputMap.put("name", petDVO.getName());
		sqlSessionTemplate.update("updatePetData", inputMap);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void doInsertAndUpdateUsingTxTemplate() {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					/*
					 * 애완동물 삽입
					 */
					insertPet();
					/*
					 * 의도적 예외 유발
					 */
//					int i = 0;
//					int j = 100 / i;
					/*
					 * 애완동물 갱신
					 */
					updatePetData(); // 정보 갱신 키로 id를 사용
					System.out.println("삽입 및 갱신 성공.");
				} catch (Exception ex) {
					boolean res = status.isCompleted();
					System.out.println("** 삽입&갱신(prog) 성공인가? : " + res);
					status.setRollbackOnly();
				}
			}
		});
	}

	@Override
	@Transactional(readOnly = false)
	public void doInsertAndUpdateInTxAnno() {
		PetDVO petDVO = new PetDVO();
		petDVO.setName("날씨니");
		petDVO.setOwner("국사봉");
		petDVO.setSpecies("족제비");
		petDVO.setSex("f");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		petDVO.setBirth(cal.getTime());

		/**
		 * 행 삽입
		 */
		insertPet(petDVO);

		/*
		 * 의도적 예외 유발
		 */
		int i = 0;
		int j = 100 / i;

		/**
		 * 행 갱신
		 */
		petDVO.setSex("m");
		updatePetData(petDVO);
		System.out.println("삽입 및 갱신 성공");
	}
	
	private void insertPet(PetDVO petDVO) {
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("name", petDVO.getName());
		inputMap.put("owner", petDVO.getOwner());
		inputMap.put("species", petDVO.getSpecies());
		inputMap.put("sex", petDVO.getSex());
		inputMap.put("birth", petDVO.getBirth());
		sqlSessionTemplate.insert("createPet", inputMap);
	}
	
	private void updatePetData(PetDVO petDVO) {
		HashMap<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("birth", petDVO.getBirth());
		inputMap.put("sex", petDVO.getSex());
		inputMap.put("name", petDVO.getName());
		sqlSessionTemplate.update("updatePetData", inputMap);
	}
}
