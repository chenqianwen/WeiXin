package cn.bigzhu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bigzhu.util.CheckUtils;
import cn.bigzhu.util.MessageUtils;

public class WeiXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public WeiXinServlet() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/WEB-INF/index.jsp");
//		String token = "yujing";
//		String signature =  request.getParameter("signature");
//		String timestamp = request.getParameter("timestamp");
//		String nonce = request.getParameter("nonce");
//		String echostr = request.getParameter("echostr");
//		String[] arr = new String[]{token,timestamp,nonce};
//		Arrays.sort(arr);
//		String goals = "";
//		for(int i =0 ;i <arr.length ;i++){
//			goals += arr[i];
//		}
//		PrintWriter resp = response.getWriter();
//		if(signature.equals(CheckUtils.getBySha1(goals))){
//			resp.print(echostr);
//		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
//		PrintWriter pw = response.getWriter();
//		Map<String,String> map = new HashMap<String,String>();
//		map = MessageUtils.xmlToMap(request);
//		if(MessageUtils.MESSAGE_TEXT.equals(map.get("MsgType"))){
//			if("1".equals(map.get("Content"))){
//				pw.print(MessageUtils.initText(map,MessageUtils.menu1()));
//			}else if("2".equals(map.get("Content"))){
//				pw.print(MessageUtils.initText(map,MessageUtils.menu2()));
//			}else if("?".equals(map.get("Content"))||"？".equals(map.get("Content"))){
//				pw.print(MessageUtils.initText(map,MessageUtils.menuText()));
//			}else if("3".equals(map.get("Content"))){
//				pw.print(MessageUtils.initImage(map));
//			}else if("4".equals(map.get("Content"))){
//				pw.print(MessageUtils.initVoice(map));
//			}else if("5".equals(map.get("Content"))){
//				pw.print(MessageUtils.initVideo(map));
//			}else if("6".equals(map.get("Content"))){
//				pw.print(MessageUtils.initMusic(map));
//			}else if("7".equals(map.get("Content"))){
//				pw.print(MessageUtils.initNews(map));
//			}else{
//				pw.print(MessageUtils.initText(map,"你说  "+map.get("Content")+",可是我并不想回答你！"));
//			}
//		}else if(MessageUtils.MESSAGE_EVENT.equals(map.get("MsgType"))){
//			String eventType = map.get("Event");
//			if(MessageUtils.MESSAGE_SUBSCRIBE.equals(eventType)){
//				pw.print(MessageUtils.initText(map,MessageUtils.menuText()));
//			}else if(MessageUtils.MESSAGE_CLICK.equals(eventType)){
//				String buttonVal = map.get("EventKey");
//				if("b1".equals(buttonVal)){//判断点击那个按钮
//					pw.print(MessageUtils.initText(map,MessageUtils.menuText()));
//				}else {
//					pw.print(MessageUtils.initText(map,"需要接口开发"));
//				}
//			}
		}
				
	}

}
