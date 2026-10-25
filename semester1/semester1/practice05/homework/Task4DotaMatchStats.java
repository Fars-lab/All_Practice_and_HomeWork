package semester1.semester1.practice05.homework;

import java.util.Scanner;
import java.util.Locale;

public class Task4DotaMatchStats {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] heroes = {"Pudge", "Invoker", "Juggernaut", "Crystal Maiden", "Earthshaker"};
        int[] kills = {8, 12, 10, 2, 4};
        int[] deaths = {6, 3, 2, 9, 5};
        int[] assists = {14, 9, 8, 18, 16};
        int[] netWorth = {14500, 21200, 24800, 9800, 12300};
        double[] kdaRatios = new double[5];

        int totalKills = 0;
        int totalNetWorth = 0;
        int mvp = 0;
        for (int i = 0; i < heroes.length; i++) {
            kdaRatios[i] = (double) (kills[i] + assists[i]) / Math.max(1, deaths[i]);
            totalKills += kills[i];
            totalNetWorth += netWorth[i];
            if (kdaRatios[i] > kdaRatios[mvp]) {
                mvp = i;
            }
        }

        System.out.println("========================================================================");
        System.out.println("                       🏆 DOTA 2: POST-MATCH REPORT");
        System.out.println("========================================================================");
        System.out.printf("%-17s| %-11s| %-10s| %-10s| %s%n",
                "Герой", "K / D / A", "Net Worth", "KDA Ratio", "Kill Participation");
        System.out.println("------------------------------------------------------------------------");
        for (int i = 0; i < heroes.length; i++) {
            double kp = (double) (kills[i] + assists[i]) / totalKills * 100;
            String kda = kills[i] + " / " + deaths[i] + " / " + assists[i];
            System.out.printf(Locale.US, "%-17s| %-11s| %-10s| %-10.2f| %.2f%%%n",
                    heroes[i], kda, String.format(Locale.US, "%,d", netWorth[i]), kdaRatios[i], kp);
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Загальна кількість вбивств команди: " + totalKills);
        System.out.println("Сумарний Net Worth команди:          " + String.format(Locale.US, "%,d", totalNetWorth) + " золота");
        System.out.printf(Locale.US, "⭐ НАЙКРАЩИЙ ГРАВЕЦЬ МАТЧУ (MVP):    %s (KDA: %.2f)%n", heroes[mvp], kdaRatios[mvp]);
        System.out.println("========================================================================");

        scanner.close();
    }
}
