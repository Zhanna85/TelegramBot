package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "My_game_about_a_hacker_cat_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "8162596225:AAF_6lv5FlxXEjDycR3PBk91T8xzWYmUaVY"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // TODO: основной функционал бота будем писать здесь
        //отобразить сообщаение о начале игы - нужно взломать холодильник
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT,
                    Map.of("Взлом холодильника", "step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Взять сосиску! +20 славы", "step_2_btn",
                            "Взять рыбку! + 20 славы", "step_2_btn",
                            "Скинуть банку с огурцами! +20 славы", "step_2_btn"));
        }

        // взломаем робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT,
                    Map.of("Взлом робота пылесоса", "step_3_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Отправить робот пылесос за едой! +30 славы", "step_4_btn",
                            "Покататься на роботе пылесосе! + 30 славы", "step_4_btn",
                            "Убежать от робота пылесоса! +30 славы", "step_4_btn"));
        }

        // взломаем камеру go-pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT,
                    Map.of("Надеть и включить камеру GoPro", "step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Бегать по крышам и снимать все подряд! +40 славы", "step_6_btn",
                            "Снять на камеру GoPro себя! +40 славы", "step_6_btn",
                            "Снять на камеру GoPro дворовых котов! +40 славы", "step_6_btn"));
        }

        // взламаем компьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT,
                    Map.of("Взломай компьютер и залить видео", "step_7_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("Похвастаться перед дворовыми котами", "step_8_btn"));
        }

        // хвастаемся дворовым котоам
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendPhotoMessageAsync("С:\\Projects Demo\\images\\final_pic.jpg");
            sendTextMessageAsync(FINAL_TEXT);
        }

        if (getMessageText().equals("/glory")) {
            int glory = getUserGlory();
            sendTextMessageAsync("количество очков славы - " + glory);
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}