package com.messenger;
import com.operations.OperationsWithDateBase;
import org.json.JSONException;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

import javax.servlet.FilterRegistration;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;



@Path("/features")
public class MessengerFeatures {

    /*перед передачей данные кодируются на клиенте и декодируются на сервере
    * анализ на ошибки пользователя лучше делать на клиенте*/
    @GET
    @Path("/login")
    public boolean login(String system){

        return false;
    }

    @POST
    @Path("/registry")

    public String checkIn(String system) throws JSONException {
        JSONObject obj = new JSONObject(system);
        String fname = obj.getString("fname");
        String sname = obj.getString("sname");
        String login = obj.getString("login");
        String password = obj.getString("password");
        String department = obj.getString("department");
        String mail = null;
        String telegram = null;
        String address = null;
        OperationsWithDateBase DB = new OperationsWithDateBase();

        String res = DB.registry(fname,sname,login,password,department,mail,
                telegram,address);
        System.out.println("result "+ res);
        return res;
    }

    @POST
    @Path("/send")
    public void sendMessage(String message){

    }

//    id можно передавать и как параметр запроса
//    если не сработает, то передаем как json
    @POST
    @Path("/mbyid")
    public String receivingMessagesById(@QueryParam("id") String id){

        return "";
    }

    @POST
    @Path("/mbylogin")
    public String receivingMessagesByLogin(@QueryParam("login") String login){

        return "";
    }

    //организовать определение подтверждения доставки
}
