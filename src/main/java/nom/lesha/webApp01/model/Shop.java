package nom.lesha.webApp01.model;

import static javax.persistence.GenerationType.IDENTITY;
//import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexey Levchenko
 */
@Entity
@Table(name = "study.shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = IDENTITY)
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "tel", length = 45)
    private String tel;

    @Column(name = "working_time", length = 200)
    private String workingTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    private Set<Employee> employeeSet = new HashSet<>(0);


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
