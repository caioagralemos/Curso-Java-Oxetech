public class Arrays {
    public static void main(String[] args) {
       // arrays em JAVA
        int [] arrayDeNumeros;
        arrayDeNumeros = new int[5]; // criando, arrays em java tem tamanho pré definido

        // preenchendo array
        for (int g = 0; g < 5; g++) {
            arrayDeNumeros[g] = g*2;
        }

        // iterando array
        for (int g = 0; g < arrayDeNumeros.length; g++) {
            System.out.println("Indice:" + g + " Valor:" + arrayDeNumeros[g]);
        }

        // for each
        for (int g:arrayDeNumeros) {
            System.out.println(g);
        }
    }
}