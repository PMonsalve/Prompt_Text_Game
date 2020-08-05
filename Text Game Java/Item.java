 

/**
 *
 * @author Pedro
 */
public abstract class Item {
    private String NomeItem;
    private String DescItem;
    private int PesoItem;
    
    /**
     *
     * @param nome
     * @param desc
     * @param peso
     */
    public Item(String nome, String desc, int peso){
        this.NomeItem = nome;
        this.DescItem = desc;
        this.PesoItem = peso;
    }
    
    /**
     *
     * @return
     */
    public String pegaNome() {
		return NomeItem;
	}
	
    /**
     *
     * @return
     */
    public String pegaDescricao() {
		return DescItem;
	}
	
    /**
     *
     * @return
     */
    public int pegaPeso() {
		return PesoItem;
	}
}
