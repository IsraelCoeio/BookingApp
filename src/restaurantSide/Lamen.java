package restaurantSide;

public class Lamen extends Prato {
    public Lamen(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public void preparar() {
        System.out.println("🍜 Preparando o lámen: " + this.getNome());
    }
}
