package com.community.community.controller;

import com.community.community.dto.AccessTokenDTO;
import com.community.community.dto.GitHubUser;
import com.community.community.model.User;
import com.community.community.provider.GithubProvider;
import com.community.community.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
    public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/callback")
    public String callBack(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser userInfo = githubProvider.getUserInfo(accessToken);
        if (userInfo != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            if (iUserService.selectByAccountId(userInfo.getId())!=null) {
                user.setToken(token);
                user.setAccountId(userInfo.getId().toString());
                iUserService.updateUserByAccountId(user);
                response.addCookie(new Cookie("token", token));
                return "redirect:/";
            } else {
                user.setName(userInfo.getName());
                user.setToken(token);
                user.setAvatarUrl(userInfo.getAvatar_url());
                user.setGmtCreate(String.valueOf(System.currentTimeMillis()));
                user.setGmtModifted(user.getGmtCreate());
                user.setAccountId(userInfo.getId().toString());
                //登录成功，将信息存入到数据库和写cookie和session
                iUserService.insertUser(user);
                response.addCookie(new Cookie("token", token));
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
}
