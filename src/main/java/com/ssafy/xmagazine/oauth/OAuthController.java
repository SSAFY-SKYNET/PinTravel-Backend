package com.ssafy.xmagazine.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

    @GetMapping("/oauth2/callback/google")
    public String handleGoogleOAuth2Callback(@RequestParam String code) {
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/private")
    public String privatePage() {
        return "privatePage";
    }
}
