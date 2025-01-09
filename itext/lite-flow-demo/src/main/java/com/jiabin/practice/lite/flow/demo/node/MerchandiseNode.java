package com.jiabin.practice.lite.flow.demo.node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;


@Component
public class MerchandiseNode extends NodeComponent {
    @Override
    public void process() {
        System.out.println("价格确认");
    }
}
