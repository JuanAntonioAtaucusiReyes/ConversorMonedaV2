package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class BuscarTipoMoneda {

    public double tipoDeMoneda(String baseCode, String targetCode) {
        String apiKey = "f76c1648c89175ccdf01c992"; // Asegúrate de que esta clave sea válida
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCode);

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder().uri(uri).build(), HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Lo sentimos, no pudimos obtener la tasa de conversión. Intenta nuevamente más tarde.");
            }

            Type tipoMapa = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> responseMap = new Gson().fromJson(response.body(), tipoMapa);
            Map<String, Double> conversionRates = (Map<String, Double>) responseMap.get("conversion_rates");

            if (conversionRates == null || !conversionRates.containsKey(targetCode)) {
                throw new RuntimeException("La moneda " + targetCode + " no está disponible. Por favor, revisa el código de moneda.");
            }

            return conversionRates.get(targetCode);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al procesar tu solicitud. Por favor, inténtalo nuevamente.");
        }
    }
}
