package org.seidelsoft;

import org.junit.jupiter.api.Test;
import org.seidelsoft.DAO.BaseDAO;
import org.seidelsoft.model.Usuario;
import org.seidelsoft.util.HibernateUtil;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldConnectToDatabase() {
        assertTrue(HibernateUtil.getEntityManager().isOpen());
    }

    @Test
    public void testDaoUser() {
        BaseDAO<Usuario> dao = new BaseDAO<>();

        Usuario u = new Usuario();
        u.setNome("Nome");

        u.setSenha("Senha");
        u.setLogin(u.getNome().toLowerCase().trim().replace(" ", ""));
        u.setEmail("nome@emial.com");

        Calendar data = Calendar.getInstance();
        data.set(Calendar.YEAR, 1994);
        data.set(Calendar.MONTH, 12);
        data.set(Calendar.DAY_OF_MONTH, 23);
        data.set(Calendar.HOUR_OF_DAY, 16);
        data.set(Calendar.MINUTE, 30);

        u.setDataNascimento(data);

        dao.save(u);
    }

    @Test
    public void testeBuscarEntity() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        Usuario u = new Usuario();
        u.setId(1L);
        u = dao.search(u);
        System.out.println(u);
    }
    @Test
    public void testeBuscarId() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        Usuario u = dao.search(1L, Usuario.class);
        System.out.println(u);
    }

    @Test
    public void testeUpdate() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        Usuario u = dao.search(1L, Usuario.class);
        System.out.println("Before update: \n " + u);

        u.setNome("Nome e Sobrenome");

        dao.update(u);
        System.out.println("After update: \n " + u);
    }
}
