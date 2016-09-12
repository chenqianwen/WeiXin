package cn.bigzhu.entity;

public class TextMessage extends BaseMessage{
	private String Content;//	文本消息内容
	private Long MsgId;//	消息id，64位整型
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
}
