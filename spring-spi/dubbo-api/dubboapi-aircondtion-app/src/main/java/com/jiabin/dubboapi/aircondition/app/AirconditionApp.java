package com.jiabin.dubboapi.aircondition.app;


import com.jiabin.dubboapi.aircondition.service.AirconditionStandardService;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class AirconditionApp {


    public static void main(String[] args) {

        new AirconditionApp().turnOn("VerticalType");
    }

    public void turnOn(String type){
        ExtensionLoader<AirconditionStandardService> extensionLoader = ExtensionLoader.getExtensionLoader(
                AirconditionStandardService.class);

        AirconditionStandardService hangingTypeAirCondition = extensionLoader.getExtension("hangingTypeAirCondition");
        System.out.println("获取到hangingTypeAirCondition键对应的实现类对象：" + hangingTypeAirCondition);
    }

}
