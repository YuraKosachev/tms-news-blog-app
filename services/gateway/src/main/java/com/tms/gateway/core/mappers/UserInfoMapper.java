package com.tms.gateway.core.mappers;

import com.tms.gateway.core.models.gateway.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserInfoMapper {

    public UserInfo getUserInfo(JwtAuthenticationToken token){
        if(token == null) return null;
        var builder = UserInfo.builder();
        var attributes = token.getTokenAttributes();

        if(attributes.containsKey("sub"))
        {
            builder.id(UUID.fromString(attributes.get("sub").toString()));
        }
        if(attributes.containsKey("name")){
            builder.username(attributes.get("name").toString());
        }
        if(attributes.containsKey("email")){
            builder.email(attributes.get("email").toString());
        }
        return builder.build();
    }
}
