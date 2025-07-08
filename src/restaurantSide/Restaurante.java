package restaurantSide;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import booking.Reserva;
import userSide.Usuario;

public class Restaurante {

    private String nome;
    private String descricao;
    private List<Prato> cardapio = new ArrayList<>();
    private List<Integer> avaliacoes = new ArrayList<>();
    private int quantidadeMesas;
    private List<Reserva> reservas = new ArrayList<>();

    public Restaurante(String nome, String descricao, int quantidadeMesas) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeMesas = quantidadeMesas;
    }

    public boolean mesaDisponivel(int indiceMesa, LocalDate data, LocalTime hora) {
        if (indiceMesa >= 0 && indiceMesa < this.quantidadeMesas) {
            return reservas.stream().noneMatch(reserva ->
                reserva.getIndiceMesa() == indiceMesa &&
                reserva.getData().equals(data) &&
                (
                    (hora.isAfter(reserva.getHora()) && hora.isBefore(reserva.getHora().plusHours(2))) ||
                    (reserva.getHora().isAfter(hora) && reserva.getHora().isBefore(hora.plusHours(2))) ||
                    reserva.getHora().equals(hora)
                )
            );
        }
        return false;
    }

    public void registrarReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        this.reservas.removeIf(r -> r.equals(reserva));
    }

    public void exibirMesasDisponiveis(LocalDate data, LocalTime hora) {
        System.out.println("Mesas disponíveis em " + nome + ", dia " + data + ", às " + hora);
        for (int i = 0; i < this.quantidadeMesas; i++) {
            if (mesaDisponivel(i, data, hora)) {
                System.out.println("Mesa #" + (i + 1) + " está livre.");
            }
        }
    }

    public void adicionarAvaliacao(int nota) {
        this.avaliacoes.add(nota);
    }

    public void exibirAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            System.out.println("Ainda não há avaliações. Seja o primeiro a avaliar :)");
        } else {
            int soma = 0;
            for (int nota : avaliacoes) {
                soma += nota;
            }
            double media = (double) soma / avaliacoes.size();
            System.out.printf("Avaliação de %s: %.2f\n", nome, media);
        }
    }

    public void adicionarPrato(Prato prato) {
        cardapio.add(prato);
    }

    public void exibirCardapio() {
        System.out.println("🍽️ Cardápio de " + nome + ":");
        for (Prato prato : cardapio) {
            prato.descrever();
        }
    }
    
    public void prepararPedido(Usuario usuario) {
        System.out.println("\n👨‍🍳 Preparando pedido de " + usuario.getNome() + ":");

        if (usuario.getPedido().isEmpty()) {
            System.out.println("⚠️ Nenhum prato no pedido.");
        } else {
            for (restaurantSide.Prato prato : usuario.getPedido()) {
                prato.preparar();
            }
        }
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeMesas() {
        return quantidadeMesas;
    }

    public void setQuantidadeMesas(int quantidadeMesas) {
        this.quantidadeMesas = quantidadeMesas;
    }

    public List<Prato> getCardapio() {
        return cardapio;
    }

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
