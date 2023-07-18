package com.example.demo.SampleCombine.resource;

import com.example.demo.SampleCombine.model.UserManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class Hello {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        UserManager um = UserManager.getInstance();
        return "Hello World";
    }
}
