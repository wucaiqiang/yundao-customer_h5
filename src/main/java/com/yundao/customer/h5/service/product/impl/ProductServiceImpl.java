
package com.yundao.customer.h5.service.product.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.customer.h5.constant.url.TenantUrl;
import com.yundao.customer.h5.dto.aa.product.AssetAllocationProductPageReqDto;
import com.yundao.customer.h5.dto.aa.product.ProductSimpleResDto;
import com.yundao.customer.h5.dto.product.IncomeRuleDetailDto;
import com.yundao.customer.h5.dto.product.ProductBaseQueryReqDto;
import com.yundao.customer.h5.dto.product.ProductBaseResDto;
import com.yundao.customer.h5.dto.product.ProductH5BaseResDto;
import com.yundao.customer.h5.dto.product.ProductH5DetailDto;
import com.yundao.customer.h5.dto.product.ProductIncomeDetailDto;
import com.yundao.customer.h5.dto.product.ProductIncomeDto;
import com.yundao.customer.h5.dto.product.ProductIncomeRuleDto;
import com.yundao.customer.h5.enums.ProductIncomeTypeEnum;
import com.yundao.customer.h5.service.product.ProductService;
import com.yundao.customer.h5.util.ArgsUtils;
import com.yundao.customer.h5.util.HttpUtils;
import com.yundao.customer.h5.util.NumberUtil;

/**
 * Function: Reason: Date: 2017年9月7日 下午2:24:20
 * 
 * @author 欧阳利
 * @version
 */
@Service
public class ProductServiceImpl implements ProductService {

	
	/**
	 * 通过资产配置id查询关联的产品
	 * getAssetAllocationProductPage:
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<PaginationSupport<ProductBaseResDto>> getAssetAllocationProductPage(AssetAllocationProductPageReqDto reqDto)throws BaseException{
		
		Result<PaginationSupport<ProductSimpleResDto>> result = HttpUtils.get(TenantUrl.GETS_ASSET_PRODUCT_PAGE, ArgsUtils.toMap(reqDto),
				new BaseTypeReference<Result<PaginationSupport<ProductSimpleResDto>>>() {
				});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		PaginationSupport<ProductSimpleResDto> oldResult = result.getResult();
		PaginationSupport<ProductBaseResDto> currentResult = reqDto.getPaginationSupport();
		currentResult.setTotalCount(oldResult.getTotalCount());
		List<ProductSimpleResDto> productSimpleResDtos = oldResult.getDatas();
		if (!BooleanUtils.isEmpty(productSimpleResDtos)) {
			List<ProductBaseResDto> productBaseResDtos = new ArrayList<ProductBaseResDto>();
			for (ProductSimpleResDto dto : productSimpleResDtos) {
				ProductBaseResDto resDto = new ProductBaseResDto();
				BeanUtils.copyProperties(dto, resDto);
				resDto.setIncomeRate(getIncomeInterval(dto.getPrIncomeRuleDtos()));
				productBaseResDtos.add(resDto);
			}
			currentResult.setDatas(productBaseResDtos);
		}
		return Result.newSuccessResult(currentResult);
	}

	
	/**
	 * 分页查询产品列表 getList:
	 * 
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<PaginationSupport<ProductBaseResDto>> getList(ProductBaseQueryReqDto reqDto) throws BaseException {
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		map.put("platformCode", "service_number");
		map.put("positionCode", "index_recommend_product");
		Result<PaginationSupport<ProductH5BaseResDto>> result = HttpUtils.get(TenantUrl.GETS_PRODUCT_LIST, map,
				new BaseTypeReference<Result<PaginationSupport<ProductH5BaseResDto>>>() {
				});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		PaginationSupport<ProductH5BaseResDto> oldResult = result.getResult();
		PaginationSupport<ProductBaseResDto> currentResult = reqDto.getPaginationSupport();
		currentResult.setTotalCount(oldResult.getTotalCount());
		List<ProductH5BaseResDto> productH5BaseResDtos = oldResult.getDatas();
		if (!BooleanUtils.isEmpty(productH5BaseResDtos)) {
			List<ProductBaseResDto> productBaseResDtos = new ArrayList<ProductBaseResDto>();
			for (ProductH5BaseResDto dto : productH5BaseResDtos) {
				ProductBaseResDto resDto = new ProductBaseResDto();
				BeanUtils.copyProperties(dto, resDto);
				resDto.setIncomeRate(getIncomeInterval(dto.getPrIncomeRuleDtos()));
				productBaseResDtos.add(resDto);
			}
			currentResult.setDatas(productBaseResDtos);
		}
		return Result.newSuccessResult(currentResult);
	}

	 /**
	  * 查询产品详情
	  * getProductDetailDto:
	  * @author: 欧阳利
	  * @param id
	  * @return
	  * @throws BaseException
	  * @description:
	  */
	 public Result<ProductH5DetailDto> getProductDetailDto(Long id) throws BaseException{
		 Result<ProductH5DetailDto> result = HttpUtils.get(TenantUrl.GET_PRODUCT_DETAIL, ArgsUtils.toIdMap(id),
					new BaseTypeReference<Result<ProductH5DetailDto>>() {
					});
		 
		 if(!result.getSuccess()){
			 return Result.newFailureResult(result.getCode(),result.getMessage(),null);
		 }
		 ProductH5DetailDto dto = result.getResult(); 
		 dto.setIncomeRate(getIncomeInterval1(dto.getIncomeDtos()));
		 return result;
	 }
	
	
	 
	private String getIncomeInterval1(List<IncomeRuleDetailDto> incomeDtos) throws BaseException {
		String typeValue = "";
		Double minRate = 0d;
		Double maxRate = 0d;
		if (!BooleanUtils.isEmpty(incomeDtos)) {
			boolean flag = true;
			for (IncomeRuleDetailDto rule : incomeDtos) {
				List<ProductIncomeDetailDto> incomes = rule.getProductIncomeDtos();
				if (BooleanUtils.isEmpty(incomes)) {
					continue;
				}
				for (ProductIncomeDetailDto income : incomes) {
					Double fixIncomeRate = income.getFixIncomeRate() == null ? 0 : income.getFixIncomeRate();
					if (fixIncomeRate > 0) {
						if (minRate == 0) {
							minRate = fixIncomeRate;
						} else if (minRate > fixIncomeRate) {
							minRate = fixIncomeRate;
						}
						if (maxRate == 0) {
							maxRate = fixIncomeRate;
						} else if (maxRate < fixIncomeRate) {
							maxRate = fixIncomeRate;
						}
					}

					String type = income.getIncomeType();
					if (flag) {
						if (ProductIncomeTypeEnum.FLOAT.getValue().equals(type)
								|| ProductIncomeTypeEnum.FIXED_FLOAT.getValue().equals(type)) {
							typeValue = "浮动";
							flag = false;
						}
					}
				}
			}
			StringBuilder builder = new StringBuilder();
			if (minRate > 0) {
				builder.append(NumberUtil.trimDoubleZero(String.valueOf(minRate)));
			}
			if (maxRate > 0 && maxRate > minRate) {
				if (builder.length() > 0) {
					builder.append("~");
				}

				builder.append(NumberUtil.trimDoubleZero(String.valueOf(maxRate)));
			}
			if (builder.length() > 0) {
				builder.append("%");
			}
			if (StringUtils.isNotBlank(typeValue)) {
				builder.append("+").append(typeValue);
			}
			return builder.toString();
		}
		return "";
	}
	 
	 
	private String getIncomeInterval(List<ProductIncomeRuleDto> rules) throws BaseException {
		String typeValue = "";
		Double minRate = 0d;
		Double maxRate = 0d;
		if (rules != null && !rules.isEmpty()) {
			boolean flag = true;
			for (ProductIncomeRuleDto rule : rules) {
				List<ProductIncomeDto> incomes = rule.getProductIncomeDtos();
				if (incomes == null || incomes.isEmpty()) {
					continue;
				}
				for (ProductIncomeDto income : incomes) {
					Double fixIncomeRate = income.getFixIncomeRate() == null ? 0 : income.getFixIncomeRate();
					if (fixIncomeRate > 0) {
						if (minRate == 0) {
							minRate = fixIncomeRate;
						} else if (minRate > fixIncomeRate) {
							minRate = fixIncomeRate;
						}
						if (maxRate == 0) {
							maxRate = fixIncomeRate;
						} else if (maxRate < fixIncomeRate) {
							maxRate = fixIncomeRate;
						}
					}

					String type = income.getIncomeType();
					if (flag) {
						if (ProductIncomeTypeEnum.FLOAT.getValue().equals(type)
								|| ProductIncomeTypeEnum.FIXED_FLOAT.getValue().equals(type)) {
							typeValue = "浮动";
							flag = false;
						}
					}
				}
			}
			StringBuilder builder = new StringBuilder();
			if (minRate > 0) {
				builder.append(NumberUtil.trimDoubleZero(String.valueOf(minRate)));
			}
			if (maxRate > 0 && maxRate > minRate) {
				if (builder.length() > 0) {
					builder.append("~");
				}

				builder.append(NumberUtil.trimDoubleZero(String.valueOf(maxRate)));
			}
			if (builder.length() > 0) {
				builder.append("%");
			}
			if (StringUtils.isNotBlank(typeValue)) {
				builder.append("+").append(typeValue);
			}
			return builder.toString();
		}
		return "";
	}
}
