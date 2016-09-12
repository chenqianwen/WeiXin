package cn.bigzhu.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.bigzhu.entity.Image;
import cn.bigzhu.entity.ImageMessage;
import cn.bigzhu.entity.Item;
import cn.bigzhu.entity.Music;
import cn.bigzhu.entity.MusicMessage;
import cn.bigzhu.entity.NewsMessage;
import cn.bigzhu.entity.TextMessage;
import cn.bigzhu.entity.Video;
import cn.bigzhu.entity.VideoMessage;
import cn.bigzhu.entity.Voice;
import cn.bigzhu.entity.VoiceMessage;

import com.thoughtworks.xstream.XStream;

public class MessageUtils {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_LINK = "lin";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	/**
	 * 主菜单内容
	 * */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的帮助，请按以下菜单操作:\n\n");
		sb.append("1.师资力量\n\n");
		sb.append("2.录取分数\n\n");
		sb.append("3.心中美图\n\n");
		sb.append("4.语音\n\n");
		sb.append("5.视频\n\n");
		sb.append("6.音乐\n\n");
		sb.append("7.图文\n\n");
		sb.append("回复?调出主菜单\n\n");
		sb.append("作者:天青色等烟雨");
		return sb.toString();
	}
	/**
	 * 菜单1
	 * */
	public static String menu1(){
		StringBuffer sb = new StringBuffer();
		sb.append("1.于静\n\n");
		sb.append("2.邵思清\n\n");
		sb.append("3.朱琳");
		return sb.toString();
	}
	/**
	 * 菜单2
	 * */
	public static String menu2(){
		StringBuffer sb = new StringBuffer();
		sb.append("2014年520分\n\n");
		sb.append("2015年530分\n\n");
		sb.append("2016年540分");
		return sb.toString();
	}
	
	/**
	 * 普通消息的回复
	 * */
	public static String initText(Map<String, String> map,String content){
		TextMessage mes = new TextMessage();
		mes.setFromUserName(map.get("ToUserName"));
		mes.setToUserName(map.get("FromUserName"));
		mes.setContent(content);
		mes.setCreateTime(new Date().getTime());
		mes.setMsgType("text");
		return MessageUtils.textToXml(mes);
	}
	/**
	 * 普通图文的回复
	 * */
	public static String initNews(Map<String, String> map){
		NewsMessage mes = new NewsMessage();
		mes.setFromUserName(map.get("ToUserName"));
		mes.setToUserName(map.get("FromUserName"));
		mes.setArticleCount(2);
		mes.setCreateTime(new Date().getTime());
		mes.setMsgType(MESSAGE_NEWS);
		List<Item> items = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setPicUrl("http://njsqhzx.ngrok.cc/WeiXin/image/sc1.png");
		item1.setTitle("你对自己多心软，生活对你对无情！");
		item1.setDescription("生活给人带来的只有不停向前走，对自己的放松停滞，生活就将你抛弃在车站！");
		item1.setUrl("http://www.vsuda.cn/jingdianmeiwen/13150.html");
		Item item2 = new Item();
		item2.setPicUrl("http://njsqhzx.ngrok.cc/WeiXin/image/sc2.png");
		item2.setTitle("夏天吃西瓜！");
		item2.setDescription("在西瓜上切了个小口，令人惊讶的事发生了！");
		item2.setUrl("http://www.vsuda.cn/shenghuobaike/11401.html");
		items.add(item1);
		items.add(item2);
		mes.setArticles(items);
		return MessageUtils.newsToXml(mes);
	}
	/**
	 * 回复图片信息
	 * */
	public static String initImage(Map<String, String> map) {
		ImageMessage mes = new ImageMessage();
		mes.setFromUserName(map.get("ToUserName"));
		mes.setToUserName(map.get("FromUserName"));
		mes.setCreateTime(new Date().getTime());
		mes.setMsgType(MESSAGE_IMAGE);
		Image image = new Image();
		image.setMediaId("WDTW8eMlT2NLZVQ3bxAIz_-_57VXj534f9L6VbL7MeA4sZTyTvK2jZaXLl-OVLaP");
		mes.setImage(image);
		return MessageUtils.imageToXml(mes);
	}
	
	/**
	 * 回复语音信息
	 * */
	public static String initVoice(Map<String, String> map) {
		VoiceMessage mes = new VoiceMessage();
		mes.setFromUserName(map.get("ToUserName"));
		mes.setToUserName(map.get("FromUserName"));
		mes.setCreateTime(new Date().getTime());
		mes.setMsgType(MESSAGE_VOICE);
		Voice voice = new Voice();
		voice.setMediaId("CmKtsfNxvvfJMsL2oquVCPG2OQGci_sZNQohzb5druXGl1wgM4KHi99Q4QWVMggX");
		mes.setVoice(voice);
		return MessageUtils.voiceToXml(mes);
	}
	/**
	 * 回复视频信息
	 * */
	public static String initVideo(Map<String, String> map) {
		VideoMessage mes = new VideoMessage();
		mes.setFromUserName(map.get("ToUserName"));
		mes.setToUserName(map.get("FromUserName"));
		mes.setCreateTime(new Date().getTime());
		mes.setMsgType(MESSAGE_VIDEO);
		Video video = new Video();
		video.setMediaId("qKKZWEW8C2JFDlRvRN6-5QyuSdjrWoxtF0DbqOxA1Cm553M43PiFmwa5QObMaX60");
		video.setTitle("南海");
		video.setDescription("母亲叫儿打东洋，妻子送郎上战场");
		mes.setVideo(video);
		return MessageUtils.videoToXml(mes);
	}
	/**
	 * 回复音乐信息
	 * */
	public static String initMusic(Map<String, String> map) {
		MusicMessage mes = new MusicMessage();
		mes.setFromUserName(map.get("ToUserName"));
		mes.setToUserName(map.get("FromUserName"));
		mes.setCreateTime(new Date().getTime());
		mes.setMsgType(MESSAGE_MUSIC);
		Music music = new Music();
		music.setThumbMediaId("pH0xfTKvlWeIOxvKSSre6YJkbXEL7J11w6wVuIOuajvFRNCNX2VtSZP8u8C1dIZf");
		music.setMusicUrl("http://njsqhzx.ngrok.cc/WeiXin/music/See You Again.mp3");
		music.setHQMusicUrl("http://njsqhzx.ngrok.cc/WeiXin/music/See You Again.mp3");
		music.setTitle("see you");
		music.setDescription("see you again");
		mes.setMusic(music);
		return MessageUtils.musicToXml(mes);
	}
	
	
	/**
	 * xml转成map格式
	 * */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		SAXReader read = new SAXReader();
		try {
			Document doc = read.read(request.getInputStream());
			Element ele = doc.getRootElement();
			Iterator<Element> it = ele.elementIterator();
			while(it.hasNext()){
				Element el =  it.next();
				map.put(el.getName(), el.getTextTrim());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * TextMessage转成xml格式
	 * */
	public static String textToXml(TextMessage mes){
		XStream xs = new XStream();
		xs.alias("xml", TextMessage.class);
		System.out.println(xs.toXML(mes));
		return xs.toXML(mes);
	}
	
	/**
	 * image转成xml格式
	 * */
	public static String imageToXml(ImageMessage mes){
		XStream xs = new XStream();
		xs.alias("xml", ImageMessage.class);
		System.out.println(xs.toXML(mes));
		return xs.toXML(mes);
	}
	
	/**
	 * voice转成xml格式
	 * */
	public static String voiceToXml(VoiceMessage mes){
		XStream xs = new XStream();
		xs.alias("xml", VoiceMessage.class);
		System.out.println(xs.toXML(mes));
		return xs.toXML(mes);
	}
	/**
	 * video转成xml格式
	 * */
	public static String videoToXml(VideoMessage mes){
		XStream xs = new XStream();
		xs.alias("xml", VideoMessage.class);
		System.out.println(xs.toXML(mes));
		return xs.toXML(mes);
	}
	/**
	 * music转成xml格式
	 * */
	public static String musicToXml(MusicMessage mes){
		XStream xs = new XStream();
		xs.alias("xml", MusicMessage.class);
		System.out.println(xs.toXML(mes));
		return xs.toXML(mes);
	}
	/**
	 * news转成xml格式
	 * */
	public static String newsToXml(NewsMessage mes){
		XStream xs = new XStream();
		xs.alias("xml", NewsMessage.class);
		xs.alias("item", Item.class);
		System.out.println(xs.toXML(mes));
		return xs.toXML(mes);
	}

}
