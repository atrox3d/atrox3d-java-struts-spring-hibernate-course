package it.formarete.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookiesMap implements Filter {
	private String[] cookieNames;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String initParameter = config.getInitParameter("cookieNames");
		if(initParameter != null) {
			cookieNames = initParameter.split(",");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Map<String, String> cookies = new HashMap<String, String>();
		for (Cookie cookie : httpRequest.getCookies()) {
			if(cookieNames != null && cookieNames.length >0) {
				request.setAttribute(cookie.getName(), cookie.getValue());
			}
			cookies.put(cookie.getName(), cookie.getValue());
		}
		request.setAttribute("cookies", cookies);
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {
	}
}
