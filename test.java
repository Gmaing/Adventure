public class Test {

   /*

      This is a class where you can write your test methods
      and implement them in the main before importing them
      into the Character class.

    */
import java.util.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Character {


   // instance variables
   private String name;
   private int age;
   private String race;
   private int life = 100;
   private static File achievement = new File("win.WAV");
   private static File lose = new File ("lose.WAV");



   public Character() {

      Scanner key = new Scanner(System.in);


      dialogue("What is your character's name?");
      this.name = key.nextLine();

      this.age = ageChooser();
      this.race = raceChooser();
      printCharacter();

   }

   public int worldChooser() {

      Scanner keyboard = new Scanner(System.in);
      dialogue("Let's enter PLTL world...");
      sleeper(2);

      System.out.println("Where would you like to go?");
         System.out.println("For Forest - press 1");
         System.out.println("For Sea - press 2");
         System.out.println("For Desert - press 3");
         System.out.println("For Arctic Tundra - press 4");

      int num = keyboard.nextInt();
         return num;
   }

   //monster attack
   public void monsterAttack() {

      dialogue(this.name + " is fighting a monster.");
      Random rand = new Random();
      int you =damage();
      int mons = damage();
      if (you < mons) {
         this.life -= mons;
         play(lose);
         emote();
      }
      else {
         dialogue("Monster is dead! ");
         play(achievement);
          emote();
          //lootBody();
      }
      isAlive();
   }

   public void world(int n) {
      System.out.println("");
   }

   public void printCharacter() {
      System.out.println("You are " + this.race + "-type who is " + this.age + " years\n old, and " + " named " + name + ".");
      sleeper(2);
   }

   public void setAge(int a) {
      this.age=age;
   }

   public void setName(String n) {
      this.name = n;
   }

   public void setRace(String r) {
      this.race = r;
   }

   public int getAge(int a) {
      return this.age;
   }

   public String getName(String n) {
      return this.name;
   }

   public String getRace(String r) {
      return this.race;
   }

   public int getLife() {
    return this.life;
   }

   public boolean isAlive() {

      boolean alive = true;

      if (this.life < 1) {
         alive = false;
         this.life = 0;
         System.out.println(this.name + " is dead.");
      }
      else {
      alive = true;
         dialogue(this.name + " is still alive with " + this.life
            + "/100 life points left.");
     }


      return alive;
   }


  /*

      Void methods and boolean methods for actions/behavior here.

  */

   // a method where you take random damage.
   public int damage() {
      System.out.println();
      Random rand = new Random();

      int x = rand.nextInt(30);

      this.life -= x;

      //System.out.println("You have lost " + x + " life points.");
      return x;
   }




   //helper method for chosing age
   public int ageChooser() {

      Scanner key = new Scanner(System.in);

      int myAge = 0;
      System.out.println("What is your character's age? (ENTER AN INTEGER VALUE)");

      try {
        myAge = key.nextInt();
      } catch (Exception e) {
        System.out.println("ERROR: You did not exit a valid integer.");
        return ageChooser();
      }

      return myAge;
   }


   // helper method for choosing character type
   public String raceChooser() {
      Scanner key = new Scanner(System.in);

      System.out.println("What type of chatacter are you?");
      System.out.println("Choose a character type:\n\n");
      System.out.println("For 'Human' chose 1");
      System.out.println("For 'Elf' chose 2");
      System.out.println("For 'Kajit' chose 3");
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
                        sleeper(2);
                        break;
         case 2: race = "Elf";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        sleeper(2);
                        break;
         case 3: race = "Kajit";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        sleeper(2);
                        break;
         case 4: race = "Fish Guy";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        sleeper(2);
                        break;
         case 5: race = "Cat Man";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        sleeper(2);
                        break;
         case 6: race = "Crab Person";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        sleeper(2);
                        break;
         case 7: race = "Bird Thing";
                        sleeper(2);
                        System.out.println("\nYou chose: " + race);
                        sleeper(2);
                        break;
         default: System.out.println("Not a valid entry...\n");
                        race = raceChooser();
                        break;
      }
      dialogue("Creating character.........\n");
      sleeper(2);
      return race;
   }

   //audio method
   public static void play(File sound){
      try{
          Clip clip = AudioSystem.getClip();
          clip.open(AudioSystem.getAudioInputStream(sound));
          clip.start();

          Thread.sleep(clip.getMicrosecondLength()/1000);
      }
      catch(Exception e){

      }
   }


   //emote method
   public void emote(){
      System.out.println();
      Random rand = new Random();

      int x = rand.nextInt(5) + 1;

      System.out.println(this.name + ":");

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
            case 4 : dialogue("\"May your road lead you to warm sands.\"");
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
            case 3 : dialogue("\"I will find that red dot\"");
                     dialogue("\"...one day.\"");
                     break;
            case 4 : dialogue("\"Fighter of the Night Man!\"");
                     break;
            case 5 : dialogue("\"There is a great evil ahead.\"");
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


   public static void sleeper(int x) {
      for (int i = 0; i<x * 14; i++) {
         System.out.print("=");
         try {
            Thread.sleep(50); 
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
      }
      System.out.println();
   }


   public static void dialogue(String s) {
      for (int i = 0; i<s.length(); i++) {
         System.out.print(s.charAt(i));
         try {
            Thread.sleep(50);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
      }
      System.out.println();
   }




}

}
