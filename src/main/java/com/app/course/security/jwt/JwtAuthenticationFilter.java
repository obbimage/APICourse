package com.app.course.security.jwt;

import com.app.course.security.user.UserSecurity;
import com.app.course.security.user.UserSecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserSecurityService userSecurityService;

    // lay jwt tu header request
    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            // lay jwt tu request
            String jwt = getJwtFromRequest(request);
            // kiểm tra chuỗi có rông và hợp lê hay không
            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
                // lay user name tu jwt
                String userName = jwtTokenProvider.getUserNameFromJWT(jwt);
                // lay thong tin tu username
                UserSecurity userDetail = (UserSecurity) userSecurityService.loadUserByUsername(userName);
                if (userDetail != null){
                    UsernamePasswordAuthenticationToken
                            authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        catch (Exception ex){
            log.error("failed on set user authentication", ex);

        }

        filterChain.doFilter(request, response);
    }
}
