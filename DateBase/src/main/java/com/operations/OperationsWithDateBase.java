package com.operations;

import com.tables.SystemEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class OperationsWithDateBase {
   private EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();
    public boolean login(String login, String password){
        //EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();
        List<Integer> idSystem = em.createNamedQuery("Select systemId from SystemEntity " +
                "where(name= :name AND password= :password)").setParameter("name",login)
                .setParameter("password",password).getResultList();
        return false;
    }

    public String registry(String fname, String sname, String login,
                            String password, String department,
                            String mail,String telegram, String address){
        try {
            em.getTransaction().begin();
            int idDepartment = (int) em.createQuery("Select departmentId from DepartmentEntity where" +
                    "(departmentName = :department)").setParameter("department", department)
                    .getResultList().get(0);
            System.out.println(idDepartment);
            System.out.println(login.intern());
            System.out.println(password);
            System.out.println(fname);
            System.out.println(sname);
            //System.out.println(idDepartment);

            //проверку на существование отдела делать не надо, т.к.
            //предполагается выпадающий список отделов
            em.persist(new SystemEntity(login, password, sname,
                    fname, idDepartment, address, mail, telegram));
            //em.flush();
            em.getTransaction().commit();
          }catch(Exception e){
        //}catch (Exception e){
            System.out.println(String.valueOf(e));
            System.out.println(e.getCause().getCause().getCause().getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
            return e.getCause().getCause().getCause().getLocalizedMessage();

        }finally {
            em.close();
        }
        return "true";
    }
}
