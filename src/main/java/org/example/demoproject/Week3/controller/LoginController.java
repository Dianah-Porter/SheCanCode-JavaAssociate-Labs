package org.example.demoproject.Week3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    public String loginPage() {
        return """
                <html>
                <head>
                    <title>Login</title>
                </head>
                <body style="font-family: Arial; text-align:center; margin-top:80px;">
                    <h1>Welcome</h1>
                    <h3>Please choose a login provider</h3>

                    <p>
                        <a href="/oauth2/authorization/google">
                            Login with Google
                        </a>
                    </p>

                    <p>
                        <a href="/oauth2/authorization/github">
                            Login with GitHub
                        </a>
                    </p>
                </body>
                </html>
                """;
    }
}
