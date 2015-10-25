package nom.lesha.webApp01.dao.hibernate;


import nom.lesha.webApp01.model.Shop;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Alexey Levchenko
 */
public class ShopHibernateDao  {


    public static boolean addItem(Shop shop) {
        Session session = null;
        boolean result = false;
        try {
            session =  HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(shop);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Add shop dao Exception -" + e);
        }
        return result;
    }

    public static boolean update(int id, Shop shop) {
        Session session = null;
        boolean result = false;
        try {
            session =  HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Shop targetShop = (Shop)session.load(Shop.class, id);
            targetShop.setName(shop.getName());
            targetShop.setAddress(shop.getAddress());
            targetShop.setTel(shop.getTel());
            targetShop.setWorkingTime(shop.getWorkingTime());
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Update shop dao Exception -" + e);
        }
        return result;
    }

    public static boolean delete(int id){

        Session session = null;
        boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Shop shop = (Shop) session.load(Shop.class, id);
            session.delete(shop);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Delete shop dao Exception -" + e);
        }
        return result;
    }

    public static Shop getShopById(int id){
        Session session = null;
        Shop shop = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            shop = (Shop) session.load(Shop.class, id);
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Get shop by id dao Exception -" + e);
        }
        return shop;
    }

    public static List<Shop> getShopsList(){
        Session session = null;
        List<Shop> shopsList = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            shopsList = (List<Shop>) session.createCriteria(Shop.class).list();
            session.getTransaction().commit();
        }  catch (Exception e) {
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
        System.err.println("Get shops list dao Exception -" + e);
        }
        return shopsList;
    }


}

