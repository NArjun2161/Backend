package com.istato.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExecutiveExpense {
    @JsonProperty("iExeName")
    private String name;

    @JsonProperty("dExeSalary")
    private double salary;

    @JsonProperty("dExeIncentives")
    private double incentives;

    @JsonProperty("dExeBonuses")
    private double bonuses;

    @JsonProperty("iExeDate")
    private Date date;

}