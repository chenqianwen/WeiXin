package cn.bigzhu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.bigzhu.entity.Button;
import cn.bigzhu.entity.ClickButton;
import cn.bigzhu.entity.Menu;
import cn.bigzhu.entity.ViewButton;

@SuppressWarnings("deprecation")
public class WeiXinUtil {
	private static final String appID = "wx0a736945d43cbf6c";
	private static final String appsecret = "cf1a6359113170c147d9731aab0103df";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	
	/**
	 * 初始化菜单
	 * */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton button1 = new ClickButton();
		button1.setName("助学资讯");
		ClickButton cb11 = new ClickButton();
		cb11.setKey("cb11");
		cb11.setName("分数线查询");
		cb11.setType("click");
		ClickButton cb12 = new ClickButton();
		cb12.setKey("cb12");
		cb12.setName("通知书查询");
		cb12.setType("click");
		ClickButton cb13 = new ClickButton();
		cb13.setKey("cb13");
		cb13.setName("名师风采");
		cb13.setType("click");
		button1.setSub_button(new Button[]{cb11,cb12,cb13});
		ViewButton button2 = new ViewButton();
		button2.setName("秦中官网");
		button2.setType("view");
		button2.setUrl("http://www.njqhzx.cn/");
		Button button3 = new Button();
		button3.setName("学生指南");
		ClickButton cb31 = new ClickButton();
		cb31.setKey("b31");
		cb31.setName("饭卡查询");
		cb31.setType("click");
		ClickButton cb32 = new ClickButton();
		cb32.setKey("b32");
		cb32.setName("成绩查询");
		cb32.setType("click");
		button3.setSub_button(new Button[]{cb31,cb32});
		menu.setButton(new Button[]{button1,button2,button3});
		return menu;
	}
	
	/**
	 * 创建菜单
	 * @throws Exception 
	 * */
	public static String createMenu(String menu) throws Exception{
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken());
		JSONObject json = doPostStr(url, menu);
		int res = -1;
		if(json != null){
			res = json.getInt("errcode");
		}
		if(res == 0){
			return "---菜单创建成功!---";
		}else{
			System.out.println(res);
			return "===菜单创建失败!===";
		}
	}
	
	/**
	 * 删除菜单
	 * @throws Exception 
	 * */
	public static String deleteMenu() throws Exception{
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", getAccessToken());
		JSONObject json = doGetStr(url);
		int res = -1;
		if(json != null){
			res = json.getInt("errcode");
		}
		if(res == 0){
			return "---删除菜单成功!---";
		}else{
			return "===删除菜单失败!===";
		}
	}
	/**
	 * 查询菜单
	 * @throws Exception 
	 * */
	public static JSONObject queryMenu() throws Exception{
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", getAccessToken());
		JSONObject json = doGetStr(url);
		if(json != null){
			if(json.containsKey("errcode")){
				System.out.println("====没有查询到菜单!====");
			}else{
				System.out.println("---查询菜单成功!菜单如下：---");
				System.out.println(json);
			}
		}else{
			System.out.println("====查询菜单失败!====");
		}
		return json;
	}
	
	
	
	
	/**
	 * 获取access_token
	 * */
	public static String getAccessToken() {
		String url = ACCESS_TOKEN_URL.replace("APPID", appID).replace(
				"APPSECRET", appsecret);
		String fileName = "access_token.txt";
		String path = System.getProperty("user.dir")+"/WebContent/"+fileName;//保存"access_token.txt"的路径
		File file = new File(path);
		if(!file.isFile()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("创建access_token.txt文件失败！");
			}
		}
		String content = readFile(file);
		if(content == null || "".equals(content)){
			return writeFile(file, doGetStr(url));
		}
		String date = content.split(",")[1];
		Long old = 0l;
		try {
			old = Long.parseLong(date);
		} catch (Exception e) {
			System.out.println("保存时间报错");
		}
		if((System.currentTimeMillis()-old)>7200*1000){//失效获得
			return writeFile(file, doGetStr(url));
		}else{//获得已有的
			System.out.println("获得已有的access_token");
			return content.split(",")[0];
		}
	}

	/**
	 * doGet请求
	 * */
	@SuppressWarnings("resource")
	public static JSONObject doGetStr(String url) {
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		JSONObject jsonObject = null;
		try {
			httpClient = new DefaultHttpClient();
			httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "utf-8");
					jsonObject = JSONObject.fromObject(result);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * doPost请求
	 * */
	@SuppressWarnings("resource")
	public static JSONObject doPostStr(String url,String outStr){
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		try {
			HttpResponse response = client.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken, String type)
			throws IOException, NoSuchAlgorithmException,
			NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace(
				"TYPE", type);
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		System.out.println("--"+result);
		JSONObject jsonObj = JSONObject.fromObject(result);
		String typeName = "media_id";
		String mediaId = "获取media_id出错了";
		if(result.contains(typeName)){
			if (!"image".equals(type)) {
				typeName = type + "_media_id";
			}
			mediaId = jsonObj.getString(typeName);
		}
		return mediaId;
	}

	/**
	 * 读取文件
	 * */
	public static String readFile(File file) {
		String text = "";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fr);
			text = bufferedReader.readLine();
			System.out.println("读取的accesss_token："+text);
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * 写文件 返回access_token
	 * */
	public static String writeFile(File file, JSONObject json) {
		String content = json.toString();
		String access_token = content.substring(content.indexOf(":")+2, content.indexOf(",")-1);
		content = access_token+","+System.currentTimeMillis();
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			BufferedWriter bufWriter = new BufferedWriter(fw);
			bufWriter.write(content);
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-----------写access_token成功！-------");
		return access_token;
	}
}
