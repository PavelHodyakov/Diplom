package com.messenger;
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
    public boolean checkIn(String system){

        return false;
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
