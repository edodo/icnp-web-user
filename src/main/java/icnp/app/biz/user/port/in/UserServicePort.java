/***************************************************
 * Copyright(c) 2023 in-soft All right reserved.
 * This software is the proprietary information of in-soft.
 *
 * Revision History
 * Author                         Date          Description
 * --------------------------     ----------    ----------------------------------------
 * smchoi@in-soft.co.kr           2023. 01. 26.
 *
 ****************************************************/
package icnp.app.biz.user.port.in;


import icnp.app.domain.product.res.ProductRes;
import icnp.app.domain.user.req.UserReq;
import icnp.app.domain.user.res.UserRes;

import java.util.List;

/**
 * @Project : web-sample
 * @FileName : UserServicePort.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description :
 */
public interface UserServicePort {
    /**
     * 사용자 등록
     * @Method      : save
     * @Date        : 2023. 01. 26.
     * @author      : smchoi@in-soft.co.kr
     * @description : 
     * @param user
     * @return
     */
    Long save(UserReq user);
    
    /**
     * 사용자 로그인
     * @Method      : login
     * @Date        : 2023. 01. 26.
     * @author      : smchoi@in-soft.co.kr
     * @description :
     * @param userEmail
     * @param userPassword
     * @return
     */
    Boolean login(String userEmail, String userPassword);

    /**
     * 사용자 리스트
     * @Method      : selectUserAll
     * @Date        : 2023. 01. 26.
     * @author      : smchoi@in-soft.co.kr
     * @description : 
     * @param
     * @return
     */
    List<UserRes> selectUserAll();


    /**
     * 사용자 ID 조회
     * @Method      : selectUserId
     * @Date        : 2023. 01. 26.
     * @author      : smchoi@in-soft.co.kr
     * @description :
     * @param userId
     * @return
     */
    UserRes selectUserId(String userId);

    /**
     * 사용자 ID 삭제
     * @Method      : deleteUserId
     * @Date        : 2023. 01. 26.
     * @author      : smchoi@in-soft.co.kr
     * @description :
     * @param userId
     * @return
     */
    Long deleteUserId(String userId);

    /**
     * 사용자 ID별 상품 조회
     * @Method      : selectUserIdProducts
     * @Date        : 2023. 01. 26.
     * @author      : smchoi@in-soft.co.kr
     * @description :
     * @param userId
     * @return
     */
    List<ProductRes> selectUserIdProducts(String userId);
}
