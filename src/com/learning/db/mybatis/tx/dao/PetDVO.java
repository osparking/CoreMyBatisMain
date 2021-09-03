package com.learning.db.mybatis.tx.dao;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PetDVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String owner;
	private String species;
	private String sex;
	private Date birth;
	private Date death;
	public String getName() {
		return name;
	}
}
