package com.istato.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class MastersData {
    private String masterName;
    private List<StateMaster> stateMaster;
}
