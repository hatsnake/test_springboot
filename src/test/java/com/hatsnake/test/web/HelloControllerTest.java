package com.hatsnake.test.web;

import com.hatsnake.test.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)
// Web(Spring Web)에 집중할 수 있는 어노테이션
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    // 스프링이 관리하는 빈을 주입 받습니다.
    @Autowired
    // 스프링 MVC 테스트의 시작점
    // HTTP GET, POST등에 대한 API 테스트를 할 수 있습니다.
    private MockMvc mvc;

    /*  상태코드
        200 : 요청 성공적
        400 :
        401 : 접근 권한 X
        403 : 접근 권한 X, 서버가 클라이언트가 누구인지 알고 있음
        404 : 서버가 요청받은 리소스를 찾을 수 없음
        500 : 서버의 문제
        502 : 서버가 게이트웨이로부터 잘못된 응답 수신
    */

    @WithMockUser(roles = "USER")
    @Test
    public void hello() throws Exception {
        String hello = "hello";

        // HTTP GET 요청을 합니다.
        mvc.perform(get("/hello"))
                // 상태 코드 200인지 아닌지 검증
                .andExpect(status().isOk())
                // 응답 본문의 내용을 검증
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;
        
        mvc.perform(get("/hello/dto")
                // 요청 파라미터 설정, String만 허용 (형변환 시켜야됨)
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // json 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
