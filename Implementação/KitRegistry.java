import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Papel: PROTOTYPE REGISTRY.
 * Guarda os protótipos em um HashMap (identificador -> kit-modelo) e entrega
 * sempre CÓPIAS, nunca a referência ao protótipo armazenado. Assim o original
 * fica protegido contra alterações externas.
 */
public class KitRegistry {
    private final Map<String, KitViagem> prototipos = new HashMap<>();

    /** Registra um protótipo. Guarda uma cópia própria para isolar o registry do chamador. */
    public void registrar(String id, KitViagem prototipo) {
        prototipos.put(id.toLowerCase(), prototipo.clonar());
    }

    /** Retorna uma nova cópia do protótipo identificado por id. */
    public KitViagem obterCopia(String id) {
        KitViagem prototipo = prototipos.get(id.toLowerCase());
        if (prototipo == null) {
            throw new IllegalArgumentException("Protótipo não encontrado: " + id);
        }
        return prototipo.clonar();
    }

    public Set<String> listarIds() {
        return prototipos.keySet();
    }
}
