package restaurantSide;

public class Sushi extends Prato {
    public Sushi(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public void preparar() {
        System.out.println("🍣 Preparando o sushi: " + this.getNome());
    }
}
