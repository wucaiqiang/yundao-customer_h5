

package com.yundao.customer.h5.service.product;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.customer.h5.dto.aa.product.AssetAllocationProductPageReqDto;
import com.yundao.customer.h5.dto.aa.product.ProductSimpleResDto;
import com.yundao.customer.h5.dto.product.ProductBaseQueryReqDto;
import com.yundao.customer.h5.dto.product.ProductBaseResDto;
import com.yundao.customer.h5.dto.product.ProductH5DetailDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月7日 下午2:11:11 
 * @author   欧阳利
 * @version   
 */
public interface ProductService {

	/**
	 * 通过资产配置id查询关联的产品
	 * getAssetAllocationProductPage:
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<PaginationSupport<ProductBaseResDto>> getAssetAllocationProductPage(AssetAllocationProductPageReqDto reqDto)throws BaseException;
	 /**
	  * 分页查询产品列表
	  * getList:
	  * @author: 欧阳利
	  * @param reqDto
	  * @return
	  * @throws BaseException
	  * @description:
	  */
	 public Result<PaginationSupport<ProductBaseResDto>> getList(ProductBaseQueryReqDto reqDto) throws BaseException;
	 
	 
	 /**
	  * 查询产品详情
	  * getProductDetailDto:
	  * @author: 欧阳利
	  * @param id
	  * @return
	  * @throws BaseException
	  * @description:
	  */
	 public Result<ProductH5DetailDto> getProductDetailDto(Long id) throws BaseException;
}

