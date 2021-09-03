package com.learning.db.mybatis.tx.dao;

public interface PetDAO {
	public void doInsertAndUpdateInTx();
	public void insertPet();
	public void updatePetData(); 
}
