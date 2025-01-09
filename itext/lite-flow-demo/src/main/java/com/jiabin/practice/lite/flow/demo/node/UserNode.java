package com.jiabin.practice.lite.flow.demo.node;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.flow.element.Node;
import org.springframework.stereotype.Component;


@Component
public class UserNode extends NodeComponent {
    @Override
    public void process() {
        System.out.println("用户身份验证");
    }
}
