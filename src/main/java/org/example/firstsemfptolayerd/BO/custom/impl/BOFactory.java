package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.SuperBO;

public class BOFactory { private static BOFactory instance;
    public static BOFactory getInstance(){
        if(instance==null){
            instance=new BOFactory();

        }
        return instance;
    }
    private BOFactory(){

    }
    public enum BOtypes{
        CUSTOMER,EMPLOYEE,SUPPLIER,TICKET
    }
    public SuperBO getBO(BOFactory.BOtypes bo){
        switch(bo){
            case CUSTOMER:
                return new CustomerBOImpl();
                case EMPLOYEE:
                return new EmployeeBOImpl();
                case SUPPLIER:
                return new SupplierBOImpl();
                case TICKET:
                return new TicketBOImpl();
            default:
                return null;
        }
    }
}
