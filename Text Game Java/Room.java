 

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;       // stores exits of this room.
    private HashMap<String, Vilao> viloes;
    private HashMap<String, VilaoChefe> chefes;
    private HashMap<String, Equip> equips;
    private HashMap<String, Consumivel> consum;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        viloes = new HashMap<String, Vilao>();
        chefes = new HashMap<String, VilaoChefe>();
        equips = new HashMap<String, Equip>();
        consum = new HashMap<String, Consumivel>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "Voce esta " + description + ".\n"
               + getExitString() + "\n"
               + pegaViloesString() + "\n"
               + pegaChefeString() + "\n"
               + pegaEquipString() + "\n"
               + pegaConsumivelString()+"\n";
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Saidas:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    private String pegaViloesString()
    {
        String returnString = "Inimigos:";
        Set<String> keys = viloes.keySet();
        for(String nome : keys) {
            returnString += " " + nome;
        }
        return returnString;
    }
    
    private String pegaChefeString()
    {
        String returnString = "Chefe:";
        Set<String> keys = chefes.keySet();
        for(String nome : keys) {
            returnString += " " + nome;
        }
        return returnString;
    }

    private String pegaEquipString(){
        String returnString = "Equipamentos:";
        Set<String> keys = equips.keySet();
        for(String nomeItem : keys){
            returnString += " "+nomeItem;
        }
        return returnString;
    }
    
    private String pegaConsumivelString(){
        String returnString = "Consumiveis:";
        Set<String> keys = consum.keySet();
        for(String nomeItem : keys){
            returnString += " "+nomeItem;
        }
        return returnString;
    }
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public Vilao pegaVilao(String nome) {
    	return viloes.get(nome);
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public VilaoChefe pegaChefe(String nome) {
    	return chefes.get(nome);
    }
    
    /**
     *
     * @param vilao
     */
    public void inserirVilao(Vilao vilao) {
    	viloes.put(vilao.pegaNome(), vilao);
    }
    
    /**
     *
     * @param chefe
     */
    public void inserirChefe(VilaoChefe chefe) {
    	chefes.put(chefe.pegaNome(), chefe);
    }
    /**
     *
     * @param nome
     */
    public void removerVilao(String nome){
        viloes.remove(nome);
    }
    
    /**
     *
     * @param nome
     */
    public void removerChefe(String nome){
        chefes.remove(nome);
    }
    
    /**
     *
     * @param equip
     */
    public void inserirEquip(Equip equip){
    	equips.put(equip.pegaNome(), equip);
    }
    
    /**
     *
     * @param nomeEquip
     */
    public void removerEquip(String nomeEquip){
    	equips.remove(nomeEquip);
    }
    
    /**
     *
     * @param nomeEquip
     * @return
     */
    public Equip pegaEquip(String nomeEquip){
        return equips.get(nomeEquip);
    }
    
    /**
     *
     * @param consumivel
     */
    public void inserirConsumivel(Consumivel consumivel){
    	consum.put(consumivel.pegaNome(), consumivel);
    }
    
    /**
     *
     * @param nomeUso
     */
    public void removerConsumivel(String nomeUso){
    	consum.remove(nomeUso);
    }
    
    /**
     *
     * @param nomeUso
     * @return
     */
    public Consumivel pegaConsumivel(String nomeUso){
        return consum.get(nomeUso);
    }
}


