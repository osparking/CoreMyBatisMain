package com.ezen.mybatis.core;

import java.util.List;

public interface PetDao {
	List<PetDVO> callReadAllPets() throws Exception;
}
