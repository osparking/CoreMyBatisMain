package com.ezen.mybatis.core;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface PetMapper {
	@Select(value="{CALL read_all_pets()}")
	@Options(statementType = StatementType.CALLABLE)
	public List<PetDVO> callReadAllPets();
}

