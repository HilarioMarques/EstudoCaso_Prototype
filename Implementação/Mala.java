/**
 * Objeto interno do kit (mutável). Por ser mutável e referenciado pelo kit,
 * precisa ser copiado em profundidade (deep copy) para que a cópia seja independente.
 */
public class Mala {
    private String tamanho;
    private String cor;

    public Mala(String tamanho, String cor) {
        this.tamanho = tamanho;
        this.cor = cor;
    }

    /** Construtor de cópia: cria uma nova Mala com os mesmos valores. */
    public Mala(Mala outra) {
        this.tamanho = outra.tamanho;
        this.cor = outra.cor;
    }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    @Override
    public String toString() {
        return "Mala " + tamanho + " (" + cor + ")";
    }
}
