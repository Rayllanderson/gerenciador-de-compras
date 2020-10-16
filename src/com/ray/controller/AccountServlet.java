package com.ray.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.Gson;
import com.ray.informacoes.InformacoesUsuario;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.UserDao;
import com.ray.model.entities.User;
import com.ray.model.exception.MyLoginException;
import com.ray.model.service.UserService;
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
	String action = request.getParameter("action");
	if (action != null) {
	    setInformacoes(request);
	    if (action.equals("view")) {
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("account.jsp");
		dispatcher.forward(request, response);
	    }
	}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String action = request.getParameter("action");
	System.out.println(action);
	if (action != null) {
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    String id = request.getParameter("id");
	    String name = request.getParameter("nome");
	    String username = request.getParameter("username");

	    String foto64 = createBase64(request);
	    
	    String miniatura = createMiniatureBase64(request);

	    if (action.equals("editar")) {
		if (UserValidation.idIsValid(request, id)) { // verificando se o id é de fato o id do user logado
		    if (!UserValidation.fieldsAreValids(name, username)) { // validando os campos
			response.setStatus(400);
			response.getWriter().write("Um ou mais campos estãos vazios");
		    } else {
			if (UserValidation.userIsModified(name, username, foto64, request)) { // verificando se
												 // modificou pra evitar
												 // requisição
												 // desnecessária
			    try {
				if (service.update(Long.valueOf(id), name, username, miniatura, foto64)) {
				    response.setStatus(200);
				    response.getWriter().write("Editado com sucesso!");
				    User user = repository.findById(Long.valueOf(id));
				    System.out.println(user.getName());
				    System.out.println(user.getMiniatura());
				    System.out.println(user.getFoto());
				    request.getSession().setAttribute("user", user);
				    setInformacoes(request);
				    request.getRequestDispatcher("account.jsp").forward(request, response);
				} else {
				    response.setStatus(500);
				    response.getWriter().write("Ocorreu um erro inesperado");
				}
			    } catch (MyLoginException e) {
				response.setStatus(409);
				response.getWriter().write("O username escolhido já está em uso. Tente usar outro");
			    }
			} else { // usuario nao mudou nenhum campo
			    response.setStatus(200);
			    response.getWriter().write("Nenhuma alteração foi detectada.");
			}
		    }
		} else { // id não é o mesmo
		    response.setStatus(400);
		    response.getWriter().write(
			    "Ocorreu um erro. Por favor, atualize a página e se o problema persistir, faça login novamente.");
		}
	    } else if (action.equals("base64")) {
		Part filePart = request.getPart("file");
		System.out.println(filePart);
		String jsonStr = request.getParameter("file");
		System.out.println(jsonStr);
		String oi = new Gson().toJson(jsonStr);
		System.out.println(oi);

	    } else {// se mudar o parametro de action
		response.setStatus(400);
		response.getWriter().write("Ocorreu um erro. Por favor, atualize a página.");
	    }
	}
    }

    private byte[] streamToByte(InputStream imagem) throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int reads = imagem.read();
	while (reads != -1) {
	    baos.write(reads);
	    reads = imagem.read();
	}
	return baos.toByteArray();
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

    /**
     * Converte a imagem em base 64, converte em PNG e então inicia o processo de criação de miniatura
     * @param request
     * @return imagem em forma de miniatura
     * @throws IOException
     * @throws ServletException
     */
    private String createMiniatureBase64(HttpServletRequest request) throws IOException, ServletException {
	if (ServletFileUpload.isMultipartContent(request)) {// validando de form é de upload
	    Part imagem = request.getPart("file");
	    if (imagem != null && imagem.getInputStream().available() > 0) {
		String fotoBase64 = Base64.encodeBase64String(streamToByte(imagem.getInputStream()));

		/* Transforma emum bufferedImage */
		byte[] imageByteDecode = Base64.decodeBase64(fotoBase64);
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));

		/* Pega o tipo da imagem */
		int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

		/* Cria imagem em miniatura */
		BufferedImage resizedImage = new BufferedImage(100, 100, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(bufferedImage, 0, 0, 100, 100, null);
		g.dispose();

		/* Escrever imagem novamente */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(resizedImage, "png", baos);

		return "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
	    }
	}
	return "";
    }

    /**
     * retorna a base 64 do arquivo 
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     */
    private String createBase64 (HttpServletRequest request) throws IOException, ServletException {
	if (ServletFileUpload.isMultipartContent(request)) {
	    Part imagem = request.getPart("file");
	    if (imagem.getSize() > 0) {
		return "data:" + imagem.getContentType() + ";base64," +  Base64.encodeBase64String(streamToByte(imagem.getInputStream()));
	    }
	}
	return "";
    }
}
