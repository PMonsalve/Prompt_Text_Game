 

/**
 *
 * @author Pedro
 */
public class Consumivel extends Item{

    private int ValorCura;
    
    /**
     *
     * @param nome
     * @param desc
     * @param cura
     * @param peso
     */
    public Consumivel(String nome, String desc,int cura, int peso) {
        super(nome, desc, peso);
        this.ValorCura = cura;
    }
    
    /**
     *
     * @return
     */
    public int pegaCura(){
        return ValorCura;
    }
}
