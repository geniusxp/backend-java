package br.com.fiap.sprintjava.filters;

import br.com.fiap.sprintjava.repositories.UserRepository;
import br.com.fiap.sprintjava.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var tokenJwt = request.getHeader("Authorization");
//
//        if (tokenJwt != null) {
//            tokenJwt = tokenJwt.replace("Bearer ", "");
//
//            var subject = tokenService.getSubject(tokenJwt);
//            var user = userRepository.findByEmail(subject);
//
//            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);


        var tokenJwt = request.getHeader("Authorization");
        if (tokenJwt == null){
            filterChain.doFilter(request, response);
            return;
        }

        if (!tokenJwt.startsWith("Bearer ")) {
            response.setStatus(401);
            response.addHeader("Content-Type", "application/json");
            response.getWriter().write("""
                        {
                            "message": "Token must starts with Bearer"
                        }
                    """);
            return;
        }

        try {
            var subject = tokenService.getSubject(tokenJwt.replace("Bearer ", ""));
            var user = userRepository.findByEmail(subject);

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(403);
            response.addHeader("Content-Type", "application/json");
            response.getWriter().write("""
                        {
                            "message": "%s"
                        }
                    """.formatted(e.getMessage()));
        }
    }
}
