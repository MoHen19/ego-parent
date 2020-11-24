package com.ego.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.common.result.EgoPageInfo;
import com.ego.common.util.JsonUtil;
import com.ego.rpc.service.SearchService;
import com.ego.rpc.vo.GoodsVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
/**
 * @author MH
 * @site
 * @company
 * @create 2020-03-25-14:37
 */
@SpringBootTest
public class SearchServiceTest {

    @Reference(interfaceClass = SearchService.class)
    private SearchService searchService;

    @Test
    public void testSearch() throws IOException {
        EgoPageInfo<GoodsVo> pageInfo = searchService.doSearch("中国移动联通电信", 1, 9);
        System.out.println(JsonUtil.object2JsonStr(pageInfo));
    }
}
