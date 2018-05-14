package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //以下所有的*都代表变量

    //1.查询名字是*的第一个employee
    Employee findFirstByName(String name);

    //2.找出Employee表中第一个姓名包含`*`字符并且薪资大于*的雇员个人信息
    Employee findFirstByNameContainingAndSalaryGreaterThan(String nameContains,Integer salary);
//    @Query("select e FROM employee_db e WHERE e.name LIKE ?1 AND e.salary >= ?2")
//    Employee findFirstEmployeeContentNameAndMoreSalary(String name,Integer salary);

    //3.找出一个薪资最高且公司ID是*的雇员以及该雇员的姓名
    @Query(value = "SELECT * FROM Employee t WHERE t.companyId =:companyId ORDER BY salary DESC limit 1", nativeQuery = true)
    Employee findMostSalaryById(@Param("companyId") Integer companyId);

    //4.实现对Employee的分页查询，每页两个数据
    Page<Employee> findAll(Pageable pageable);

    //5.查找**的所在的公司的公司名称
    @Query("select c.companyName From Company c right join Employee e ON c.id = e.companyId where e.name = ?1")
    String findCompanyNameByEmployeeName(String name);

    //6.将*的名字改成*,输出这次修改影响的行数
    @Modifying
    @Query("update Employee e set e.name= ?1 where e.name=?2")
    int modifyByName(String name1, String name2);

    //7.删除姓名是*的employee
    void deleteEmployeeByName(String name);
}
