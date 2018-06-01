package com.qbb.scheduler.action;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qbb.scheduler.vo.AjaxVO;
import com.qbb.scheduler.vo.PageList;

/**
 * Action基类
 */
public class BaseAction<T> extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 429832322438758703L;
	public final Logger log = Logger.getLogger(this.getClass());

	/* HTTP objects */
	public Map<String, Object> session;
	public HttpServletRequest request;
	public HttpServletResponse response;

	/*
	 * 参数，传参格式：args.a=1&args.b=2；注意：参数值可能是数组，需要的时候可以强制转换。
	 */
	public T args;
	public String func;
	public String token;
	public int page = 0;
	public int start = 0;
	public int limit = 0;
	@SuppressWarnings("unused")
	private String _dc;// 声明此属性仅为禁用struts开发模式下的一个错误警告。

	/* Parameter */
	private String definedURL;
	public AjaxVO ajaxVO = AjaxVO.build();
	public PageList resultList = PageList.build();

	/**
	 * 响应json数据，实体类：com.qbb.common.dto.AjaxVO
	 *
	 * @return
	 */
	public String resultAjaxVO() {
		return ActionResultEnum.AJAX.toString();
	}

	/**
	 * @return
	 * @see this.resultAjaxVO();
	 */
	public String resultAjaxVO(AjaxVO ajaxVO) {
		this.ajaxVO = ajaxVO;
		return resultAjaxVO();
	}

	/**
	 * @return
	 * @see this.resultAjaxPageList();
	 */
	public String resultAjaxPageList(PageList resultList) {
		this.resultList = resultList;
		return resultAjaxPageList();
	}

	/**
	 * 响应json数据，实体类：com.qbb.common.plugins.page.PageList
	 *
	 * @return
	 */
	public String resultAjaxPageList() {
		return ActionResultEnum.AJAX_PAGELIST.toString();
	}

	/**
	 * 跳转到definedURL
	 *
	 * @param definedURL
	 *            指定的URL地址
	 * @return
	 */
	public String resultDefinedURL(String definedURL) {
		this.definedURL = definedURL;
		return ActionResultEnum.DEFINED.toString();
	}

	/**
	 * 重定向到definedURL
	 *
	 * @param definedURL
	 *            指定的URL地址
	 * @return
	 */
	public String resultRedirect(String definedURL) {
		this.definedURL = definedURL;
		return ActionResultEnum.REDIRECT.toString();
	}

	/**
	 * 自定义result
	 *
	 * @param defined
	 *            result配置的name属性值，如：
	 *            <p>
	 * 
	 *            <pre>
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  <result name="login">login.do<\/result>
	 * </pre>
	 * @return
	 */
	public String resultDefined(String defined) {
		return defined;
	}

	/**
	 * 返回NULL
	 *
	 * @return
	 */
	public String resultNULL() {
		return null;
	}

	// --------------
	public String getDefinedURL() {
		return definedURL;
	}

	public void setDefinedURL(String definedURL) {
		this.definedURL = definedURL;
	}

	public AjaxVO getAjaxVO() {
		return ajaxVO;
	}

	public void setAjaxVO(AjaxVO ajaxVO) {
		this.ajaxVO = ajaxVO;
	}

	public PageList getResultList() {
		return resultList;
	}

	public void setResultList(PageList resultList) {
		this.resultList = resultList;
	}

	public T getArgs() {
		return args;
	}

	public void setArgs(T args) {
		this.args = args;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 获得请求参数集合信息
	 *
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getRequestParamMap() {
		Map<String, Object> remap = new ConcurrentHashMap<String, Object>();
		try {
			Enumeration<?> eretion = request.getParameterNames();
			while (eretion.hasMoreElements()) {
				String elem = (String) eretion.nextElement();
				if (elem == null || elem.indexOf("args.") >= 0) {
					continue;
				}
				String[] result = request.getParameterValues(elem);
				if (result != null && result.length > 0) {
					if (result.length == 1) {
						remap.put(elem, result[0]);
					} else {
						remap.put(elem, result);
					}
				} else {
					remap.put(elem, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			remap.clear();
		}
		return remap;
	}

	public void set_dc(String _dc) {
		this._dc = _dc;
	}

	// -------------

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	protected String request(String key) {
		return request.getParameter(key);
	}

	protected Object session(String key) {
		return session.get(key);
	}

	protected String getClientIp() {
		return request.getRemoteAddr();
	}

	protected String getClientHostName() {
		return request.getRemoteHost();
	}

}
