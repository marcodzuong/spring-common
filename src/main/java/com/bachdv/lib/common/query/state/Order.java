package com.bachdv.lib.common.query.state;

/**
 * @author bachdv
 */
public enum Order {

    ASC("ASC"), DESC("DESC");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

}
