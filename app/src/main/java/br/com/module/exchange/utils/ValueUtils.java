package br.com.module.exchange.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class ValueUtils {

    public static String formatValue(Double valor){
        Locale localeBrazil = new Locale( "pt", "BR" );
        return NumberFormat.getCurrencyInstance(localeBrazil).format(valor);
    }
}
