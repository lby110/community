package com.community.community.controller;

import com.community.community.Mapper.UserMapper;
import com.community.community.dto.AccessTokenDTO;
import com.community.community.dto.GitHubUser;
import com.community.community.model.User;
import com.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                            HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser userInfo = githubProvider.getUserInfo(accessToken);
        if (userInfo !=null){
            User user=new User();
            user.setName(userInfo.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(String.valueOf(System.currentTimeMillis()));
            user.setGmtModifted(user.getGmtCreate());
            user.setAccountId(userInfo.getId().toString());
            //登录成功，将信息存入到数据库和写cookie和session
            userMapper.insertUser(user);
            request.getSession().setAttribute("user",userInfo);
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUserInfo(){
        User user = userMapper.selectAllUser();
        System.out.println(user.getName());
        return user;
    }
}
