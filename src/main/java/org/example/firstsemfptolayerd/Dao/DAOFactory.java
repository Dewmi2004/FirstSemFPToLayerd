package org.example.firstsemfptolayerd.Dao;


import org.example.firstsemfptolayerd.Dao.custom.impl.*;

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
        CUSTOMER,EMPLOYEE,SUPPLIER,TICKET,PH_CHEMICAL,FISH,TANK,CHEMICAL,PLANT,FOOD,ORDER,PAYMENT,CART
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case CUSTOMER:
              return new CustomerDaoImpl();
              case EMPLOYEE:
              return new EmployeeDaoImpl();
              case SUPPLIER:
              return new SupplierDaoImpl();
              case TICKET:
              return new TicketDaoImpl();
              case PH_CHEMICAL:
              return new PHChemicalDaoImpl();
              case FISH:
              return new FishDaoImpl();
              case TANK:
              return new TankDaoImpl();
              case CHEMICAL:
              return new ChemicalDaoImpl();
            case PLANT:
              return new PlantDaoImpl();
              case FOOD:
              return new FoodDaoImpl();
            case ORDER:
              return new OrderDaoImpl();
            case PAYMENT:
              return new PaymentDaoImpl();
              case CART:
              return new CartDaoImpl();
              default:
                  return null;
        }
    }
}
