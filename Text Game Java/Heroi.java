 



import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Pedro
 */
public class Heroi extends Personagem{
    
    private int wallet;
    private final int CapacCarga;
    private Map<String, Equip> mochilaEquip;
    private Map<String, Consumivel> mochilaConsumivel;
    private Equip armadura;
    private Equip arma;
    private Equip amuleto;
    
    /**
     *
     * @param nomeChar
     * @param hpChar
     * @param hpMax
     * @param acChar
     * @param forca
     */
    public Heroi(String nomeChar, int hpChar, int hpMax, int acChar, int forca) {
        super(nomeChar, hpChar, hpMax, acChar, forca);
	mochilaEquip = new HashMap();
        mochilaConsumivel = new HashMap();
        wallet = 0;
        CapacCarga = 100*(this.pegaStr()/2);
        armadura= new Equip("(SemArmadura)","Nao usar armadura",0,0,0,0,1,0);
        arma=new Equip("(SemArma)","Nao usar arma",0,0,0,4,2,0);
        amuleto= new Equip("(SemAmuleto)","Nao usar amuleto",0,0,0,0,3,0);
    }
    
    
    private int pesoWallet(){
        int peso=0;
        peso = wallet/1000;
        if(wallet%1000!=0)
            peso++;
        return peso;
    }
    
    /**
     *
     * @param moedas
     */
    public void adicionarMoedas(int moedas){
        wallet+=moedas;
    }
    
    private int calcularPeso() {
	int pesoTotal = 0;
            for(Equip equip : mochilaEquip.values()) {
		pesoTotal += equip.pegaPeso();
            }
            for(Consumivel consum : mochilaConsumivel.values()) {
		pesoTotal += consum.pegaPeso();
            }
	return pesoTotal;
    }
    
    /**
     *
     * @param equip
     * @return
     */
    public boolean inserirEquip(Equip equip) {
	if (calcularPeso() + equip.pegaPeso() <= CapacCarga) {
		mochilaEquip.put(equip.pegaNome(), equip);
                return true;
	} else {
		System.out.println("\nO " + pegaNome() + " nao pode carregar mais itens na mochila!\n");
                return false;
	}
    }
    
    /**
     *
     * @param consum
     * @return
     */
    public boolean inserirConsumivel(Consumivel consum) {
	if (calcularPeso() + consum.pegaPeso() <= CapacCarga) {
		mochilaConsumivel.put(consum.pegaNome(), consum);
                return true;
	} else {
		System.out.println("\nO " + pegaNome() + " nao pode carregar mais itens na mochila!\n");
                return false;
	}
    }
   
    /**
     *
     * @param nome
     * @return
     */
    public Equip removerEquip(String nome) {
	Equip equip = mochilaEquip.get(nome);
	if (equip != null)
            mochilaEquip.remove(nome);
        return equip;     
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public Consumivel removerConsumivel(String nome) {
	Consumivel consum = mochilaConsumivel.get(nome);
	if (consum != null)
            mochilaConsumivel.remove(nome);
	return consum;
    }
    
    private String pegaEquipMochila(){
        String returnString = "Equipamentos:";
        Set<String> keys = mochilaEquip.keySet();
        for(String nomeItem : keys){
            returnString += " "+nomeItem;
        }
        return returnString;
    }
    
    private String pegaConsumivelMochila(){
        String returnString = "Consumiveis:";
        Set<String> keys = mochilaConsumivel.keySet();
        for(String nomeItem : keys){
            returnString += " "+nomeItem;
        }
        return returnString;
    }
    
    /**
     *
     * @param item
     */
    public void trocaArmadura(Equip item){
        mochilaEquip.put(armadura.pegaNome(), armadura);
        armadura = item;
    }
    
    /**
     *
     * @param item
     */
    public void trocaArma(Equip item){
        mochilaEquip.put(arma.pegaNome(), arma);                 
        arma = item;
    }
    
    /**
     *
     * @param item
     */
    public void trocaAmuleto(Equip item){
        mochilaEquip.put(amuleto.pegaNome(), amuleto);
        amuleto = item;
    }
    
    /**
     *
     */
    public void printJogador(){
        System.out.println("Nome: "+ this.pegaNome());
        System.out.println("Pontos de vida maximos: " + (pegaHpMax() + amuleto.itemHP()) );
        System.out.println("Pontos de vida atuais: " + pegaHp() );
        System.out.println("Armadura: "+ (pegaAC()+armadura.itemAC()));
        System.out.println("Acerto: 1d20 + "+ (pegaStr()+amuleto.itemStr()));
        System.out.println("Dano: 1d"+arma.itemDano()+" + "+ (pegaStr()+amuleto.itemStr()));
        System.out.println("Itens Equipados:\n  Armadura: "+armadura.pegaNome());
        System.out.println("  Arma: "+arma.pegaNome());
        System.out.println("  Amuleto: "+amuleto.pegaNome());
        System.out.println("Mochila:\n " + pegaEquipMochila() + "\n " + pegaConsumivelMochila()+"\n Moedas: "+wallet+"\n");
    }
    
    /**
     *
     * @param oponente
     */
    public void fight(Vilao oponente){
        int i;
        int dano;
        int atkHeroi = dado(20)+pegaStr()+(int)(amuleto.itemStr()/2);
        int atkVilao = oponente.dado(20)+oponente.pegaStr();
        
        if(atkHeroi>oponente.pegaAC()){
            dano = dado(arma.itemDano())+ pegaStr()+(int)(amuleto.itemStr()/2);
            for(i=0;i<dano;i++){
                oponente.decremento();
            }
            System.out.println("O heroi acertou o oponente!\nO ataque foi "+atkHeroi+". Causou "+dano+" de dano");
            
        }else
            System.out.println("O heroi errou o oponente.\nO ataque foi "+atkHeroi);
        if(atkVilao>(pegaAC()+armadura.itemAC())){
            dano = dado(oponente.pegaDano())+ oponente.pegaStr();
            for(i=0;i<dano;i++){
                decremento();
            }   
            System.out.println("O oponente acertou o heroi!\nO ataque foi "+atkVilao+". Causou "+dano+" de dano");
        }else
            System.out.println("O oponente errou o oponente.\nO ataque foi "+atkVilao);
    }
    
}
