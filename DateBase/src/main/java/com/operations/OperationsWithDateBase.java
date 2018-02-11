package com.operations;

import com.tables.MessageBaseEntity;
import com.tables.MessageEntity;
import com.tables.SystemEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationsWithDateBase {
   private EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();

    public String login(String login, String password) {
        try {
            em.getTransaction().begin();
            List<Integer> idSystem = em.createQuery("Select systemId from SystemEntity where" +
                    "(login= :login AND password= :password)").setParameter("login", login)
                    .setParameter("password", password).getResultList();
            //System.out.println(idSystem.get(0));
            if (idSystem.size() == 1) {
                return "true";
            } else {
                return "false";
            }
        } catch (Exception e){
            em.close();
            return "false";
        }finally{
            em.close();
         }
    }

    public String registry(String fname, String sname, String login,
                            String password, String department,
                            String mail,String telegram, String address){
        try {
            em.getTransaction().begin();
            int idDepartment = (int) em.createQuery("Select departmentId from DepartmentEntity where" +
                    "(departmentName = :department)").setParameter("department", department)
                    .getResultList().get(0);
            //проверку на существование отдела делать не надо, т.к.
            //предполагается выпадающий список отделов
            em.persist(new SystemEntity(login, password, fname,
                    sname, idDepartment, address, mail, telegram));
            //em.flush();
            em.getTransaction().commit();
          }catch(Exception e){
            em.close();
            return e.getCause().getCause().getCause().getLocalizedMessage();
        }finally {
            em.close();
        }
        return "true";
    }

    public void sendMessage(String content, String sender, Date departureDate, boolean delivery
    , boolean read, ArrayList<String> owners){
        try {
            em.getTransaction().begin();
//            int idSystem = (int) em.createQuery("Select systemId from SystemEntity where" +
//                    "(login= :login)").setParameter("login", sender).getResultList().get(0);
            int idSender = getSystemId(sender);
            MessageEntity ME = new MessageEntity(content, (java.sql.Date) departureDate, delivery, read, idSender);
            em.persist(ME);
            em.flush();
            int idMessage = (int) ME.getMessageId();

            for(int i=0;i<owners.size();i++){
//                idSystem = (int) em.createQuery("Select systemId from SystemEntity where" +
//                        "(login= :login)").setParameter("login", sender).getResultList().get(0);
                int idOwner = getSystemId(owners.get(i));
                boolean delivReq;
                if(delivery == true){
                    delivReq = true;
                } else{
                    delivReq = Boolean.parseBoolean(null);
                }
                boolean readReq;
                if(read == true){
                    readReq = true;
                } else{
                    readReq = Boolean.parseBoolean(null);
                }
                em.persist(new MessageBaseEntity(idOwner, idMessage , delivReq, readReq));
            }
        em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            em.close();
        }
    }
    private int getSystemId(String login){

        em.getTransaction().begin();
        int id = (int) em.createQuery("Select systemId from SystemEntity where" +
                    "(login= :login)").setParameter("login", login).getResultList().get(0);
        em.close();
        return id;

    }
}
