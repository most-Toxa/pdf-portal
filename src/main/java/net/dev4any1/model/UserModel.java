package net.dev4any1.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.dev4any1.pojo.User;
@XmlRootElement
public class UserModel extends User implements DbObject, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3149709616339527183L;
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;	
	}

	
}
