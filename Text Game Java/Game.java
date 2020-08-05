 

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Heroi heroi;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        heroi = new Heroi("Jon",50,50,10,18);//("Nome", hp atual, hp max, armadura, força)
    }

    /**
     * Criar salas e liga-las, colocar os itens e os inimigos nelas
     */
    private void createRooms()
    {//criação das salas e suas descrições
        Room salaInicial, corredor1, cela1, corredor2, corredor3,cela2,cela3,
                salaEscada, norteDoPatio, nordesteDoPatio, noroesteDoPatio,
                lesteDoPatio, oesteDoPatio, patio, salaoDaArmadura, salaChefeFinal;
        
        salaInicial = new Room("em uma pequena cela. Com a porta de grade encostada");
        corredor1 = new Room("em uma ponta do corredor, com celas na volta, mas uma não da para abrir");
        cela1 = new Room("em uma pequena cela com um esqueleto caído no canto");
        corredor2 = new Room("no meio do corredor, há duas celas, mas não da para abrir");
        corredor3 = new Room("na ponta do corredor, com celas e uma sala com portas abertas");
        cela2 = new Room("em uma pequena cela vazia");
        cela3 = new Room("em uma cela com um esqueleto caído no canto");
        salaEscada = new Room("em uma pequena sala com uma escada que sobe na parede");
        norteDoPatio = new Room("em volta do pátio, na parte norte");
        nordesteDoPatio = new Room("em volta do pátio, na parte nordeste");
        noroesteDoPatio = new Room("em volta do pátio, na parte noroeste");
        lesteDoPatio = new Room("em volta do pátio, na parte leste, com uma pequena caixa de madeira aberta");
        oesteDoPatio = new Room("em volta do pátio, na parte oeste, com um esqueleto jogado no canto");
        patio = new Room("no pátio");
        salaoDaArmadura = new Room("em um grande salão com uma estátua de um poderoso guerreiro no centro");
        salaChefeFinal = new Room("em uma sala grande com um trono no fundo e uma porta atrás dele");
        
        //saidas das salas
        salaInicial.setExit("sul", corredor1);

        corredor1.setExit("norte", salaInicial);
        corredor1.setExit("leste", cela1);
        corredor1.setExit("sul", corredor2);
        
        cela1.setExit("oeste", corredor1);
        
        corredor2.setExit("norte", corredor1);
        corredor2.setExit("sul", corredor3);
        
        corredor3.setExit("norte", corredor2);
        corredor3.setExit("leste",cela2);
        corredor3.setExit("sul", cela3);
        corredor3.setExit("oeste", salaEscada);
        
        cela2.setExit("oeste", corredor3);
        cela3.setExit("norte",corredor3);
        
        salaEscada.setExit("leste", corredor3);
        salaEscada.setExit("sul", norteDoPatio);
        
        norteDoPatio.setExit("norte", salaEscada);
        norteDoPatio.setExit("sul", patio);
        norteDoPatio.setExit("leste", nordesteDoPatio);
        norteDoPatio.setExit("oeste", noroesteDoPatio);
        
        nordesteDoPatio.setExit("oeste", norteDoPatio);
        nordesteDoPatio.setExit("sul", lesteDoPatio);
        
        lesteDoPatio.setExit("norte", nordesteDoPatio);
        lesteDoPatio.setExit("oeste", patio);
        
        noroesteDoPatio.setExit("leste", norteDoPatio);
        noroesteDoPatio.setExit("sul", oesteDoPatio);
        
        oesteDoPatio.setExit("norte", noroesteDoPatio);
        oesteDoPatio.setExit("leste", patio);
        
        patio.setExit("norte", norteDoPatio);
        patio.setExit("oeste", oesteDoPatio);
        patio.setExit("leste", lesteDoPatio);
        patio.setExit("sul", salaoDaArmadura);
        
        salaoDaArmadura.setExit("norte", patio);
        salaoDaArmadura.setExit("sul",salaChefeFinal);
        
        //itens do jogo em suas respectivas salas  ("Nome", hp atual, hp max, armadura, dano, força, moedas)
        Equip amuleto1 = new Equip("Amuleto","Amuleto que oferece um bonus de +4 de Força",4,0,0,0,3,2);
        corredor1.inserirEquip(amuleto1);
        Equip armadura1 = new Equip("Armadura", "Armadura de ferro",0,6,0,0,1,10);
        corredor1.inserirEquip(armadura1);
        Consumivel pocao1 = new Consumivel("Poção","Uma poção que cura 20 pontos de vida",20,2);
        corredor1.inserirConsumivel(pocao1);
        Equip espada1 = new Equip("Espada","Uma espada de ferro",0,0,0,8,2,5);
        cela1.inserirEquip(espada1);
        
        //viloes do jogo em suas respectivas salas ("Nome", hp atual, hp max, armadura, dano, força, moedas)
        Vilao criatura1 = new Vilao("Zumbi",10,10,8,6,14,50);
        corredor1.inserirVilao(criatura1);
        Vilao criatura2 = new Vilao("Zumbi",15,15,12,6,14,50);
        corredor2.inserirVilao(criatura2);
        
        //chefes com seus itens e em suas salas
        VilaoChefe criaturaForte1 = new VilaoChefe("ZumbiForte",30,30,16,6,16,70);
        salaInicial.inserirChefe(criaturaForte1);
        Equip espada2 = new Equip("EspadaGrande","Uma espada grande",0,0,0,10,2,7);
        criaturaForte1.inserirEquip(espada2);

        currentRoom = salaInicial;  // start game inicialRoom
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Fim do jogo. Obrigado por jogar!");
    }

    
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem vindo a Catacombs!");
        System.out.println("Catacombs é um jogo de aventura baseado em World of Zuul e com\nalgumas mecânicas simples do RPG Dungeons & Dragons.");
        System.out.println("Digite '" + CommandWord.HELP + "' se precisar de ajuda.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("Nao entendi o comando...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.LOOK) {
            look();
        }
        else if (commandWord == CommandWord.ATTACK) {
            attack(command);
        }
        else if (commandWord == CommandWord.PICK){
            pickItem(command);  
        }
        else if (commandWord == CommandWord.DROP){
            dropItem(command);
        }
        else if (commandWord == CommandWord.USE){
            useItem(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     *
     */
    private void printHelp() 
    {
        System.out.println("Voce acordou em uma expecie de prisao sem se ");
        System.out.println("lembrar do que aconteceu. Voce esta vagando por la.");
        System.out.println();
        System.out.println("Seus comandos sao:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir onde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Nao ha saidas.");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair o que? Sair é um comando unico.");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private void look() {
        
    	System.out.println(currentRoom.getLongDescription());
        heroi.printJogador();
    }
    
    private void attack(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Atacar quem?");
            return;
        }
        VilaoChefe oponenteChefe = currentRoom.pegaChefe(command.getSecondWord());
        if(oponenteChefe != null){
            heroi.fight(oponenteChefe);
            heroi.imprimir();
            if(oponenteChefe.Morto()){
                System.out.println("Voce matou o "+oponenteChefe.pegaNome()+" que estava na sala");
                if(oponenteChefe.removerEquip()!=null)
                    currentRoom.inserirEquip(oponenteChefe.removerEquip());
                
                if(oponenteChefe.removerConsumivel()!=null)
                    currentRoom.inserirConsumivel(oponenteChefe.removerConsumivel());
                heroi.adicionarMoedas(oponenteChefe.pegaMoedas());
                currentRoom.removerChefe(oponenteChefe.pegaNome());     
            }else
                oponenteChefe.imprimir();
        }
        
    	Vilao oponente = currentRoom.pegaVilao(command.getSecondWord());
    	if(oponente != null) {
            heroi.fight(oponente);
            heroi.imprimir();
            if(oponente.Morto()){
                System.out.println("Voce matou o "+oponente.pegaNome()+" que estava na sala");
                heroi.adicionarMoedas(oponente.pegaMoedas());
                currentRoom.removerVilao(oponente.pegaNome());      
            }else
                oponente.imprimir();
    	}else{
            System.out.println("'" + command.getSecondWord() + "' nao esta nesta sala.");
    	}
    }
    
    
    private void pickItem(Command command){
        if(!command.hasSecondWord()){//caso o comando seja apenas o PICK
            System.out.println("O que voce deseja pegar?");
            return;
        }
        String nomeItem = command.getSecondWord();

        Equip equip = currentRoom.pegaEquip(nomeItem);
        
        if(equip!=null){
            heroi.inserirEquip(equip);
            currentRoom.removerEquip(equip.pegaNome());
            System.out.println("Voce pegou o/a "+equip.pegaNome()+"\n");
        }else{
            Consumivel consum = currentRoom.pegaConsumivel(nomeItem);
            if(consum!=null){
                heroi.inserirConsumivel(consum);
                currentRoom.removerConsumivel(consum.pegaNome());
                System.out.println("Voce pegou o/a "+consum.pegaNome()+"\n");
            }else
                System.out.println("Nao ha "+nomeItem+" nesta sala");
        }
    }
    
    
    private void dropItem(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Que item deseja largar?");  //caso o comando dado seja apenas o DROP
            return;
        }
        
        String nomeItem = command.getSecondWord();

        Equip equip=heroi.removerEquip(nomeItem);
        if(equip!=null){
            currentRoom.inserirEquip(equip);
            System.out.println("Voce largou o/a "+equip.pegaNome()+" na sala\n");
        }else{
            Consumivel consum=heroi.removerConsumivel(nomeItem);
            if(consum!=null){
                currentRoom.inserirConsumivel(consum);
                System.out.println("Voce largou o/a "+consum.pegaNome()+" na sala\n");
            }else
                System.out.println("O item "+nomeItem+" nao esta na sua mochila");
        }        
    }
    
    
    private void useItem(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Que item voce deseja usar?\n"); //caso o comando seja apenas USE
            return;
        }
        
        String nomeItem = command.getSecondWord();
        
        Equip equip = heroi.removerEquip(nomeItem);
        
        if(equip!=null){
            if(equip.itemTipo()==1){
                heroi.trocaArmadura(equip);
                System.out.println("Voce esta usando o/a "+equip.pegaNome()+" em seu peito\n");
            }else{
                if(equip.itemTipo()==2){
                    heroi.trocaArma(equip);
                    System.out.println("Voce esta usando o/a "+equip.pegaNome()+" em suas maos\n");
                }else{
                    heroi.trocaAmuleto(equip);
                    System.out.println("Voce esta usando o/a "+ equip.pegaNome()+" em seu pescoço\n");
                }
            }
        }else{
            Consumivel consum = currentRoom.pegaConsumivel(nomeItem);
            if(consum!=null){
                heroi.curar(consum.pegaCura());
                heroi.removerConsumivel(consum.pegaNome());
                System.out.println("Voce pegou o/a "+consum.pegaNome()+"\n");
            }else               
                System.out.println("O item "+nomeItem+" nao esta na mochila\n");
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
    	Game game = new Game();
    	game.play();
    }
}
