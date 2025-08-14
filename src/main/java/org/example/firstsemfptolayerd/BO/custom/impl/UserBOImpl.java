package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.UserBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.UserDAO;
import org.example.firstsemfptolayerd.entity.User;
import org.example.firstsemfptolayerd.model.UserDTO;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.USER);

    @Override
    public ArrayList<String> getAll() throws Exception {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> dtos = new ArrayList<>();
        for (User e : users) {
            dtos.add(new UserDTO(e.getId(), e.getName(), e.getEmail(), e.getRole(), e.getPassword()));
        }
        return null;
    }

    @Override
    public boolean add(UserDTO dto) throws Exception {
        return userDAO.Save(new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getRole(), dto.getPassword()));
    }

    @Override
    public boolean update(String id, String name, String email, String password, String role) throws Exception {
        return userDAO.update(new User(id, name, email, role, password));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return userDAO.Delete(id);
    }

    @Override
    public UserDTO search(String id) throws Exception {
        User user = userDAO.search(id);
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getPassword());
    }

    @Override
    public String generateNewId() throws Exception {
        return userDAO.getNextId();
    }

    @Override
    public boolean checkUser(String username) throws Exception {
        return userDAO.checkUser(username);
    }

    @Override
    public UserDTO getUser(String username) throws Exception {
        User user = userDAO.getUser(username);
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getPassword());
    }
}