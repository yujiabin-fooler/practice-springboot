package com.jiabin.elasticsearch.query.practice.repository;

import com.jiabin.elasticsearch.query.practice.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES 操作类
 * <p>
 * @author jiabin.yu 20/06/2017.
 */
public interface CityRepository extends ElasticsearchRepository<City, Long> {

}
