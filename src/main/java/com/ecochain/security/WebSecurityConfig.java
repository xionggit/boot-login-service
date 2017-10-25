package com.ecochain.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Athos on 2016-10-16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private MySecurityFilter mySecurityFilter;

	@Resource
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
	@Autowired
    private UserDetailsService userDetailsService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .addFilterBefore(MyUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器  
            .authorizeRequests()
                //问号需要转译，不要使用antMatcers，因为ant里?不知道怎么转译
                .regexMatchers("/login\\?invalid","/login\\?expired",
                        "/index","/home","/getToken","/loginexpired")
//                .antMatchers("/")
                .permitAll()//访问：/home 无需登录认证权限  
                .anyRequest()
                .authenticated() //其他所有资源都需要认证，登陆后访问  
            .and()
                .formLogin()
//                    .authenticationDetailsSource(customAuthenticationDetailsSource)
//                    .authenticationDetailsSource(authenticationDetailsSource)
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(new CustomerAuthenticationSuccessHandler())
                    .defaultSuccessUrl("/hello", true)
            .and()
                .sessionManagement()
                .invalidSessionUrl("/login?invalid")
                .maximumSessions(1)
                .expiredUrl("/loginexpired")
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry())
            .and()
            .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
            .and()
                .logout()
                    .invalidateHttpSession(false)
                    .logoutUrl("/logout")
                    //定义logoutSeccessHandler后logouturl和logoutsuccessurl无效，
                    //需要在logoutSeccessHandler中定义
//                    .logoutSuccessHandler(new MyLogoutSuccessHandler())
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            .and()
                .csrf().disable();
            
    }
	
//	private UsernamePasswordAuthenticationFilter MyUsernamePasswordAuthenticationFilter() {
//	    RockUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new RockUsernamePasswordAuthenticationFilter();
//        myUsernamePasswordAuthenticationFilter.setPostOnly(true);
//        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        myUsernamePasswordAuthenticationFilter.setUsernameParameter("username");
//        myUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
//        myUsernamePasswordAuthenticationFilter.setMessageSource(messageSource());
////        myUsernamePasswordAuthenticationFilter.setAuthenticationDetailsSource(customAuthenticationDetailsSource);
//        myUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
//        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
////        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
//        myUsernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(sessionControlAuthenticationStrategy());
//        return myUsernamePasswordAuthenticationFilter;
//    }
//	
//	@Bean
//    public ConcurrentSessionControlAuthenticationStrategy sessionControlAuthenticationStrategy(){
//	    ConcurrentSessionControlAuthenticationStrategy currentStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
//        currentStrategy.setExceptionIfMaximumExceeded(false);
//        currentStrategy.setMaximumSessions(1);
//        return currentStrategy;
//    }
//	
//	public AuthenticationManager authenticationManagerBean() {
//        /*
//         * 参考链接 http://dead-knight.iteye.com/blog/1512779
//         * */
//        List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
//        MyProvider provider = new MyProvider();
//        //自定义userDetailsService
//        provider.setUserDetailsService(userDetailsService);
//
////        provider.setPasswordEncoder(passwordEncoder());
//
//        ReflectionSaltSource saltSource = new ReflectionSaltSource();
//        // 密码使用username加盐
//        saltSource.setUserPropertyToUse("username");
//
////        provider.setSaltSource(saltSource);
//        //国际化
//        provider.setMessageSource(messageSource());
//        //自定义provider
//        providers.add(provider);
//
//        //猜测为匿名用户用的
//        providers.add(new AnonymousAuthenticationProvider("work"));
//
//        ProviderManager authenticationManager = new ProviderManager(providers);
//
//        authenticationManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher());
//
//        return authenticationManager;
//    }

    class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request,
	                                        HttpServletResponse response,
	                                        Authentication authentication) throws ServletException, IOException {
	      //例1：不跳到XML设定的页面，而是直接返回json字符串
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json");
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
	        HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("realname", securityUser.getRealname());
	        map.put("peopletype", securityUser.getPeopletype());
	        map.put("username", securityUser.getUsername());
	        map.put("mobile", securityUser.getMobile());
	        map.put("code", 200);
	        map.put("message", "success");
	        ObjectMapper mapper = new ObjectMapper();
	        response.getWriter().println(mapper.writeValueAsString(map));
	    }
	}
	
	
    class MyLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.setHeader("Access-Control-Allow-Origin", "*");
                HashMap<String, Object> map = new HashMap<String, Object>();
                String sessionId=request.getRequestedSessionId();
                map.put("code", 200);
                map.put("message", "success");
                map.put("sessionId", sessionId);
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().println(mapper.writeValueAsString(map));
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //这里如果不设置"/home" 退出登录时总是重定向到/login?type=invalid
        //而且这里不能写"/login"，否则登录页面csrf无法显示
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico", "/home","/loginexpired");
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.userDetailsService(userDetailsService);
	    auth.authenticationProvider(myProvider());
        auth.authenticationProvider(new AnonymousAuthenticationProvider("work"));
        auth.authenticationEventPublisher(new DefaultAuthenticationEventPublisher());
	}
	
	@Bean
	public MyProvider myProvider(){
	    MyProvider myProvider = new MyProvider();
	    myProvider.setUserDetailsService(userDetailsService);
//	    myProvider.setPasswordEncoder(passwordEncoder());
	    myProvider.setMessageSource(messageSource());
	    return myProvider;
	}
	
	public MessageSource messageSource() {
        System.out.println("---------messageSource load-----------");
        ReloadableResourceBundleMessageSource messages = new ReloadableResourceBundleMessageSource();
        messages.addBasenames("classpath:locale/securityMessages", "classpath:org/springframework/security/messages");

        return messages;
    }
	
	@Bean  
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder(4);  
    }  

    // Work around https://jira.spring.io/browse/SEC-2855
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
//    @Bean
//    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
//        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
//    }
//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }
}
