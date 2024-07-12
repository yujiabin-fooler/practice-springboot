package com.jiabin.mybatis.generator.practice.domain;

import com.jiabin.mybatis.generator.practice.mbg.model.UmsResource;
import com.jiabin.mybatis.generator.practice.mbg.model.UmsResourceCategory;

/**
 * @author jiabin.yu 2020/12/9.
 */
public class ResourceWithCateDto extends UmsResource {
    private UmsResourceCategory resourceCategory;

    public UmsResourceCategory getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(UmsResourceCategory resourceCategory) {
        this.resourceCategory = resourceCategory;
    }
}
