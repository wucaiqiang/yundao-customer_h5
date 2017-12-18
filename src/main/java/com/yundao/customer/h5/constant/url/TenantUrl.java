package com.yundao.customer.h5.constant.url;

import com.yundao.core.utils.ConfigUtils;
import com.yundao.customer.h5.enums.url.UrlEnum;

/**
 * 租户后置系统url
 *
 * @author jan
 * @create 2017-06-30 PM4:11
 **/
public interface TenantUrl {

    /**
     * HOST地址
     */
    String BASE_URL = ConfigUtils.getValue(UrlEnum.TENANT_URL.getKey());

    //####################################gjl start###################
    /**
     * 用户登陆
     */
    String USER_LOGIN = BASE_URL + "/cc/user/login";

    /**
     * 用户登出
     */
    String USER_LOGIN_OUT = BASE_URL + "/cc/user/login_out";

    /**
     * 修改密码
     */
    String USER_UPDATE_PASSWORD = BASE_URL + "/cc/user/update_password";

    /**
     * 发送找回密码短信验证码
     */
    String SEND_RETRIEVE_CAPTCHA = BASE_URL + "/cc/user/send_retrieve_captcha";

    /**
     * 发送注册验证码
     */
    String SEND_REGISTER_CAPTCHA = BASE_URL + "/cc/user/send_register_captcha";

    /**
     * 找回密码
     */
    String RETRIEVE_PASSWORD = BASE_URL + "/cc/user/retrieve_password";

    /**
     * 验证手机是否存在
     */
    String VALIDATE_MOBILE = BASE_URL + "/cc/user/validate_mobile";

    /**
     * 找回密码
     */
    String REGISTER = BASE_URL + "/cc/user/register";

    /**
     * 验证ticket
     */
    String TICKET_VALIDATE = BASE_URL + "/cc/ticket/validate";

    /**
     * 更新ticket
     */
    String TICKET_UPDATE_EXPIRETIME = BASE_URL + "/cc/ticket/update_expireTime";

    /**
     * 发送消息
     */
    String SEND_MESSAGE = BASE_URL + "/msg/message/send_message";

    //####################################gjl end###################

    /**
     * 获取风险评测题目
     */
    String GETS_RISK_QUESTION = BASE_URL + "/question/gets_risk_question";
    
    /**
     * 风险评测
     */
    String RISK_EVALUATION = BASE_URL + "/question/risk/evaluation";
    
    /**
     * 获取风险评测结果
     */
    String GET_RISK_EVALUATION_RESULT = BASE_URL + "/question/get/risk_evaluation_result";
    
    /**
     * 获取产品列表
     */
    String GETS_PRODUCT_LIST = BASE_URL + "/product/h5/get_List";
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_PRODUCT_PAGE = BASE_URL + "/asset/product/get_page";
    
    
    String QUESTION_GET_CUSTOMER_RISK_EVALUATION =   BASE_URL + "/question/get/customer/risk_evaluation_result";
    
    
    
    String QUESTION_GET_USER_RISK_EVALUATION =   BASE_URL + "/question/get/current/customer/risk_evaluation_result";
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_PLAN_DESCRIPTION = BASE_URL + "/asset/plan/get_plan_description";
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_PLAN = BASE_URL + "/asset/plan/get_asset_plan";
    
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_CUSTOMER_PLAN = BASE_URL + "/asset/plan/get_customer_plan";
    
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_CURRENT_USER_PLAN = BASE_URL + "/asset/plan/get_current_user_plan";
    
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_PLAN_CUSTOMER_DESCRIPTION = BASE_URL + "/asset/plan/customer/get_plan_description";
    
    /**
     * 获取资产配置关联的产品列表
     */
    String GETS_ASSET_PLAN_CUSTOMER = BASE_URL + "/asset/plan/customer/get_asset_plan";
    
    
    
    /**
     * 获取查询详情
     */
    String GET_PRODUCT_DETAIL = BASE_URL + "/product/h5/get_detail_by_id";
    
    /**
     * 获取公告详情
     */
    String GET_NOTICE_DETAIL =  BASE_URL + "/product/h5/notice/get";
    
    /**
     * 获取我的报单
     */
    String DECLARATION_MY_GET_PAGE =  BASE_URL + "/declaration/h5/my/get_page";
    
    
    /**
     * 获取我的报单
     */
    String RESERVATION_MY_GET_PAGE =  BASE_URL + "/reservation/h5/my/get_page";
    
    /**
     * 获取我的首页信息
     */
    String GET_MY_INDEX =  BASE_URL + "/get/h5/my/index";
    
    /**
     * 获取理财师名片
     */
    String GET_AFP_CARD =  BASE_URL + "/user_detail/get_card_by_uuid";
    
    

    /**=======C端客户预约========**/

    /**
     * 预约产品
     */
    String CONSULT_PRODUCT = BASE_URL + "/consult/product";
    
    /**
     * 预约理财师
     */
    String CONSULT_TP = BASE_URL + "/consult/fp";
    String ROADSHOW_GET = BASE_URL + "/roadshow/get";

    /**=======C端客户预约========**/
}
