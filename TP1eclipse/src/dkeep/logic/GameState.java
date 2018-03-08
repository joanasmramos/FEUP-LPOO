    package dkeep.logic;
    import java.util.HashSet;
    import dkeep.cli.*;

    import java.util.ArrayList;

    public class GameState {
        public Map map;
        public Hero hero = new Hero(1,1, 'H');
        public Guard guard = new Guard(1, 8, 'G');
        public Ogre ogre = new Ogre(5, 1, 'O');
        public HashSet<Ogre> ogres = new HashSet<Ogre>(7);
        private Key key = new Key(1,8,'k');
        public Club club = new Club(8, 2, 'C');

        private int level;

        public enum States { DONE, GAME_OVER, PLAYING, MAP_DONE}

        private States current_state = States.PLAYING;


        private static char map1[][] =  {
                { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
                { 'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
                { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
                { 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
                { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
                { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
                { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
                { 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
                { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X' },
                { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
        };

        private static char map2[][] = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        public GameState() {
            ArrayList<Character> chars = new ArrayList<>();
            ArrayList<Object> objects = new ArrayList<>();
            level = 1;
            map = new Map(map1);
            chars.add(hero);
            chars.add(guard);
            map.setChars(chars);
            map.setObjs(objects);
            guard.setAsleep(false);
            guard.setReverse(false);
        }

        public Hero getHero() {
            return hero;
        }

        public Map getMap(){return map;}

        public void setMap(Map map) {
            this.map = map;
        }

        public States getCurrent_state(){return current_state;}


        public void checkEvents(){
            switch(level){
                case 1:
                    if(hero.checkIfCaught(guard.getLine(), guard.getColumn())) {
                        current_state = States.GAME_OVER;
                        Interaction.promptMsg("GAME OVER");
                    }


                    if(current_state == States.MAP_DONE){ current_state = States.PLAYING; }
                    break;

                case 2:

                    for (Ogre o : ogres) {
                        if (hero.checkIfCaught(o.getLine(), o.getColumn())) {
                            if (hero.HasCub()) {
                                o.setStunned(true);
                                o.setTurns(2);
                            }else current_state = States.GAME_OVER;
                        }else if (hero.checkIfCaught(o.getOgre_club().getLine(), o.getOgre_club().getColumn())) {
                            if (!hero.HasCub()) {
                                current_state = States.GAME_OVER;
                                Interaction.promptMsg("GAME OVER");
                            }
                        }
                    }

                    if(current_state == States.MAP_DONE){ current_state = States.PLAYING; }
                    break;
            }
        }



        public void game(char user_input){
            switch (level){
                case 1:
                    updatePos(user_input);
                    if(current_state == States.MAP_DONE) levelup();
                    Interaction.print(map);
                    break;
                case 2:
                    updatePos(user_input);
                    if(current_state == States.MAP_DONE) levelup();
                    Interaction.print(map);
                    break;

            }
        }


        public boolean checkObstacle(GameElement character, char obstacle, char dir) {
            switch (dir) {
                case 'w':
                    return (map.getMap()[character.getLine()-1][character.getColumn()] == obstacle);
                case 's':
                    return (map.getMap()[character.getLine()+1][character.getColumn()] == obstacle);
                case 'd':
                    return (map.getMap()[character.getLine()][character.getColumn()+1] == obstacle);
                case 'a':
                    return (map.getMap()[character.getLine()][character.getColumn()-1] == obstacle);

                default: return true;
            }
        }

        public boolean checkObstacle(GameElement object, GameElement obstacle, char dir) {
            switch (dir) {
                case 'w':
                    return (object.getLine()-1 == obstacle.getLine() && object.getColumn() == obstacle.getColumn());
                case 's':
                    return (object.getLine()+1 == obstacle.getLine() && object.getColumn() == obstacle.getColumn());
                case 'd':
                    return (object.getLine() == obstacle.getLine() && object.getColumn()+1 == obstacle.getColumn());
                case 'a':
                    return (object.getLine() == obstacle.getLine() && object.getColumn()-1 == obstacle.getColumn());

                default: return true;
            }
        }





        public  void updatePos(char dir) {
            switch (this.level) {
                case 1:
                    if (moveHero(dir) != 1) {
                        guard.moveChar();
                    }
                    break;

                case 2:
                    if (moveHero(dir) != 1) {
                        for (Ogre o : ogres) {
                            moveOgre(o);
                        }
                    }
                    break;
            }

        }

        public int moveHero(char dir) {
            switch (this.level) {
                case 1:
                    if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)){

                        if(checkObstacle(hero, 'S', dir)) current_state = States.MAP_DONE;

                        else {
                            if (checkObstacle(hero, 'K', dir))
                                map.openDoors();
                            else hero.moveChar(dir);
                        }
                    }else  {
                        Interaction.promptMsg("GAME OVER");
                        return 1;
                    }

                    break;

                case 2:
                    if(!checkObstacle(hero, 'I',dir) && !checkObstacle(hero, 'X',dir)) {
                        if (hero.catchClub(club, dir)) {
                            map.remObj(club);
                        }

                        hero.moveChar(dir);

                    }else if (checkObstacle(hero, 'I', dir) && hero.HasKey()) {
                            hero.moveChar(dir);
                            current_state = States.MAP_DONE;

                    } else {
                        Interaction.promptMsg("GAME OVER");
                        return 1;
                    }

                    if(key.getLine() == hero.getLine() && key.getColumn() == hero.getColumn()) {
                        hero.setKey(true);
                        hero.setChar('K');
                        key.visible = false;
                    }

                    break;
            }
            return 0;
        }

        public void moveOgre(Ogre ogre){

            if(ogre.getStunned()) {
                if(ogre.getTurns() != 0) {
                    ogre.setTurns(ogre.getTurns()-1);
                    return;
                }
                else {
                    ogre.setStunned(false);
                    ogre.setTurns(2);
                }
            }

            //move Ogre
            ogre.setOgreDir(ogre.generateDir());

            //generate a direction possible for ogre to move to
            while(checkObstacle(ogre, 'I',ogre.getDir()) || checkObstacle(ogre, 'X',ogre.getDir()) ||
                    checkObstacle(hero, ogre, ogre.getDir()) || checkObstacle(key, ogre, ogre.getDir())){
                ogre.setOgreDir(ogre.generateDir());
            }

            ogre.moveChar(ogre.getDir());


            //move ogre's club
            ogre.getOgre_club().setDir(ogre.generateDir());

            //generate a direction possible for ogre's club to move to
            while(checkObstacle(ogre.getOgre_club(), 'I',ogre.getOgre_club().getDir()) || checkObstacle(ogre.getOgre_club(), 'X',ogre.getOgre_club().getDir())||
                      checkObstacle(hero, ogre, ogre.getDir())){
                ogre.getOgre_club().setDir(ogre.generateDir());
            }

            if(checkObstacle(ogre.getOgre_club(), key, ogre.getOgre_club().getDir()))
                ogre.getOgre_club().isInKeyPos(key.getLine(), key.getColumn());

            ogre.throwClub(ogre.getOgre_club().getDir());
        }


        public void levelup(){
            switch (this.level){
                case 1:
                    level++;
                    map.setMap(map2);
                    hero.setCoordinates(8 ,1);
                    hero.setKey(false);
                    ogres.add(ogre);
                    map.addChar(ogre);
                    int nr = Ogre.generateNr(0, 4);
                    club.setVisible(true);
                    map.addObj(club);
                    map.addObj(key);
                    map.addObj(ogre.getOgre_club());
                    map.remChar(guard);

                    for(int i=0; i<nr; i++) {
                        Ogre anotherOgre = new Ogre(Ogre.generateNr(1, 5),
                                Ogre.generateNr(1, 8), 'O');
                        ogres.add(anotherOgre);
                        map.addChar(anotherOgre);
                        map.addObj(anotherOgre.getOgre_club());
                    }

                    break;
                case 2:
                    current_state = States.DONE;
            }

        }

        public boolean isGameOver(){
            checkEvents();
            return (current_state==States.GAME_OVER);
        }
    }
