package com.yundao.customer.h5.enums;

/**
 * Created by gjl on 2017/9/25.
 */
public enum ChannelEnum {
    C_H5("c_h5","客户h5端"),
    C_WX_P("c_wx_p","服务号");
    private String code;
    private String name;

    public static ChannelEnum getByCode(String code){
        ChannelEnum[] enums = ChannelEnum.values();
        for (ChannelEnum anEnum : enums) {
            if(anEnum.getCode().equals(code)){
                return anEnum;
            }
        }
        return null;
    }
    ChannelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
