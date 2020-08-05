 


import java.util.HashMap;
/**
 *
 * @author Pedro
 */
public class Vilao extends Personagem{
    private int DamageDice;
    private int Moedas;
    
    /**
     *
     * @param nomeChar
     * @param hpChar
     * @param hpMax
     * @param acChar
     * @param dano
     * @param forca
     * @param moedas
     */
    public Vilao(String nomeChar, int hpChar,int hpMax, int acChar, int dano, int forca,int moedas) {
        super(nomeChar, hpChar,hpMax, acChar, forca);
        this.DamageDice = dano;
        this.Moedas = moedas;
    }
    
    /**
     *
     * @return
     */
    public int pegaDano(){
        return DamageDice;
    }
    
    /**
     *
     * @return
     */
    public int pegaMoedas(){
        return Moedas;
    }
    
}
