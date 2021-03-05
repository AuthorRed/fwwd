package cn.author.fwwd.filter;


import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token过滤器,刷新一下token
 */
//@Component
public class TokenFilter extends OncePerRequestFilter {

	private static final String TOKEN_KEY = "token";

	@Autowired
	private TokenService tokenService;



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		if (StringUtils.isNotBlank(token)) {
			User loginUser = tokenService.getLoginUser(token);

		}
		filterChain.doFilter(request, response);
	}



	/**
	 * 根据参数或者header获取token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		String token = request.getHeader(TOKEN_KEY);
		if (StringUtils.isBlank(token)) {
			 token = request.getParameter(TOKEN_KEY);
		}
		return token;
	}

}
