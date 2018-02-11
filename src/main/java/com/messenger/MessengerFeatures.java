package com.messenger;
import com.operations.OperationsWithDateBase;
import org.apache.http.entity.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

import javax.servlet.FilterRegistration;
import javax.ws.rs.*;
import java.util.Date;


@Path("/features")
public class MessengerFeatures {

    /*перед передачей данные кодируются на клиенте и декодируются на сервере
    * анализ на ошибки пользователя лучше делать на клиенте*/
    @GET
    @Path("/login")
    public String login(@QueryParam("name") String name, @QueryParam("password") String password) throws JSONException {
//
        OperationsWithDateBase operationsWithDateBase = new OperationsWithDateBase();
        return operationsWithDateBase.login(name, password);

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
    public void sendMessage(String jsonMessage) throws JSONException {
        JSONObject message = new JSONObject(jsonMessage);
        String sender = message.getString("sender");
        String content = message.getString("content");
        Date departureDate = (Date) message.get("date");
        boolean deliveryConfirmationRequest = message.getBoolean("delivery");
        boolean confirmationReading = message.getBoolean("reading");


    }

//    id можно передавать и как параметр запроса
//    если не сработает, то передаем как json
    @POST
    @Path("/messagebyid")
    public String receivingMessagesById(@QueryParam("id") String id){

        return "";
    }

    @POST
    @Path("/messagebylogin")
    public String receivingMessagesByLogin(@QueryParam("login") String login){

        return "";
    }

    //организовать определение подтверждения доставки
}
