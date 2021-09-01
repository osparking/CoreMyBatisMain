package com.ezen.mybatis.core;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface PetMapper {
	@Select(value = "{ CALL read_all_pets()} ")
	@Options(statementType = StatementType.CALLABLE)
	public List<PetDVO> callReadAllPets();
	
	@Select(value = "{ CALL read_pet(" + 
			" #{ name, mode = IN, jdbcType = VARCHAR}, " + 
			" #{ owner, mode = OUT, jdbcType = VARCHAR}, " + 
			" #{ species, mode = OUT, jdbcType = VARCHAR}, " + 
			" #{ sex, mode = OUT, jdbcType = VARCHAR}, " + 
			" #{ birth, mode = OUT, jdbcType = DATE}, " + 
			" #{ death, mode = OUT, jdbcType = DATE} " + 
			" )} ")
	@Options(statementType = StatementType.CALLABLE)
	public void callReadPet(PetDVO petDVO);
	
}
