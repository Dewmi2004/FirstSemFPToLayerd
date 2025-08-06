package org.example.firstsemfptolayerd.Dao;


import org.example.firstsemfptolayerd.Dao.custom.impl.CustomerDaoImpl;
import org.example.firstsemfptolayerd.Dao.custom.impl.EmployeeDaoImpl;

public class DAOFactory {
    private static DAOFactory instance;
    public static DAOFactory getInstance(){
        if(instance==null){
            instance=new DAOFactory();

        }
        return instance;
    }
    private DAOFactory(){

    }
    public enum DAOtypes{
        CUSTOMER,EMPLOYEE
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case CUSTOMER:
              return new CustomerDaoImpl();
              case EMPLOYEE:
              return new EmployeeDaoImpl();
              default:
                  return null;
        }
    }
}
