package com.jiabin.dubboapi.aircondition.type;


import com.jiabin.dubboapi.aircondition.service.AirconditionStandardService;

public class HangingTypeAircondition implements AirconditionStandardService {

    public String getType() {
        return "HangingType";
    }

    public void turnOnOff() {
        System.out.println("挂式空调开关");
    }

    public void adjustTemperature(int i) {
        System.out.println("挂式空调调节温度");
    }

    public void changeModel(int i) {
        System.out.println("挂式空调更换模式");
    }

}
