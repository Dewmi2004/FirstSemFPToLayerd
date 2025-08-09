package org.example.firstsemfptolayerd.Dao;


import org.example.firstsemfptolayerd.Dao.custom.impl.CustomerDaoImpl;
import org.example.firstsemfptolayerd.Dao.custom.impl.EmployeeDaoImpl;
import org.example.firstsemfptolayerd.Dao.custom.impl.SupplierDaoImpl;

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
        CUSTOMER,EMPLOYEE,SUPPLIER
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case CUSTOMER:
              return new CustomerDaoImpl();
              case EMPLOYEE:
              return new EmployeeDaoImpl();
              case SUPPLIER:
              return new SupplierDaoImpl();
              default:
                  return null;
        }
    }
}
