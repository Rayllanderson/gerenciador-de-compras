package com.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.informacoes.InformacoesUsuario;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;
import com.ray.model.util.ArquivosUtil;
import com.ray.model.validacoes.UserValidation;

@MultipartConfig
@WebServlet("/my-account")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao repository = DaoFactory.createUserDao();
    private UserService service = new UserService();

    public AccountServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	listarTudo(request, response);
    }

    private void listarTudo(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	setInformacoes(request);
	response.setStatus(200);
	request.getSession().setAttribute("success", "");
	request.getSession().setAttribute("error", "");
	request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String action = request.getParameter("action");
	System.out.println(action);
	try {
	    if (action != null) {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("nome");
		String username = request.getParameter("username");
		String foto64 = ArquivosUtil.createBase64(request);
		String miniatura = ArquivosUtil.createMiniatureBase64(request);
		if (action.equals("editar")) {
		    if (UserValidation.idIsValid(request, id)) { // verificando se o id é de fato o id do user logado
			if (!UserValidation.fieldsAreValids(name, username)) { // validando os campos
			    redirect(request, response, 400, "Um ou mais campos estãos vazios");
			    System.out.println("user válido...");
			} else {
			    // verificando se modificou pra evitar requisição desnecessária
			    if (UserValidation.userIsModified(name, username, foto64, request)) {
				System.out.println("user modificado...");
				if (service.update(Long.valueOf(id), name, username, miniatura, foto64)) {
				    System.out.println("upou!");
				    response.setStatus(200);
				    request.getSession().setAttribute("success", "Editado com sucesso!");
				    User user = repository.findById(Long.valueOf(id));
				    request.getSession().setAttribute("user", user);
				    setInformacoes(request);
				} else {
				    System.out.println("nao upou");
				    redirect(request, response, 500, "Ocorreu um erro inesperado");
				}
				request.getRequestDispatcher("account.jsp").forward(request, response);
			    } else { // usuario nao mudou nenhum campo
				redirect(request, response, 200, "Nenhuma alteração foi detectada.");
			    }
			}
		    } else { // id não é o mesmo
			redirect(request, response, 400,
				"Ocorreu um erro. Por favor, atualize a página e se o problema persistir, faça login novamente.");
		    }
		} else if(action.equals("change-password")) {
		   changePassword(request, response);
		} else {// se mudar o parametro de action
		    redirect(request, response, 400, "Ocorreu um erro. Por favor, atualize a página.");
		}
	    } else { // se nao for editar, simplemtente volta tudo
		request.getRequestDispatcher("account.jsp").forward(request, response);
	    }

	} catch (EntradaInvalidaException e) { // upou arquivo inválido
	    redirect(request, response, 400, e.getMessage());
	} catch (MyLoginException e) { // username ja existente
	    redirect(request, response, 409, "O username escolhido já está em uso. Tente usar outro");
	}catch (RuntimeException e) {
	    e.printStackTrace();
	    redirect(request, response, 500, "ocorreu um erro inesperado");
	}
    }

    /**
     * redireciona para account.jsp. se o codigo for entre 200 e 399, alert success, senao, alert error.
     * @param request
     * @param response
     * @param codigo
     * @param mensagem
     * @throws ServletException
     * @throws IOException
     */
    private void redirect(HttpServletRequest request, HttpServletResponse response, int codigo, String mensagem)
	    throws ServletException, IOException {
	response.setStatus(codigo);
	String tipo = null;
	if (codigo >= 200 && codigo < 400) { //success
	    tipo = "success";
	    request.getSession().setAttribute("error", ""); //resetando o atributo error
	}else { //fail
	    tipo = "error";
	    request.getSession().setAttribute("success", ""); //resetando o atributo success
	}
	request.getSession().setAttribute(tipo, mensagem); //setando novo status com mensagem enviada
	request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    private void setInformacoes(HttpServletRequest request) {
	User user = (User) request.getSession().getAttribute("user");
	InformacoesUsuario infos = new InformacoesUsuario(user);
	request.getSession().setAttribute("tListas", infos.getNumeroTotalCategorias());
	request.getSession().setAttribute("tProdutos", infos.getNumTotalProdutos());
	request.getSession().setAttribute("nProdutosComprados", infos.getNumTotalProdutosComprados());
	request.getSession().setAttribute("tEstipulado", infos.getTotalEstipulado());
	request.getSession().setAttribute("tGasto", infos.getTotalReal());
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String old = request.getParameter("old");
	String new1 = request.getParameter("new1");
	String new2 = request.getParameter("new2");
	if(UserValidation.passwordIsValid(new1, new2)) { //senhas se batem
	    User user = (User) request.getAttribute("user");
	   try {
	       	user = service.changePassword(user, old, new1); 
		request.getSession().setAttribute("user", user);
	       	response.setStatus(200);
		response.getWriter().write("Senha Alterada com sucesso!");
	    }catch (MyLoginException e) {
		response.setStatus(400);
		response.getWriter().write("A senha atual digitada não corresponde");
	    } 
	}else {
	    response.setStatus(400);
	    response.getWriter().write("As novas senham não correspodem");
	}
    }
}
