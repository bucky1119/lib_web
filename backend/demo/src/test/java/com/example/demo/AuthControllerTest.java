package com.example.demo;
import com.example.demo.demos.web.controller.AuthController;
import com.example.demo.demos.web.controller.RegisterRequest;
import com.example.demo.demos.web.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @Mock
    private AuthService authService; // 假设您有一个名为 AuthService 的服务类

    @InjectMocks
    private AuthController authController; // 将自动注入 authService 到 AuthController

    @BeforeEach
    public void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() {
        // 准备测试数据
        RegisterRequest request = new RegisterRequest();
        request.setUsername("testuser");
        request.setPassword("testpassword");
        request.setEmail("test@example.com");

        // 模拟 AuthService 的行为
        when(authService.registerUser(any(String.class), any(String.class), any(String.class))).thenReturn(true);

        // 执行测试
        ResponseEntity<String> response = authController.registerUser(request);

        // 断言检查结果
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully.", response.getBody());
    }
}
