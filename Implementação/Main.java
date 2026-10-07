/**
 * Papel: CLIENT.
 * Usa o Registry para obter cópias e as personaliza, demonstrando
 * que o protótipo original permanece inalterado.
 */
public class Main {

    public static void main(String[] args) {
        KitRegistry registry = new KitRegistry();

        // ---------- Situação 1: protótipos sendo registrados ----------
        titulo("SITUAÇÃO 1 - Registrando protótipos");

        KitViagem internacional = new KitViagem("Kit Internacional", "Internacional", "A definir",
                new Mala("grande", "preta"), "Conferir validade do passaporte", "Mala + necessaire");
        internacional.adicionarItem("Necessaire", 1);
        internacional.adicionarItem("Adaptador universal", 1);
        internacional.adicionarItem("Kit higiene", 1);
        internacional.adicionarItem("Etiqueta de bagagem", 1);

        KitViagem praia = new KitViagem("Kit Praia", "Lazer", "Litoral",
                new Mala("média", "azul"), "Levar protetor FPS 50", "Mala + bolsa térmica");
        praia.adicionarItem("Toalha", 1);
        praia.adicionarItem("Protetor solar", 1);
        praia.adicionarItem("Óculos", 1);

        KitViagem aventura = new KitViagem("Kit Aventura", "Aventura", "Trilha",
                new Mala("mochila 60L", "verde"), "Verificar previsão do tempo", "Mochila impermeável");
        aventura.adicionarItem("Lanterna", 1);
        aventura.adicionarItem("Garrafa térmica", 1);
        aventura.adicionarItem("Kit primeiros socorros", 1);

        registry.registrar("internacional", internacional);
        registry.registrar("praia", praia);
        registry.registrar("aventura", aventura);

        System.out.println("Protótipos registrados: " + registry.listarIds());
        System.out.println();
        System.out.println("PROTÓTIPO");
        System.out.println(registry.obterCopia("praia"));

        // ---------- Situação 2: cópia criada a partir do Registry ----------
        titulo("SITUAÇÃO 2 - Criando cópia a partir do Registry (id = \"praia\")");

        KitViagem copia = registry.obterCopia("praia");
        copia.setNome("Kit Praia - Cliente João");
        System.out.println("CÓPIA (recém-clonada, só o nome foi trocado)");
        System.out.println(copia);

        // ---------- Situação 3: cópia sendo modificada ----------
        titulo("SITUAÇÃO 3 - Personalizando a cópia");

        copia.setDestino("Jericoacoara");              // 1) altera destino
        copia.adicionarItem("Boné", 1);                // 2) adiciona item
        copia.alterarQuantidade("Toalha", 2);          // 3) altera quantidade
        copia.setObservacoes("Cliente prefere hotel em frente à praia"); // 4) altera observação
        copia.getMala().setCor("vermelha");            // 5) altera objeto interno (Mala)

        System.out.println("CÓPIA (personalizada)");
        System.out.println(copia);

        // ---------- Situação 4: protótipo original inalterado ----------
        titulo("SITUAÇÃO 4 - Protótipo original permanece inalterado");

        KitViagem originalNoRegistry = registry.obterCopia("praia");
        System.out.println("PROTÓTIPO ORIGINAL (consultado novamente no Registry)");
        System.out.println(originalNoRegistry);

        // ---------- Atividade 6: o que aconteceria com shallow copy ----------
        titulo("ANÁLISE - Shallow copy (somente demonstrativa)");

        KitViagem rasa = originalNoRegistry.copiaRasaDemonstrativa();
        rasa.setNome("Kit Praia - Cópia RASA");
        rasa.adicionarItem("Boné", 1);       // altera a lista compartilhada
        rasa.getMala().setCor("rosa");       // altera a Mala compartilhada

        System.out.println("Após alterar a cópia RASA, o 'original' usado na comparação ficou assim:");
        System.out.println(originalNoRegistry);
        System.out.println();
        System.out.println("-> Itens e cor da mala do original mudaram junto com a cópia (objetos compartilhados).");
        System.out.println("   O nome não mudou porque String é imutável e setNome apenas troca a referência da cópia.");
        System.out.println();
        System.out.println("Com deep copy (clonar()), o Registry continua intacto:");
        System.out.println(registry.obterCopia("praia"));
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(texto);
        System.out.println("==================================================");
    }
}
