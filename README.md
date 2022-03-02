[![wtf?](https://raw.githubusercontent.com/VinuXD/Portfolio-assets/master/Projects/thirukkural.png)]()

# Thirukkural Bot

A telegram bot that provides thirukkural in tamil its english translations with all information!</br>Liked my work? Don't forget to :star: the repo :)

[![java](https://img.shields.io/badge/Made%20with-JAVA-orange.svg?style=flat)]()
[![stars](https://img.shields.io/github/stars/vinuxd/thirukkural-bot?style=flat)]()
[![Website vinuxd.github.io/Thirukkural-Bot](https://img.shields.io/website-up-down-green-red/https/vinuxd.github.io/Thirukkural-Bot.svg?style=flat)](https://github.com/vinuxd/Thirukkural-Bot-Web)
[![size](https://img.shields.io/github/repo-size/vinuxd/thirukkural-bot?style=flat)]()
[![issues](https://img.shields.io/github/issues/vinuxd/thirukkural-bot?style=flat)]()
[![license](https://img.shields.io/github/license/vinuxd/thirukkural-bot?style=flat&color=yellow)]()


## Features

* Get Random Thirukkural.
* Get Thirukkural with its number.
* Get a daily digest of thirukkural in every groups.[EXAMPLE](https://t.me/ThirukkuralEveryday)
* [Click here to start the bot](https://telegram.me/ThirukkuralRobot?start)


## Deployment

* First clone this repository to your local machine.

```git
git clone https://github.com/VinuXD/Thirukkural-Bot.git
```

* Rename `.env.example` to `.env`
* Fill [.env](#variables) variables.
* Navigate to `src/main/java/me/vinuxd/ThirukkuralBot.java`
* Run `ThirukkuralBot.java`
* It starts on your local server. 
* Go to your telegram bot and send [/start](https://t.me/ThirukkuralRobot?start) to get started.

```diff
- Note: Support for deploying in VPS server will not be given.
```


## Variables

```bash
# Bot Username without @
BOT_USERNAME=

# Bot Token. Get one from @BotFather
BOT_TOKEN=

# Add @MissRose_Bot in a dedicated private group
# Then send /id. (Eg: -100*********)
LOG_GROUP=
```

## FAQs

### How can I schedule bot to send Thirukkural daily in my Group?

* **Its Automated!** All you need is *patience* btw. Just add [Thirukkural Bot](https://t.me/ThirukkuralRobot?startgroup=true) to your group and wait untill **6.30 AM IST**.
* Psst🤫! Rarely very rarely, If you really doesn't get a daily digest, Kick and re-add the bot.


### How can I opt out from daily digest?

* **NO!** You can't, Coz there is *no protocol* at our end for opting out. If you don't want to receive daily digest, just **remove the bot** from your group.
* If you really *don't want* to get daily digest and want [Thirukkural Bot](https://t.me/ThirukkuralRobot) **that badly?** Head to the [Support Chat](https://t.me/VINUsChat).


### I have no Groups in common with Thirukkural Bot still can I get daily digest?

* **Absolutely!** Join this [Dedicated Channel](https://t.me/ThirukkuralEveryday) which is *maintained by me*, to get daily digest.

---

Still unclear? [Support Chat](https://t.me/VINUsChat) is for you!.

## License

![GPLv3 LOGO](https://gnu.org/graphics/gplv3-127x51.png)

```txt
Copyright © 2022 VINU
Thirukkural-Bot is a free software licensed under GPL V3.0

Being Open Source doesn't mean you can just make a copy and change anything 
and release it.
Read the following carefully,

1. You must provide the copy with the original software or with instructions 
on how to obtain original software, should clearly state all changes, should
clearly disclose full source code, should include same license
and all copyrights should be retained.

2. In simple words, You can ONLY use the source code for `Open Source` Project 
under `GPL v3.0` or later with all your source code CLEARLY DISCLOSED on any code 
hosting platform like GitHub, with clear INSTRUCTIONS on how to obtain the original 
software, should clearly STATE ALL CHANGES made and should RETAIN all copyrights.
Use of this software under any "non-free" license is NOT permitted.
```


## Credits

* [Rubenlagus](https://github.com/rubenlagus) for his awesome [TelegramBots](https://github.com/rubenlagus/telegrambots) library.
* OFC [Me](https://github.com/vinuxd).


## Changelogs

Visit the [Telegram Channel](https://t.me/BotUpdatesXD) for updates.


## TODOs

1. [x] Schedule bot to send kural in every groups at a specific time.
2. [x] Write program to get kural with its number.
3. [x] Add Threads to make bot faster.
4. [x] Improve logging in console & telegram.

```diff
! This repository is deprecated and moved to a private repository.
```
