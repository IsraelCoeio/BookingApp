package userSide;

import java.util.ArrayList;
import java.util.List;

import restaurantSide.Prato;

public class Usuario {
    private String nome;
    private String email;
    private List<Prato> pedido = new ArrayList<>();

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void exibirInformacoes() {
        System.out.println("Usuário: " + nome + " | Email: " + email);
    }

    public void adicionarAoPedido(Prato prato) {
        pedido.add(prato);
        System.out.println("🍽️ " + prato.getNome() + " adicionado ao pedido de " + nome + ".");
    }

    public List<Prato> getPedido() {
        return pedido;
    }

    public void limparPedido() {
        pedido.clear();
        System.out.println("🧼 Pedido de " + nome + " foi limpo.");
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
