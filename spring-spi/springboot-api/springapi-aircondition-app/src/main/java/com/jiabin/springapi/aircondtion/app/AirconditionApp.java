package com.jiabin.springapi.aircondtion.app;

import com.jiabin.springapi.aircondition.service.AirconditionStandardService;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

public class AirconditionApp {


    public static void main(String[] args) {
        new AirconditionApp().turnOn("VerticalType");
    }

    public void turnOn(String type){
        List<AirconditionStandardService> serviceList = SpringFactoriesLoader.loadFactories(
                AirconditionStandardService.class,Thread.currentThread().getContextClassLoader());

        for (AirconditionStandardService iAircondition : serviceList) {
            System.out.println("检测到:"+iAircondition.getClass().getSimpleName());
            if (type.equals(iAircondition.getType())){
                iAircondition.turnOnOff();
            }
        }
    }

}
