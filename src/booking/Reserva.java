package booking;

import java.time.LocalDate;
import java.time.LocalTime;

import restaurantSide.Restaurante;
import userSide.Usuario;

public class Reserva {

    private Usuario usuario;
    private Restaurante restaurante;
    private int indiceMesa;
    private LocalDate data;
    private LocalTime hora;

    public Reserva(Usuario usuario, Restaurante restaurante, int indiceMesa, LocalDate data, LocalTime hora) {
        if (restaurante.mesaDisponivel(indiceMesa, data, hora)) {
            this.usuario = usuario;
            this.restaurante = restaurante;
            this.indiceMesa = indiceMesa;
            this.data = data;
            this.hora = hora;

            restaurante.registrarReserva(this);

            System.out.println("✅ " + usuario.getNome() + " reservou a mesa #" + (indiceMesa + 1) +
                               " no restaurante " + restaurante.getNome() +
                               " para o dia " + data + " às " + hora);
        } else {
            System.out.println("❌ A mesa #" + (indiceMesa + 1) + " não está disponível em " + restaurante.getNome());
        }
    }

    public void exibirDetalhesReserva() {
        System.out.println("📋 Detalhes da Reserva:");
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Restaurante: " + restaurante.getNome());
        System.out.println("Mesa: #" + (indiceMesa + 1));
        System.out.println("Data: " + data);
        System.out.println("Hora: " + hora);
    }

    // Getters e Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public int getIndiceMesa() {
        return indiceMesa;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setIndiceMesa(int indiceMesa) {
        this.indiceMesa = indiceMesa;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
