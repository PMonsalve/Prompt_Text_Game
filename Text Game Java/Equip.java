 

/**
 *
 * @author Pedro
 */
public class Equip extends Item{
    
    private int MaisStr;
    private int MaisAC;
    private int MaisHP;
    private int Dano;
    private int Tipo;
    
    /**
     *
     * @param Nome
     * @param Desc
     * @param Str
     * @param AC
     * @param HP
     * @param Dano
     * @param Peso 
     * @param Tipo (1 = armadura, 2 = espada, 3 = amuleto)
     */
    public Equip(String Nome, String Desc,int Str, int AC, int HP, int Dano, int Tipo, int Peso) {
        super(Nome, Desc, Peso);
        this.MaisStr = Str/2;
        this.MaisAC = AC;
        this.MaisHP = HP;
        this.Dano = Dano;
        this.Tipo = Tipo;
    }
    
    /**
     *
     * @return
     */
    public int itemStr(){
            return MaisStr;
        }
        
    /**
     *
     * @return
     */
    public int itemAC(){
            return MaisAC;
        }
        
    /**
     *
     * @return
     */
    public int itemHP(){
            return MaisHP;
        }
        
    /**
     *
     * @return
     */
    public int itemDano(){
            return Dano;
        }
        
    /**
     *
     * @return
     */
    public int itemTipo(){
            return Tipo;
        }
}
