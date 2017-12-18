
package com.yundao.customer.h5.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.customer.h5.dto.product.ProductBaseQueryReqDto;
import com.yundao.customer.h5.dto.product.ProductBaseResDto;
import com.yundao.customer.h5.dto.product.ProductH5DetailDto;
import com.yundao.customer.h5.service.product.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月1日 下午3:59:26
 *
 * @author wucq
 */
@Controller
@ResponseBody
@RequestMapping("/product")
@Api("产品相关")
public class ProductController {
	
    @Autowired
    private ProductService productService;

    /**
     * 分页产品产品管理列表
     *
     * @param reqDto
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "/get_recommend_list", method = RequestMethod.GET)
    @ApiOperation(value = "推荐产品列表", notes = "推荐产品列表")
    public Result<PaginationSupport<ProductBaseResDto>> getList(@ModelAttribute ProductBaseQueryReqDto reqDto) throws BaseException {
        return productService.getList(reqDto);
    }

    @ApiOperation(value = "通过产品id查询产品详情", notes = "通过产品id查询产品详情")
    @RequestMapping(value = "/get_detail_by_id", method = RequestMethod.GET)
    public Result<ProductH5DetailDto> getProductDetailDto(@RequestParam Long id) throws Exception {
        return productService.getProductDetailDto(id);
    }
    

//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    @ApiOperation(value = "产品搜索", notes = "产品搜索")
//    public Result<ItemListDTO> search(String keyword, Integer page, Integer pageSize) throws BaseException {
//        return productService.search(keyword, page, pageSize);
//    }
//
//    @RequestMapping(value = "/get_detail_by_id", method = RequestMethod.POST)
//    @ApiOperation(value = "产品详情", notes = "产品详情")
//    public Result<ProductDetailView> getDetailById(@RequestParam Long productId) throws BaseException {
//        return productService.getDetailById(productId);
//    }

}
