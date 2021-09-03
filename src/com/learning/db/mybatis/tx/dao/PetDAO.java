package com.learning.db.mybatis.tx.dao;

public interface PetDAO {
	void doInsertAndUpdateInTx();
	void doInsertAndUpdateUsingTxTemplate();
	void doInsertAndUpdateInTxAnno();
	void insertPet();
	void updatePetData(); 
}
