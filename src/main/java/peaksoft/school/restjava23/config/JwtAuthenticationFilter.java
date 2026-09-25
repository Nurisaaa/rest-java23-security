package peaksoft.school.restjava23.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import peaksoft.school.restjava23.services.JwtService;

import java.io.IOException;

/**
 * Отрабатывает на КАЖДОМ HTTP-запросе, ещё до контроллера.
 * Задача простая: если в заголовке Authorization есть валидный JWT —
 * "залогинить" пользователя на время этого запроса (положить его в
 * SecurityContext). Никакой сессии при этом не создаётся — на следующий
 * запрос всё повторится заново по тому же токену.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // токена нет или заголовок не "Bearer <token>" — пропускаем дальше
        // без аутентификации, а решит уже authorizeHttpRequests (даст 401/403)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7); // отрезаем "Bearer "
        String userEmail;

        try {
            userEmail = jwtService.extractUsername(token);
        } catch (Exception e) {
            // токен битый, просроченный или подделан — считаем, что его нет
            filterChain.doFilter(request, response);
            return;
        }

        boolean userNotAuthenticatedYet =
                userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null;

        if (userNotAuthenticatedYet) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null, // пароль тут не нужен, мы уже проверили токен
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
