package app;

import java.time.*;

import booking.Reserva;
import restaurantSide.Lamen;
import restaurantSide.Restaurante;
import restaurantSide.Sushi;
import userSide.Usuario;

public class AplicativoReserva {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante("Arcadia Izakaya", "Um restaurante japonês", 3);
        Usuario usuario = new Usuario("Israel", "israel@example.com");

        // Cadastrando pratos
        restaurante.adicionarPrato(new Sushi("Nigiri de Salmão", 18.50));
        restaurante.adicionarPrato(new Sushi("Uramaki de Atum", 21.00));
        restaurante.adicionarPrato(new Lamen("Lámen Shoyu", 32.00));
        restaurante.adicionarPrato(new Lamen("Lámen Missô", 29.00));

        // Apresentação
        System.out.println("\nBem-vindo ao " + restaurante.getNome() + "!");
        restaurante.exibirCardapio();

        // Consulta de mesas
        restaurante.exibirMesasDisponiveis(LocalDate.of(2025, 7, 10), LocalTime.of(19, 30));

        // Reserva
        Reserva reserva1 = new Reserva(usuario, restaurante, 1, LocalDate.of(2025, 7, 10), LocalTime.of(19, 30));
        reserva1.exibirDetalhesReserva();

        restaurante.exibirMesasDisponiveis(LocalDate.of(2025, 7, 10), LocalTime.of(19, 30));
        restaurante.exibirMesasDisponiveis(LocalDate.of(2025, 7, 10), LocalTime.of(21, 30));

     // Adicionando pratos ao pedido do usuário
        usuario.adicionarAoPedido(restaurante.getCardapio().get(0)); // Sushi
        usuario.adicionarAoPedido(restaurante.getCardapio().get(2)); // Lamen

        // Preparando o pedido
        restaurante.prepararPedido(usuario);
        	
        // Avaliação
        restaurante.exibirAvaliacoes();

        restaurante.addAvaliacao(4);
        restaurante.exibirAvaliacoes();

        restaurante.addAvaliacao(5);
        restaurante.exibirAvaliacoes();
        
        restaurante.getCardapio().get(0).addAvaliacao(2);
        restaurante.getCardapio().get(0).exibirAvaliacoes();

        restaurante.getCardapio().get(0).addAvaliacao(4);
        restaurante.getCardapio().get(0).exibirAvaliacoes();
    }
}
