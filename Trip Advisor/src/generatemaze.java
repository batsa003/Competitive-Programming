import java.util.*;

public class generatemaze {
    public static int randInt(int min, int max) { //min inclusive, max exclusive
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min) + min;
        return randomNum;
    }


    // This method generates list of integers with each between is in range [0,up], and sum up to sum.
    // size is the size of the generated list
    public static int[] randomList(int sum, int size, int[] up) {
        ArrayList<Integer> cur = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            if(up[i]!=0){
                cur.add(i);
            }
        }
        int[] vals = new int[size];
        for (int j = 0; j < sum; j++) {
            int ind = randInt(0, cur.size());
            int i = cur.get(ind);
            vals[i]++;
            if (vals[i] == up[i]) {
                cur.remove(ind);
            }
        }
        return vals;
    }


    //Creates a random variable between min and max for the length of the path
    static void updatePath(int min, int max){
        while (true) {
            int t = randInt(min, max);
            int parity = (max - 1) % 2;
            if (t % 2 == parity) {
                path = t;
                break;
            }
        }
    }

    static LinkedList<Pair> generatePath() {

        // Now we have width, height, and valid path length.
        // Now we have to generate path for the maze.
        int stretch= path-(width+height-2);
        int sum = stretch / 2; // sum of stretch
        int Horizontal= randInt(0,2); // Randomness for direction
        LinkedList<Pair> route= new LinkedList<Pair>();

        if(Horizontal==0) {
            if (width % 2 == 1) {
                int x= randInt(0, (height+1)/2);
                int[] up = new int[x+(width/2)];
                for(int i=0; i<x; i++){
                    up[i]= (width-1);
                }
                for(int i=x; i<up.length; i++){
                    up[i]= (height-2*x-1);
                }
                int[] ra= randomList(sum,up.length,up);
                for(int c=0; c<x; c++){
                    for(int j=0; j<=ra[c]; j++){
                        route.add(new Pair(j,2*c));
                    }
                    for(int j=ra[c]; j>=0; j--){
                        route.add(new Pair(j, 2*c+1));
                    }
                }
                for(int j=2*x; j<height; j++){
                    route.add( new Pair(0,j));
                }
                for(int j=1; j<width-1; j+=2){
                    for(int k=(height-1);k>=(height-1-ra[x+(j-1)/2]); k--){
                        route.add(new Pair(j,k));
                    }
                    for(int k=(height-1-ra[x+(j-1)/2]); k<=height-1; k++){
                        route.add(new Pair(j+1,k));
                    }
                }
            }else{
                int x= (height-1)/2;
                int[] up = new int[x+((width-1)/2)];
                for(int i=0; i<x; i++){
                    up[i]= (width-1);
                }
                for(int i=x; i<up.length; i++){
                    up[i]= (height-2*x-1);
                }
                int[] ra= randomList(sum,up.length,up);
                for(int c=0; c<x; c++){
                    for(int j=0; j<=ra[c]; j++){
                        route.add(new Pair(j,2*c));
                    }
                    for(int j=ra[c]; j>=0; j--){
                        route.add(new Pair(j, 2*c+1));
                    }
                }
                for(int j=2*x; j<height; j++){
                    route.add( new Pair(0,j));
                }
                for(int j=1; j<(width-1); j+=2){
                    for(int k=(height-1);k>=(height-1-ra[x+(j-1)/2]); k--){
                        route.add(new Pair(j,k));
                    }
                    for(int k=(height-1-ra[x+(j-1)/2]); k<=height-1; k++){
                        route.add(new Pair(j+1,k));
                    }
                }
            }
        }else{//Vertical same style code as Horizontal. The only dif is width, height, coordinates swapped
            int x= (width-1)/2;
            int[] up = new int[x+((height-1)/2)];
            for(int i=0; i<x; i++){
                up[i]= (height-1);
            }
            for(int i=x; i<up.length; i++){
                up[i]= (width-2*x-1);
            }
            int[] ra= randomList(sum,up.length,up);
            for(int c=0; c<x; c++){
                for(int j=0; j<=ra[c]; j++){
                    route.add(new Pair(2*c,j));
                }
                for(int j=ra[c]; j>=0; j--){
                    route.add(new Pair(2*c+1,j));
                }
            }
            for(int j=2*x; j<width; j++){
                route.add( new Pair(j,0));
            }
            for(int j=1; j<(height-1); j+=2){
                for(int k=(width-1);k>=(width-1-ra[x+(j-1)/2]); k--){
                    route.add(new Pair(k,j));
                }
                for(int k=(width-1-ra[x+(j-1)/2]); k<=(width-1); k++){
                    route.add(new Pair(k,j+1));
                }
            }
        }
        return route;
    }

    static int width, height, path=-1;
    static char[][] field;

    public static void main(String[] args) {
        //Enter the input format as 5 12 >1 or 2 5 or 2 1 13
        /*Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(" ");*/

        String[] line = args;

        //******************* line= args
        height = Integer.parseInt(line[0]);
        width = Integer.parseInt(line[1]);

        int Min = width + height - 2;
        int Max = width * height; //exclusive, max path will be width*height-1;
        if (width % 2 == 0 && height % 2 == 0) { // if both even, we can't have a path includes all cells
            Max--;
        }

        // No Path specified
        if (line.length == 2) {
            updatePath(Min, Max);
        } else {
            if (line[2].charAt(0) == '>') {
                Min = Math.max(Min, Integer.parseInt(line[2].substring(1))+1);
                if (Min >= Max) {
                    System.exit(42);
                }else{
                    updatePath(Min,Max);
                }
            } else if (line[2].charAt(0) == '<') {
                Max=  Math.min(Max, Integer.parseInt(line[2]));
                if(Min>=Max){
                    System.exit(42);
                }else{
                    updatePath(Min,Max);
                }
            } else {
                int p = Integer.parseInt(line[2]);
                if (p >= Min && p< Max ) {
                    int parity = (Max - 1) % 2; // parity of a path
                    if(p %2 == parity){
                        path=p;
                    }else{
                        System.exit(42);
                    }
                } else {
                    System.exit(42);
                }
            }
        }

        if(path!= -1) {
            LinkedList<Pair> route = generatePath();
            if (!(route.getLast().x == width - 1 && route.getLast().y == height - 1)) {
                route.add(new Pair(width - 1, height - 1));
            }

            //Now we have a path. Build the visual.
            field = new char[2 * height + 1][2 * width + 1];
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    char c = ' ';
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            c = '+';
                        } else {
                            c = '-';
                        }
                    } else {
                        if (j % 2 == 0) {
                            c = '|';
                        }
                    }
                    field[i][j] = c;
                }
            }
            field[0][0] = '|';
            field[0][1] = ' ';
            field[2 * height][2 * width] = '|';
            field[2 * height][2 * width - 1] = ' ';

            while (route.size() != 1) {
                Pair p1 = route.remove();
                Pair p2 = route.remove();
                if (p1.y == p2.y) {
                    if (p1.x == p2.x - 1) {
                        right(p1.y, p1.x);
                    } else {
                        right(p2.y, p2.x);
                    }
                } else {
                    if (p1.y == p2.y - 1) {
                        down(p1.y, p1.x);
                    } else {
                        down(p2.y, p2.x);
                    }
                }
                route.addFirst(p2);
            }
            for (int i = 0; i < field.length; i += 2) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == '+') {
                        int[] dx = {1, -1, 0, 0};
                        int[] dy = {0, 0, 1, -1};

                        boolean minus = false;
                        boolean vert = false;
                        for (int k = 0; k < 4; k++) {
                            int x = i + dx[k];
                            int y = j + dy[k];
                            if (0 <= x && x <= 2 * height && 0 <= y && y <= 2 * width) {
                                if (field[x][y] == '|') {
                                    vert = true;
                                } else if (field[x][y] == '-') {
                                    minus = true;
                                }
                            }
                        }
                        if (minus && vert) continue;
                        if (minus) {
                            field[i][j] = '-';
                        } else if (vert) {
                            field[i][j] = '|';
                        } else {
                            field[i][j] = ' ';
                        }
                    }
                }
            }
            for(int i=0; i<field.length; i++){
                for(int j=0; j<field[i].length; j++){
                    System.out.print(field[i][j]);
                }
                System.out.println();
            }
        }else{
            System.exit(42);
        }
    }

    // Clearing the right of the field h,w
    static void right(int h, int w){
        field[2*h+1][2*w+2]=' ';
    }
    //Clearing the down of the field h,w
    static void down(int h, int w){
        field[2*h+2][2*w+1]=' ';
    }


    static class Pair implements Comparable<Pair>{
        int x,y;

        Pair(int x, int y){
            this.x=x;
            this.y=y;
        }


        @Override
        public int compareTo(Pair o) {
            if(x==o.x && y==o.y){
                return 0;
            }else{
                return 1;
            }
        }
    }
}