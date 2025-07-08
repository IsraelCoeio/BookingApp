package restaurantSide;

import java.util.ArrayList;
import java.util.List;

public abstract class Prato implements Avaliacao {
    private String nome;
    private double preco;
	private List<Integer> avaliacoes = new ArrayList<>();

    public Prato(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    @Override
    public void addAvaliacao(int nota) {
        this.avaliacoes.add(nota);
    }

    @Override
    public double getAvaliacao() {
        if (avaliacoes.isEmpty()) {
            return 0.0; // Nenhuma avaliação
        }

        int soma = 0;
        for (int nota : avaliacoes) {
            soma += nota;
        }
        return (double) soma / avaliacoes.size();
    }
    
    public void exibirAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            System.out.println("Ainda não há avaliações. Seja o primeiro a avaliar :)");
        } else {
            System.out.printf("Avaliação de %s: %.2f\n", nome, getAvaliacao());
        }
    }

    public abstract void preparar();

    public void descrever() {
        System.out.printf("🍱 %s - R$ %.2f\n", nome, preco);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
