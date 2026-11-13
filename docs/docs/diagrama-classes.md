# Diagramas de classes - Projeto RPG - SOLID

Código Mermaid (renderizado automaticamente pelo GitHub). Imagens PNG nesta mesma pasta: diagrama-1-personagens.png, diagrama-2-itens.png e diagrama-3-combate.png.

### Diagrama 1: personagens, habilidades e ações

```mermaid
classDiagram
    class Destrutivel {
        <<interface>>
        +receberDano(int valor, Notificador notificador) void
        +estaDestruido() boolean
        +getNome() String
    }
    class HabilidadeEspecial {
        <<interface>>
        +getChaveHabilidade() String
        +podeUsarHabilidade() boolean
        +usarHabilidade(Destrutivel alvo, Notificador notificador) void
    }
    class Personagem {
        <<abstract>>
        -String nome
        -int vidaMax
        -int vidaAtual
        -int ataqueBase
        -int defesa
        +atacar(Destrutivel alvo, Notificador notificador) void
        +receberDano(int valor, Notificador notificador) void
        +curar(int pontos, Notificador notificador) void
        +mostrarStatus(Notificador notificador) void
        +mostrarResumo(Notificador notificador) void
        +getAtaqueTotal() int
        +getDefesaTotal() int
        +getAcoes() List~Acao~
    }
    class Aliado {
        <<abstract>>
        -Inventario inventario
        -Map~Slot,Equipavel~ equipados
        +equipar(Equipavel novo, Notificador notificador) void
        +descansar(Notificador notificador) void
        +mostrarFicha(Notificador notificador) void
        #acoesEspecificas() List~Acao~
        #restaurarRecurso() void
    }
    class Omori {
        -int foco
    }
    class Aubrey {
        -int coragem
    }
    class Kel {
        -int energia
    }
    class Hero {
        -int suco
        +setAmigos(List~Aliado~ amigos) void
    }
    class Inimigo {
        <<abstract>>
        +getChaveIntroducao() String
        +agir(Destrutivel alvo, Notificador notificador) void
        +getButim() List~Item~
    }
    class ToupeiraBroto
    class CoelhoDaFloresta
    class Chefe {
        <<abstract>>
        -int cargas
    }
    class ExNamoradoEspacial
    class Sweetheart
    class Acao {
        <<interface>>
        +getRotulo(Notificador notificador) String
        +estaDisponivel() boolean
        +executar(Destrutivel alvo, Notificador notificador) void
    }
    class AtaqueBasico
    class UsarHabilidade
    class UsarConsumivel
    class EquiparItem

    Destrutivel <|.. Personagem
    Personagem <|-- Aliado
    Personagem <|-- Inimigo
    Aliado <|-- Omori
    Aliado <|-- Aubrey
    Aliado <|-- Kel
    Aliado <|-- Hero
    Inimigo <|-- ToupeiraBroto
    Inimigo <|-- CoelhoDaFloresta
    Inimigo <|-- Chefe
    Chefe <|-- ExNamoradoEspacial
    Chefe <|-- Sweetheart
    HabilidadeEspecial <|.. Omori
    HabilidadeEspecial <|.. Aubrey
    HabilidadeEspecial <|.. Kel
    HabilidadeEspecial <|.. Hero
    HabilidadeEspecial <|.. Chefe
    Hero o-- Aliado : amigos curados pelo banquete
    Acao <|.. AtaqueBasico
    Acao <|.. UsarHabilidade
    Acao <|.. UsarConsumivel
    Acao <|.. EquiparItem
    Personagem ..> Acao : monta a lista de ações
    AtaqueBasico --> Personagem : dono
    UsarHabilidade --> HabilidadeEspecial : dono
    UsarConsumivel --> Aliado : dono
    EquiparItem --> Aliado : dono
```

### Diagrama 2: itens, inventário e equipamentos

```mermaid
classDiagram
    class Item {
        <<interface>>
        +getChaveNome() String
    }
    class Consumivel {
        <<interface>>
        +podeSerUsadoEm(Personagem alvo) boolean
        +usar(Personagem alvo, Notificador notificador) void
    }
    class Equipavel {
        <<interface>>
        +getSlot() Slot
        +getBonusAtaque() int
        +getBonusDefesa() int
    }
    class Slot {
        <<enumeration>>
        ARMA
        ARMADURA
    }
    class Alimento {
        -String chaveNome
        -int pontosDeCura
    }
    class Arma {
        -int bonusAtaque
    }
    class Armadura {
        -int bonusDefesa
    }
    class Inventario {
        -List~Item~ itens
        +adicionar(Item item) void
        +remover(Item item) void
        +getConsumiveis() List~Consumivel~
        +getEquipaveis() List~Equipavel~
    }
    class Aliado {
        <<abstract>>
    }
    class Inimigo {
        <<abstract>>
    }

    Item <|-- Consumivel
    Item <|-- Equipavel
    Consumivel <|.. Alimento
    Equipavel <|.. Arma
    Equipavel <|.. Armadura
    Equipavel ..> Slot
    Aliado *-- Inventario : composição
    Inventario o-- Item : agregação
    Aliado o-- Equipavel : equipados, um por slot
    Inimigo ..> Item : deixa como butim
```

### Diagrama 3: combate, entrada/saída e idiomas

```mermaid
classDiagram
    class GerenciadorCombate {
        -Entrada entrada
        -Notificador notificador
        -Random sorteio
        -int proximoBeneficiado
        +iniciarAventura(List~Aliado~ aliados, List~Inimigo~ inimigos) boolean
    }
    class Notificador {
        -Saida saida
        -Mensagens mensagens
        +notificar(String chave, Object args) void
        +traduzir(String chave, Object args) String
        +barra(int atual, int max) String
    }
    class Entrada {
        <<interface>>
        +lerNumero() int
    }
    class EntradaConsole {
        -Scanner scanner
    }
    class Saida {
        <<interface>>
        +exibir(String mensagem) void
    }
    class SaidaConsole
    class Mensagens {
        <<interface>>
        +getMensagem(String chave, Object args) String
    }
    class MensagensBase {
        <<abstract>>
        #Map~String,String~ textos
    }
    class MensagensPortugues
    class MensagensIngles
    class Aliado {
        <<abstract>>
    }
    class Inimigo {
        <<abstract>>
    }
    class Acao {
        <<interface>>
    }
    class Main

    Entrada <|.. EntradaConsole
    Saida <|.. SaidaConsole
    Mensagens <|.. MensagensBase
    MensagensBase <|-- MensagensPortugues
    MensagensBase <|-- MensagensIngles
    Notificador --> Saida
    Notificador --> Mensagens
    GerenciadorCombate --> Entrada
    GerenciadorCombate --> Notificador
    GerenciadorCombate --> Aliado
    GerenciadorCombate --> Inimigo
    GerenciadorCombate ..> Acao : monta o menu
    Main ..> GerenciadorCombate : monta e injeta as dependências
```
