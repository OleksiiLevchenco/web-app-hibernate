package nom.lesha.webApp01.services;


import nom.lesha.webApp01.dao.hibernate.ShopHibernateDao;
import nom.lesha.webApp01.model.Employee;
import nom.lesha.webApp01.model.Shop;
import nom.lesha.webApp01.dao.jdbc.ShopDao;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Levchenko
 */
public class ShopService {

    public static Shop getShopById(int id) {
        return ShopHibernateDao.getShopById(id);

    }

    public static List<Shop> getShopsList() {
        return ShopHibernateDao.getShopsList();
    }

    public static HttpServletRequest validateAndParsShopsParams(HttpServletRequest req) {
        String name = "";
        String address = "";
        String tel = "";
        String workingTime = "";

        String idStr = req.getParameter("shopId");
        if (idStr == null) {
            idStr = (String) req.getAttribute("shopId");
        }
        if (idStr == null) {
            idStr = "-1";
        }
        int id = Integer.parseInt(idStr);

        Map<String, String> shopValidationReport = new LinkedHashMap<>();


        Shop shop = new Shop();

        if (stringLengthValidate(req.getParameter("shopName"), 45)) {
            name = req.getParameter("shopName");
        } else {
            shopValidationReport.put("name", "This field should not be empty, and can not contain more than 45 characters");
        }

        if (stringLengthValidate(req.getParameter("shopAddress"), 200)) {
            address = req.getParameter("shopAddress");
        } else {
            shopValidationReport.put("address", "This field should not be empty, and can not contain more than 200 characters");
        }

        if (stringLengthValidate(req.getParameter("shopTel"), 45)) {
            tel = req.getParameter("shopTel");
        } else {
            shopValidationReport.put("tel", "This field should not be empty, and can not contain more than 45 characters");
        }

        if (stringLengthValidate(req.getParameter("shopWorkingTime"), 45)) {
            workingTime = req.getParameter("shopWorkingTime");
        } else {
            shopValidationReport.put("workingTime", "This field should not be empty, and can not contain more than 200 characters");
        }


//        if(intValidate(req.getParameter("transformer.id"))){
//            id = Integer.parseInt(req.getParameter("transformer.id"));
//        } else {
//            transformerValidationReport.put("Id", "This field incremented automatically");
//        }

        shop.setName(name);
        shop.setAddress(address);
        shop.setTel(tel);
        shop.setWorkingTime(workingTime);
        shop.setId(id);

        if (shopValidationReport.size() != 0) {
            req.setAttribute("shopValidationReport", shopValidationReport);
        }

        req.setAttribute("shop", shop);

        return req;
    }

    private static boolean stringLengthValidate(String string, int length) {
        return !(string == null || string.equals("")) && (string.length() <= length);
    }

    public static boolean addItem(Shop shop) {
        return ShopHibernateDao.addItem(shop);
    }

    public static boolean update(int id, Shop shop) {

        return ShopHibernateDao.update(id, shop);
    }

    public static boolean delete(int id) {
        return ShopHibernateDao.delete(id);
    }

    public static int getEmployeesCount(int shopId) {
       return EmployeeService.getEmployeesListByShopId(shopId).size();
    }
}

