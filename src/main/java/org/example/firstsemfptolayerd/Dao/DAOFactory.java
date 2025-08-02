package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.Dao.custom.impl.*;

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
        CUSTOMER,ITEM,ORDER,ORDERDETAILS,QUARY
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case CUSTOMER:
              return new CustomerDaoImpl();
                 case ITEM:
                    return new ItemDaoimpl();
                        case ORDER:
                            return new OrderDaoimpl();
                                 case ORDERDETAILS:
                                     return new OrderDetailDaoimpl();
                                     case QUARY:
                                         return new QuaryDaoImpl();
                                         default:
                                            return null;
        }
    }
}
