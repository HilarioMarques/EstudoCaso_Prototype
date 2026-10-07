/**
 * Item do kit (mutável: a quantidade pode ser alterada).
 * Cada Item da lista também precisa ser copiado: copiar só a lista
 * deixaria os mesmos objetos Item compartilhados entre original e cópia.
 */
public class Item {
    private String nome;
    private int quantidade;

    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    /** Construtor de cópia. */
    public Item(Item outro) {
        this.nome = outro.nome;
        this.quantidade = outro.quantidade;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return quantidade > 1 ? nome + " (x" + quantidade + ")" : nome;
    }
}
