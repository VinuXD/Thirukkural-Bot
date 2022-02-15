package me.vinuxd;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

public class ThirukkuralBot extends TelegramLongPollingBot {

    Dotenv dotenv = new DotenvBuilder().ignoreIfMissing().load();
    String botUsername = dotenv.get("BOT_USERNAME");
    String botToken = dotenv.get("BOT_TOKEN");
    String logGroup = dotenv.get("LOG_GROUP");
    String ownerId = dotenv.get("OWNER_ID");

    public static void main(String[] args) {

        System.out.println("Bot Starting..");
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new ThirukkuralBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("==============================");
        System.out.println("===Bot Started Successfully===");
        System.out.println("==============================");
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String username = "<a href=\"tg://user?id=" + update.getMessage().getFrom().getId() + "\">"
                    + update.getMessage().getFrom().getFirstName().replaceAll("[^a-zA-Z0-9]", " ") + "</a>";
            String startText = "Hello " + username
                    + ",\nI can send you thirukkural its meaning, translations with additional information.\nClick /help to learn more!";
            String helpText = "Hello " + username
                    + ",\nHere are the possible commands I can help with.\n\n/start - To Start me\n/help - Probably this message\n/kural - To get random Thirukural.\n\nThat's all for now.";

            String msgChatId = update.getMessage().getChatId().toString();
            int msgReplyId = update.getMessage().getMessageId();
            String msgId = update.getMessage().getMessageId().toString();

            if (update.getMessage().getText().equals("/start")
                    || update.getMessage().getText().equals("/start@" + getBotUsername())) {

                try {

                    SendMessage message = new SendMessage();
                    message.setChatId(msgChatId);
                    message.setReplyToMessageId(msgReplyId);
                    message.enableHtml(true);
                    message.setAllowSendingWithoutReply(true);
                    message.setText(startText);

                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline3 = new ArrayList<>();

                    InlineKeyboardButton button1 = new InlineKeyboardButton();
                    button1.setUrl("https://github.com/VinuXD/Thirukkural-Bot");
                    button1.setText("👨‍💻 Source Code");

                    InlineKeyboardButton button2 = new InlineKeyboardButton();
                    button2.setUrl("t.me/" + getBotUsername() + "?startgroup=0");
                    button2.setText("⚡ Add me in Groups");

                    InlineKeyboardButton button3 = new InlineKeyboardButton();
                    button3.setUrl("tg://user?id=" + getOwnerId());
                    button3.setText("Made with ❤ & JAVA");

                    InlineKeyboardButton button4 = new InlineKeyboardButton();
                    button4.setUrl("https://t.me/BotUpdatesXD");
                    button4.setText("🎊 Updates");

                    rowInline1.add(button1);
                    rowInline1.add(button4);
                    rowInline2.add(button2);
                    rowInline3.add(button3);

                    rowsInline.add(rowInline2);
                    rowsInline.add(rowInline1);
                    rowsInline.add(rowInline3);
                    markupInline.setKeyboard(rowsInline);
                    if (update.getMessage().isGroupMessage() == false
                            || update.getMessage().isSuperGroupMessage() == false) {
                        message.setReplyMarkup(markupInline);
                    }
                    Message msg = execute(message);
                    SendMessage logStart = new SendMessage();
                    logStart.setChatId(getLogGroup());
                    logStart.enableHtml(true);
                    logStart.setText("#NewUserOn" + getBotUsername() + "\n\nName: " + username + "\nUser ID: <code>"
                            + update.getMessage().getFrom().getId() + "</code>\nStarted on: <code>" + msg.getChatId()
                            + "</code>");
                    execute(logStart);
                } catch (Exception e) {
                    logging(e, msgChatId,
                            update.getMessage().getChat().getTitle(), msgId,
                            username, "https://t.me/c/" + msgChatId.replaceFirst("-100", "") + "/" + msgId);
                }

            } else if (update.getMessage().getText().equals("/help")
                    || update.getMessage().getText().equals("/help@" + getBotUsername())) {

                try {
                    SendMessage message = new SendMessage();
                    message.setChatId(msgChatId);
                    message.enableHtml(true);
                    message.setReplyToMessageId(msgReplyId);
                    message.setAllowSendingWithoutReply(true);
                    message.setText(helpText);
                    execute(message);
                } catch (Exception e) {
                    logging(e, msgChatId,
                            update.getMessage().getChat().getTitle(), msgId,
                            username, "https://t.me/c/" + msgChatId.replaceFirst("-100", "") + "/" + msgId);
                }
            }

            else if (update.getMessage().getText().equals("/kural")
                    || update.getMessage().getText().equals("/kural@" + getBotUsername())) {

                try {
                    String response = "";
                    Random random = new Random();
                    SendMessage message = new SendMessage();
                    message.setChatId(msgChatId);
                    message.enableHtml(true);
                    message.setReplyToMessageId(msgReplyId);
                    message.setAllowSendingWithoutReply(true);
                    message.setText("<code>Processing...</code>");
                    Message msg = new Message();
                    msg = execute(message);
                    String apiUrl = "https://api-thirukkural.vercel.app/api?num=" + random.nextInt(1329 + 1);
                    URL url = new URL(apiUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    Scanner sc = new Scanner(url.openStream(), StandardCharsets.UTF_8);
                    while (sc.hasNext()) {
                        response += sc.nextLine();
                    }
                    sc.close();
                    JSONArray jsonArray = new JSONArray("[" + response + "]");
                    JSONObject object = jsonArray.getJSONObject(0);
                    EditMessageText editmsg = new EditMessageText();
                    editmsg.setChatId(msgChatId);
                    editmsg.setMessageId(msg.getMessageId());
                    editmsg.enableHtml(true);
                    int number = object.getInt("number");
                    String sectTam = object.getString("sect_tam");
                    String sectEng = object.getString("sect_eng");
                    String grpTam = object.getString("chapgrp_tam");
                    String grpEng = object.getString("chapgrp_eng");
                    String chapTam = object.getString("chap_tam");
                    String chapEng = object.getString("chap_eng");
                    String line1 = object.getString("line1");
                    String line2 = object.getString("line2");
                    String tamExp = object.getString("tam_exp");
                    String eng = object.getString("eng");
                    String engExp = object.getString("eng_exp");
                    editmsg.setText("🔢 Number: <code>" + number + "</code>\n🔰 Section: " + sectTam + " [<code>"
                            + sectEng + "</code>]\n👥 Group: " + grpTam + " [<code>" + grpEng + "</code>]\n💭 Chapter: "
                            + chapTam
                            + " [<code>" + chapEng + "</code>]\n\n<u>🔎 திருக்குறள்:</u>\n\n<b>" + line1 + "\n" + line2
                            + "</b>\n\n<b>📚 பொருள்:</b> " + tamExp + "\n\n\n<u>🚀 Translation:</u>\n<b>" + eng
                            + "</b>\n\n<b>📚 Explanation:</b> " + engExp + ".");
                    conn.disconnect();
                    execute(editmsg);
                } catch (Exception e) {
                    logging(e, msgChatId,
                            update.getMessage().getChat().getTitle(), msgId,
                            username, "https://t.me/c/" + msgChatId.replaceFirst("-100", "") + "/" + msgId);
                }
            }
        }
    }

    public void logging(Exception error, String erchatId, String erChatTitle, String ermsgId, String erUsrId,
            String erLink) {

        try {
            SendMessage log = new SendMessage();
            log.setChatId(getLogGroup());
            log.enableHtml(true);
            log.setText("#ExceptionOn"+getBotUsername()+"\n\nChat: <code>" + erChatTitle + "</code>\nVictim: " + erUsrId
                    + "\nChat ID: <code>" + erchatId + "</code>\nMessage ID: <code>" + ermsgId
                    + "</code>\nTarget: <a href=\"" + erLink + "\">Click</a>\n\nError:\n<code>" + error + "</code>");
            Message msg = new Message();
            msg = execute(log);
            SendMessage onLog = new SendMessage();
            onLog.setChatId(erchatId);
            onLog.setReplyToMessageId(Integer.parseInt(ermsgId));
            onLog.enableHtml(true);
            onLog.setAllowSendingWithoutReply(true);
            onLog.setText("[<a href=\"https://t.me/c/" + getLogGroup().toString().replaceFirst("-100", "") + "/"
                    + msg.getMessageId() + "\">An Exception Occured</a>]\nPlease forward this message to @VinuXD.");
            execute(onLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public String getLogGroup() {
        return logGroup;
    }

    public String getOwnerId() {
        return ownerId;
    }

}