/*
    INSTITUTO TECNOLÓGICO SUPERIOR DE APATZINGÁN
    INGENIERÍA EN SISTEMAS COMPUTACCIONALES

    LENGUAJE Y AUTÓMATAS II
    UNIDAD 1: ANÁLISIS SEMÁNTICO

    PRÁCTICA: CONSTRUCCIÓN DE UN ANALIZADOR LÉXICO
    CLASE: ANALIZADOR LÉXICO

    ALUMNO: ALONSO DE JESÚS VARGAS RINCÓN
    NO. CONTROL: 19020104

    SEPTIEMBRE DE 2022 
*/

// Bibliotecas necesarias para:
import java.io.File; // Especificar un archivo.
import java.io.FileReader; // Lectura de datos de un determinado archivo.
import java.io.IOException; // Manejo de excepciones en la entrada o salida de datos.
import java.io.BufferedReader; // Lectura de texto.
import java.util.StringTokenizer; // Descomposición de cadenas en tokens.
import java.util.regex.Pattern; // Fabricación de expresiones regulares.
import java.util.regex.Matcher; // Búsqueda de expresiones regulares en una secuencia de caracteres (en este caso, los tokens).


public class AnalizaLexico {
    static int ctaLinea; // Contador de líneas de texto contenido en algún determinado archivo.
    
    // Método para leer un archivo y copiar su contenido.
    public static String leerArchivo() throws IOException{
        // Este objeto de la clase file ayuda a especificar el archivo del cual se desea leer datos.
        File archivo = new File("C:\\Users\\alons\\OneDrive\\Documentos\\academicos\\evidencias\\Septimo Semestre\\Lenguajes y Automatas II\\Practicas\\Compilador\\src\\Prueba.txt");
        FileReader fr = new FileReader(archivo); // Se crea un objeto del archivo que permitirá la lectura de sus datos.
        BufferedReader br = new BufferedReader(fr); // Se lee el archivo.
        
        /*
        Variables para almacenamiento del texto obtenido del archivo.
        - cadena almacenará todo el texto obtenido.
        - linea almacenará una línea a la vez del texto obtenido.
        */
        String cadena = "", linea = "";
        
        /* 
        Ciclo para la obtención y almacenamiento del texto proveniente del archivo:
        En la comparación primero se obtiene una determinada línea de texto del archivo y se 
        corrobora que esta si exista (es decir que su valor no sea nulo).
        */
        while( (linea=br.readLine()) != null){
            cadena += linea + "\n"; // La variable de cadena concatena a su contenido la línea obtenida y luego un salto de línea.
            ctaLinea ++; // El contador incrementa en uno llevando así un conteo de las líneas. 
        }
        
        fr.close(); // Se cierra el acceso a la lectura del archivo.
        return cadena; // El texto del archivo es retornado a quien lo solicite.
    }
    
    /*
    Método que divide todo el texto obtenido 
    del archivo en tokens para después enviarlos 
    a un análisis individual.
    */
    public static void analizar(String cadena){ // Se prevee que la cadena sea la que se obtuvo del contenido del archivo.
        StringTokenizer tokens = new StringTokenizer(cadena); // Descomposición de la cadena en tokens.
        int i = 0;
        
        // Ciclo para un tratamiento individual hacia cada token.
        while(tokens.hasMoreTokens()){ // El ciclo se ejecuta entre tanto que haya tokens disponibles por analizar.
            expRegulares(tokens.nextToken()); // Se toma el token más cercano y se envía a un método que lo comparará con expresiones regulares.
            i++;
        }
    }
    
    // Método para analizar tokens en base a expresiones regulares.
    public static void expRegulares(String token){
        // Patrón o expresión regular 1: Contiene las palabras reservadas del lenguaje de Java.
        Pattern palRes = Pattern.compile("abstract|continue|for|new|switch|"
                                        + "assert|default|goto|package|synchronized|"
                                        + "do|if|private|this|"
                                        + "break|implements|protected|throw|"
                                        + "else|import|public|throws|"
                                        + "case|enum|instanceof|return|transient|"
                                        + "catch|extends|short|try|"
                                        + "final|interface|static|void|"
                                        + "class|finally|strictfp|volatile|"
                                        + "const|float|native|super|while|main");
        
        // Patrón o expresión regular 2: Contiene los tipos de datos primitivos del lenguaje de Java.
        Pattern tipoDato = Pattern.compile("int|float|double|long|byte|boolean|String|char|short");
        
        // Patrón o expresión regular 3: Contiene los símbolos de los operadores aritméticos empleados en Java.
        Pattern operadorAritmetico = Pattern.compile("\\+|\\*|\\/|\\-|\\%");
        
        // Patrón o expresión regular 4: Contiene los símbolos de los operadores lógicos utilizados en Java.
        Pattern operadorLogico = Pattern.compile("\\=\\=|"
                                                + "\\!\\=|"
                                                + "\\<|"
                                                + "\\<\\=|"
                                                + "\\>|"
                                                + "\\>\\=|"
                                                + "\\&\\&|"
                                                + "\\!"
                                                + "\\|\\|");
        
        /*
        Patrón o expresión regular 5: Detecta nombres de idetificadores para variables y 
        constantes en Java.
        
        Su interpretación es la siguiente:
        Se considerarán como identificadores todas aquellas cadenas que:
        - Comiencen con alguna letra minúscula o con alguna letra mayúscula o con un guión bajo(_)
          o con un signo de pesos ($).
        - Contengan además 0 o más caracteres de palabra (letras mayúsculas, minúsculas, números o guión bajo).
        - Contengan además 0 o más sígnos de pesos ($).
        */
        Pattern identificador = Pattern.compile("[a-zA-Z_$]\\w*\\$*");
        
        
        Matcher matPR = palRes.matcher(token);
        Matcher matTD = tipoDato.matcher(token);
        Matcher matOA = operadorAritmetico.matcher(token);
        Matcher matOL = operadorLogico.matcher(token);
        Matcher matId = identificador.matcher(token);
        
        // Búsqueda de resultados en los tokens.
        if(matPR.find()){ // Si se encontró que el token posee una coincidencia con una palabra reservada:
            System.out.println(matPR.group() + "<palabra reservada " + matPR.group().toUpperCase() + " >"); // Se especifica que es palabra reserevada y se indica cuál es.
        }else if(matTD.find()){ // Por el contrario, si se encontró que el token posee una coincidenccia con una palabra referente a un tipo de dato:
            System.out.println(matTD.group() + "<tipo de dato " + matTD.group().toUpperCase() + " >"); // Se especifica que es tipo de dato y se indica cuál es.
        }else if(matId.find()){ // Si no es ninguno de los casos, y se tiene que la palabra corresponde a un identificador de variable o constante:
            System.out.println(matId.group() + "<identificador " + matId.group().toUpperCase() + " >"); // Se especifica que es identificador y se indica cuál es.
        }
        
        
        if(matOA.find()){ // Si se encuentra que el token contiene un símbolo de operador aritmético.
            System.out.println(matOA.group() + "<operador aritmetico " + matOA.group().toUpperCase() + " >"); // Se especifica que es operador aritmético y se indica cuál es.
        }
        
        if(matOL.find()){ // Si se encuentra que el token contiene un símbolo de operador lógico.
            System.out.println(matOL.group() + "<operador logico " + matOL.group().toUpperCase() + " >"); // Se especifica que es operador lógico y se indica cuál es.
        }

    }
}
