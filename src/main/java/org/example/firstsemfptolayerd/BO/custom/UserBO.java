package org.example.firstsemfptolayerd.BO.custom;


import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.UserDTO;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<String> getAll() throws Exception;
    boolean add(UserDTO userDto) throws Exception;
    boolean update(String id,String name,String email,String password,String role) throws Exception;
    boolean delete(String id) throws Exception;
    UserDTO search(String id) throws Exception;
    String generateNewId() throws Exception;
    boolean checkUser(String username) throws Exception;
    UserDTO getUser(String username) throws Exception;
}