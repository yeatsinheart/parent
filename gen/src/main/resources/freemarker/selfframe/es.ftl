package com.example.esspringdemo.service.game.dao${pac};

import com.example.esspringdemo.service.game.dao.BaseDao;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import com.example.esspringdemo.service.game.model.DaoArgs;
import com.example.esspringdemo.service.game.model.DaoResult;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* 原始模板${raw}
**/
public class ${class} implements BaseDao {
/**
* 生成查询对象
* 服务类调用searchRequestBuilder.internalBuilder(SearchSourceBuilder sourceBuilder) 设置查询内容 获得响应内容
*
* @param args
*/
@Override
public SearchSourceBuilder initTemple(DaoArgs args) {
SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
${java}
/*
${content}
*/

return searchSourceBuilder;
}

/**
* 查询响应封装
*
* @param args
* @param response
*/
@Override
public DaoResult get(DaoArgs args, SearchResponse response) {
DaoResult result = new DaoResult<>();
List list = new ArrayList<>();


result.setResult(list);
return result;
}
}
