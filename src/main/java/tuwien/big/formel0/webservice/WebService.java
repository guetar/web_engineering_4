package tuwien.big.formel0.webservice;

import tuwien.big.formel0.webservice.TournamentType.Players;
import tuwien.big.formel0.webservice.TournamentType.Players.Player;
import tuwien.big.formel0.webservice.TournamentType.Rounds;
import tuwien.big.formel0.webservice.TournamentType.Rounds.Round;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class WebService {

    private static String username;
    private static Date birthDate;
    private static String gender;
    private static long duration;
    private static String winner;
    private static String uuid;

    public static void setUsername(String username) {
        WebService.username = username;
    }

    public static void setBirthDate(String birthDate) {
        if (birthDate != null) {
            try {
                WebService.birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(birthDate);
            } catch (ParseException pe) {
                System.err.println(pe.getStackTrace());
            }
        } else {
            WebService.birthDate = new Date();
        }
    }

    public static void setGender(String gender) {
        WebService.gender = gender.equals("m") ? "MALE" : "FEMALE";
    }

    public static void setDuration(long duration) {
        WebService.duration = duration;
    }

    public static void setWinner(String winner) {
        WebService.winner = winner;
    }

    public static void setUuid(String uuid) {
        WebService.uuid = uuid;
    }

    public static String getUuid() {
        return uuid;
    }

    public static void sendHighScore() {
        ObjectFactory factory = new ObjectFactory();

        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar date = null;
        XMLGregorianCalendar birth = null;

        try {
            calendar.setTime(new Date());
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            calendar.setTime(birthDate);
            birth = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException ex) {
            System.err.println(ex);
        }

        HighScoreRequestType request = factory.createHighScoreRequestType();
        TournamentType tournament = factory.createTournamentType();
        tournament.setStartDate(date);
        tournament.setEndDate(date);
        tournament.setRegistrationDeadline(date);
        request.setTournament(tournament);
        request.setUserKey("34EphAp2C4ebaswu");

        Players players = factory.createTournamentTypePlayers();
        Player player = factory.createTournamentTypePlayersPlayer();
        player.setUsername(username);
        player.setDateOfBirth(birth);
        player.setGender(gender);
        players.getPlayer().add(player);

        Rounds rounds = factory.createTournamentTypeRounds();
        Round round = factory.createTournamentTypeRoundsRound();
        round.setNumber(0);
        GameType game = factory.createGameType();
        game.setDate(date);
        game.setStatus("finished");
        game.setDuration(BigInteger.valueOf(duration));
        game.setWinner(winner);
        game.setPlayers(factory.createGameTypePlayers());
        round.getGame().add(game);
        rounds.getRound().add(round);

        tournament.setPlayers(players);
        tournament.setRounds(rounds);

        try {
            uuid = new PublishHighScoreService().getPublishHighScorePort().publishHighScore(request);
            System.out.println(uuid);
        } catch (tuwien.big.formel0.webservice.Failure ex) {
            System.out.println(ex.getFaultInfo().getDetail());
        }
    }
}
