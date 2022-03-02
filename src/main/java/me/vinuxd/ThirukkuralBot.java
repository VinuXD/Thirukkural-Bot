/*
Copyright © 2022 VINU
Thirukkural-Bot is a free software licensed under GPL V3.0

Being Open Source doesn't mean you can just make a copy and change anything and release it.
Read the following carefully,

1. You must provide the copy with the original software or with instructions on how to obtain original software,
should clearly state all changes, should clearly disclose full source code, should include same license
and all copyrights should be retained.

2. In simple words, You can ONLY use the source code for `Open Source` Project under `GPL v3.0` or later
with all your source code CLEARLY DISCLOSED on any code hosting platform like GitHub, with clear INSTRUCTIONS on
how to obtain the original software, should clearly STATE ALL CHANGES made and should RETAIN all copyrights.
Use of this software under any "non-free" license is NOT permitted.
*/

package me.vinuxd;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

/**
 * @author VinuXD
 * @site vinuxd.github.io
 */

public class ThirukkuralBot extends TelegramLongPollingBot {

    Dotenv dotenv = new DotenvBuilder().ignoreIfMissing().load();
    String botUsername = dotenv.get("BOT_USERNAME");
    String botToken = dotenv.get("BOT_TOKEN");
    String logGroup = dotenv.get("LOG_GROUP");
    String ownerId = dotenv.get("OWNER_ID");

    public static void main(final String[] args) {

        System.out.println("Bot Starting..");
        try {
            final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new ThirukkuralBot());
        } catch (final TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Bot Started.");
    }

    @Override
    public void onUpdateReceived(final Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            final String username = "<a href=\"tg://user?id=" + update.getMessage().getFrom().getId() + "\">"
                    + update.getMessage().getFrom().getFirstName().replaceAll("[^a-zA-Z0-9]", " ") + "</a>";
            final String startText = "Hello " + username
                    + ",\nI can send you thirukkural its meaning, translations with additional information.\nClick /help to learn more!";
            final String helpText = "Hello " + username
                    + ",\nHere are the possible commands I can help with.\n\n/start - To Start me\n/help - Probably this message\n/kural - To get random Thirukural.\n\nThat's all for now.";

            final String msgChatId = update.getMessage().getChatId().toString();
            final int msgReplyId = update.getMessage().getMessageId();
            final String msgId = update.getMessage().getMessageId().toString();

            if (update.getMessage().getText().equals("/start")
                    || update.getMessage().getText().equals("/start@" + getBotUsername())) {

                try {

                    final SendMessage message = new SendMessage();
                    message.setChatId(msgChatId);
                    message.setReplyToMessageId(msgReplyId);
                    message.enableHtml(true);
                    message.setAllowSendingWithoutReply(true);
                    message.setText(startText);

                    final InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    final List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    final List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
                    final List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
                    final List<InlineKeyboardButton> rowInline3 = new ArrayList<>();

                    final InlineKeyboardButton button1 = new InlineKeyboardButton();
                    button1.setUrl("https://github.com/VinuXD/Thirukkural-Bot");
                    button1.setText("👨‍💻 Source Code");

                    final InlineKeyboardButton button2 = new InlineKeyboardButton();
                    button2.setUrl("t.me/" + getBotUsername() + "?startgroup=0");
                    button2.setText("⚡ Add me in Groups");

                    final InlineKeyboardButton button3 = new InlineKeyboardButton();
                    button3.setUrl("tg://user?id=" + getOwnerId());
                    button3.setText("Made with ❤ & JAVA");

                    final InlineKeyboardButton button4 = new InlineKeyboardButton();
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
                    final Message msg = execute(message);
                    final SendMessage logStart = new SendMessage();
                    logStart.setChatId(getLogGroup());
                    logStart.enableHtml(true);
                    logStart.setText("#NewUserOn" + getBotUsername() + "\n\nName: " + username + "\nUser ID: <code>"
                            + update.getMessage().getFrom().getId() + "</code>\nStarted on: <code>" + msg.getChatId()
                            + "</code>");
                    execute(logStart);
                } catch (final Exception e) {
                    logging(e, msgChatId,
                            update.getMessage().getChat().getTitle(), msgId,
                            username, "https://t.me/c/" + msgChatId.replaceFirst("-100", "") + "/" + msgId);
                }

            } else if (update.getMessage().getText().equals("/help")
                    || update.getMessage().getText().equals("/help@" + getBotUsername())) {

                try {
                    final SendMessage message = new SendMessage();
                    message.setChatId(msgChatId);
                    message.enableHtml(true);
                    message.setReplyToMessageId(msgReplyId);
                    message.setAllowSendingWithoutReply(true);
                    message.setText(helpText);
                    execute(message);
                } catch (final Exception e) {
                    logging(e, msgChatId,
                            update.getMessage().getChat().getTitle(), msgId,
                            username, "https://t.me/c/" + msgChatId.replaceFirst("-100", "") + "/" + msgId);
                }
            }

            else if (update.getMessage().getText().equals("/kural")
                    || update.getMessage().getText().equals("/kural@" + getBotUsername())) {

                try {
                    String response = "";
                    final Random random = new Random();
                    final SendMessage message = new SendMessage();
                    message.setChatId(msgChatId);
                    message.enableHtml(true);
                    message.setReplyToMessageId(msgReplyId);
                    message.setAllowSendingWithoutReply(true);
                    message.setText("<code>Processing...</code>");
                    Message msg = new Message();
                    msg = execute(message);
                    final String apiUrl = "https://api-thirukkural.vercel.app/api?num=" + random.nextInt(1329 + 1);
                    final URL url = new URL(apiUrl);
                    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    final Scanner sc = new Scanner(url.openStream(), StandardCharsets.UTF_8);
                    while (sc.hasNext()) {
                        response += sc.nextLine();
                    }
                    sc.close();
                    final JSONArray jsonArray = new JSONArray("[" + response + "]");
                    final JSONObject object = jsonArray.getJSONObject(0);
                    final EditMessageText editmsg = new EditMessageText();
                    editmsg.setChatId(msgChatId);
                    editmsg.setMessageId(msg.getMessageId());
                    editmsg.enableHtml(true);
                    final int number = object.getInt("number");
                    final String sectTam = object.getString("sect_tam");
                    final String sectEng = object.getString("sect_eng");
                    final String grpTam = object.getString("chapgrp_tam");
                    final String grpEng = object.getString("chapgrp_eng");
                    final String chapTam = object.getString("chap_tam");
                    final String chapEng = object.getString("chap_eng");
                    final String line1 = object.getString("line1");
                    final String line2 = object.getString("line2");
                    final String tamExp = object.getString("tam_exp");
                    final String eng = object.getString("eng");
                    final String engExp = object.getString("eng_exp");
                    editmsg.setText("🔢 Number: <code>" + number + "</code>\n🔰 Section: " + sectTam + " [<code>"
                            + sectEng + "</code>]\n👥 Group: " + grpTam + " [<code>" + grpEng + "</code>]\n💭 Chapter: "
                            + chapTam
                            + " [<code>" + chapEng + "</code>]\n\n<u>🔎 திருக்குறள்:</u>\n\n<b>" + line1 + "\n" + line2
                            + "</b>\n\n<b>📚 பொருள்:</b> " + tamExp + "\n\n\n<u>🚀 Translation:</u>\n<b>" + eng
                            + "</b>\n\n<b>📚 Explanation:</b> " + engExp + ".");
                    conn.disconnect();
                    execute(editmsg);
                } catch (final Exception e) {
                    logging(e, msgChatId,
                            update.getMessage().getChat().getTitle(), msgId,
                            username, "https://t.me/c/" + msgChatId.replaceFirst("-100", "") + "/" + msgId);
                }
            }
        }
    }

    public void logging(final Exception error, final String erchatId, final String erChatTitle, final String ermsgId,
            final String erUsrId,
            final String erLink) {

        try {
            final SendMessage log = new SendMessage();
            log.setChatId(getLogGroup());
            log.enableHtml(true);
            log.setText("#ExceptionOn" + getBotUsername() + "\n\nChat: <code>" + erChatTitle + "</code>\nVictim: "
                    + erUsrId
                    + "\nChat ID: <code>" + erchatId + "</code>\nMessage ID: <code>" + ermsgId
                    + "</code>\nTarget: <a href=\"" + erLink + "\">Click</a>\n\nError:\n<code>" + error + "</code>");
            Message msg = new Message();
            msg = execute(log);
            final SendMessage onLog = new SendMessage();
            onLog.setChatId(erchatId);
            onLog.setReplyToMessageId(Integer.parseInt(ermsgId));
            onLog.enableHtml(true);
            onLog.setAllowSendingWithoutReply(true);
            onLog.setText("[<a href=\"https://t.me/c/" + getLogGroup().toString().replaceFirst("-100", "") + "/"
                    + msg.getMessageId() + "\">An Exception Occured</a>]\nPlease forward this message to @VinuXD.");
            execute(onLog);
        } catch (final Exception e) {
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