// 负责处理用户的注册和登录请求，包括验证用户信息、生成并返回访问令牌等。
package com.example.demo.demos.web.controller;
import com.example.demo.demos.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        // 从请求中获取用户注册信息
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();

        // 调用 AuthService 完成用户注册
        boolean success = authService.registerUser(username, password, email);

        // 根据注册结果返回响应
        if (success) {
            return ResponseEntity.ok("User registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        // 从请求中获取用户登录信息
        String username = request.getUsername();
        String password = request.getPassword();

        // 调用 AuthService 完成用户登录
        boolean success = authService.loginUser(username, password);

        // 根据登录结果返回响应
        if (success) {
            return ResponseEntity.ok("User logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
}

