package com.jiabin.practice.lite.flow.demo.node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;


@Component
public class StorageNode extends NodeComponent {
    @Override
    public void process() {
        System.out.println("库存检查");
    }
}
