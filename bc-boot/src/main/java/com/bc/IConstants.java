package com.bc;



/**
 * @author 杨程
 * @version Oct 19, 2011 1:55:18 PM
 * @declaration
 */
public class IConstants {
	
	/**
	 * 时间格式(年-月-日）
	 */
	public static final String DATE_FORMAT_YMD_LONG = "yyyy-MM-dd";
	/**
	 * 是否开启用户请求过滤，true：开启false：关闭
	 */
	public static final boolean USER_SESSION_SWITCH = true;
	/**
	 * session内容：user用户
	 */
	public static final String SESSION_USER = "user";
	
	/**
	 * 招标中
	 */
	public static final String BORROW_STATUS_SECOND = "2";

	/**
	 * 复审中(满标)
	 */
	public static final String BORROW_STATUS_THIRD = "3";

	/**
	 * 成功
	 */
	public static final String BORROW_STATUS_FOUR = "4";

	/**
	 * 申请失败
	 */
	public static final String BORROW_STATUS_FIVE = "5";

	/**
	 * 流标
	 */

	public static final String BORROW_STATUS_SIX = "6";

	
	
	/**
	 * support@qian88.com.cn  config
	 */
	public static String MAIL_HOST_QIAN88=""	;
	public static String MAIL_USERNAME_SUPPORT=""	;
	public static String MAIL_PASSWORD_SUPPORT=""	;
	public static String MAIL_FROM_SUPPORT=""	;
	
	public static String SERVERPORT=""	;
	
	public static String imagePathHttps = "https://images.qian88.com.cn/";

	//语音短信验证密码
	public static String  SPEECH_MESSAGE_PASS ="";
	
	//上传文件以及图片的路径
	public static  String UPFEFOLDER ="";
	public static  String UPFEFIMG ="";
	
	public static String showImagePath="";
	
	 public static String CLIENT_SECRET = "";
	 
//	public static final String CN_DOMAIN = "https://www.qian88.com.cn";
	 public static final String CN_DOMAIN = "https://www.qian88.com.cn/userCenterNew";
	
	
	public static final String luosimaoVoice = "http://voice-api.luosimao.com/v1/verify.json";
	
	public static final String luosimaoVoiceStatus = "http://voice-api.luosimao.com/v1/status.json";
	 
	//测试 模拟POST提交地址
//	public static final String urlAddress = "http://test.qian88.com.cn:9088/qian88_app/comm!doJsonList.action";
	
//	public static final String urlAddress = "http://192.168.16.253:8091/app/comm!doJsonList.action";
	
//	 public static final String urlAddress = "http://192.168.16.253:8091/app/comm!doJsonList.action";
	 public static final String urlAddress = "http://192.168.8.17:8080/app/comm!doJsonList.action";
	
	
//	public static final String urlAddress = "http://202.104.101.122:8080/comm!doJsonList.action";
	
//	public static final String urlAddress = "http://127.0.0.1:8089/app/comm!doJsonList.action";
	
	public static String isSendMsgOrMail;   //短信邮件开关
	//玄武短信设置;
	public static final String XUANWU_USER ="szqbb@szqbb";
	public static final String XUANWU_PASSWORD ="123456";
	public static final String XUANWU_SERVER ="211.147.239.62";
	public static final int XUANWU_SEND_PORT =9080;
	public static final int XUANWU_CHECK_PORT =9070;
	
	//中信银行订单推送RUL
	public static String zxOrderURL="";
	//证书
	public static String signercrt="";
	//私钥
	public static String signerkey="";
	//私钥密码
	public static String keypasswd="";
	//商户编号
	public static String MERID="";
	//后台通知URL
	public static String BACKURL="";
	//前台通知url
	public static String FRONTURL="";
	//中信银行订单交易查询RUL
	public static String zxQueryTranURL="";
	
	
	 // 银通公钥
    public static String YT_PUB_KEY   = "";
    // 商户私钥
    public static String TRADER_PRI_KEY = "";
    // MD5 KEY
    public static String MD5_KEY        = "";
    // 接收异步通知地址
    public static String NOTIFY_URL     = "";
    // 支付结束后返回地址
    public static String URL_RETURN     = "";
    // 商户编号
    public static String OID_PARTNER    = ""; 
    // 签名方式 RSA或MD5
    public static String SIGN_TYPE      = "";
    // 接口版本号，固定1.0
    public static String VERSION        = "";
    // 业务类型，连连支付根据商户业务为商户开设的业务类型； （101001：虚拟商品销售、109001：实物商品销售、108001：外部账户充值）
    public static String BUSI_PARTNER   = "";
    public static String PAY_URL = ""; // 连连支付WEB收银台支付服务地址
    public static String QUERY_USER_BANKCARD_URL = ""; // 用户已绑定银行卡列表查询
    public static String QUERY_BANKCARD_URL = ""; //银行卡卡bin信息查询
	
    
    public static String baofu_memberID=""; //商户号
    public static String baofu_terminalID =""; //终端号
    public static String baofu_interfaceVersion ="";  //接口版本
    public static String baofu_pageUrl="";  // 页面通知地址
    public static String baofu_returnUrl=""; // 服务通知地址
    public static String baofu_md5Key="";  //MD5 key
    public static String baofu_payUrl="";  // 订单请求地址
    
    public static String service_url="";	//
    public static String  qbb_gateway="";
    public static String  api_gateway="";
    public static String  user_gateway="";
    public static String  phone_gateway="";
    public static String  commonQuery_gateway="http://localhost:8007/commonQuery/";
    public static String  wap_url="";
    public static String  lucky_service_url="";	//
	public static String  qbb_fastdfs="";
	public static String  localDomain="";
	public static String dream_post_url;//造梦计划url
	public static String wap_weixin_url;//微信下打开url

	public  String getUPFEFOLDER() {
		return UPFEFOLDER;
	}
	public  void setUPFEFOLDER(String upfefolder) {
		UPFEFOLDER = upfefolder;
	}
	public  String getUPFEFIMG() {
		return UPFEFIMG;
	}
	public  void setUPFEFIMG(String upfefimg) {
		UPFEFIMG = upfefimg;
	}
	
	public  String getSERVERPORT() {
		return SERVERPORT;
	}
	public  void setSERVERPORT(String serverport) {
		SERVERPORT = serverport;
	}
}
