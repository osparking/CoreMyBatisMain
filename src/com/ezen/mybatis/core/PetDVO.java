package com.ezen.mybatis.core; // 2 단계 type 값과 일치해야!
import java.util.Date;
import java.io.Serializable;

public class PetDVO implements Serializable { 
	private static final long serialVersionUID = 5727276484706126393L;
	private Integer id;
    private String name;
    private String owner;
    private String species;
    private char sex;
    private Date birth;
    private Date death;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getDeath() {
		return death;
	}
	public void setDeath(Date death) {
		this.death = death;
	}
}

