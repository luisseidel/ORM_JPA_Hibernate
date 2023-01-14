package org.seidelsoft;

import org.junit.jupiter.api.Test;
import org.seidelsoft.DAO.BaseDAO;
import org.seidelsoft.model.Telefone;
import org.seidelsoft.model.Usuario;
import org.seidelsoft.model.enums.TipoTelefone;
import org.seidelsoft.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.List;

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
    public void testeCriar() {
        BaseDAO<Usuario> dao = new BaseDAO<>();

        Usuario u = new Usuario();
        u.setNome("Nome 5");

        u.setSenha("Senha 5");
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
        u.setId(2L);
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

    @Test
    public void testeDelete() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        Usuario u = dao.search(2L, Usuario.class);
        System.out.println("Before update: \n " + u);

        dao.delete(u);
        System.out.println("Usuario deletado!");
    }

    @Test
    public void testListar() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        List<Usuario> list = dao.list(Usuario.class, 100);
        print(list);
    }

    @Test
    public void testeParameter() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        List<Usuario> list = dao.getEntityManager()
                .createQuery("from Usuario where nome like :nome")
                .setParameter("nome", "%Nome%")
                .getResultList();

        print(list);
    }

    @Test
    public void testeNamedQuery() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        List<Usuario> list = dao.getEntityManager().createNamedQuery("Usuario.findAll").getResultList();
        print(list);
    }

    @Test
    public void testeNamedQuery2() {
        BaseDAO<Usuario> dao = new BaseDAO<>();
        List<Usuario> list =
                dao.getEntityManager()
                        .createNamedQuery("Usuario.findByName")
                        .setParameter("nome", "%Nome%")
                        .getResultList();
        print(list);
    }

    @Test
    public void testTelefones() {
        BaseDAO dao = new BaseDAO<>();

        Usuario u = (Usuario) dao.search(3L, Usuario.class);
        Telefone t = new Telefone();
        t.setTipoTelefone(TipoTelefone.FIXO);
        t.setNumero("555133333333");
        t.setUsuario(u);

        dao.save(t);
    }

    @Test
    public void testSearchTelefones() {
        BaseDAO dao = new BaseDAO<>();

        Usuario u = (Usuario) dao.search(3L, Usuario.class);

        print(u.getTelefoneList());
    }

    public void print(List list) {
        for (Object u : list) {
            System.out.println(u);
        }
    }
}
