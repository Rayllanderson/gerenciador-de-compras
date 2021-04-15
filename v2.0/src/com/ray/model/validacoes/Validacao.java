package com.ray.model.validacoes;

import java.util.List;

import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.entities.Categoria;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.EntradaInvalidaException;

/**
 * Classe para validar nome e valor
 */
public class Validacao {

    /**
     * Valida os campos Nome e pre�o estipulado
     *
     * @param Nome
     *
     * @throws EntradaInvalidaException lan�ada quando o campo Nome estiver em branco ou nulo
     */
    public static void validarNome(String nome) throws EntradaInvalidaException {
        if (nome.trim().isEmpty() || nome == null) {
            throw new EntradaInvalidaException("O campo 'Nome' n�o pode ser nulo");
        }
    }

    /**
     * @param value
     *
     * @return 0 caso o valor esteja nulo, senao, retorna o pr�prio valor
     */
    public static Double validarPreco(Double value) {
        return value == null ? 0.0 : value;
    }

    /**
     * m�todo para verifica se a categoria atual percente ao usu�rio <br>
     * H� uma possibilidade de editar o html e mudar a categoria, portanto, antes de
     * atualizar,
     * procura no banco de dados para verificar se o usu�rio � mesmo o dono da
     * categoria, se nao for, retorna nulo.
     *
     * @throws CategoriaInexistenteException caso a categoria escolhida n�o exista para o
     * usu�rio atual
     */
    public static void validarCategoria(Categoria cat) throws CategoriaInexistenteException {
        CategoriaDao catRespotitory = DaoFactory.createCategoriaDao(cat.getUser());
        List<Categoria> list = catRespotitory.findAll();
        if (!list.contains(cat)) {
            throw new CategoriaInexistenteException("Essa categoria n�o existe.");
        }
    }


}
