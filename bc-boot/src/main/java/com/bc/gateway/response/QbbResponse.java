package com.bc.gateway.response;

public class QbbResponse<T> {
		
		private String code = "000000"; 
		private String error = "";
		private T data; 
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		
	}
	/*public class WebApiResponse<T> { 
		public static final int SUCCESS_CODE = 0; 
		public static final int ERROR_CODE = 1; 
		private int code; 
		private String error; 
		private T data; 
		public static <T> WebApiResponse<T> success(T data) { 
			WebApiResponse<T> response = new WebApiResponse<date>(); 
			response.setCode(SUCCESS_CODE); 
			response.setData(data); 
			return response; 
		} 
		public static <T> WebApiResponse<T> error(String errorMessage) { 
			return WebApiResponse.<T>error(errorMessage, ERROR_CODE); } // ... }
		@RequestMapping(value = "/", method = RequestMethod.GET) @ResponseBody 
		public WebApiResponse<ExchangeRate> quote(String symbol) throws IOException { 
			return WebApiResponse.success(currencyRateService.quote (CurrencyPair.from(symbol))); 
	}
	}

	@Configuration 
	public class WebApiConfiguration extends WebMvcConfigurerAdapter { 
		@Override public void extendMessageConverters(List<HttpMessageConvert-er<?>> converters) {
			// 添加或者插入我们自定义的HttpMessageConverter实现类 // 
			converters.add(converter)或者converters.add(0, converter) }
		}
		}*/
