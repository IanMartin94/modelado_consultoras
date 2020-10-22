package model;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioConsultoras implements WithGlobalEntityManager {

  public static RepositorioConsultoras instancia = new RepositorioConsultoras();

  public List<Consultora> listar() {
    return entityManager()//
        .createQuery("from Consultora", Consultora.class)
        .setMaxResults(3)
        .getResultList();
  }

  public List<Consultora> listarPrimerasTres() {
    return entityManager()//
            .createQuery("from Consultora c order by c.cantidadEmpleados desc", Consultora.class)
            .getResultList();
  }

  public Consultora buscar(long id) {
    return entityManager().find(Consultora.class, id);
  }

  public void agregar(Consultora consultora) {
    entityManager().persist(consultora);
  }

  public Consultora buscarPorNombre(String nombre) {
    Consultora consultoras = entityManager() //
        .createQuery("from Consultora c where c.nombre like :nombre", Consultora.class) //
        .setParameter("nombre", "%" + nombre + "%")
        .getSingleResult();

    consultoras.setProyectos(consultoras.getProyectos().subList(0,3));
    return consultoras;
  }

}
