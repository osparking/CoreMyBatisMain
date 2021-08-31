package com.ezen.mybatis.core;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PetMapper {
	//@formatter:off
	/**
	 * 애완 동물 한 마리 선택
	 * 
	 * @param petName
	 * @return 이름이 일치하는 애완 동물
	 */
	@Select("SELECT ID as id, NAME as name, OWNER as owner, SPECIES as "
			+ "species, SEX as sex, "
			+ "BIRTH as birth, DEATH as death " 
			+ "FROM Pet where id = #{petId} ")
	public PetDVO getPetObject(int petId); 

	/**
	 * 모든 애완 동물 선택
	 * 
	 * @return 애완 동물 목록
	 */
	@Select("SELECT * FROM PET")
	@Results(value = { @Result(property = "id", column = "ID"), 
			@Result(property = "name", column = "NAME"),
			@Result(property = "owner", column = "OWNER"), 
			@Result(property = "species", column = "SPECIES"),
			@Result(property = "sex", column = "SEX") })
	List<PetDVO> selectAllPets();

	/**
	 * 모든 애완 동물 선택
	 * @return
	 */
	@Select("SELECT ID as id, NAME as name, OWNER as owner, SPECIES as "
			+ " species, SEX as sex, BIRTH as birth, DEATH as death FROM Pet")
	public List<PetDVO> getAllPetsData();

	/**
	 * 성별 조건으로 애완동물 검색
	 * @param sex
	 * @return 애완 동물 목록
	 */
	@Select("SELECT ID as id, NAME as name, OWNER as owner, SPECIES as "
			+ " species, SEX as sex, BIRTH as birth, DEATH as death" + " FROM Pet where sex = #{sex} ")
	public List<PetDVO> selectPets(String sex);

	/**
	 * 갱신 - 모든 열
	 * @param petDVO
	 */
	@Update("UPDATE Pet p SET p.birth = #{birth}, p.sex = #{sex} WHERE " + " p.name = #{name} ")
	public boolean updatePetData(PetDVO petDVO);

	/**
	 * 갱신 - 출생일, 성별 열
	 * @param birth
	 * @param sex
	 * @param name
	 */
	@Update("UPDATE Pet SET birth = #{birth}, "
			+ "sex = #{sex} " 
			+ " WHERE name = #{name} ")
	public void updateData(
			@Param("birth") Date birthday, 
			@Param("sex") String sex, 
			@Param("name") String name);

	/**
	 * 삭제
	 * @param petDVO
	 */
	@Delete("DELETE FROM Pet WHERE name = #{name} " 
	 + "AND species = #{species} ")
	public void deletePet(PetDVO petDVO);

	/**
	 * 애완동물을 삽입한다
	 * 
	 * @param petDVO
	 */
	@Insert("INSERT INTO Pet (NAME, OWNER, SPECIES, SEX, BIRTH) "
			+ "VALUES (#{name}, #{owner}, #{species}, #{sex},  #{birth}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertPet(PetDVO petDVO);
}
