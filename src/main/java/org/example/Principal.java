package org.example;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        BuscarTipoMoneda consulta = new BuscarTipoMoneda();
        Scanner teclado = new Scanner(System.in);
        int eleccion = 0;

        while (true) {
            System.out.println("**********************************************");
            System.out.println("¡Bienvenido al conversor de moneda! :) \n");
            System.out.println("**********************************************");
            System.out.println("""
                1. Dólar           ==> Peso argentino
                2. Peso argentino  ==> Dólar
                3. Dólar           ==> Real brasileño
                4. Real brasileño  ==> Dólar
                5. Dólar           ==> Peso colombiano
                6. Peso colombiano ==> Dólar
                7. Salir""");
            System.out.println("**********************************************");

            System.out.print("Elija una opción válida: ");
            eleccion = teclado.nextInt();
            if (eleccion == 7) {
                break;
            }

            System.out.print("Ingresa el valor a convertir: ");
            double cantidad = teclado.nextDouble(); // Cambiar a double para permitir decimales

            String baseCode = "", targetCode = "";
            switch (eleccion) {
                case 1 -> {
                    baseCode = "USD";
                    targetCode = "ARS";
                }
                case 2 -> {
                    baseCode = "ARS";
                    targetCode = "USD";
                }
                case 3 -> {
                    baseCode = "USD";
                    targetCode = "BRL";
                }
                case 4 -> {
                    baseCode = "BRL";
                    targetCode = "USD";
                }
                case 5 -> {
                    baseCode = "USD";
                    targetCode = "COP";
                }
                case 6 -> {
                    baseCode = "COP";
                    targetCode = "USD";
                }
                default -> {
                    System.out.println("El valor ingresado no es válido, por favor elija una opción de 1 a 7.");
                    continue; // Salta a la siguiente iteración si la opción es inválida
                }
            }
            try {
                double tasaConversion = consulta.tipoDeMoneda(baseCode, targetCode);
                double valorFinal = cantidad * tasaConversion; // Cambiar a double para mayor precisión
                System.out.printf("Has convertido %.2f %s a %.2f %s.%n", cantidad, baseCode, valorFinal, targetCode);

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("***************************************************");
        System.out.println("¡Cuídate, hasta la próxima!");
        teclado.close(); // Cerrar el scanner al final
    }
}
