package nom.lesha.webApp01.services;



import nom.lesha.webApp01.dao.hibernate.EmployeeHibernateDao;
import nom.lesha.webApp01.model.Employee;
import nom.lesha.webApp01.dao.jdbc.EmployeeDao;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Levchenko
 */
public class EmployeeService {

    public static Set<Employee> getEmployeesListByShopId(int shopId) {
        return EmployeeHibernateDao.getEmployeesListByShopId(shopId);
    }

    public static Employee getEmployeeById(int id) {
        return EmployeeHibernateDao.getEmployeeById(id);
    }

    public static HttpServletRequest validateAndParsEmployeesParams(HttpServletRequest req) {
//        int id = -1;
        String name = "";
//        int employeesShopId = -1;
//
//        String idStr = req.getParameter("employeeId");
//        if (idStr == null) {
//            idStr = (String) req.getAttribute("employeeId");
//        }
//        if (idStr == null) {
//            idStr = "-1";
//        }
//        id = Integer.parseInt(idStr);
//
//        String shopIdStr = req.getParameter("shopId");
//        if (shopIdStr == null) {
//            shopIdStr = (String) req.getAttribute("shopId");
//        }
//        if (shopIdStr == null) {
//            shopIdStr = "-1";
//        }
//        employeesShopId = Integer.parseInt(shopIdStr);

        Map<String, String> employeeValidationReport = new LinkedHashMap<>();


        Employee employee = new Employee();

        if (stringLengthValidate(req.getParameter("employeeName"), 45)) {
            name = req.getParameter("employeeName");
        } else {
            employeeValidationReport.put("name", "This field should not be empty, and can not contain more than 45 characters");
        }

        employee.setName(name);
//        employee.setShop(employeesShopId);
//        employee.setId(id);


        if (employeeValidationReport.size() != 0) {
            req.setAttribute("employeeValidationReport", employeeValidationReport);
        }

        req.setAttribute("employee", employee);

        return req;
    }

    private static boolean stringLengthValidate(String string, int length) {
        return !(string == null || string.equals("")) && (string.length() <= length);
    }

    public static boolean addItem(int shopId, Employee employee) {
        return EmployeeHibernateDao.addItem(shopId, employee);
    }

    public static boolean update(int employeeId, Employee employee) {
        return EmployeeHibernateDao.update(employeeId, employee);
    }

    public static boolean delete(int id) {
        return EmployeeHibernateDao.delete(id);
    }
}
