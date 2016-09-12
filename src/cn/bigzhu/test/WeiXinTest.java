package cn.bigzhu.test;
import net.sf.json.JSONObject;
import cn.bigzhu.util.WeiXinUtil;
public class WeiXinTest {
	public static void main(String[] args) {
		/*
		 * {"access_token":"
		 * 1DvfIusIReC8Rm2RlWPfQcIG8hmTL9aPTHVg_GtMYQbsRqA20pQ72y1MgsPvt3T2eNOwibAnqyKnZWu2eBaRoO_iOXB_3cnFHaMjjfsR1MV_LRZfR0bk7_mwLHnpXifCJXGbAIATHQ
		 * ","expires_in":7200}
		 * */
		//WeiXinUtil.readFile(System.getProperty("user.dir")+"/WebContent/"+"access_token.txt");
		//WeiXinUtil.writeFile(System.getProperty("user.dir")+"/WebContent/"+"access_token.txt","dadad");
		//WeiXinUtil.getAccessToken();
		//System.out.println(WeiXinUtil.getAccessToken());
//		String accessToken = "1DvfIusIReC8Rm2RlWPfQcIG8hmTL9aPTHVg_GtMYQbsRqA20pQ72y1MgsPvt3T2eNOwibAnqyKnZWu2eBaRoO_iOXB_3cnFHaMjjfsR1MV_LRZfR0bk7_mwLHnpXifCJXGbAIATHQ";
		try {
			//图片
//			String result = WeiXinUtil.upload("D:\myway\weixin\jing.jpg", WeiXinUtil.getAccessToken(), "image");
//			System.out.println("media_id："+result);
			//语音
//			String result = WeiXinUtil.upload("D:\\myway\\weixin\\sb.mp3", WeiXinUtil.getAccessToken(), "voice");
//			System.out.println("media_id："+result);
			//视频
//			String result 	 = WeiXinUtil.upload("D:\\myway\\weixin\\nanhai.mp4", WeiXinUtil.getAccessToken(), "video");
//			System.out.println("media_id："+result);
			//缩略图
//			String result 	 = WeiXinUtil.upload("D:\\myway\\weixin\\jing.jpg", WeiXinUtil.getAccessToken(), "thumb");
//			System.out.println("media_id："+result);
			//创建菜单：
			JSONObject json = JSONObject.fromObject(WeiXinUtil.initMenu()); System.out.println(WeiXinUtil.createMenu(json.toString()));
			//查询菜单：
//			WeiXinUtil.queryMenu();
			//删除菜单：
//			System.out.println(WeiXinUtil.deleteMenu());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
