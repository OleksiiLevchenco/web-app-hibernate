package nom.lesha.webApp01.dao.hibernate;


import nom.lesha.webApp01.model.Employee;
import nom.lesha.webApp01.model.Shop;
import org.hibernate.Session;

import java.util.Set;


/**
 * @author Alexey Levchenko
 */
public class EmployeeHibernateDao {


    public static boolean addItem(int shopId, Employee employee) {
        Session session = null;
        boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Shop shop = (Shop) session.load(Shop.class, shopId);
            employee.setShop(shop);
            shop.getEmployeeSet().add(employee);
            session.save(employee);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Add employee to shop, dao Exception -" + e);
        }
        return result;
    }

    public static Employee getEmployeeById(int id) {
        Session session = null;
        Employee employee = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            employee = (Employee) session.load(Employee.class, id);
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Get employee by id, dao Exception -" + e);
        }
        return employee;
    }

    public static Set<Employee> getEmployeesListByShopId(int shopId) {
        Session session = null;
        Set<Employee> employees = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Shop shop = (Shop) session.load(Shop.class, shopId);
            employees = shop.getEmployeeSet();
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Get Employees List, dao Exception -" + e);
        }

        return employees;
    }


    public static boolean update(int employeeId, Employee employee) {
        Session session = null;
        boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Employee employeeToEdit = (Employee) session.load(Employee.class, employeeId);
            employeeToEdit.setName(employee.getName());
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Update Employee, dao Exception -" + e);
        }
        return result;
    }

    public static boolean delete(int id){
        Session session = null;
        boolean result = false;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Employee employee = (Employee)session.load(Employee.class, id);
            session.delete(employee);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            System.err.println("Delete Employee, dao Exception -" + e);
        }
        return result;
    }


}

