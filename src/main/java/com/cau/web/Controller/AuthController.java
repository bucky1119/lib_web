package com.cau.web.Controller;
import com.cau.web.dto.ApiResponseLogin;
import com.cau.web.dto.ApiResponseNormal;
import com.cau.web.entity.User;
import com.cau.web.service.AuthService;
import com.cau.web.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/register")
    public ApiResponseNormal<User> register(@RequestBody User user) {
        System.out.println("register");
        if (authService.register(user)) {
            return new ApiResponseNormal<>(200,user,"注册成功");
        } else {
            return new ApiResponseNormal<>(400, null, "用户名已存在");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public void options() {
        // This method is empty because Spring will handle the OPTIONS request.
    }

    @PostMapping("/login")
    public ApiResponseLogin<User> login(@RequestBody User user) {
        System.out.println("login");
        User loginUser = authService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            // 登录成功，生成访问令牌
            String token = JwtUtil.generateToken(loginUser.getUsername(),loginUser.getRole());
            return new ApiResponseLogin<>(200,loginUser,"登录成功",token);
        } else {
            return new ApiResponseLogin<>(401, null, "用户名或密码错误",null);
        }
    }
}
