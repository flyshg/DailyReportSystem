package com.techacademy.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** Employeeを1件検索して返す */
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }

    /** Employeeの登録を行う */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        employee.setCreated_at(new Date(new java.util.Date().getTime()));
        employee.setDelete_flag(0);
        employee.setUpdated_at(new Date(new java.util.Date().getTime()));
        Authentication authentication = employee.getAuthentication();
        authentication.setEmployee(employee);

//        System.out.println(authentication);
//        System.out.println(employee);

        return employeeRepository.save(employee);
    }
    /** Employeeの削除を行う */
    @Transactional
    public void deleteEmployee(Set<Integer> idck) {
        for(Integer id : idck) {
            employeeRepository.deleteById(id);
        }
    }
}