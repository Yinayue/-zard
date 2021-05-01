package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.WebSocketConfig;
import com.example.demo.model.ChatMessage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ocly
 * @date 2018/2/2 15:42
 */
@RestController
public class ChatController {

  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");

  //Fake data from database
  private Set<String> all_users = new HashSet<String>(){{add("Tom");add("Kobe");add("James");}};
  private Set<String> online_users = new HashSet();
  private List<ChatMessage> hist_messages = new ArrayList();

  @Autowired
  private SimpMessagingTemplate template;

  @Autowired
  WebSocketConfig webSocketConfig;

  @GetMapping("/userlist")
  public JSONObject getUserlist(){
    JSONObject users = webSocketConfig.users;
    System.out.print(users);
    //users is an json object of all online user
    //key is session id (unique), value is name(Also id in DB)
    //Read all users(including offline users) from database
    for (Object online_u: users.values()){
      online_users.add(String.valueOf(online_u));
    }
    System.out.println("-----------------------Online Users:-----------------------");
    System.out.println(online_users);
    return users;
  }

  @GetMapping("/offline_userlist")
  public JSONObject getOfflineUserlist(){

    JSONObject off_line_user = new JSONObject();
    try {
      //添加
      int j = 1;
      System.out.println("fuckfuckfuckfuckfuck!!!!!!@@!!!!!!!!!!");
      System.out.println(all_users);
      Object[] all_users_arr = all_users.toArray();
      for(int i = 0; i < all_users_arr.length; i++){
        //假如该该user不在线即放入这个offline的JSONobject中
        if(!online_users.contains(String.valueOf(all_users_arr[i]))){
          //将offline放入object中
          off_line_user.put(String.valueOf(j),all_users_arr[i]);
          j++;
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }


    online_users = new HashSet<>();

//    //list转json字符串
//    String string = JSON.toJSON(all_users).toString();
//    System.out.println(string);
//
//    //json字符串转listJson格式
//    JSONArray jsonArray = JSONObject.parseArray(string);
//
//    Iterator iter = jsonArray.entrySet().iterator();
//    while (iter.hasNext()) {
//      Map.Entry entry = (Map.Entry) iter.next();
//      System.out.println(entry.getKey().toString());
//      System.out.println(entry.getValue().toString());
//    }
    System.out.print(off_line_user);
    return off_line_user;
  }

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public ChatMessage greeting(ChatMessage message) throws Exception {
    String date = simpleDateFormat.format(new Date());
    String content = date + "【" + message.getName() + "】说：" + message.getContent();
    message.setDate(date);
    message.setContent(content);
    Thread.sleep(1000);
    return message;
  }

  @MessageMapping("/private")
  public void privatechat(ChatMessage message) throws Exception {

    String ctx = message.getContent();
    String userid = message.getName();
    String touser = message.getReceiver();

    String date = simpleDateFormat.format(new Date());
    String content =date+"【"+userid+"】对你说：" + ctx;
    String contents =date+" 你对【"+ touser +"】说："+ ctx;

    //Send message to sender(Yourself)
    template.convertAndSendToUser(userid,"/topic/private",new ChatMessage(touser,contents,touser,date));
    //Save message history
    //Implementation:





    //Implementation completed
    Thread.sleep(1000);
    if("机器人".equals(touser)){
      touser = userid;
      String url = "http://www.tuling123.com/openapi/api";
      //请填写自己的key
      String post = "{\"key\": \"\",\"info\": \""+ctx+"\",\"userid\":\""+userid+"\"}";
      String body = Jsoup.connect(url).method(Connection.Method.POST)
        .requestBody(post)
        .header("Content-Type", "application/json; charset=utf-8")
        .ignoreContentType(true).execute().body();
      String text = JSONObject.parseObject(body).getString("text");
      content =date+"【机器人】对你说：" + text;
      template.convertAndSendToUser(touser,"/topic/private",new ChatMessage("机器人",content,"机器人",date));
      return;
    }

    //Send message to receiver
    template.convertAndSendToUser(touser,"/topic/private",new ChatMessage(userid,content,userid,date));
  }

  //Load all a user history message from database and show on the page
  public void loadUserHistory(String userName, List<ChatMessage> messages){
      for(ChatMessage m: messages){

      }

  }
}
