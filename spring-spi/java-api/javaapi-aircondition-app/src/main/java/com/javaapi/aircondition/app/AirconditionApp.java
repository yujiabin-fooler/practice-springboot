package com.javaapi.aircondition.app;


import com.jiabin.javaapi.aircondition.service.AirconditionStandardService;

import java.util.ServiceLoader;

public class AirconditionApp {


    public static void main(String[] args) {
        new AirconditionApp().turnOn("VerticalType");
    }

    public void turnOn(String type){
        ServiceLoader<AirconditionStandardService> load = ServiceLoader
                .load(AirconditionStandardService.class);

        for (AirconditionStandardService iAircondition : load) {
            System.out.println("检测到:"+iAircondition.getClass().getSimpleName());
            if (type.equals(iAircondition.getType())){
                iAircondition.turnOnOff();
            }
        }
    }

}
