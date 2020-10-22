package model;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioProyectos implements WithGlobalEntityManager {

  public static RepositorioProyectos instancia = new RepositorioProyectos();

  public List<Proyecto> listar() {
    return entityManager()//
        .createQuery("from Proyecto", Proyecto.class) //
        .getResultList();
  }

  public Proyecto buscar(long id) {
    return entityManager().find(Proyecto.class, id);
  }

  public void agregar(Proyecto proyecto) {
    entityManager().persist(proyecto);
  }

  public List<Proyecto> buscarPorNombre(String nombre) {
    return entityManager() //
        .createQuery("from Proyecto c where c.nombre like :nombre", Proyecto.class) //
        .setParameter("nombre", "%" + nombre + "%") //
        .getResultList();
  }

}
