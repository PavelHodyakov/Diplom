package com.operations;

import com.tables.BaseMessageEntity;
import com.tables.MessageEntity;
import com.tables.SystemEntity;
import org.json.JSONArray;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.sql.Timestamp;
import java.util.List;


public class OperationsWithDateBase {
   private EntityManager em= Persistence.createEntityManagerFactory("Entities").createEntityManager();

   //@PersistenceContext(unitName = "Entities", type = PersistenceContextType.EXTENDED)
    //private EntityManager em;
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
            System.out.println("Exception: "+ e);
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

    public void sendMessage(String content, String sender, Timestamp departureDate, boolean delivery
    , boolean read, JSONArray owners){
        try {
            em.getTransaction().begin();
//            int idSystem = (int) em.createQuery("Select systemId from SystemEntity where" +
//                    "(login= :login)").setParameter("login", sender).getResultList().get(0);
            //int idSender = getSystemId(sender);
            //Изменился конструктор
            MessageEntity ME = new MessageEntity(content, departureDate, delivery, read, sender, null);
            em.persist(ME);
            em.flush();
            int idMessage = (int) ME.getMessageId();

            for(int i=0;i<owners.length();i++){
//                idSystem = (int) em.createQuery("Select systemId from SystemEntity where" +
//                        "(login= :login)").setParameter("login", sender).getResultList().get(0);
                String owner = owners.getString(i);
                //Error: записывает в БД не null а false
                boolean delivReq;
                if(delivery == true){
                    delivReq = false;//т.к. условия не выполнены
                } else{
                    //delivReq = new Boolean(null);//Boolean.parseBoolean(null);//т.к. не надо выполнять условия
                }
                boolean readReq;
                if(read == true){
                    readReq = false;// т.к. условия не выполнены
                } else{
                    //readReq = Boolean.parseBoolean(null);//т.к. не надо выполнять условия
                }
                //Изменился конструктор
                em.persist(new BaseMessageEntity(idMessage, owner , null, null));
            }
        em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            em.close();
        }
    }
    private int getSystemId(String login){

        //em.getTransaction().begin();
        int id = (int) em.createQuery("Select systemId from SystemEntity where" +
                    "(login= :login)").setParameter("login", login).getResultList().get(0);
        //em.close();
        return id;

    }
}
