package application;

import model.enttities.Seller;
import medel.dao.Daofactory;
import medel.dao.SellerDao;


/*
 *
 * @author Evandro
 */
public class Program {

    public static void main(String[] args) {
         SellerDao sellerDao = Daofactory.createSellerDao();
         System.out.println("===  Teste 1: Seller FindById  === ");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);
    }
}
