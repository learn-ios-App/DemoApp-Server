package com.example.demo.SampleCombine.resource;

import com.example.demo.SampleCombine.model.Memo;
import com.example.demo.SampleCombine.model.Request.MemoRequest;
import com.example.demo.SampleCombine.model.User;
import com.example.demo.SampleCombine.model.UserManager;
import com.example.demo.SampleCombine.model.Request.UserRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Component
@Path("/user")
public class UserRest {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(@RequestBody UserRequest userRequest) {
        UserManager um = UserManager.getInstance();
        User user = um.createUser(userRequest);
        if (user == null) {
            var response = Response.status(Response.Status.NO_CONTENT);
            response.status(409).entity("このIDはすでに使用されています");
            throw new WebApplicationException(response.build());
        } else {
            return user;
        }
    }
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@RequestBody UserRequest userRequest) {
        UserManager um = UserManager.getInstance();
        User user = um.getUser(userRequest.getId());
        if (user == null) {
            var response = Response.status(Response.Status.NO_CONTENT);
            response.status(404).entity("アカウントが存在しません");
            throw new WebApplicationException(response.build());
        } else if(!user.getPw().equals(userRequest.getPw())) {
            var response = Response.status(Response.Status.NO_CONTENT);
            response.status(401).entity("パスワードが間違っています");
            throw new WebApplicationException(response.build());
        } else {
            return user;
        }
    }

    @GET
    @Path("/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("uid") String uid) {
        return UserManager.getInstance().getUser(uid);
    }

    @PUT
    @Path("/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User changeName(@PathParam("uid") String uid, @RequestParam UserRequest userRequest) {
        UserManager um = UserManager.getInstance();
        User user = um.getUser(uid);
        user.setName(userRequest.getName());
        um.setUserHashMap(uid, user);
        return user;
    }

    @DELETE
    @Path("/{uid}")
    public void deleteUser(@PathParam("uid") String uid) {
        UserManager.getInstance().deleteUser(uid);
    }

    @GET
    @Path("/{uid}/memo")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Memo> getMemos(@PathParam("uid") String uid) {
        return UserManager.getInstance().getUser(uid).getMemos();
    }

    @POST
    @Path("/{uid}/memo")
    @Produces(MediaType.APPLICATION_JSON)
    public Memo postMemo(@PathParam("uid") String uid, @RequestParam MemoRequest memoRequest) {
        User user = UserManager.getInstance().getUser(uid);
        return user.createMemo(memoRequest);
    }

    @PUT
    @Path("/{uid}/memo/{mid}")
    public Memo update(@PathParam("uid") String uid,
                       @PathParam("mid") String mid,
                       @RequestParam MemoRequest memoRequest) {
        UserManager um = UserManager.getInstance();
        User user = um.getUser(uid);
        Memo memo = user.update(mid, memoRequest);
        return memo;
    }

    @DELETE
    @Path("/{uid}/memo/{mid}")
    public void getMemo(@PathParam("uid") String uid, @PathParam("mid") String mid) {
        UserManager.getInstance().getUser(uid).deleteMemo(mid);
    }
}
