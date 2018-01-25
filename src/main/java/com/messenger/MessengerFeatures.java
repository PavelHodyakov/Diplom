package com.messenger;
import javax.ws.rs.GET;

import javax.ws.rs.Path;

@Path("/features")
public class MessengerFeatures {

    /*перед передачей данные кодируются на клиенте и декодируются на сервере
    * анализ на ошибки пользователя лучше делать на клиенте*/
    @GET
    @Path("/login")
    public boolean login(String system){

        return false;
    }


}
