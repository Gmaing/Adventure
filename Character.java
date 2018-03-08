/*

   This is the 'QUEST FRAMEWORK' V1.0.0
    - - - - - - - - - - - - - - - - - - -
   "A simple way to traverse a unique world."

   Created by:
       @TommyDato



   ENJOY!


   any...

        ? ?     's?
       ?   ?
           ?
          ?
         ?
         @

 */


import java.util.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Character {


   // instance variables
   private String name;
   private String race;
   private int life;
   private int gold;
   private int potions;
   private int weight;
   private int crimePts = 5;
   private final int totalWeight = 100;
   private File win = new File("win.WAV");
   private File lose = new File ("lose.WAV");


   public Character() {

      Scanner key = new Scanner(System.in);

      dialogue("What is your character's name?");
      this.name = key.nextLine();
      this.race = raceChooser();
      printCoin();
      worldChooser();
      merchant();

   }




   /* THESE METHODS ARE HELPER METHODS FOR THE CONSTRUCTOR

      = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    */

     public static void intro() {

     backgroundStory();
   }

   //background story
   public static void backgroundStory() {

      File f = new File("intro.txt");
        try
        {
            Scanner in = new Scanner(f);

            while (in.hasNext())
            {
                String name = in.nextLine();
                dialogue(name);
            }

            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("Missing file");
        }
   }


   //chose a world...
   public void worldChooser() {

      Scanner keyboard = new Scanner(System.in);
      dialogue("Where would you like to go?");
         dialogue("For Forest - press 1");
         dialogue("For Sea - press 2");
         dialogue("For Desert - press 3");
         dialogue("For Arctic Tundra - press 4");

      int num = keyboard.nextInt();
      switch (num) {

         case 1: dialogue("Nice, the forest sounds great. Let's go there.");
                 job();
                 break;
         case 2: dialogue("The sea is off limits, we have no boats.");
                 worldChooser();
                 break;
         case 3: dialogue("The desert? Really? Ok, let's go...");
                 this.life -= 10;
                 printLife();
                 job();
                 break;
         case 4: dialogue("What a fool! I would say see you later, but I've never seen anyone come back from the tundra.");
                 job();
                 this.life -= 30;
                 printLife();
                 break;
         default: worldChooser();
                  break;
      }
   }


   // helper method for choosing character type
   public String raceChooser() {
      Scanner key = new Scanner(System.in);

      dialogue("What type of character are you, \"" + this.name + "\"");
      System.out.println("Choose a character type:\n");
      System.out.println("For 'Human' chose 1");
      System.out.println("For 'Elf' chose 2");
      System.out.println("For 'Khajit' chose 3");
      System.out.println("For 'Fish Guy' chose 4");
      System.out.println("For 'Cat Man' chose 5");
      System.out.println("For 'Crab Person' chose 6");
      System.out.println("For 'Bird Thing' chose 7");
      String race="";
      int num = key.nextInt();

      switch (num) {
         case 1: race = "Human";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 3;
                        this.potions = 3;
                        this.gold = 100;
                        this.life = 100;
                        sleeper(2);
                        break;
         case 2: race = "Elf";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 3;
                        this.potions = 3;
                        this.gold = 100;
                        this.life = 130;
                        sleeper(2);
                        break;
         case 3: race = "Khajit";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 5;
                        this.potions = 5;
                        this.gold = 100;
                        this.life = 100;
                        sleeper(2);
                        break;
         case 4: race = "Fish Guy";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 2;
                        this.potions = 2;
                        this.gold = 100;
                        this.life = 90;
                        sleeper(2);
                        break;
         case 5: race = "Cat Man";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 9;
                        this.potions = 9;
                        this.gold = 100;
                        this.life = 120;
                        sleeper(2);
                        break;
         case 6: race = "Crab Person";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 1;
                        this.potions = 1;
                        this.gold = 100;
                        this.life = 70;
                        sleeper(2);
                        break;
         case 7: race = "Bird Thing";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        this.weight = 3;
                        this.potions = 3;
                        this.gold = 100;
                        this.life = 101;
                        sleeper(2);
                        break;
         default: System.out.println("Not a valid entry...\n");
                        race = raceChooser();
                        break;
      }
      dialogue("Creating character............\n");
      sleeper(2);
      return race;
   }

   /* THESE METHODS ARE ACTION METHODS CALLED BY THE CHARACTER

    = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    */

    //prints the character's information
   public void printCoin() {
      dialogue("\nYou have " + this.gold + " coin.");
   }


   //walk around, monster might attack, or you might find a local town.
   public void walking() {
      Random rand = new Random();
      Scanner keyboard = new Scanner(System.in);
      String answer = "";
      int r = rand.nextInt(7);

      if (r>3)
         monsterAttack();
      else
         dialogue("You walked towards your destination, and nothing happened.");
         law();
      if (r>3) {
      dialogue("Keep walking or stop at local town? [W/T]");

         answer = keyboard.nextLine();

            if (answer.equalsIgnoreCase("W")) {
               walking();
               job();
               law();
            }
            else {
               law();
               keepWalking();
               enterTown();
           }
      }
   }


   //do some walking
   public void keepWalking() {
      Random rand = new Random();
      int r = rand.nextInt(7);

      if (r>3) {
         monsterAttack();
         law();
      }
      else {
         dialogue("You kept walking towards your destination, and nothing happened.");
         law();
      }
   }

   // enter a town
   public void enterTown() {
      law();
      Random rand = new Random();
      Scanner keyboard = new Scanner(System.in);
      dialogue("As always...");
      merchant();
      if (this.gold > 5) {
         dialogue("This town has a tavern, you enter it, grab a bite to eat, and sleep for the night.");
         this.gold-=5;
         this.life+=25;
      }
      else {
         dialogue("Bartender: \"No coin? You can sleep with the goats!\"");
         this.life+=5;
      }

      dialogue("You are rested and ready to leave.");

      if (rand.nextInt(3) == 2)
         job();

      walking();
   }


   //do random job for coin
   public void job() {
      Random rand = new Random();
      Scanner keyboard = new Scanner(System.in);
      String answer = "";

      int r = rand.nextInt(10);
      dialogue("A Crab person appears, he offers you a job.\nIt's probably a pretty dangerous "
         + "and shady job. Do you accept? [Y/N]");
         answer = keyboard.next();
      if (answer.equalsIgnoreCase("Y")) {
         this.crimePts += 5;
         sleeper(2);
         this.gold += r;
         dialogue("The crab person has awarded you " + r + " gold for doing something super shady.\nI hope it was worth it.");
         printCoin();
      }
      else {
         this.crimePts -= 2;
         dialogue("I guess the Crab person assumed you needed some coin. Nevermind.");
         printCoin();
           }
      law();
   }

   //monster attack
   public void monsterAttack() {

      dialogue(this.name + " is fighting a monster.");
      emote();
      int you = damage();
      int mons = damage();
      if (you < mons) {
         this.life -= mons;
         emote();
         dialogue("You lost, the monster attacked you for " + mons + " damage. You barely make it out alive.");
      }
      else {
         dialogue("Monster is dead!");
          printLife();
          emote();
          loot();
      }
      isAlive();
      law();
   }

   // they dont need this stuff anyways, right?
   public void loot() {
      Random rand = new Random();
      int coins = rand.nextInt(70);
      int potions = rand.nextInt(2);
      Scanner keyboard = new Scanner(System.in);
      String answer = "";
      dialogue("You have the option to loot the body of the thing you just vanquished. Do it? [Y/N]");
         answer = keyboard.next();
         if (answer.equalsIgnoreCase("Y")) {
            this.crimePts += 10;
            dialogue("This is not very honorable, but I guess the monster doesn't need this stuff anyways...");
            dialogue("You have recovered " + coins + " coins and " + potions + " potions from the dead monster.");
            this.gold += coins;
            this.potions += potions;
            printCoin();
            emote();
         }
       law();
   }

   // would you like to buy some wares?
   public void merchant() {
      Scanner keyboard = new Scanner(System.in);
      int pot = 0;
      String answer = "";
      dialogue("A merchant has appeared....\n");
      dialogue("Merchant:\n\"My wares are limited. I have some potions for sale.\"");
      dialogue("Each potion will cost 20 coin. Would you like to purchase any? [Y/N]\"");
      answer = keyboard.next();

      if (answer.equalsIgnoreCase("y")) {
          dialogue("Merchant: \"You have " + this.gold + " coin, so you may purchase up to " + this.gold/20 + " potions.");
          dialogue("How many would you like?\"");
             pot = keyboard.nextInt();
             if (pot <= this.gold/20) {
               this.gold -= (pot * 20);
               this.potions += pot;
               dialogue("Merchant:\n \"Great, you now have " + this.potions + " in your inventory\"");
               printCoin();
             }
             else {
               dialogue("Merchant: \"Sorry you do not have enough gold for that.\"");
               printCoin();
               merchant();
             }
      }
      else {
         dialogue("Merchant:\"Fine, suit yourself...\"");
         printCoin();
      }
      law();
   }

   //take a potion
   public void takePotion() {
      Scanner keyboard = new Scanner(System.in);
      String answer = "";

      dialogue("Would you like to consume a potion? {Y/N]");
      answer = keyboard.next();
      if ((answer.equalsIgnoreCase("y")) && (this.potions > 0)) {
         this.life+= 25;
         this.potions -= 1;
         printInventory();
         printLife();
         takePotion();
      }
      else if (this.potions <1)
         dialogue("You do not have any potions left.");
      else
         dialogue("Hmm... good choice. You seem fine to me.");
      law();
   }

   //Is the character alive?
   public boolean isAlive() {

      Scanner keyboard = new Scanner(System.in);
      boolean alive = true;
      String answer = "";
      if (this.life > 1) {
         dialogue("You appear to be alive");
         printLife();
         takePotion();
      }
      if ((this.life <= 0) && (this.potions >0)) {
         dialogue("You are about to die.");
         dialogue("Would you like to consume a potion to restore life? [Y/N]");
               answer = keyboard.next();
                  if (answer.equalsIgnoreCase("y"));
                      this.potions -= 1;
                      this.life += 25;
                      dialogue("You have added 25 health.");
      }
      if ((this.life <= 0) && (this.potions < 0)) {
         this.life = 0;
         alive = false;
         dialogue(this.name + " is dead.");
         dialogue("Your quest is over....");
         dialogue("Please try again....");
         System.exit(0);
         }

      printLife();
      return alive;
   }

   //prints life point status
   public void printLife() {
      dialogue("You have " + this.life + " life points left");
   }

   // this is a fight method, dont lose
   public boolean fight() {
      Scanner keyboard = new Scanner(System.in);
      String answer = "";
      dialogue("Fight this enemy and redeem your honor? [Y/N]");
      if (answer.equalsIgnoreCase("y")) {
        int me = damage();
        int cops = damage();
        return me > cops;
      }
      return false;
   }

  // in trouble? the law will catch up to you!
   public void law() {
      String answer = "";
      Scanner keyboard = new Scanner(System.in);

      if (this.crimePts >= 10){
         dialogue("You are wanted for crimes! Perhaps you did too many Crab-People jobs\n or looted some innocent monsters.");
         dialogue("You may note have enough coin to pay the law!");
         printCoin();
         dialogue("You may pay a fine of " + this.crimePts*2 + " coin, or you can wait in jail or fight me!\n Pay fine?");
         answer = keyboard.next();
         if ((answer.equalsIgnoreCase("y")) && (this.gold >= this.crimePts*2)) {
            this.gold -= this.crimePts*2;
            this.crimePts = 0;
            dialogue("You may be on your way, but the town watch is keeping their eyes on you!");
         }
         else {
            if(fight())
               dialogue("I have been vanquished.");
            else
               dialogue("To the dungeon with you!");
         }
        }
      }


   //prints inventory
   public void printInventory() {
      int lweight = totalWeight - weight;
      dialogue("Your inventory consists of... ");
      dialogue("Potions: " + this.potions);
      printCoin();
      dialogue("You are carrying " + this.weight + " lbs worth of goods.");
      dialogue("You can carry " + lweight + " more lbs.");
   }


   // a method where you take random damage.
   public int damage() {
      System.out.println();
      Random rand = new Random();

      int x = rand.nextInt(15);
      this.crimePts += 2;
      return x;
   }

   //emote method
   public void emote(){
      System.out.println();
      Random rand = new Random();

      int x = rand.nextInt(5) + 1;

      dialogue(this.name + ":");

      if (this.race.equals("Human")){
         switch (x){
            case 1 : dialogue("\"I have a bad feeling about this.\"");
                     break;
            case 2 : dialogue("\"Everything is better with a flagon of ale.\"");
                     break;
            case 3 : dialogue("\"I took an arrow in the knee!\"");
                     break;
            case 4 : dialogue("\"Fish Guys bring good luck?\"");
                     dialogue("\"...");
                     dialogue("\"Sounds... fishy.\"");
                     break;
            case 5 : dialogue("\"Forward!\"");
                     break;
          }
       }
       else if (this.race.equals("Elf")){
         switch (x){
            case 1 : dialogue("\"Orcs, such fowl creatures.\"");
                     break;
            case 2 : dialogue("\"...\"");
                     break;
            case 3 : dialogue("\"Grant me strength!\"");
                     break;
            case 4 : dialogue("\"Stay awhile and listen!\"");
                     break;
            case 5 : dialogue("\"For Gondor!\"");
                     break;
         }
       }
       else if (this.race.equals("Kajit")){
         switch (x){
            case 1 : dialogue("\"Kajit can get you what you need, for the right price of course.\"");
                     break;
            case 2 : dialogue("\"This one will follow.\"");
                     break;
            case 3 : dialogue("\"Kajit does not discriminate.\"");
                     dialogue("\"If you have coin, Kajit has wares.\"");
                     break;
            case 4 : dialogue("\"May your road lead you to warm sands.");
                     break;
            case 5 : dialogue("\"Kajit stole nothing.\"");
                     dialogue("\"Kajit is innocent of this crime.\"");
                     break;
         }
       }
       else if (this.race.equals("\"Fish Guy")){
         switch (x){
            case 1 : dialogue("*Bloop*");
                     break;
            case 2 : dialogue("\"I don't need your armor...\"");
                     dialogue("\"I have scales!\"");
                     break;
            case 3 : dialogue("\"You killed my father, prepare to die!\"");
                     break;
            case 4 : dialogue("\"Where might one find some water?\"");
                     break;
            case 5 : dialogue("\"Upstream!\"");
                     break;
         }
       }
       else if (this.race.equals("Cat Man")){
         switch (x){
            case 1 : dialogue("*swishes tail*");
                     break;
            case 2 : dialogue("\"Day Man!\"");
                     break;
            case 3 : dialogue("\"I will find that red dot");
                     dialogue("\"...one day.");
                     break;
            case 4 : dialogue("\"Fighter of the Night Man!\"");
                     break;
            case 5 : dialogue("\"There is a great evil ahead.");
                     break;
         }
       }
       else if (this.race.equals("Crab Person")){
         switch (x){
            case 1 : dialogue("\"Mirelurk? What's a mirelurk?\"");
                     break;
            case 2 : dialogue("\"Why always the fighting?\"");
                     break;
            case 3 : dialogue("\"Hooray, I'm useful!\"");
                     break;
            case 4 : dialogue("\"Help! A guinea pig tricked me!\"");
                     break;
            case 5 : dialogue("\"Gumbercules? I love that guy!\"");
                     break;
         }
       }
       else{// last case : Bird Thing
         switch (x){
            case 1 : dialogue("\"The Beacon was activated. Who is in danger?\"");
                     break;
            case 2 : dialogue("\"You appear to be dying. I will make efforts to prevent this, but can promise nothing.\"");
                     break;
            case 3 : dialogue("\"I fear nothing...\"");
                     dialogue("\"Alright, I may be afraid of Cat Men.\"");
                     break;
            case 4 : dialogue("*floofs*");
                     break;
            case 5 : dialogue("*whistles*");
                     break;
       }

      }
   }

   /* THESE METHODS ARE STATIC METHODS FOR AESTHETIC PURPOSES

    = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    */

   //prints out a line to insert between parts of the game text
   // x == ms
   public static void sleeper(int x) {
      for (int i = 0; i<1 * x; i++) {
         System.out.print(".");
         try {
            Thread.sleep(1);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
      }
      System.out.println();
   }

   // prints dialogue very slowly for effect.
   public static void dialogue(String s) {

   }

   //audio method
   public void playWin(){

      try{
          Clip clip = AudioSystem.getClip();
          clip.open(AudioSystem.getAudioInputStream(win));
          clip.start();

          Thread.sleep(clip.getMicrosecondLength()/1000);
      }
      catch(Exception e){

      }
   }

   public void playLose(){

      try{
          Clip clip = AudioSystem.getClip();
          clip.open(AudioSystem.getAudioInputStream(lose));
          clip.start();

          Thread.sleep(clip.getMicrosecondLength()/1000);
      }
      catch(Exception e){

      }
   }
}
