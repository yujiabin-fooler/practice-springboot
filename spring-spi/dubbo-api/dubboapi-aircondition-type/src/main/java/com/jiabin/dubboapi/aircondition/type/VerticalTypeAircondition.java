package com.jiabin.dubboapi.aircondition.type;


import com.jiabin.dubboapi.aircondition.service.AirconditionStandardService;

public class VerticalTypeAircondition implements AirconditionStandardService {


    public String getType() {
        return "VerticalType";
    }

    public void turnOnOff() {
        System.out.println("立式空调开关");
    }

    public void adjustTemperature(int i) {
        System.out.println("立式空调调节温度");
    }

    public void changeModel(int i) {
        System.out.println("立式空调更换模式");
    }

}
