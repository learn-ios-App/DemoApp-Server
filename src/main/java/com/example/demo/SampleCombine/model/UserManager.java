package com.example.demo.SampleCombine.model;

import com.example.demo.SampleCombine.model.Request.MemoRequest;
import com.example.demo.SampleCombine.model.Request.UserRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class UserManager {
    private UserManager() {
        UserRequest sampleUserRequest1 = new UserRequest();
        sampleUserRequest1.setName("かい");
        sampleUserRequest1.setId("kai1110");
        sampleUserRequest1.setPw("1111");

        MemoRequest memoRequest1 = new MemoRequest();
        memoRequest1.setTitle("テスト勉強");
        memoRequest1.setDescription("27日の数学のテスト勉強");

        User user = createUser(sampleUserRequest1);
        user.createMemo(memoRequest1);
    }
    private static UserManager instance = null;
    private HashMap<String, User> userHashMap = new HashMap<>();
    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    public Collection<User> getAllUsers() {
        return userHashMap.values();
    }
    public User getUser(String id) {
        return userHashMap.get(id);
    }
    public User createUser(UserRequest request) {
        if (userHashMap.get(request.getId()) == null) {
            HashMap<String, Memo> memos = new HashMap<>();
            User user = new User(request, memos);
            userHashMap.put(user.getId(), user);
            return user;
        } else {
            return null;
        }
    }
    public void deleteUser(String id) {
        userHashMap.remove(id);
    }

    public void setUserHashMap(String id, User user) {
        userHashMap.put(id, user);
    }
}
