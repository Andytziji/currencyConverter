import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Convertir la respuesta JSON a un objeto JsonObject usando Gson
            Gson gson = new Gson();
            JsonObject exchangeRates = gson.fromJson(response.body().string(), JsonObject.class);

            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            while (opcion != 9) {
                // Mostrar las opciones de conversión
                mostrarMenu();

                try {
                    opcion = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            convertirUSDToEUR(exchangeRates);
                            break;
                        case 2:
                            convertirEURToUSD(exchangeRates);
                            break;
                        case 3:
                            convertirUSDToMXN(exchangeRates);
                            break;
                        case 4:
                            convertirMXNToUSD(exchangeRates);
                            break;
                        case 5:
                            convertirUSDToBRL(exchangeRates);
                            break;
                        case 6:
                            convertirBRLToUSD(exchangeRates);
                            break;
                        case 7:
                            convertirUSDToARS(exchangeRates);
                            break;
                        case 8:
                            convertirARSToUSD(exchangeRates);
                            break;
                        case 9:
                            System.out.println("Saliendo del programa.");
                            break;
                        default:
                            System.out.println("Opción inválida. Por favor, intente de nuevo.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número del 1 al 9.");
                    scanner.next(); // Limpiar el scanner
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nSeleccione la operación que desea realizar:");
        System.out.println("1. Dólares (USD) a Euros (EUR)");
        System.out.println("2. Euros (EUR) a Dólares (USD)");
        System.out.println("3. Dólares (USD) a Pesos Mexicanos (MXN)");
        System.out.println("4. Pesos Mexicanos (MXN) a Dólares (USD)");
        System.out.println("5. Dólares (USD) a Reales Brasileños (BRL)");
        System.out.println("6. Reales Brasileños (BRL) a Dólares (USD)");
        System.out.println("7. Dólares (USD) a Pesos Argentinos (ARS)");
        System.out.println("8. Pesos Argentinos (ARS) a Dólares (USD)");
        System.out.println("9. Salir");
        System.out.print("Ingrese la opción elegida (1 a 9): ");
    }

    private static void convertirUSDToEUR(JsonObject exchangeRates) {
        double cantidadUSD = ingresarCantidad("USD");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("EUR").getAsDouble();
        double resultado = cantidadUSD * tasaConversion;
        mostrarResultado("USD", "EUR", cantidadUSD, resultado);
    }

    private static void convertirEURToUSD(JsonObject exchangeRates) {
        double cantidadEUR = ingresarCantidad("EUR");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("EUR").getAsDouble();
        double resultado = cantidadEUR / tasaConversion;
        mostrarResultado("EUR", "USD", cantidadEUR, resultado);
    }

    private static void convertirUSDToMXN(JsonObject exchangeRates) {
        double cantidadUSD = ingresarCantidad("USD");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("MXN").getAsDouble();
        double resultado = cantidadUSD * tasaConversion;
        mostrarResultado("USD", "MXN", cantidadUSD, resultado);
    }

    private static void convertirMXNToUSD(JsonObject exchangeRates) {
        double cantidadMXN = ingresarCantidad("MXN");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("MXN").getAsDouble();
        double resultado = cantidadMXN / tasaConversion;
        mostrarResultado("MXN", "USD", cantidadMXN, resultado);
    }

    private static void convertirUSDToBRL(JsonObject exchangeRates) {
        double cantidadUSD = ingresarCantidad("USD");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("BRL").getAsDouble();
        double resultado = cantidadUSD * tasaConversion;
        mostrarResultado("USD", "BRL", cantidadUSD, resultado);
    }

    private static void convertirBRLToUSD(JsonObject exchangeRates) {
        double cantidadBRL = ingresarCantidad("BRL");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("BRL").getAsDouble();
        double resultado = cantidadBRL / tasaConversion;
        mostrarResultado("BRL", "USD", cantidadBRL, resultado);
    }

    private static void convertirUSDToARS(JsonObject exchangeRates) {
        double cantidadUSD = ingresarCantidad("USD");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("ARS").getAsDouble();
        double resultado = cantidadUSD * tasaConversion;
        mostrarResultado("USD", "ARS", cantidadUSD, resultado);
    }

    private static void convertirARSToUSD(JsonObject exchangeRates) {
        double cantidadARS = ingresarCantidad("ARS");
        double tasaConversion = exchangeRates.getAsJsonObject("rates").get("ARS").getAsDouble();
        double resultado = cantidadARS / tasaConversion;
        mostrarResultado("ARS", "USD", cantidadARS, resultado);
    }

    private static double ingresarCantidad(String monedaOrigen) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad en " + monedaOrigen + ": ");
        return scanner.nextDouble();
    }

    private static void mostrarResultado(String monedaOrigen, String monedaDestino, double cantidadOrigen, double cantidadDestino) {
        System.out.printf("%.2f %s equivale a %.2f %s\n", cantidadOrigen, monedaOrigen, cantidadDestino, monedaDestino);
    }
}
