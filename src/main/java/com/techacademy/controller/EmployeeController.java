package com.techacademy.controller;

import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.Set;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist",service.getEmployeeList());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }
    /** Employee登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {

        //Employee登録画面に遷移
        return "employee/register";
    }
    /** Employee登録処理 */
    @PostMapping("/register")
    public String postRegister(Employee employee, BindingResult res) {
        if(res.hasErrors()) {
            //エラーあり
            return "employee/register";
        }
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    /** Employee更新画面を表示 */
    @GetMapping("/update/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        Employee emp=service.getEmployee(id);
        // Modelに登録
        model.addAttribute("employee", emp);
        // Employee更新画面に遷移
        return "employee/update";
    }
    /** Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postEmployee(@Validated Employee employee, BindingResult res, Model model) {
        if(res.hasErrors()) {
            //エラーあり
            return "employee/update";
        }
        employee.setUpdated_at(new Date(new java.util.Date().getTime()));
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    /** Employee削除処理 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Employeeを一括削除
        service.deleteEmployee(idck);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    /** Employee詳細を表示 */
    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable int id, Model model) {

        // Modelに登録
        model.addAttribute("emp",service.getEmployee(id));
        // Employee更新画面に遷移
        return "employee/detail";
    }
}