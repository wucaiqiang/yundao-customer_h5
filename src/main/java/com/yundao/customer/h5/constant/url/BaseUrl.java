package com.yundao.customer.h5.constant.url;

import com.yundao.core.utils.ConfigUtils;
import com.yundao.customer.h5.enums.url.UrlEnum;

/**
 * 基础服务url
 *
 * @author jan
 * @create 2017-06-30 PM8:27
 **/
public interface BaseUrl {

    /**
     * HOST地址
     */
    String BASE_URL = ConfigUtils.getValue(UrlEnum.BASE_URL.getKey());

    /**
     * 文件上传
     */
    String FILE_UPLOAD_POST = BASE_URL + "/cloudfile/upload";


}
