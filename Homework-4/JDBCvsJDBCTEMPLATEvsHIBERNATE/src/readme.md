##  Spring JdbcTemplate'in JDBC API'sine Göre Avantajları

Spring JdbcTemplate, veritabanına bağlanmak ve SQL sorgularını yürütmek için güçlü bir mekanizmadır. Dahili olarak JDBC API'sini kullanır, ancak JDBC API'sinin birçok sorununu ortadan kaldırır.

## JDBC API kullanırken karşılaşılan sorunlar :

JDBC API kullanırken sık karşılaşılan sorunlar aşağıdaki gibidir:

1) Bağlantı oluşturma gibi sorguyu yürütmeden önce ve uyguladıktan sonra çok fazla kod yazmamız gerekir,
   ifade ve sonuç kümesi ve sonuç kümesini kapatma, ekstre ve bağlantı vb.

2) Veritabanı mantığında exception handling code gerçekleştirmemiz gerekir.

3) İşlemi halletmemiz gerekir.

4) Bu kodların tekrarlanması, yani her işlem için bağlantı, beyan ve sonuç kümesi kodları, gibi sorunlar yüzünden zaman alır.
##  Spring JdbcTemplate Avantajları :
Spring, org.springframework.jdbc.core paketinde bulunan Spring JdbcTemplate ile veritabanı erişimini işlemede basitleştirme sağlar.

1) Spring JdbcTemplate otomatik olarak kaynakları temizlemek için izin verir, extra kod yazmanıza gerek yoktur.

2) Spring JdbcTemplate, standart JDBC SqlExceptions'ı RuntimeExceptions'a dönüştürür.
   Bu, programcının hatalara daha esnek tepki vermesini sağlar. Ayrıca özel hata mesajlarını daha iyi anlaşılabilir hata mesajlarına dönüştürür.

3) Spring JdbcTemplate veritabanını sorgulamak için çeşitli yollar sunar, queryForList(), HashMaps'in bir listesini döndürür. Key, veritabanının sütun adıdır ve değer, gerçek sütun verileridir. .

4)SQL sonucunu doğrudan bir Nesneye veya Nesneler listesine çevirmeye izin veren ResultSetExtractor veya RowMapper'ın kullanılması daha uygun olur.

## JdbcTemplate CRUD Örneği


#### Veritabanı konfigürasyonu

application.properties dosyasını açın ve aşağıdaki içerikleri ekleyin.

```java
spring.datasource.url=jdbc:mysql://localhost:3306/springbootjdbc
spring.datasource.username=root
spring.datasource.password=scbushan05
```

#### Entity class ı oluşturma
```java
package in.bushansirgur.springbootjdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private Long id;
	
	private String name;
	
	private String location;
	
	private String department;
	
	public Employee(String name, String location, String department) {
		this.name = name;
		this.location = location;
		this.department = department;
	}
}
```

#### DAO oluşturma

```java
package in.bushansirgur.springbootjdbc.DAO;

import java.util.List;

import in.bushansirgur.springbootjdbc.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public int deleteById(int id);
	
	public int save(Employee e);
	
	public int update(Employee e, int id);
}
```
```java
package in.bushansirgur.springbootjdbc.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootjdbc.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> findAll() {
		return jdbcTemplate.query("SELECT * FROM tbl_employees", new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public Employee findById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM tbl_employees WHERE id=?", new BeanPropertyRowMapper<Employee>(Employee.class), id);
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM tbl_employees WHERE id=?", id);
	}

	@Override
	public int save(Employee e) {
		return jdbcTemplate.update("INSERT INTO tbl_employees (name, location, department) VALUES (?, ?, ?)", new Object[] {e.getName(), e.getLocation(), e.getDepartment()});
	}

	@Override
	public int update(Employee e, int id) {
		return jdbcTemplate.update("UPDATE tbl_employees SET name = ?, location = ?, department = ? WHERE id = ?", new Object[] {e.getName(), e.getLocation(), e.getDepartment(), id});
	}
		
}
```
## Jdbc  CRUD Örneği

#### Dao Oluşturma

```java
package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author sourcecodeexamples
 *
 */
public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List < User > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < User > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
```


## Java'da JDBC ve Hibernate Arasındaki Farklar

Java, mevcut senaryodaki en güçlü ve popüler sunucu tarafı dillerinden biridir. Sunucu taraflı bir dilin ana özelliklerinden biri, veritabanları ile iletişim kurma yeteneğidir. Bu yazıda, veritabanına bağlanmanın iki yolu (yani JDBC ve Hibernate) arasındaki farkı anlayalım.

Farklılıklara girmeden önce, her birinin gerçekte ne anlama geldiğini anlayalım.

JDBC: JDBC, Java Veritabanı Bağlantısı anlamına gelir. Java programlama dili ile çok çeşitli veritabanları arasında bir bağlantı sağlamak için bir Java uygulama programlama arayüzüdür (yani), bir programcının Java kodundan veri gönderebilmesi ve veritabanında depolayabilmesi için ikisi arasında bir bağlantı kurar. gelecekteki kullanım.

Hibernate: Hibernate, veritabanı yazılımından bağımsız nesneler geliştirmek ve tüm JAVA, JEE'de bağımsız kalıcılık mantığı yapmak için açık kaynaklı, istilacı olmayan, hafif bir Java ORM (Nesne-ilişkisel haritalama) çerçevesidir. Java uygulamalarının veritabanları ile etkileşimini basitleştirir. Hibernate, JPA'nın (Java Persistence API) bir uygulamasıdır.

## Hibernate  CRUD Örneği

#### Entity Oluşturma
```java
package com.java2novice.model;
 
import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="EMPLOYEES")
public class Employee implements Serializable {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="EMP_ID")
    private Long empId;
 
    private String name;
 
    private String department;
 
    private Long salary;
 
    @Column(name="JOINED_ON")
    private Date joinedOn;
 
    public Long getEmpId() {
        return empId;
    }
 
    public void setEmpId(Long empId) {
        this.empId = empId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDepartment() {
        return department;
    }
 
    public void setDepartment(String department) {
        this.department = department;
    }
 
    public Long getSalary() {
        return salary;
    }
 
    public void setSalary(Long salary) {
        this.salary = salary;
    }
 
    public Date getJoinedOn() {
        return joinedOn;
    }
 
    public void setJoinedOn(Date joinedOn) {
        this.joinedOn = joinedOn;
    }
 
    @Override
    public String toString() {
 
        return this.empId+" | "+this.name+" | "+this.department+" | "+this.salary+" | "+this.joinedOn;
    }
}
```
#### Dao Oluşturma

```java
package com.java2novice.hibernate;
 
import java.util.Date;
import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
import com.java2novice.model.Employee;
 
public class EmployeesDao {
 
    public List<Employee> getEmployeeList(){
 
        Session session = null;
        List<Employee> empList = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "select emp from Employee emp";
            Query query = session.createQuery(queryStr);
            empList = query.list();
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return empList;
    }
 
    public Employee getEmployeeById(Long empId){
 
        Session session = null;
        Employee emp = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "select emp from Employee emp";
            emp = session.get(Employee.class, empId);
 
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return emp;
    }
 
    public void insertEmployee(Employee emp) {
 
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.save(emp);
            System.out.println("inserted employee: "+emp.getName());
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if(transaction != null) transaction.rollback();
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
    }
 
    public void deleteEmployee(Employee emp) {
 
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.delete(emp);
            transaction.commit();
            System.out.println("deleted employee: "+emp.getName());
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if(transaction != null) transaction.rollback();
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
    }
 
    public static void main(String a[]) {
 
        EmployeesDao empDao = new EmployeesDao();
 
        Employee emp = new Employee();
        emp.setName("Babu");
        emp.setDepartment("Security");
        emp.setJoinedOn(new Date());
        emp.setSalary(new Long(5250));
        empDao.insertEmployee(emp);
 
        System.out.println("---------------------------");
 
        List<Employee> empList = empDao.getEmployeeList();
        System.out.println("emp size: "+empList.size());
        empList.stream().forEach(System.out::println);
 
        System.out.println("---------------------------");
 
        Employee empObj = empDao.getEmployeeById(emp.getEmpId());
        System.out.println(empObj);
 
        System.out.println("---------------------------");
        empDao.deleteEmployee(empObj);
 
        System.out.println("---------------------------");
 
        empList = empDao.getEmployeeList();
        System.out.println("emp size: "+empList.size());
        empList.stream().forEach(System.out::println);
 
        System.out.println("---------------------------");
    }
}

```
