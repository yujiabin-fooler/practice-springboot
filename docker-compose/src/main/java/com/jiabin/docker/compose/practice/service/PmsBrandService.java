package com.jiabin.docker.compose.practice.service;


import com.jiabin.docker.compose.practice.mbg.model.PmsBrand;

import java.util.List;

/**
 * PmsBrandService
 * @author jiabin.yu 2019/4/19.
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
