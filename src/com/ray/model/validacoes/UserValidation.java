package com.ray.model.validacoes;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;

/**
 * Classe para validar nome e valor
 *
 */
public class UserValidation {

    /**
     * Verifica se o username já existe
     * @param newUsername
     * @throws MyLoginException caso o username já exista 
     */
    public static void validarUsername(String newUsername, UserDao dao ) throws MyLoginException{
	List<String> usernames = dao.findAllUsernames();
	for (String name : usernames) {
	    if (name.equals(newUsername)) {
		throw new MyLoginException("Username já existente!");
	    }
	}
    }
       
    /**
     * verifica se um dos campos estão vazios
     * 
     * @param name
     * @param username
     * @return true caso os campos sejam válidos
     */
    public static boolean fieldsAreValids(String name, String username) {
	return !(name.isEmpty() || username.isEmpty());
    }
    

    /**
     * checa se nome e username do user foi alterado
     * 
     * @param nome    & username - para verificar com o user atual
     * @param request para capturar o user atual
     * @return true caso detecte que um dos campos foi modificado
     */
    public static boolean userIsModified(String nome, String username, HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	return !(user.getName().equals(nome) && user.getUsername().equals(username));
    }
    
    /**
     * Verifica se o ID do usuário é o mesmo ID da sessão atual setado via Cookie
     * @param request
     * @param id
     * @return true caso o id seja válido
     */
    public static boolean idIsValid(HttpServletRequest request, String id) {
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
	    if (cookie.getName().equals("b80bb7740288fda1f201890375a60c8f")) { // md5 de ID
		System.out.println(cookie.getValue() + "< cookie;\n id normal >" + id);
		return cookie.getValue().equals(id);
	    }
	}
	return false;
    }
}
