package semester1.semester1.practice02.homework;

public class Task1CurrencyExchange {
    static void main() {
        double usdAmount = 250.0;
        final double EXCHANGE_RATE = 41.25;
        final double COMMISION_PERCENT = 1.5; // 1.5%
        double totalUAH = usdAmount * EXCHANGE_RATE;
        double commissionUah = totalUAH * (COMMISION_PERCENT / 100);
        double netAmountUah = totalUAH - commissionUah;
        long roundedAmouthUAH = Math.round(netAmountUah);
        System.out.println("================ КВИНТАЦІЯ ОБМІНУ ================");
        System.out.println("Сума до обміну:              " + usdAmount + " USD");
        System.out.println("Курс валют:                  " +  EXCHANGE_RATE + " UAH/USD");
        System.out.println("Нараховано до комісії:       " + totalUAH + (" UAH"));
        System.out.println("Комісія банку (1.5%):        " + commissionUah + (" UAH"));
        System.out.println("-------------------------------------------------");
        System.out.println("Чиста сума до видачі:        "+ netAmountUah + (" UAH"));
        System.out.println("До видачі готівкою:          " + roundedAmouthUAH + (" UAH"));
        System.out.println("==================================================");
    }

    }


