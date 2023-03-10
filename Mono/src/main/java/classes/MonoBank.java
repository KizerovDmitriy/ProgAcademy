package classes;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MonoBank {
    private static final int UA_ISO_4217 = 980;
    private Map<Integer, String> currencyMap = new HashMap<>();

    public MonoBank() {
        currencyMap.put(840, "USD");
        currencyMap.put(978, "EUR");
    }

    /***
     *
     * @return JSON String representation
     */
   private String getJson() {
        String json = "";
        try {
            URL url = new URL("https://api.monobank.ua/bank/currency");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()))) {
                while (bf.ready()) {
                    json = bf.readLine();
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
    
    /***
     *
     * @return array of Currency objects
     */
    private Currency[] getCurrencyArray() {
        return new Gson().fromJson(getJson(), Currency[].class);
    }

    /***
     *
     * @param currencyToConvert the currency to which you need to convert
     * @return result of conversion
     */
    public String prettyPrint(String currencyToConvert) {
        StringBuilder sb = new StringBuilder();
        for (Currency currency : getCurrencyArray()) {
            for (Map.Entry<Integer, String> entry : currencyMap.entrySet()) {
                if (entry.getValue().equals(currencyToConvert)) {
                    if (currency.getCurrencyCodeA() == entry.getKey()) {
                        return buildString(sb, currency, entry);
                    }
                }
            }
        }
        return null;
    }

    /***
     *
     * @return exchange rate
     */
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        for (Currency currency : getCurrencyArray()) {
            for (Map.Entry<Integer, String> entry : currencyMap.entrySet()) {
                if (currency.getCurrencyCodeA() == entry.getKey() && currency.getCurrencyCodeB() == UA_ISO_4217) {
                    buildString(sb, currency, entry);
                }
            }
        }
        return sb.toString().trim();
    }

    /***
     *
     * @param sb StringBuilder
     * @param currency Currency object
     * @param entry pair key+value of CurrencyMap
     * @return String representation of exchange rate
     */
    private String buildString(StringBuilder sb, Currency currency, Map.Entry<Integer, String> entry) {
        sb.append(entry.getValue())
                .append(":")
                .append("\n")
                .append("Buy: ")
                .append(currency.getRateBuy())
                .append(" UAH")
                .append("\n")
                .append("Sell: ")
                .append(currency.getRateSell())
                .append(" UAH")
                .append("\n");

        return sb.toString().trim();
    }

    public Map<Integer, String> getCurrencyMap() {
        return currencyMap;
    }

    /***
     * Add new currency if we need
     * @param currencyMap
     */
    public void setCurrencyMap(Map<Integer, String> currencyMap) {
        this.currencyMap = currencyMap;
    }
}
