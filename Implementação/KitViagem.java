import java.util.ArrayList;
import java.util.List;

/**
 * Papel: CONCRETE PROTOTYPE.
 * Kit de viagem que sabe se clonar. A clonagem é feita por CONSTRUTOR DE CÓPIA
 * (em vez de Object.clone()), pois dá controle explícito sobre o que é copiado
 * em profundidade e evita as armadilhas de Cloneable/CloneNotSupportedException.
 *
 * Atributos:
 *  - String (nome, tipoViagem, destino, observacoes, embalagem): imutáveis, podem ser compartilhados.
 *  - Mala: objeto interno mutável  -> DEEP COPY.
 *  - List<Item>: coleção mutável de objetos mutáveis -> DEEP COPY (nova lista + novos Items).
 */
public class KitViagem implements Prototipo<KitViagem> {
    private String nome;
    private String tipoViagem;
    private String destino;
    private Mala mala;
    private List<Item> itens;
    private String observacoes;
    private String embalagem;

    public KitViagem(String nome, String tipoViagem, String destino,
                     Mala mala, String observacoes, String embalagem) {
        this.nome = nome;
        this.tipoViagem = tipoViagem;
        this.destino = destino;
        this.mala = mala;
        this.observacoes = observacoes;
        this.embalagem = embalagem;
        this.itens = new ArrayList<>();
    }

    /** Construtor de cópia: realiza a DEEP COPY. */
    public KitViagem(KitViagem original) {
        this.nome = original.nome;
        this.tipoViagem = original.tipoViagem;
        this.destino = original.destino;
        this.observacoes = original.observacoes;
        this.embalagem = original.embalagem;
        this.mala = new Mala(original.mala);              // novo objeto Mala
        this.itens = new ArrayList<>();                   // nova lista
        for (Item item : original.itens) {
            this.itens.add(new Item(item));               // novo objeto para cada Item
        }
    }

    @Override
    public KitViagem clonar() {
        return new KitViagem(this);
    }

    /**
     * SOMENTE PARA DEMONSTRAÇÃO (Atividade 6): cópia rasa (shallow copy).
     * Mala e lista de itens são compartilhadas com o original.
     * Não deve ser usada no sistema real.
     */
    public KitViagem copiaRasaDemonstrativa() {
        KitViagem c = new KitViagem(nome, tipoViagem, destino, this.mala, observacoes, embalagem);
        c.itens = this.itens; // mesma referência
        return c;
    }

    // ---- Personalização ----
    public void adicionarItem(String nome, int quantidade) {
        itens.add(new Item(nome, quantidade));
    }

    public boolean removerItem(String nome) {
        return itens.removeIf(i -> i.getNome().equalsIgnoreCase(nome));
    }

    public boolean alterarQuantidade(String nome, int novaQuantidade) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                i.setQuantidade(novaQuantidade);
                return true;
            }
        }
        return false;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipoViagem() { return tipoViagem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Mala getMala() { return mala; }

    public List<Item> getItens() { return itens; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getEmbalagem() { return embalagem; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append("\n");
        sb.append("  Tipo de viagem: ").append(tipoViagem).append("\n");
        sb.append("  Destino: ").append(destino).append("\n");
        sb.append("  Mala: ").append(mala).append("\n");
        sb.append("  Itens: ");
        for (int i = 0; i < itens.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(itens.get(i));
        }
        sb.append("\n");
        sb.append("  Observações: ").append(observacoes).append("\n");
        sb.append("  Embalagem: ").append(embalagem);
        return sb.toString();
    }
}
