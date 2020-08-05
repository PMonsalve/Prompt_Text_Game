/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Pedro
 */
public class VilaoChefe extends Vilao{
    private Equip equip;
    private Consumivel consum;
    
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
    public VilaoChefe(String nomeChar, int hpChar, int hpMax, int acChar, int dano, int forca,int moedas) {
        super(nomeChar, hpChar, hpMax, acChar, dano, forca, moedas);
        equip=null;
        consum=null;        
    }
    
    /**
     * @param item
     */
    public void inserirEquip(Equip item){
    	equip=item;
    }
    
    /**
     *
     * @return 
     */
    public Equip removerEquip(){
	if (equip != null)
            return equip;  
        else
            return null;
    }
    
    
    /**
     * @param item
     */
    public void inserirConsumivel(Consumivel item){
    	consum=item;
    }
    
    /**
     *
     * @return 
     */
    public Consumivel removerConsumivel(){
    	if(consum!=null)
            return consum;
        else
            return null;
    }
}
