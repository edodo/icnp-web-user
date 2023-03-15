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
package icnp.app.adaptor.in.controller;

import icnp.app.biz.user.port.in.UserServicePort;
import icnp.app.domain.product.res.ProductRes;
import icnp.app.domain.user.req.UserReq;
import icnp.app.domain.user.res.UserRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * @Project : web-sample
 * @FileName : UserRestController.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description :
 */
@RestController
@Slf4j
@Tag(name = "회원 관리 컨트롤러")
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserServicePort userServicePort;
    private final Environment env;
    //private final DataSource lazyDatasource;
    private void datasourceCheck() {
        log.info("===================================");
        /*try(Connection connection = lazyDatasource.getConnection()) {
            log.info("url : {}", connection.getMetaData().getURL());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }*/
    }
    @Operation(summary = "헬스체크", description = "회원 서비스 헬스체크")
    @GetMapping("/health_check")
    public ResponseEntity<String> status() {
        datasourceCheck();
        return new ResponseEntity<>( String.format(
                "It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time")
                + ", gateway.ip=" + env.getProperty("gateway.ip"))
                , HttpStatus.OK) ;
    }

    @Operation(summary = "회원 계정 로그인", description = "이메일, 비밀번호 존재 여부 체크")
    @GetMapping("/{userEmail}/{userPassword}")
    public ResponseEntity<Boolean> login(@PathVariable String userEmail, @PathVariable String userPassword) {
        datasourceCheck();
        return new ResponseEntity<>( userServicePort.login(userEmail, userPassword) , HttpStatus.OK );
    }

    @Operation(summary = "회원 전체 조회", description = "회원 전체 조회")
    @GetMapping("")
    public ResponseEntity<List<UserRes>> selectUserAll() {
        datasourceCheck();
        log.info("GET /users");
        return new ResponseEntity<>(userServicePort.selectUserAll(), HttpStatus.OK);
    }

    @Operation(summary = "회원 가입", description = "아이디, 비밀번호, 이메일을 전달받아 DB에 등록")
    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody UserReq user) {
        datasourceCheck();
        log.info("POST /users");
        /*
        ResponseEntity<String> entity = null;
        try {
            userServicePort.save(user);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        */
        return new ResponseEntity<>(userServicePort.save(user), HttpStatus.CREATED);
    }


    @Operation(summary = "회원 삭제", description = "회원 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> userDelete(@PathVariable String userId) {
        datasourceCheck();
        log.info("DELETE /users/{userId}");
        ResponseEntity<String> entity = null;
        Optional<UserRes> optional = Optional.ofNullable(userServicePort.selectUserId(userId));
        if ( !optional.isEmpty()) {
            return new ResponseEntity<>(userServicePort.deleteUserId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0L, HttpStatus.OK);
        }
        /*
        if (optional.isEmpty()) {
            entity =  ResponseEntity.status(404).body(userId + " - User not Exist");
        } else {
            try {
                return userServicePort.deleteUserId(userId);
                entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
            } catch (Exception e) {
                entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return entity;
        */
    }

    @Operation(summary = "상품서비스 연계 회원별 상품", description = "feign client와 gateway를 통해 products의 회원별 상품 서비스 호출")
    @GetMapping("/products/{userId}")
    public ResponseEntity<List <ProductRes>> selectUserProducts(@PathVariable String userId) {
        datasourceCheck();
        log.info("GET /users/products/{userId}");
        return new ResponseEntity<>(userServicePort.selectUserIdProducts(userId), HttpStatus.OK);
    }

    /*
    @Operation(summary = "회원 삭제", description = "회원 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> userDelete(@PathVariable String userId) {
        log.info("DELETE /user/delete");
        Optional<UserRes> optional = Optional.ofNullable(userServicePort.selectUserId(userId));
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body(userId + "User Not Found");
        }
        userServicePort.deleteUserId(userId);
        //return ResponseEntity.ok().body(id + " User Delete OK");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users/list"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    */

}
