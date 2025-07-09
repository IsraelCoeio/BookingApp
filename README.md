# BookingApp - Java Application <img src="BookingApp.png" width="135" align="left">
Este projeto implementa um sistema simples para gerenciamento de reservas e pedidos em um restaurante.
Construído na disciplina de Programação Orientada a Objetos do Curso de Sistemas e Mídias Digitais da Universidade Federal do Ceará em 2025.

## Contribuidores

- [Israel Coelho](https://github.com/IsraelCoeio) <img src="https://github.com/IsraelCoeio.png" width="50" align="left">
<br> <br> <br>
- [Wesley Barbosa](https://github.com/WesleyBarbosaMR) <img src="https://github.com/WesleyBarbosaMR.png" width="50" align="left">
<br> <br> <br>

## Funcionalidades deste sistema
> Este projeto simula um sistema de reservas e pedidos em um restaurante, utilizando os conceitos de orientação a objetos, exemplificando especificamente **herança**, **classes abstratas**, **interfaces** e **polimorfismo**.
> O programa irá simular o funcionamento de um restaurante, permitindo realizar reservas, adicionar pratos ao pedido, e visualizar avaliações.

- Cadastro de pratos no restaurante.
- Reserva de mesas no restaurante.
- Adição de pratos ao pedido do usuário.
- Status de Preparação do pedido.
- Avaliação do restaurante.

### Diagrama de Classe


## Conceitos Demonstrados

### 1. Herança

A herança pode ser vista nas classes `Sushi` e `Lamen`, por exemplo, que herdam da classe `Prato`.
Ambas as classes compartilham os atributos `nome` e `preco` de `Prato`, mas implementam o método `preparar()` de maneira específica para cada uma.

#### Exemplo ([Classe Prato.java](src/restaurantSide/Prato.java) -> [Classe Sushi.java](src/restaurantSide/Sushi.java) OU [Classe Lamen.java](src/restaurantSide/Lamen.java)):
**Primeiro temos na classe Prato:**
```java
public abstract class Prato {
    private String nome;
    private double preco;

    public Prato(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public abstract void preparar(); // Método abstrato, a ser implementado nas subclasses

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
````

**Então na classe `Sushi`, que herda de `Prato`, temos:**

```java
public class Sushi extends Prato {
    public Sushi(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public void preparar() {
        System.out.println("🍣 Preparando o sushi: " + this.getNome());
    }
}
```
**Da mesma maneira na classe `Lamen`, temos:**
```java
public class Lamen extends Prato {
    public Lamen(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public void preparar() {
        System.out.println("🍜 Preparando o lámen: " + this.getNome());
    }
}
```

### 2. Classes Abstratas

A classe `Prato` é uma classe abstrata, ou seja, não pode ser instanciada diretamente.
Ela apenas define um método abstrato `preparar()`, visto acima, que deve ser implementado nas classes que herdam dela, como `Sushi`, `Lamen` e outros pratos futuros.

[Classe Prato.java](src/restaurantSide/Prato.java)
```java
    public abstract void preparar(); // Método abstrato, a ser implementado nas subclasses
```

[Classe Sushi.java](src/restaurantSide/Sushi.java)
```java
    @Override
    public void preparar() {
        System.out.println("🍣 Preparando o sushi: " + this.getNome());
    }
```
[Classe Lamen.java](src/restaurantSide/Lamen.java)
```java
    @Override
    public void preparar() {
        System.out.println("🍜 Preparando o lámen: " + this.getNome());
    }
```

### 3. Interfaces
A interface `Avaliação` foi implementada para ser usada por várias classes para fornecer uma maneira de obter e manipular as avaliações.
São definidos dois métodos: `adicionarAvaliacao()` para adicionar uma nota e `obterAvaliacao()` para calcular e retornar a média das avaliações (no caso de `Restaurante`) ou permitindo que cada prato tenha uma avaliação associada (no caso de `Prato`).

**Exemplo [Avaliacao.java](src/restaurantSide/Avaliacao.java)**

```java
public interface Avaliacao {
	void addAvaliacao(int nota);
    double getAvaliacao();
}
```
**[Restaurante.java](src/restaurantSide/Restaurante.java):**

```java
public class Restaurante implements Avaliacao {

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

    ...

    public void exibirAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            System.out.println("Ainda não há avaliações. Seja o primeiro a avaliar :)");
        } else {
            System.out.printf("Avaliação de %s: %.2f\n", nome, getAvaliacao());
        }
    }

    ...

    // Getters e Setters

    ...

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }
    ...
}
```

**[Prato.java](src/restaurantSide/Prato.java):**
```java
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
    ...
}
```

### 4. Polimorfismo

O polimorfismo também pode ser exemplificado no uso do método `preparar()`, que é chamado tanto para objetos do tipo `Sushi` quanto `Lamen`, mas o comportamento de preparação será diferente, dependendo do tipo do prato.
Ou seja, o método `preparar()` será chamado para cada objeto `Prato` presente no pedido do usuário, e, dependendo do tipo do prato (Sushi ou Lamen), o comportamento de preparação será diferente.

**Exemplo [Classe Restaurante.java](src/restaurantSide/Restaurante.java):**

```java
public void prepararPedido(Usuario usuario) {
    System.out.println("\n👨‍🍳 Preparando pedido de " + usuario.getNome() + ":");

    if (usuario.getPedido().isEmpty()) {
        System.out.println("⚠️ Nenhum prato no pedido.");
    } else {
        for (Prato prato : usuario.getPedido()) {
            prato.preparar(); // Polimorfismo: O método preparar será diferente para cada tipo de prato
        }
    }
}
```

## Como rodar o projeto
Para testar o nosso projeto basta seguir os seguintes passos:

1. Clone o repositório;
2. Compile o projeto;
3. Execute a classe `AplicativoReserva`.

