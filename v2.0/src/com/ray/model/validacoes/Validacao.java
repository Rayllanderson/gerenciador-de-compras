package com.ray.model.validacoes;

import java.util.List;

import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.entities.Categoria;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.EntradaInvalidaException;

/**
 * Classe para validar nome e valor
 *
 */
public class Validacao {

    /**
     * Valida os campos Nome e preço estipulado
     * 
     * @param Nome
     * @throws EntradaInvalidaException lançada quando o campo Nome estiver em branco ou nulo
     */
    public static void validarNome(String nome) throws EntradaInvalidaException {
	if (nome.trim().isEmpty() || nome == null) {
	    throw new EntradaInvalidaException("O campo 'Nome' não pode ser nulo");
	}
    }
    
    /**
     * @param value
     * @return 0 caso o valor esteja nulo, senao, retorna o próprio valor
     */
    public static Double validarPreco(Double value) {
	return value == null ? 0.0 : value;
    } 
    
    /**
     * método para verifica se a categoria atual percente ao usuário <br>
     * Há uma possibilidade de editar o html e mudar a categoria, portanto, antes de
     * atualizar,
     * procura no banco de dados para verificar se o usuário é mesmo o dono da
     * categoria, se nao for, retorna nulo.
     * @throws CategoriaInexistenteException caso a categoria escolhida não exista para o
     *                           usuário atual
     */
    public static void validarCategoria(Categoria cat) throws CategoriaInexistenteException {
	CategoriaDao catRespotitory = DaoFactory.createCategoriaDao(cat.getUser());
	List<Categoria> list = catRespotitory.findAll();
	if (!list.contains(cat)) {
	    throw new CategoriaInexistenteException("Essa categoria não existe.");
	}
    }
   
    
}
