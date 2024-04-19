package com.istato.admin.model;


public enum MasterDetails {

    ISTATO_STATE_MASTER("stateMaster", StateMaster.class);

    String masterName;
    Class masterClass;
    Class masterFiledsEnum;


    MasterDetails(String masterNm, Class masterClss) {

        masterName = masterNm;
        masterClass = masterClss;
    }


    public String getMasterName() {
        return masterName;
    }

    public Class getMasterClass() {
        return masterClass;
    }

    public Class getMasterFiledsEnum() {
        return masterFiledsEnum;
    }

}
