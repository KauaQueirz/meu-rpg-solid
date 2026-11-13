# Projeto RPG - SOLID

RPG de batalhas em turnos para o **console**, desenvolvido em **Java** para a disciplina de Paradigmas de Programação. O objetivo do projeto é aplicar **Programação Orientada a Objetos** e os cinco princípios **SOLID** em uma aplicação funcional.

**Autor:** Kauã Pires (trabalho individual)

## O jogo

Três heróis enfrentam, em sequência, um **Goblin** e o chefão **Smaug (Dragão)**. A cada turno, o jogador escolhe pelo terminal a ação de cada herói. O inimigo ataca um herói vivo, sorteado. Se todos os heróis caírem, é derrota; se os inimigos caírem, é vitória.

| Personagem | Vida | Ataque | Defesa | Habilidade especial | Recurso |
|---|---|---|---|---|---|
| Guerreiro (Aragorn) | 45 | 9 | 4 | Golpe Brutal | Vigor (15) |
| Mago (Luna) | 30 | 7 | 1 | Bola de Fogo | Mana (30) |
| Arqueiro (Legolas) | 35 | 8 | 2 | Chuva de Flechas (dois disparos) | Flechas (12) |
| Goblin | 25 | 6 | 1 | nenhuma | nenhum |
| Dragão (Smaug) | 80 | 14 | 3 | Sopro de Fogo | 3 cargas |

**Itens e inventário:** cada herói começa com 2 Poções de Cura, e o Aragorn leva uma Espada de Ferro (+4 de ataque). Equipamentos precisam ser equipados (gasta um turno). O Goblin deixa como butim uma Armadura de Couro (+2 de defesa). O jogo está disponível em **Português** e **English** (escolha no início).

## Como compilar e executar

**Requisito:** JDK 17 ou superior (`javac -version` para conferir).

```bash
git clone https://github.com/KauaQueirz/projeto-rpg-solid.git
cd projeto-rpg-solid
```

Compilar:

```bash
# Linux, macOS ou Git Bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
```

```powershell
# Windows (PowerShell)
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

Executar:

```bash
java -cp out rpg.app.Main
```

> Se os acentos aparecerem estranhos no Windows, execute com `java -Dfile.encoding=UTF-8 -cp out rpg.app.Main`.

**Como jogar:** no início, digite `1` (Português) ou `2` (English). Em cada turno, digite o número da ação desejada e aperte Enter. Ações sem recurso aparecem como "indisponível".

## Estrutura do projeto

```
projeto-rpg-solid/
├── src/rpg/
│   ├── app/            # Main (ponto de entrada e montagem das dependências)
│   ├── domain/
│   │   ├── contract/   # Destrutivel, HabilidadeEspecial
│   │   ├── model/      # Personagem, Heroi, Guerreiro, Mago, Arqueiro, Inimigo, Goblin, Dragao
│   │   ├── acao/       # Acao, AtaqueBasico, UsarHabilidade, UsarConsumivel, EquiparItem
│   │   ├── item/       # Item, Consumivel, Equipavel, Slot, PocaoCura, Arma, Armadura, Inventario
│   │   └── event/      # Notificador
│   ├── i18n/           # Mensagens, MensagensBase, MensagensPortugues, MensagensIngles
│   ├── presentation/   # Entrada, EntradaConsole, Saida, SaidaConsole
│   └── service/        # GerenciadorCombate
├── docs/               # Diagramas de classes
├── screenshots/        # Capturas de tela da execução
└── README.md
```

## Conceitos de POO aplicados

- **Abstração:** `Personagem`, `Heroi` e `Inimigo` são classes abstratas; `Destrutivel`, `Acao`, `Item` e outras são interfaces.
- **Encapsulamento:** os atributos são `private`. A vida só muda por `receberDano()` e `curar()`, que garantem as regras (nunca abaixo de 0 nem acima do máximo).
- **Herança:** `Guerreiro`, `Mago` e `Arqueiro` herdam de `Heroi`; `Goblin` e `Dragao` herdam de `Inimigo`; ambos herdam de `Personagem`.
- **Polimorfismo:** o combate trata todos como `Personagem`/`Heroi`/`Inimigo`. Cada classe responde de seu jeito a `getAcoes()`, `usarHabilidade()` e `agir()`.
- **Composição e agregação:** o `Heroi` possui seu `Inventario` (composição); o `Inventario` guarda `Item`s que existem de forma independente, como o butim deixado por um inimigo (agregação).

## Diagramas de classes

Os diagramas estão em [Mermaid](https://mermaid.js.org/), que o GitHub renderiza automaticamente. A mesma versão está em [`docs/diagrama-classes.md`](docs/diagrama-classes.md). Também há versões em imagem: [diagrama 1](docs/diagrama-1-personagens.png), [diagrama 2](docs/diagrama-2-itens.png) e [diagrama 3](docs/diagrama-3-combate.png).

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
        +getAtaqueTotal() int
        +getDefesaTotal() int
        +getAcoes() List~Acao~
    }
    class Heroi {
        <<abstract>>
        -Inventario inventario
        -Map~Slot,Equipavel~ equipados
        +equipar(Equipavel novo, Notificador notificador) void
        +getInventario() Inventario
        #acoesEspecificas() List~Acao~
    }
    class Guerreiro {
        -int vigor
    }
    class Mago {
        -int mana
    }
    class Arqueiro {
        -int flechas
    }
    class Inimigo {
        <<abstract>>
        +agir(Destrutivel alvo, Notificador notificador) void
        +getButim() List~Item~
    }
    class Goblin
    class Dragao {
        -int cargas
    }
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
    Personagem <|-- Heroi
    Personagem <|-- Inimigo
    Heroi <|-- Guerreiro
    Heroi <|-- Mago
    Heroi <|-- Arqueiro
    Inimigo <|-- Goblin
    Inimigo <|-- Dragao
    HabilidadeEspecial <|.. Guerreiro
    HabilidadeEspecial <|.. Mago
    HabilidadeEspecial <|.. Arqueiro
    HabilidadeEspecial <|.. Dragao
    Acao <|.. AtaqueBasico
    Acao <|.. UsarHabilidade
    Acao <|.. UsarConsumivel
    Acao <|.. EquiparItem
    Personagem ..> Acao : monta a lista de ações
    AtaqueBasico --> Personagem : dono
    UsarHabilidade --> HabilidadeEspecial : dono
    UsarConsumivel --> Heroi : dono
    EquiparItem --> Heroi : dono
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
    class PocaoCura {
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
    class Heroi {
        <<abstract>>
    }
    class Inimigo {
        <<abstract>>
    }

    Item <|-- Consumivel
    Item <|-- Equipavel
    Consumivel <|.. PocaoCura
    Equipavel <|.. Arma
    Equipavel <|.. Armadura
    Equipavel ..> Slot
    Heroi *-- Inventario : composição
    Inventario o-- Item : agregação
    Heroi o-- Equipavel : equipados, um por slot
    Inimigo ..> Item : deixa como butim
```

### Diagrama 3: combate, entrada/saída e idiomas

```mermaid
classDiagram
    class GerenciadorCombate {
        -Entrada entrada
        -Notificador notificador
        -Random sorteio
        +iniciarAventura(List~Heroi~ herois, List~Inimigo~ inimigos) boolean
    }
    class Notificador {
        -Saida saida
        -Mensagens mensagens
        +notificar(String chave, Object args) void
        +traduzir(String chave, Object args) String
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
    class Heroi {
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
    GerenciadorCombate --> Heroi
    GerenciadorCombate --> Inimigo
    GerenciadorCombate ..> Acao : monta o menu
    Main ..> GerenciadorCombate : monta e injeta as dependências
```

## Princípios SOLID: onde e como foram aplicados

### S: Responsabilidade Única (Single Responsibility)

Cada classe tem um único motivo para mudar:

- `SaidaConsole` é a **única** classe que conhece o `System.out`, e `EntradaConsole` é a única que conhece o `Scanner`.
- `Personagem` e suas subclasses cuidam apenas das regras (vida, dano, habilidades). Elas **não imprimem nada**: apenas avisam o `Notificador` (`notificador.notificar("damage_received", ...)`).
- `Notificador` apenas liga o domínio à tela: pega o texto no idioma certo (`Mensagens`) e envia para a `Saida`.
- `GerenciadorCombate` cuida só da ordem dos turnos.
- `Inventario` cuida só de guardar itens.

### O: Aberto/Fechado (Open/Closed)

Dá para **estender** sem **modificar** o que já existe:

- `Goblin` e `Dragao` foram adicionados como novas subclasses de `Inimigo`, sem alterar nenhuma classe existente.
- O menu do jogador é montado a partir de `heroi.getAcoes()`. As ações `UsarConsumivel` e `EquiparItem` entraram no jogo sem que o `turnoDoHeroi` precisasse conhecê-las: basta implementar `Acao`.
- Um novo idioma é uma nova subclasse de `MensagensBase` (como `MensagensIngles`), sem mexer no código do jogo.
- Um novo item é uma nova classe que implementa `Consumivel` ou `Equipavel` (por exemplo, uma `PocaoMana`).

### L: Substituição de Liskov (Liskov Substitution)

- O `GerenciadorCombate` usa qualquer `Heroi` e qualquer `Inimigo` sem saber qual é o tipo concreto: `Guerreiro`, `Mago` e `Arqueiro` entram na mesma lista e funcionam igual.
- `Inimigo.agir()` funciona para `Goblin` e para `Dragao`, mesmo que só um deles tenha habilidade especial.
- `Heroi` sobrescreve `getAtaqueTotal()` e `getDefesaTotal()` apenas **somando** os bônus de equipamentos, sem quebrar o contrato da classe base.
- Nenhuma subclasse lança exceção por "não saber fazer" algo herdado: quem não tem habilidade não herda esse método (veja ISP).

### I: Segregação de Interfaces (Interface Segregation)

As interfaces são pequenas e específicas:

- `HabilidadeEspecial` é implementada **somente** por quem tem habilidade (`Guerreiro`, `Mago`, `Arqueiro` e `Dragao`). O `Goblin` não é obrigado a ter métodos vazios.
- `Consumivel` e `Equipavel` são separadas: uma poção não precisa saber de bônus de ataque, e uma espada não precisa saber "ser usada".
- `Entrada` e `Saida` são contratos distintos: quem só exibe texto não precisa saber ler.
- `Destrutivel` contém apenas o que um alvo precisa oferecer, e por isso um baú ou portão poderia ser atacado do mesmo jeito.

### D: Inversão de Dependência (Dependency Inversion)

- O `GerenciadorCombate` depende de abstrações (`Entrada`, `Notificador`, `Heroi`, `Inimigo`, `Acao`, `Item`) e **nunca** de `Goblin`, `Mago`, `Scanner` ou `System.out`.
- O `Notificador` recebe uma `Saida` e uma `Mensagens` (interfaces) **pelo construtor**. Trocar o console por outra tela, ou o português por outro idioma, não exige alterar o domínio.
- `Personagem.atacar()` recebe um `Destrutivel`, e não um `Personagem`.
- A classe `Main` é a **única** que escolhe as implementações concretas (`SaidaConsole`, `EntradaConsole`, `MensagensPortugues`, `Goblin`...) e as injeta nas demais.

## Capturas de tela

**Escolha de idioma e início da batalha**

![Início do jogo](screenshots/01-inicio.png)

**Menu de ações e combate em turnos**

![Combate](screenshots/02-combate.png)

**Equipando um item e usando uma habilidade**

![Equipamento e habilidade](screenshots/03-equipamento.png)

**Vitória sobre o Dragão**

![Vitória](screenshots/04-vitoria.png)
