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
package icnp.app.biz.user.service;



import icnp.app.adaptor.out.feignclient.product.ProductClient;
import icnp.app.adaptor.out.persistence.user.entity.UserEntity;
import icnp.app.biz.user.port.in.UserServicePort;
import icnp.app.biz.user.port.out.UserPersistencePort;
import icnp.app.domain.product.res.ProductRes;
import icnp.app.domain.user.req.UserReq;
import icnp.app.domain.user.res.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project : web-sample
 * @FileName : UserService.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description :
 */
@RequiredArgsConstructor
@Service
public class UserService implements UserServicePort {
    private final ProductClient productClient;
    private final UserPersistencePort userPersistencePort;

    @Transactional
    @Override
    public Long deleteUserId(String userId) { return userPersistencePort.deleteUserId(userId); }

    @Transactional
    @Override
    public Long save(UserReq user) {
        return userPersistencePort.insertUser(UserEntity.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userPassword(user.getUserPassword())
                .build() );
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean login(String userEmail, String userPassword) {
        UserRes us = userPersistencePort.selectUserEmail( userEmail );
        if (us != null ) {
            if(us.getUserPassword().equals(userPassword)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserRes> selectUserAll() {
        return userPersistencePort.selectUserAll();
    }

    @Transactional(readOnly = true)
    @Override
    public UserRes selectUserId(String userId) { return userPersistencePort.selectUserId(userId); }

    @Transactional(readOnly = true)
    @Override
    public List<ProductRes> selectUserIdProducts(String userId) {
        return productClient.getProducts(userId);
    }
}
