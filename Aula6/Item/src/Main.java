public class Main {
    public static void main(String[] args) {
        // frutas ok
          Fruta maca = new Fruta("Maçã", 10);
          double precoFinalFruta = maca.calcularPrecoFinal(1);
          System.out.println("Preço final - fruta: R$ " + precoFinalFruta);

        // vegetais ok
         Vegetal aspargo = new Vegetal("Aspargos", 5);
         double precoFinalVegetal = aspargo.calcularPrecoFinal(2);
         System.out.println("Preço final - vegetal: R$ " + precoFinalVegetal);

         Carne picanha = new Carne("Calango", 100);
         double precoFinalCarne = picanha.calcularPrecoFinal(2);
         System.out.println("Preço final - carne: R$ " + precoFinalCarne);
    }
}