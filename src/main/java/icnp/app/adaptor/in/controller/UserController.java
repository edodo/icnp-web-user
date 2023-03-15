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
import icnp.app.domain.user.req.UserReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Project : web-sample
 * @FileName : UserController.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description :
 */
@Controller
@Slf4j
@RequiredArgsConstructor
//@Tag(name = "로그인 화면 리스트")
@RequestMapping("/users")
public class UserController {
    private final UserServicePort userServicePort;

    //@Operation(summary  = "인덱스 화면", description  = "루트 경로로 접근시 인덱스 화면으로 이동한다")
    @GetMapping("/index/screen")
    public String index() {
        log.info("GET /users/index/screen");
        return "users/index";
    }

    //@Operation(summary  = "회원 로그인 화면", description = "아이디, 비밀번호 입력 화면")
    @GetMapping("/login/screen")
    public String loginForm(Model model) {
        log.info("GET /users/login/screen");
        model.addAttribute("userReq", new UserReq());
        return "users/login";
    }


    // 회원 가입페이지 요청
    //@Operation(summary = "회원 가입 화면", description = "아이디, 비밀번호, 이메일 입력 화면")
    @GetMapping("/save/screen")
    public String saveForm(Model model) {
        log.info("GET /users/save/screen");
        model.addAttribute("userReq", new UserReq());
        return "users/save";
    }

    //@Operation(summary = "회원 전체 조회 화면", description = "회원 전체 조회")
    @GetMapping("/list/screen")
    public String selectUserAll(Model model) {
        log.info("GET /users/list/screen");
        model.addAttribute("users",userServicePort.selectUserAll());
        return "users/userList";
    }
    //@Operation(summary = "회원별 상품 조회 화면", description = "회원별 상품 조회")
    @GetMapping("/products/{userId}/screen")
    public String selectUserProducts(@PathVariable String userId, Model model) {
        log.info("GET /users/products/{userId}/screen");
        model.addAttribute("products",userServicePort.selectUserIdProducts(userId));
        return "users/productList";
    }
}
