package com.ray.model.validacoes;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;

/**
 * Classe para validar nome e valor
 */
public class UserValidation {

    /**
     * Verifica se o username j� existe
     *
     * @param newUsername
     *
     * @throws MyLoginException caso o username j� exista
     */
    public static void usernameIsValid(String newUsername) throws MyLoginException {
        UserDao dao = DaoFactory.createUserDao();
        List<String> usernames = dao.findAllUsernames();
        for (String name : usernames) {
            if (name.equals(newUsername)) {
                throw new MyLoginException("Username j� existente!");
            }
        }
    }

    /**
     * Verifica se o username � v�lido. caso seja, seta novo username no user passado como par�metro.
     *
     * @param user - para setar o novo username nesse user.
     * @param newUsername - novo username a ser setado
     *
     * @throws MyLoginException caso username j� exista. M�todo j� possui mensagem. '(Username j� existente!)'
     */
    public static void usernameValidation(User user, String newUsername) throws MyLoginException {
        // Verificando se o username atual n�o � igual ao username dele mesmo
        if (!newUsername.equals(user.getUsername())) {
            // se nao for, verificar se o username j� existe
            UserValidation.usernameIsValid(newUsername);
            user.setUsername(newUsername);
        }
    }

    /**
     * verifica se um dos campos est�o vazios
     *
     * @param name
     * @param username
     *
     * @return true caso os campos n�o estejam vazios
     */
    public static boolean fieldsAreNotEmpty(String name, String username) {
        return !(name.isEmpty() || username.isEmpty());
    }

    /**
     * verifica se um dos campos est�o vazios
     *
     * @param username
     * @param pass1
     * @param pass2
     *
     * @return true caso os campos n�o estejam vazios
     */
    public static boolean fieldsAreNotEmpty(String username, String pass1, String pass2) {
        return !(username.isEmpty() || pass1.isEmpty() || pass2.isEmpty());
    }

    /**
     * checa se nome e username do user foi alterado
     *
     * @param nome & username - para verificar com o user atual
     * @param foto64
     * @param request para capturar o user atual
     *
     * @return true caso detecte que um dos campos foi modificado
     */
    public static boolean userIsModified(String nome, String username, String foto64, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            return !(user.getName().equals(nome) && user.getUsername().equals(username) && (user.getFoto().equals(foto64) || foto64.equals("")));
        } catch (NullPointerException e) {
            return true;
        }
    }

    /**
     * Verifica se o ID do usu�rio � o mesmo ID da sess�o atual setado via Cookie
     *
     * @param request
     * @param id
     *
     * @return true caso o id seja v�lido
     */
    public static boolean idIsValid(HttpServletRequest request, String id) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("b80bb7740288fda1f201890375a60c8f")) { // md5 de ID
                return cookie.getValue().equals(id);
            }
        }
        return false;
    }

    /**
     * @param pasw1
     * @param pasw2
     *
     * @return true caso as 2 senhas sejam iguais
     */
    public static boolean passwordIsValid(String pasw1, String pasw2) {
        return pasw1.equals(pasw2);
    }
}
