 

import java.util.Random;

/**
 *
 * @author Pedro
 */
public abstract class Personagem {

    private String Nome;
    private int HitPoints;
    private int MaxHP;
    private int StrMod;
    private int ArmorClass;
    
    private static Random Dice = new Random();
    
    /**
     *
     * @param nomeChar
     * @param hpChar
     * @param hpMax
     * @param acChar
     * @param forca
     */
    public Personagem(String nomeChar,int hpChar, int hpMax, int acChar,int forca){
        this.Nome = nomeChar;
        this.HitPoints = hpChar;
        this.MaxHP = hpMax;
        this.ArmorClass = acChar;
        this.StrMod = (forca-10)/2;
        
    }
    
    /**
     *
     * @return
     */
    public String pegaNome(){
        return Nome;
    }
    
    /**
     *
     * @return
     */
    public int pegaHp(){
        return HitPoints;
    }
    
    /**
     *
     * @return
     */
    public int pegaHpMax(){
        return MaxHP;
    }
    /**
     *
     * @return
     */
    public boolean Morto() {
	if (HitPoints <= 0)
            return true;
	else
	    return false;
    }
    
    /**
     *
     * @return
     */
    public int pegaAC(){
        return ArmorClass;
    }
    
    /**
     *
     * @return
     */
    public int pegaStr(){
        return StrMod;
    }
    
    /**
     *
     */
    public void incremento(){
        HitPoints++;
    }
     
    /**
     *
     */
    public void decremento(){
        HitPoints--;
    }
    
    /**
     *
     * @param valor
     */
    public void curar(int valor){
        for(int i=0;i<valor;i++)
            if(HitPoints<MaxHP)
                incremento();
    }
        
    /**
     *
     * @param lados
     * @return
     */
    public int dado(int lados){
        return Dice.nextInt(lados)+1;
    }
    
    /**
     *
     */
    public void imprimir(){
        System.out.println("####Resultado da acao####");
        System.out.println("# Nome: "+ Nome);
        System.out.println("# Pontos de vida: " + HitPoints);
        System.out.println("# Armadura: "+ ArmorClass);
        System.out.println("####Resultado da acao####\n");
    }
    
}
