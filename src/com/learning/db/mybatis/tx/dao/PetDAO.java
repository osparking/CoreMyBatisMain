package com.learning.db.mybatis.tx.dao;

public interface PetDAO {
	void doInsertAndUpdateInTx();
	void doInsertAndUpdateUsingTxTemplate();
	void insertPet();
	void updatePetData(); 
}
