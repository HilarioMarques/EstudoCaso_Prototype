# EstudoCaso_Prototype
 # Sistema de Criação de Kits de Viagem Personalizados (Padrão Prototype)

Estudo de Caso 4 — Padrões de Projeto (UESPI, Ciência da Computação).

## Estrutura de arquivos

| Arquivo | Papel no padrão | Responsabilidade |
|---|---|---|
| `Prototipo.java` | **Prototype** | Interface com a operação `clonar()`. |
| `KitViagem.java` | **Concrete Prototype** | Kit de viagem que sabe se clonar (construtor de cópia com deep copy). |
| `Mala.java` | Objeto interno | Objeto mutável contido no kit; copiado em profundidade. |
| `Item.java` | Objeto interno | Item mutável (nome, quantidade) da lista do kit; copiado em profundidade. |
| `KitRegistry.java` | **Prototype Registry** | `HashMap<String, KitViagem>` com os modelos; entrega sempre cópias. |
| `Main.java` | **Client** | Registra protótipos, pede cópias ao Registry, personaliza e demonstra a independência. |

## Como a implementação funciona

- **Protótipos:** três kits-modelo diferentes (`internacional`, `praia`, `aventura`), registrados no `KitRegistry`.
- **Registry:** guarda os modelos em um `HashMap`. O método `obterCopia(id)` localiza o modelo pelo identificador e retorna `clonar()`. O Registry nunca devolve a referência ao protótipo armazenado, e `registrar()` também guarda uma cópia, isolando-o do código chamador.
- **Clonagem:** feita com **construtor de cópia** (`new KitViagem(original)`), exposto via `clonar()`.
- **Personalização:** a cópia pode ter destino, observações, itens (adicionar/remover), quantidades e a cor da mala alterados, sem afetar o original.

### Por que construtor de cópia e não `Object.clone()`

- `Object.clone()` faz shallow copy por padrão; a deep copy exigiria sobrescrever e clonar manualmente cada campo mutável, de qualquer forma.
- Exige `Cloneable` e tratamento de `CloneNotSupportedException`, e tem problemas conhecidos de design (campos `final`, herança).
- O construtor de cópia deixa explícito, linha a linha, o que é copiado e o que é compartilhado.

### Shallow copy vs. deep copy (o que é compartilhado e o que é copiado)

| Atributo do `KitViagem` | Tipo | Tratamento | Justificativa |
|---|---|---|---|
| `nome`, `tipoViagem`, `destino`, `observacoes`, `embalagem` | `String` | Referência compartilhada | `String` é imutável; "alterar" cria outra String e só afeta a referência da cópia. |
| `mala` | `Mala` | **Deep copy** (`new Mala(original.mala)`) | Objeto mutável. Se compartilhado, mudar a cor da mala da cópia mudaria a do protótipo. |
| `itens` | `List<Item>` | **Deep copy** (nova lista + `new Item(item)` para cada um) | Copiar só a lista ainda compartilharia os `Item`; alterar a quantidade de um item na cópia mudaria o original. |

### O que aconteceria com apenas uma shallow copy

A cópia e o original apontariam para o **mesmo** objeto `Mala` e a **mesma** lista de `Item`. Adicionar o "Boné" ou trocar a cor da mala na cópia alteraria também o protótipo, destruindo o propósito do padrão. O `Main` demonstra isso com o método `copiaRasaDemonstrativa()` (existente apenas para fins didáticos).

Objetos internos que precisaram ser copiados para garantir a independência: **`Mala`** e **a lista de `Item` (com cada `Item`)**.

## Como executar

Requisito: **JDK 8 ou superior** (`java -version` e `javac -version` devem funcionar).

Com todos os arquivos `.java` na mesma pasta (sem pacotes):

```bash
javac *.java
java Main
```

Se o console exibir acentos incorretamente (comum no Windows), use:

```bash
javac -encoding UTF-8 *.java
java -Dfile.encoding=UTF-8 Main
```

## Saída esperada (resumo)

1. **Situação 1:** os três protótipos são registrados e o Kit Praia é exibido.
2. **Situação 2:** uma cópia é obtida do Registry com `obterCopia("praia")`.
3. **Situação 3:** a cópia é personalizada (destino, item "Boné", quantidade da toalha, observação, cor da mala).
4. **Situação 4:** o protótipo consultado novamente no Registry continua com os dados originais.
5. **Análise:** a demonstração da shallow copy mostra o original sendo alterado junto com a cópia; em seguida, mostra que `clonar()` (deep copy) preserva o original.
