package com.ray.model.entities;

import java.io.Serializable;

import com.ray.model.util.Theme;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private String foto;
    private String miniatura;
    private Theme theme;

    public User(Long id, String name, String username, String password) {
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
    }

    public User(Long id, String name, String username, String password, String miniatura, String foto, Theme theme) {
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
	this.miniatura = miniatura;
	this.foto = foto;
    }

    public User() {
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return name;
    }

    public String getMiniatura() {
	return miniatura;
    }

    public void setMiniatura(String miniatura) {
	this.miniatura = miniatura;
    }

    public String getFoto() {
	return foto;
    }

    public void setFoto(String foto) {
	this.foto = foto;
    }

    public Theme getTheme() {
	return theme;
    }

    public void setTheme(Theme theme) {
	this.theme = theme;
    }

}
