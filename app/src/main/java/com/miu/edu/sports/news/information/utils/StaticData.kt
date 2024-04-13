package com.miu.edu.sports.news.information.utils

import com.miu.edu.sports.news.information.model.AthletesModel
import com.miu.edu.sports.news.information.model.EventsModel
import com.miu.edu.sports.news.information.model.HistoricalArchivesModel
import com.miu.edu.sports.news.information.model.NewsModel
import com.miu.edu.sports.news.information.model.SportsModel

object StaticData {
     val sportsList = mutableListOf<SportsModel>()
     val newsList = mutableListOf<NewsModel>()
     val athletesList = mutableListOf<AthletesModel>()
     val eventsList = mutableListOf<EventsModel>()
     val historicalArchivesList = mutableListOf<HistoricalArchivesModel>()

    fun sportsData(): MutableList<SportsModel> {
        sportsList.clear()
        val measureData = SportsModel(
            "Measure",
            "Car Racing",
            "Measure sports are ones where the goal is to min/max a certain value. The most common measure sports are races." +
                    "You win a measured sport by running the fastest, lifting the most, throwing the farthest, etc.\n" +
                    "\n" +
                    "The objective is to get the record, and there is no “best” performance or ceiling other than the current world record."
        )
        val precisionData = SportsModel(
            "Precision",
            "Golf",
            "Precision sports are like Measured sports, but with the goal of accuracy. The goal is to hit a target, not a min/max with no bound. You win a precision sport by being the most precise with your movement."
        )
        val spectacleData = SportsModel(
            "Spectacle",
            "Gymnastics",
            "This is not to say that all non-Spectacle sports are perfectly objectively scored. For example, many Play sports have referees who make subjective calls for fouls. However, individual judges have fairly wide subjective leeway in Spectacle sports. Would a “perfect” judge with all information always make the same calls?\n" +
                    "\n" +
                    "Because Spectacle sports require subjective judging, casual enjoyment of the activity is not a sport. Skating in Central Park with your friends is not a sport unless you count how many triple lutzes you each do."
        )

        val combatData = SportsModel(
            "Combat",
            "Judo",
            "Combat sports encompass most martial arts or fighting sports, where you win when the other person loses. They involve some direct physical strength contest or grappling, so fencing is a combat sport but tennis is not. These are often 1-v-1 sports, except maybe tug-of-war.\n" +
                    "\n" +
                    "What makes fencing a Combat sport and tennis a Play sport? There’s something visceral about Combat sports, like they are one layer closer to actual fighting. Please provide a counterexample if you disagree."
        )
        val playData = SportsModel(
            "Play",
            "Football",
            "Play is the leftover, catch-all category which encompasses all sports that don’t fit the other definitions. The top 9 most popular sports in the world are all Play sports, and they are perhaps the most natural expression of “play” even as kids. You can complete the sentence “I play ___” with any Play sport.\n" +
                    "\n" +
                    "Play sports are hard to define on their own and not in contrast to the other four sports categories. Most Play sports have more complex rules than Measure, Precision, Spectacle, or Combat sports. Play sports are often, but not always team sports — both singles and doubles tennis are Play sports."
        )

        sportsList.add(measureData)
        sportsList.add(precisionData)
        sportsList.add(spectacleData)
        sportsList.add(combatData)
        sportsList.add(playData)
        return sportsList
    }

    fun newsData(): MutableList<NewsModel> {
        newsList.clear()
        val firstNews = NewsModel(
            "Sheffield United's McBurnie grabs late equaliser in 2-2 Chelsea draw",
            "SHEFFIELD, England, April 7 (Reuters) - Sheffield United striker Ollie McBurnie snatched a stoppage-team equaliser to secure a 2-2 draw at home to Chelsea in his side's battle for Premier League survival on Sunday.\n" +
                    "The Blades remain bottom of the table on 16 points with seven games left, while Chelsea are ninth on 44 after extending their unbeaten run to seven league games.",
            "https://www.usnews.com/object/image/0000018e-b996-d6f8-a9cf-bdbe01b70000/4415d0235d3041b39ec35fa3a64d1dc7Britain_Soccer_Premier_League_60664.jpg?update-time=1712507860000&size=responsive970"
        )

        val secondNews = NewsModel(
            "NHL roundup: Connor McDavid nears milestone as Oilers trim Flames",
            "Ryan Nugent-Hopkins scored once in a three-point game, while Leon Draisaitl and Connor Brown also scored for the Oilers, who are 5-1-1 in their last seven games. Goaltender Calvin Pickard made 33 saves.\n" +
                    "Advertisement · Scroll to continue\n" +
                    "\n" +
                    "Report this ad\n" +
                    "With his helpers, McDavid has 99 assists, pulling him that much closer to becoming only the fourth player",
            "https://www.reuters.com/resizer/v2/https%3A%2F%2Fcloudfront-us-east-2.images.arcpublishing.com%2Freuters%2FKZMSP2L6DRJI5FRNOD4H7APSSY.jpg?auth=56bcc9f98307684ed50a913a3e0705500044fdf95be78e7873c8be1b99349fae&width=1920&quality=80"
        )
        val thirdNews = NewsModel(
            "Clippers, Cavaliers tuning up for playoffs",
            "The Clippers (49-28) will aim for a complete effort when they face the visiting Cleveland Cavaliers (46-32) on Sunday.\n" +
                    "Los Angeles has won five of its past six games and holds the fourth spot in the Western Conference standings. Cleveland is in third place in the East and will look to avoid its third straight loss after falling 116-97 to the host Los Angeles Lakers on Saturday.",
            "https://www.reuters.com/resizer/v2/https%3A%2F%2Fcloudfront-us-east-2.images.arcpublishing.com%2Freuters%2FN4JINTWX2JNAJKXH53GRQJDYAA.jpg?auth=4f8b93ceca92c0526be2a63f369187b47f62e8d2da97c446d258ea3829be8d70&width=1080&quality=80"
        )
        val fourthNews = NewsModel(
            "Collins reaches Charleston final with 12th successive tour win",
            "The American world number 22, who won her first Masters 1000 title at the Miami Open last month, will face fourth seed Daria Kasatkina on Sunday.\n" +
                    "\"It's been so fun coming out here and battling. It's what I do. I just love to battle,\" the 30-year-old Collins said as she tied her career-best performance from 2021.",
            "https://www.reuters.com/resizer/v2/JHHETK75WJN3ZCWMTKL2AZXAPY.jpg?auth=ea5d200715958c3fd82b7ec6418402a709b295e4ed9395769ca9a146679a5d48&width=1080&quality=80"
        )

        newsList.add(firstNews)
        newsList.add(secondNews)
        newsList.add(thirdNews)
        newsList.add(fourthNews)
        return newsList
    }

    fun athletesData() : MutableList<AthletesModel>{
        athletesList.clear()
        val firstData = AthletesModel("Usain Bolt","Track and Field","Jamaica",
            "100m - 9.58s","8","Usain Bolts hold the world record for both the 100 meters and 200 meters")

        val secondData = AthletesModel("Simone Biles","Gymnastics","United States",
            "N/A","7","Simone Biles is considered one of the greatest Gymnastics of all time.")

        val thirdData = AthletesModel("Michael Phelps","Swimming","United States",
            "23 Olympic Gold Medals","28","Michael Phelps holds the record for the most olympic gold medals won by any athlete.")

        val fourthData = AthletesModel("Serena Williams","Tennis","United States",
            "23 Grand Slam singles Titles","23","Serena Williams is one of the most dominant tennis players in history")

        val test = AthletesModel("Serena Williams","Tennis","United States",
            "23 Grand Slam singles Titles","23","Serena Williams is one of the most dominant tennis players in history")

        athletesList.add(firstData)
        athletesList.add(secondData)
        athletesList.add(thirdData)
        athletesList.add(fourthData)
        athletesList.add(test)
        return athletesList
    }

    fun eventsData(): MutableList<EventsModel> {
        eventsList.clear()
        val firstEvents = EventsModel(
            "FIBA Basketball World Cup Qualifiers",
            "Qualifying games for the 2025 FIBA Basketball",
            "10/03/2024"
        )

        val secondEvents = EventsModel(
            "Indian Premier League (IPL)","Twenty20 cricket League in india","15/03/2024"        )
        eventsList.add(firstEvents)
        eventsList.add(secondEvents)
        return eventsList
    }

    fun historicalArchivesData(): MutableList<HistoricalArchivesModel> {
        historicalArchivesList.clear()
        val firstHistorical = HistoricalArchivesModel(
            "First Modern Olympic Games",
            "The first modern Olympic Games were held in Athens, Greece, and marked the revival of the ancient Olympic Games.",
            "April 6, 1869"
        )

        val secondHistorical = HistoricalArchivesModel(
            "Jesse Owens at the 1936 Olympic","American athlete Jesse Owens won four gold medals in track and field events at the 1936 Berlin Olympic, defying Adolf Hitler's Aryan supremacy ideology.","August 3, 1936")
        historicalArchivesList.add(firstHistorical)
        historicalArchivesList.add(secondHistorical)
        return historicalArchivesList
    }
}