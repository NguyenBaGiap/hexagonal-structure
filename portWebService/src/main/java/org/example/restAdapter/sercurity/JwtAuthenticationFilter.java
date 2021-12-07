package org.example.restAdapter.sercurity;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.auth.StudentUserDetails;
import org.example.domain.student.models.Student;
import org.example.services.ports.StudentService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    final StudentService studentService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, StudentService studentService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.studentService = studentService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
                String email = jwtTokenProvider.getEmailFromToken(jwt);
                Student student = studentService.findByEmail(email);
                StudentUserDetails details = StudentUserDetails.apply(student);
                UsernamePasswordAuthenticationToken
                        authentication = new UsernamePasswordAuthenticationToken(details, null,
                        details.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        log.info("check token contains JWT");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
