package cike.chatgpt.controller

import cike.chatgpt.interceptor.RequiredLogin
import cike.chatgpt.repository.User
import cike.chatgpt.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@RequestMapping("auth")
class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login/withPassword")
    CommonResponse<String> loginByPassword(@RequestBody LoginParam loginParam) {
        authService.loginByPassword(loginParam.username, loginParam.password, loginParam.verifyCode);
    }

    @GetMapping("checkToken")
    @RequiredLogin
    CommonResponse<User> checkToken(@RequestHeader("Authorization") String authorization) {
        if (authorization && authorization.startsWith("Bearer ")) {
            return authService.checkToken(authorization.substring(7))
        }
        return new CommonResponse<User>(status: CommonResponse.Fail, message: "No Authorization")
    }
}

class LoginParam {

    String username;
    String verifyCode;
    String password;

}