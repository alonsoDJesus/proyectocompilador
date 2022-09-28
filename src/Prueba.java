/*
    INSTITUTO TECNOLÓGICO SUPERIOR DE APATZINGÁN
    INGENIERÍA EN SISTEMAS COMPUTACCIONALES

    LENGUAJE Y AUTÓMATAS II
    UNIDAD 1: ANÁLISIS SEMÁNTICO

    PRÁCTICA: CONSTRUCCIÓN DE UN ANALIZADOR LÉXICO
    CLASE: PRUEBA DEL ANALIZADOR LÉXICO

    ALUMNO: ALONSO DE JESÚS VARGAS RINCÓN
    NO. CONTROL: 19020104

    SEPTIEMBRE DE 2022 
*/

import java.io.IOException; // Biblioteca necesaria para la captura de excepciones de entrada y salida de datos.

public class Prueba {
    public static void main(String[] args) throws IOException{
        
        // Se obtiene el texto contenido de un archivo mediante el método "leerArchivo" de la clase "AnalizaLexico".
        String cadena = AnalizaLexico.leerArchivo();
        
        // Impresión para mostrar resultados.
        System.out.println("Cadena analizada:\n"+cadena+"\n"+"Tokens detectados:");
        
        /*
          El método "analizar" de la clase "AnalizaLexico" se encargará de tomar la cadena,
          descomponerla en tokens y enviar estos últimos a un análisis individual en base
          a espresiones regulares.
        */
        AnalizaLexico.analizar(cadena);
        
        // Impresión para mostrar resultados.
        System.out.println("\nNumero de lineas:\n" + AnalizaLexico.ctaLinea);
    }
}
