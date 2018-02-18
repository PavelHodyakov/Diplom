package com.messenger;
import com.operations.OperationsWithDateBase;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

import javax.servlet.FilterRegistration;
import javax.ws.rs.*;
import java.rmi.registry.LocateRegistry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
    public void sendMessage(String jsonMessage) throws JSONException, ParseException {
        JSONObject message = new JSONObject(jsonMessage);
        String sender = message.getString("owner");
        String content = message.getString("content");

        //Date departureDate = (Date) message.get("date");

        String dateStr = message.getString("date");
        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        java.sql.Timestamp departureDate =  new java.sql.Timestamp((df.parse(dateStr).getTime()));
//        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//        Date departureDate = parser.parse(dateStr);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date departureDate = formatter.parse(date);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date  departureDate= sdf.parse(dateStr);

        boolean deliveryConfirmationRequest = message.getBoolean("delivery");
        boolean confirmationReading = message.getBoolean("reading");
        //добавить считывание списка получателей//если получатель ALLL, тогда сообщение отправляется всем
        //List<String> recipients = (List<String>) message.getJSONArray("users");
        JSONArray recipients = message.getJSONArray("users");

        OperationsWithDateBase operationsWithDateBase = new OperationsWithDateBase();
        operationsWithDateBase.sendMessage(content,sender,departureDate,
                deliveryConfirmationRequest,confirmationReading,
                recipients);

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
