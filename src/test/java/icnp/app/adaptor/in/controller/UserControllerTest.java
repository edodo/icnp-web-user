//package icnp.app.adaptor.in.controller;
//
//import icnp.app.biz.user.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static com.google.common.base.Predicates.equalTo;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//    // 아래 BeforeEach 에서 mockMvc 객체를 초기화 하는 것과 같다.
//    @Autowired
//    public MockMvc mockMvc;
//
//    // 이 부분은 Autowired 한 것과 동일하다
//    @BeforeEach
//    public void before() {
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(UserController.class) // 테스트 대상 Controller 를 넣어준다.
//                .alwaysExpect(status().isOk()) // 특정 필수 조건을 지정
//                .build();
//    }
//    @Test
//    void selectUserAll() throws Exception {
//        try {
//            mockMvc.perform(
//                MockMvcRequestBuilders
//                .get("/users/list") // 넣어준 컨트롤러의 Http Method 와 URL 을 지정
//                .accept(MediaType.APPLICATION_JSON) // accept encoding 타입을 지정
//                );
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(content().toString());
//
//    }
//}