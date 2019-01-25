

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component("myHttpUtil")
public class MyHttpUtilTest {

	static String app_version = "1.0";
	
	private static String service_url = "http://47.98.59.69:8001/bussInovk.action/"; 
	
	public static JSONObject sendPost(Map<String,String> map) {
		
		map.put("termno", "127.0.0.1" );
		
		if(map.get("channel")==null){
			map.put("channel", "WEBAPP");
		}
		Set<String> keys=map.keySet(); 
		StringBuffer sb=new StringBuffer();
		sb.append("app_version=").append(app_version);
		for(String key:keys){
			sb.append("&").append(key).append("=").append(map.get(key).toString().replace("+", "%2B")); 
		}
		String param=sb.toString();
		String url = service_url;
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),Charset.forName("UTF-8"))); 
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("result = "+result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            return jsonObject;
        } catch (Exception e) {
            System.out.println("·þÎñÆ÷Ã¦"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }  
	
	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println(ip+"*------");
		String ipAdd = "";
		if(ip.indexOf(",") == -1){
			if("0:0:0:0:0:0:0:1".equals(ip.trim())){
				ipAdd = "127.0.0.1";
			}else{
				ipAdd = ip.trim();
			}
		}else{
			String [] iparray = ip.split(",");
			ipAdd = iparray[0].trim();
		}
		return ipAdd;
	}
	

}
