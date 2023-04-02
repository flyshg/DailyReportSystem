package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "reports")
public class Reports {
    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 日報を登録した従業員の従業員テーブルでのID */
    private String employee_id;

    /** 日報日付 */
    private Date report_date;

    /** 日報タイトル */
    private String title;

    /** 日報内容 */
    private String content;

    /** 登録日付 */
    private Date created_at;

    /** 更新日付 */
    private Date updated_at;
}